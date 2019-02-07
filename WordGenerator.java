import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * @author Eddie Rosenblum, Rob Lorch
 * Sources:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
 */

//This class serves as a wrapper for Scanner objects, also containing fields for
//how many words and sentences the Scanner has seen
public class WordGenerator {

  // fields
  private int wordCount;
  private int sentenceCount;
  private Scanner text;

	/**
	 * @param  filename, a String representing a valid filepath
	 * @return a new initialized WordGenerator object 
	 * @throws IOException
	 */
  public WordGenerator(String filename) throws IOException {
    this.wordCount = 0;
    this.sentenceCount = 0;
    this.text = new Scanner(new File(filename));
  }// WordGenerator constructor

	/**
	 * @param none
	 * @return boolean, whether or not the Scanner has more text to read
	 * @throws none
	 */
  public boolean hasNext() {
    return this.text.hasNext();
  }// hasNext

  /**
	 * @param none
	 * @return nextString, the next String read by the Scanner. Also
	 *         increments wordCount and sentenceCount if appropriate
	 * @throws none
	 */
  public String next() {
    String nextString = this.text.next();
    char lastChar = nextString.charAt(nextString.length() - 1);
    if ((lastChar == '.') || (lastChar == '!') || (lastChar == '?')) {
      this.sentenceCount++;
    } // if
    this.wordCount++;
    return nextString;
  }// next

  /**
	 * @param none
	 * @return wordCount, the current wordCount of this
	 * @throws none
	 */
  public int getWordCount() {
    return this.wordCount;
  }// getWordCount

  /**
	 * @param none
	 * @return wordCount, the current sentenceCount of this
	 * @throws none
	 */
  public int getSentenceCount() {
    return this.sentenceCount;
  }// getSentenceCount
  
}// WordGenerator Class
