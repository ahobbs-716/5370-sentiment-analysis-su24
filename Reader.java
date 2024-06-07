

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 * @throws IllegalArgumentException if filename is null
	 */
	public static Set<Sentence> readFile(String filename) {

		//set up return value
		Set<Sentence> sentences = new HashSet<>();
		BufferedReader reader = null;

		if (filename == null) {
			throw new IllegalArgumentException();
		}

		//read from file, line by line
		try {

			//set up file reader and recipient var
			reader = new BufferedReader(new FileReader(new File(filename)));
			String line;

			//add each valid line to the set
			while ((line = reader.readLine()) != null) {

				List<String> text = new ArrayList<>(List.of(line.split(" ")));

				if (wellFormatted(line)) {

					int num = Integer.parseInt(text.get(0));
					text.remove(0);

					Sentence sentence = new Sentence(num, text.toString().replace(",", "").replace("[", "").replace("]", ""));
					sentences.add(sentence);
				}
			}

		} catch (IOException e) {
			throw new IllegalArgumentException();
		}

		//return set of sentences
		return sentences;
	}

	public static boolean wellFormatted(String text) {

		int score = 0;

		//check that there are at least 2 parts of the text
		if ((text.split(" ")).length <= 1) {
			return false;
		}

//		//check the original int
		try {
			score = Integer.parseInt(text.split(" ")[0]);
		} catch (NumberFormatException e) {
			return false;
		}

        //check the score is within expected range (nb. also checks type)
		if (!Analyzer.inRange(score)) {
			return false;
		}

		//if here, has passed all tests. Can consider well formatted.
		return true;
	}
}
