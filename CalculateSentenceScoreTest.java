import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CalculateSentenceScoreTest {

    @Test
    public void calculateSentenceScoreBasicTest() {

        Map<String, Double> dogWords = new HashMap<>();
        dogWords.put("dogs", 1.0);
        dogWords.put("are", 2.0);
        dogWords.put("cute", 2.0);

        String dogs1 = "dogs are cute";

        assertEquals((1.0+2.0+2.0)/3.0,Analyzer.calculateSentenceScore(dogWords, dogs1), 0.0);

    }

    @Test
    public void calculateSentenceScoreFaultyWordTest() {

        Map<String, Double> dogWords = new HashMap<>();
        dogWords.put("dogs", 1.0);
        dogWords.put("are", 2.0);
        dogWords.put("cute", 3.0);

        String dogs2 = "dogs are ?smart";

        assertEquals((1.0+2.0)/2.0, Analyzer.calculateSentenceScore(dogWords, dogs2), 0);

    }

    @Test
    public void calculateSentenceScoreMissingWordTest() {

        Map<String, Double> dogWords = new HashMap<>();
        dogWords.put("dogs", 1.0);
        dogWords.put("are", 2.0);

        String dogs3 = "dogs are funny";

        assertEquals((1.0 + 2.0)/3.0, Analyzer.calculateSentenceScore(dogWords, dogs3), 0.0);

    }

    @Test
    public void calculateSentenceScoreDoubleDuplicateTest() {

        Map<String, Double> dogWords = new HashMap<>();
        dogWords.put("dogs", 2.0);
        dogWords.put("are", 1.0);
        dogWords.put("cute", 1.0);

        String dogs1 = "dogs are dogs";
        String dogs2 = "dogs are cute";

        assertEquals((2.0+1.0+2.0)/3.0, Analyzer.calculateSentenceScore(dogWords, dogs1), 0.0);

    }

    @Test
    public void calculateSentenceScoreHandlesMixedCapsCases() {

        Map<String, Double> dogWords = new HashMap<>();
        dogWords.put("dogs", 2.0);
        dogWords.put("are", 1.0);
        dogWords.put("cute", 1.0);

        String dogs2 = "dogs are Cute";

        assertEquals((2.0+1.0+1.0)/3.0, Analyzer.calculateSentenceScore(dogWords, dogs2), 0.0);

    }

    @Test
    public void calculateSentenceScoreHandlesAllCapsCases() {

        Map<String, Double> dogWords = new HashMap<>();
        dogWords.put("dogs", 2.0);
        dogWords.put("are", 1.0);
        dogWords.put("cute", 1.0);

        String dogs1 = "dogs ARE dogs";

        assertEquals((2.0+1.0+2.0)/3.0, Analyzer.calculateSentenceScore(dogWords, dogs1), 0.0);

    }

    @Test
    public void calculateSentenceScoreMissingOrInvalidMapTest() {

        Map<String, Double> dogWords = new HashMap<>();
        dogWords.put("dogs", 2.0);
        dogWords.put("too", 5.0);           //should be ignored (out of range)
        dogWords.put("true", -3.0);          //should be ignored (out of range)
        dogWords.put("are", 1.0);
        dogWords.put("cute", 1.0);
        dogWords.put("!ute", 1.0);          //should be ignored (invalid input)

        String dogs1 = "dogs ARE dogs too true ! !yes";
        //check that this has correct behaviour
        assertEquals((2.0 + 1.0 + 2.0 + 0.0 + 0.0)/5.0, Analyzer.calculateSentenceScore(dogWords, dogs1),0.0);


    }

    @Test
    public void calculateSentenceScoreMissingOrInvalidSentenceTest() {
        Map<String, Double> dogWords = new HashMap<>();
        dogWords.put("dogs", 2.0);
        dogWords.put("are", 1.0);

        String dogs1 = "!dogsAre 9dogs";    //invalid sentence

        assertEquals(0, Analyzer.calculateSentenceScore(dogWords, dogs1), 0.0);
    }

    @Test
    public void calculateSentenceScoreNullMap() {

        assertEquals(0, Analyzer.calculateSentenceScore(null, "test"), 0.0);

    }

    @Test
    public void calculateSentenceScoreEmptyMap() {

        Map<String, Double> map = new HashMap<>();

        assertEquals(0, Analyzer.calculateSentenceScore(map, "test"), 0.0);
    }

    @Test
    public void calculateSentenceScoreEmptySentence() {

        Map<String, Double> map = new HashMap<>();
        map.put("test", 2.0);
        String sentence = "";

        assertEquals(0, Analyzer.calculateSentenceScore(map, sentence), 0.0);

    }

    @Test
    public void calculateSentenceScoreNullSentence() {

        Map<String, Double> map = new HashMap<>();
        map.put("test", 2.0);

        assertEquals(0, Analyzer.calculateSentenceScore(map, null), 0.0);
    }

    @Test
    public void calculateSentenceScoresNullDenominator() {

        Map<String, Double> map = new HashMap<>();
        map.put("test", 2.0);
        String sentence = "!none 0f 5hese 2ords !exist 0in 6the 2map";

        assertEquals(0, Analyzer.calculateSentenceScore(map, sentence), 0.0);

    }

    @Test
    public void startsWithLetter() {

        assertFalse(Analyzer.startsWithLetter(""));
    }





}
