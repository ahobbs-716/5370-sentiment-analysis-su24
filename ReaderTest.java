//import org.junit.Test;
//import org.junit.jupiter.api.RepeatedTest;
//import org.junit.jupiter.api.TestTemplate;
//
//import java.util.*;
//
//import static org.junit.Assert.*;
//
//public class ReaderTest {
//
//    @Test
//    public void readFileBasicTest() {
//
//        Sentence expected = new Sentence(2, "I am learning a lot");
//
//        Set<Sentence> actual = Reader.readFile("test.txt");
//
//        assertEquals(1, actual.size());
//
//        Iterator<Sentence> it = actual.iterator();
//
////        while (it.hasNext()) {
////            Sentence sentence = it.next();
////            System.out.println(sentence.getScore() + sentence.getText());
////        }
//
//    }
//
//    @Test
//    public void readFileObjectsEqualsTest() {
//
//        Set<Sentence> expected = new HashSet<>();
//        expected.add(new Sentence(1, "this is a sentence"));
//        expected.add(new Sentence(2, "this is a sentence"));
//        expected.add(new Sentence(1, "This is different"));
//        expected.add(new Sentence(3, "Totally different"));
//
//        Set<Sentence> actual = Reader.readFile("test2.txt");
//
//        Iterator<Sentence> it = actual.iterator();
//
////        while (it.hasNext()) {
////            Sentence sentence = it.next();
////            System.out.println(sentence.getScore() + sentence.getText());
////        }
//
//        assertEquals(4, actual.size());
//
//    }
//
//    @Test
//    public void readFileWorksWithContains() {
//
//        Sentence included = new Sentence(2, "I am learning a lot");
//        assertTrue(Reader.readFile("test.txt").contains(included));
//
//    }
//
//    @Test
//    public void readFileWorksWithDuplicates() {
//
//        Set<Sentence> result = Reader.readFile("test.txt");
//
//        assertEquals(1, result.size());
//
//        result.add(new Sentence(2, "I am learning a lot"));
//
//        assertEquals(1, result.size());
//
//
//    }
//
//    @Test
//    public void readFileIgnoresPoorFormatting() {
//
//        Set<Sentence> expected = new HashSet<>();
//        expected.add(new Sentence(1, "I am correct"));
//
//        Set<Sentence> actual = Reader.readFile("test3.txt");
//
//        assertEquals(1, actual.size());
//
//        Iterator<Sentence> it = actual.iterator();
//
////        while (it.hasNext()) {
////            Sentence sentence = it.next();
////            System.out.println(sentence.getScore() + sentence.getText());
////        }
//
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void readFileHandlesNoFile() {
//
//        Reader.readFile("notAFile");
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void readFileHandlesNullFilename() {
//
//        Reader.readFile(null);
//
//    }
//}


