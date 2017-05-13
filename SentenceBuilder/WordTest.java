/**
 * Course:      Data Structures and Algorithms for Language Processing SS 2017
 * Assignment:  lab 1, exercise 1. 28.04.2017
 * Author:      Timothy Day
 * Description: A junit test class for Word
 *
 * Honor Code:  I pledge that this program represents my own work.
 *		I received help from:
 * 		no one
 *		in designing and debugging my program.
 */

import junit.framework.TestCase;

public class WordTest extends TestCase {
	
	/**
	 * Test equals - test objects with same values, different cases
	 * expect true
	 */
	public void testEquals() {
		Word word1 = new Word("cAt","N");
		Word word2 = new Word("CaT","n");
		assertTrue(word1.equals(word2));
	}
	
	/**
	 * Test equals2 - test objects with different values, all lower case
	 * expect false
	 */
	public void testEquals2() {
		Word word1 = new Word("cat","N");
		Word word2 = new Word("dog","N");
		assertFalse(word1.equals(word2));
	}

}
