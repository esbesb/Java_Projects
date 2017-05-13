/**
 * Course:      Data Structures and Algorithms for Language Processing SS 2017
 * Assignment:  lab 1, exercise 1. 28.04.2017
 * Author:      Timothy Day
 * Description: A simple class that creates a word object to store a word form and the associated descriptor tags that go with it
 * 				
 *
 * Honor Code:  I pledge that this program represents my own work.
 *		I received help from:
 * 		Reka Juhasz
 *		in designing and debugging my program.
 */

/**
 * Class to represent the Word information in a TuBaTreeBank export file
 * @author timday
 *
 */
//we want the word and the tag

public class Word {
	
	// INSTANCE VARS
	private String aForm;
	private String aPOSTag;
	
	// CONSTRUCTORS
	
	/**
	 * 
	 * Default constructor
	 */
	public Word(){
		aForm = "";
		aPOSTag = "";
		// ???? is null a better choice? why empty strings
	}
	
	/**
	 * constructor
	 * @param aForm - the Word form
	 */
	public Word(String aForm) {
		this.aForm = aForm;
		this.aPOSTag = "";
	}
	
	/**
	 * constructor
	 * @param aForm - the Word form
	 * @param aPOSTag - the tag for the Word
	 */
	public Word(String aForm, String aPOSTag) {
		this.aForm = aForm;
		this.aPOSTag = aPOSTag;
	}
	
	// UTILITIES
	
	/**
	 * 
	 * Get the word form.
	 * @return the word form
	 */
	public String getForm() {
		return this.aForm;
	}
	
	/**
	 * 
	 * Get the tag.
	 * @return the POS tag
	 */
	public String getTag(){
		if (this.aPOSTag != "") {
			return this.aPOSTag;
		} else {
			System.out.println("There is no POSTag stored for this word");
			return "";
		}
	}
	
	// UTILITIES
	
	/**
	 * TO STRING
	 * Generate a String representation of the Word. Concatinates form and tag
	 * @return a concatination of the word and tag values.
	 */
	public String toString(){
		return "\nFORM: " + this.aForm + " TAG: " + this.aPOSTag;
	}
	
	/**
	 * EQUALS
	 * Two Words are equal if the forms and pos tag values are equal, ignoring case
	 * @param otherObj - other object to check for equality
	 * @return true if the Words have the same form and tags; false otherwise
	 */
	public boolean equals(Object otherObj){
		boolean isEqual = false;
		
		if (otherObj instanceof Word) {
			String firstForm = this.getForm();
			String secondForm = ((Word) otherObj).getForm(); //typecast from Object to Word
			String firstTag = this.getTag();
			String secondTag = ((Word) otherObj).getTag();
			
			if (firstForm.equalsIgnoreCase(secondForm)) {
				if (firstTag.equalsIgnoreCase(secondTag)){
					isEqual = true;
				}
			}
		}
		return isEqual;
	}
}
