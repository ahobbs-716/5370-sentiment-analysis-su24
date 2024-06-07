import java.util.*;

/**
 * 
 * Main class for the sentiment analysis program.
 * 
 */
public class Main {
	
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("no input file");
			System.exit(1);
		}

		Map<String, Double> wordScores = new HashMap<>();

		try {
			wordScores = Analyzer.calculateWordScores(Reader.readFile(args[0]));
		} catch (IllegalArgumentException e) {
			System.err.println("bad input file");
			System.exit(2);
		}

		//prompt user to enter a sentence
		Scanner scanner = new Scanner(System.in);
		String sentence = "";

		while (true) {

			System.out.println("\nPlease enter a sentence: ");

			//take in the sentence that the user enters
			if (scanner.hasNext()) {
				sentence = scanner.nextLine();
			}

			//check that it is not the termination command
			if (sentence.equals("quit")) {
				System.exit(0);
			}

			System.out.println("This sentence has a sentiment score of " + Analyzer.calculateSentenceScore(wordScores, sentence) + ".");
		}
	}

}
