

import java.util.*;

public class Analyzer {
	

	/**
	 * This method calculates the weighted average for each word in all the Sentences.
	 * This method is case-insensitive and all words should be stored in the Map using
	 * only lowercase letters.
	 * 
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average; or an empty Map if the Set of
	 * Sentences is empty or null.
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {

		//set up return vars
		Map<String, Double> wordScores = new HashMap<>();

		//check the input value
		if (sentences == null || sentences.isEmpty()) {
			return wordScores;
		}

		Map<String, double[]> wordTotals = new HashMap<>(wordTotals(sentences));

		//set up the map. For each word...
		for (String word: wordTotals(sentences).keySet()) {
			//change the case of the word
			word = word.toLowerCase();

			//check that the word is valid
			if (startsWithLetter(word)) {

				//if yes, place on the map
				wordScores.put(word, weightedAverage(wordTotals.get(word)[0], wordTotals.get(word)[1]));
			}
		}

		//return the populated map of word scores
		return wordScores;
	}
	
	/**
	 * This method determines the sentiment of the input sentence using the average of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 * 
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence; or 0 if any error occurs
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		if (wordScores == null || wordScores.isEmpty()) {
			return 0;
		}

		if (sentence == null || sentence.isEmpty()) {
			return 0;
		}

		double count = 0;
		double score = 0;

		//for each word in the sentence
		for (String word : sentence.split(" ")) {

			word = word.toLowerCase();

			if (startsWithLetter(word)) {

				//add the relevant score (if any) to the map
				if (wordScores.containsKey(word) && inRange(wordScores.get(word))) {
					score += wordScores.get(word);
				}

				//increase the count (to use in the average)
				count++;

			}
		}

		if (count == 0) {
			return 0;
		}

		//return averaged value
		return score/count;
	}

	public static Map<String, double[]> wordTotals(Set<Sentence> sentences) {

		//set up return value
		Map<String, double[]> wordTotals = new HashMap<>();
		Iterator it = sentences.iterator();

		//for each sentence...
		while (it.hasNext()) {

			Sentence sentence = ((Sentence) it.next());

			//check that the sentence is valid
			if (inRange(sentence.getScore())) {

				//and now process the sentence
				for (String word : sentence.getWords()) {
					word = word.toLowerCase();
					double[] values = new double[2];

					//ensure that the word is in the map
					if (!wordTotals.containsKey(word)) {
						wordTotals.put(word, values);
					}

					values[0] = wordTotals.get(word)[0] + 1;
					values[1] = wordTotals.get(word)[1] + sentence.getScore();
					wordTotals.put(word, values);
				}
			}
		}
		return wordTotals;}
	public static double weightedAverage(double numSentences, double valSentences) {
		if (numSentences == 0) {
			return 0;
		}
		return valSentences/numSentences;
	}

	public static boolean startsWithLetter(String word) {
		if (word.isEmpty()) {
			return false;
		}
		word = word.toLowerCase();
		return word.charAt(0) >= 'a' && word.charAt(0) <= 'z';}

	public static boolean inRange(double score) {
		return (score >= -2 && score <= 2);
	}
}
