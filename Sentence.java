import java.util.List;
import java.util.Set;

/**
 * @author Chris Murphy
 *
 * This class represents a single sentence from the input file.
 * 
 */


public class Sentence {
	
	/**
	 * The sentiment score for the sentence. Should be in the range [-2, 2]
	 */
	private int score;
	
	/**
	 * The text contained in the sentence. 
	 */
	private String text;
	
	public Sentence(int score, String text) {
		this.score = score;
		this.text = text;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getText() {
		return text;
	}

	public String[] getWords() {
		return getText().split(" ");
	}

	@Override
	public int hashCode() {
		return (getScore() + getText()).hashCode();
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Sentence)) {
			return false;
		}

		Sentence that = (Sentence) object;

		if (this == object) {
			return true;
		}

		if (this.getScore() == that.getScore() && this.getText().equals(that.getText())) {
			return true;
		}

		return false;
	}

}
