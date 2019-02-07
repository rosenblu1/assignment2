import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.AttributedString;

/**
 * @author Eddie Rosenblum, Rob Lorch
 *
 * Sources: 
 * https://www.buildingjavaprograms.com/code-files/4ed/javadoc/DrawingPanel.html
 * https://docs.oracle.com/javase/7/docs/api/java/text/AttributedString.html
 * https://stackoverflow.com/questions/21331221/how-to-change-the-color-of-a-single-character-in-a-string-with-drawstring
 */

//This class is a wrapper for the implementation of our WordGenerator Class. Using 
//a WordGenerator to read a text file, it takes a filepath, width, height, fontsize, 
//and wpm from the terminal and displays text accordingly with a focus letter
//centered in red
public class SpeedReader {

	/**
	 * @param args, an array of commandline Strings
	 * @return void, side effect of graphics window
	 * @throws IOException, Interrupted Exception
	 */
  public static void main(String[] args) throws IOException, InterruptedException {

	  //check for correctness of input
    if (args.length != 5) {
      System.err.println("Error: Incorrect number of arguments");
    } else {

    	//new WordGenerator object
      WordGenerator source = new WordGenerator(args[0]);

      //using commandline input to set graphics paramterers
      DrawingPanel panel = new DrawingPanel(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Graphics g = panel.getGraphics();
      Font f = new Font("Courier", Font.BOLD, Integer.parseInt(args[3]));
      g.setFont(f);
      int wpm = 60000 / Integer.parseInt(args[4]);
            
      while (source.hasNext()) { //loops until end of text file

        //determining place of focus letter based on current word length
    	int len;
    	String next = source.next();
        int strLen = next.length();
        if (strLen <= 1) {
          len = 1;
        } else if (strLen <= 5) {
          len = 2;
        } else if (strLen <= 9) {
          len = 3;
        } else if (strLen <= 13) {
          len = 4;
        } else {
        len = 5;
        }

        //caclulate pixel width of current word up to focus letter to determine placement
        int strWidth = g.getFontMetrics().charsWidth(next.toCharArray(), 0, len);
        
        //create new AttributedString to ease coloring of focus character
        AttributedString atString = new AttributedString(next);
        
        atString.addAttribute(TextAttribute.FOREGROUND, Color.RED, len-1, len);
        atString.addAttribute(TextAttribute.FONT, f);
        
        //draw colored Attributed string 200 pixels before the edge
        g.drawString(atString.getIterator(),200 - strWidth, 100);
        //sleep and clear
        Thread.sleep(wpm);
        panel.clear();
      } // while     
    } // else
  }// main
}// SpeedReader Class

