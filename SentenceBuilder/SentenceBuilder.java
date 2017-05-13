/**
 * Course:      Data Structures and Algorithms for Language Processing SS 2017
 * Assignment:  lab 1, exercise 1. 28.04.2017
 * Author:      Timothy Day
 * Description: This program sorts through a Negra export file, and has methods to 
 *  			- draw out sentences as arrayLists (groups of word objects containing the word form and tags), and
 * 				- check if their is another sentence in the file
 *
 * Honor Code:  I pledge that this program represents my own work.
 *		I received help from:
 * 		no one
 *		in designing and debugging my program.
 */
import java.io.*;
import java.util.*; //for array list

public class SentenceBuilder {
	
	//INSTANCE VARS
	private String fileName;
	private BufferedReader inputStream = null;
	private boolean isNextSentenceInFile = false; //used to store if we have found a #BOS
	
	public static final String DEFAULT_ENCODING = "UTF-8"; //for buffered reader
	
	/**
	 * DEFAULT CONSTRUCTOR
	 * will throw exception, because no file name is provided
	 */
	SentenceBuilder() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		this.fileName = "";
		inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), DEFAULT_ENCODING));
	}
	
	
	/**
	 * CONSTRUCTOR
	 * Create a SentenceBuilder for the TuBa export file filename.
	 * @param a string that depicts the path to the file to be read
	 */
	SentenceBuilder(String aFileName) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		this.fileName = aFileName;
		inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), DEFAULT_ENCODING));
	}
	
	
	
	// UTILITIES
	
	/**
	 * NEXT
	 * Return the next sentence in the corpus as an ArrayList of Words.
	 * @return an array list of the sentence
	 */
	public ArrayList<Word> next() throws FileNotFoundException, UnsupportedEncodingException, IOException{
		
		ArrayList<Word> sentenceNow = new ArrayList<Word>(50); //initial size 50, just because
		boolean endSentence = false;
		String line = "";
		while (!endSentence  && ((line = inputStream.readLine()) != null)) { //another line, it doesnt start with #EOS
			
			if (line.startsWith("#EOS")) {
				endSentence = true;
			}
			if (!(line.startsWith("#") || line.startsWith("%%"))){ //not beginning with # or %%
				String[] words = line.split("\\s+");
				Word aWord = new Word(words[0], words[1]); //make the new word
				//System.out.println(aWord.toString()); //TEST
				
				sentenceNow.add(aWord); //add the word to the sentence (arrayList)
			}
			//System.out.println(" NEW SENTENCE:  " + sentenceNow + "\n"); //TEST
		}//end while
		isNextSentenceInFile = false; //hasNext tracer var. we do not know if we have another sentence yet
		return sentenceNow;	
		
	} //end method
	
	
	/**
	 * HAS NEXT
	 * Return true if there is another sentence to process in the file associated with this SentenceBuilder.
	 * @return boolean - true if there is another sentence
	 */
	//this will go through the file to the next #BOS, and stop there. 
	//if called twice in a row, it should not search file the second time
	
	public boolean hasNext() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		//read lines until the next occurrence of #BOS
		//if found, return true. if not (end of file) it is false
		//if not found
		
		if (!isNextSentenceInFile) { //if already true, do not do anything, just return true
			
			String line = "";
			while ((!isNextSentenceInFile && (line = inputStream.readLine()) != null)){ //will stop when a #BOS is found
				//if isNextSentence is already true, then the reader will not run and we stay in the same place
				if (line.startsWith("#BOS")){
					isNextSentenceInFile = true;
				}
			}
		}
		return isNextSentenceInFile;
	}

	
	
// DEMO PROGRAM
	
	public static void main(String[] args) {
		
		try {
			SentenceBuilder test = new SentenceBuilder("tuebadz_1-50-utf8.export");

			int counter = 0;
			while (test.hasNext()) {
				System.out.println(test.next());
				counter++;
				System.out.println("\n SENTENCE COUNT: " + counter + " \n");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
			
		} catch (IOException e){
			System.out.println(e.getMessage());
			System.exit(0);
		} 
		
		
	}// end main demo
	
} //end class

