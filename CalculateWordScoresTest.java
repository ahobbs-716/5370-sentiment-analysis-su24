import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.plaf.SpinnerUI;
import java.rmi.server.ServerNotActiveException;
import java.util.*;

import static org.junit.Assert.*;

public class CalculateWordScoresTest {

    @Test
    public void calculateWordScoresBasicTest() {

        //set up vars
        Set<Sentence> specSet = new HashSet<>();
        specSet.add(new Sentence(2, "I like cake and could eat cake all day ."));
        specSet.add(new Sentence(1, "I hope the dog does not eat my cake ."));

        //call method
        Map<String, Double> wordScores = Analyzer.calculateWordScores(specSet);

        //test
        Iterator<Sentence> it = specSet.iterator();
        while (it.hasNext()) {

            //all necessary words are contained in set
            for (String word : ((it.next()).getWords())) {
                if (!word.equals(".")) {
                    assertTrue(wordScores.containsKey(word.toLowerCase()));
                }
            }

            //the right number of words are contained in set
            assertEquals(14, wordScores.size());

            //the correct values are contained in the set
            assertEquals((Double)1.0, (Double)wordScores.get("dog"));
            assertEquals((Double)1.5, (Double)wordScores.get("eat"));
            assertEquals((Double)1.667, (Double)wordScores.get("cake"), 0.001);

        }
    }

    @Test
    public void calculateWordScoreIsCaseInsensitive() {

        Set<Sentence> caseSensitiveSet = new HashSet<>();
        caseSensitiveSet.add(new Sentence(1, "TEST SENTENCE"));
        caseSensitiveSet.add(new Sentence(1, "With MIXED capS"));

    }

    @Test
    public void calculateWordScoreHandlesInvalidTokens() {

        Set<Sentence> invalidTokenSet = new HashSet<>();
        invalidTokenSet.add(new Sentence(1, "It 's a lot of fun to learn about data structures ."));
        invalidTokenSet.add(new Sentence(1, "she's"));

        //call method
        Map<String, Double> wordScores = Analyzer.calculateWordScores(invalidTokenSet);

        //check ignore any token that does not start with a letter
        assertFalse(wordScores.containsKey("It"));
        assertTrue(wordScores.containsKey("it"));
        assertFalse(wordScores.containsKey("it's"));
        assertFalse(wordScores.containsKey("'s"));
        assertTrue(wordScores.containsKey("she's"));

    }

    @Test
    public void calculateWordScoreHandlesNullInput() {

        assertTrue(Analyzer.calculateWordScores(null).isEmpty());
    }

    @Test
    public void calculateWordScoreHandlesEmptyInput() {

        assertTrue(Analyzer.calculateWordScores(new HashSet<Sentence>()).isEmpty());

    }

    @Test
    public void calculateWordScoreIgnoresSentencesIntOutsideRange() {

        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(3, "sentence one"));   //int outside range
        sentences.add(new Sentence(-3, "sentence two"));  //int outside range
        sentences.add(new Sentence(2, "sentence three"));   //valid
        sentences.add(new Sentence(-1, "sentence four"));   //valid
        sentences.add(new Sentence(0, "sentence five"));       //valid
        sentences.add(new Sentence(2, ""));  //invalid data
        sentences.add(new Sentence(2, "!no 1valid 3data %here")); //invalid data, ignore

        //check that this has correct behaviour
        Map<String, Double> expected = Analyzer.calculateWordScores(sentences);

        assertEquals((2.0 + -1.0 + 0)/3.0, expected.get("sentence"), 0.0);
        assertFalse(expected.containsKey("one"));
        assertFalse(expected.containsKey("two"));
        assertTrue(expected.containsKey("three"));
        assertTrue(expected.containsKey("four"));
        assertTrue(expected.containsKey("five"));
        assertEquals((2.0/1.0), expected.get("three"), 0.0);
        assertEquals(-1.0/1.0, expected.get("four"), 0.0);
        assertEquals(0.0/1.0, expected.get("five"), 0.0);

        assertEquals(4, expected.size());

//        for (String key : expected.keySet()) {
//            System.out.println(key + " " + expected.get(key));
//        }
    }

    @Test
    public void calculateWordScoresHandlesDuplicateWords() {

        //check that this has correct behaviour

        //set up vars
        Set<Sentence> specSet = new HashSet<>();
        specSet.add(new Sentence(2, "I like cake and could eat cake all day ."));
        specSet.add(new Sentence(1, "I hope the cake does not eat my cake ."));

        //call method
        assertEquals((2.0 + 2.0 + 1.0 + 1.0) / 4.0, Analyzer.calculateWordScores(specSet).get("cake"), 0.0);

    }

    @Test
    public void weightedAverageTest() {
        assertEquals(2.0, Analyzer.weightedAverage(4.0, 8.0), 0.0);
    }

    @Test
    public void weightedAverageZeroDenominatorTest() {
        assertEquals(0, Analyzer.weightedAverage(0, 4.0), 0.0);
    }

    @Test
    public void startsWithLetterTrueTest() {
        assertTrue(Analyzer.startsWithLetter("Word"));

    }

    @Test
    public void startsWithLetterEarlyAskiiTest() {
        assertFalse(Analyzer.startsWithLetter("!ord"));

    }

    @Test
    public void startsWithLetterFalseLateAskiiTest() {
        assertFalse(Analyzer.startsWithLetter("|ord"));

    }

    //very negative?
}
