/**
 * Course:      Data Structures and Algorithms for Language Processing SS 2017
 * Assignment:  lab 1, exercise 1. 28.04.2017
 * Author:      Timothy Day
 * Description: A junit test class for SentenceBuilder
 *
 * Honor Code:  I pledge that this program represents my own work.
 *		I received help from:
 * 		no one
 *		in designing and debugging my program.
 */

//Using the file tuebadz_1-50-utf8.export for testing purposes
import junit.framework.TestCase;

public class SentenceBuilderTest extends TestCase {
	
	/**
	 * Test empty constructor
	 * expect an exception
	 */
	public void testConstructorEmpty() {
		try {
			SentenceBuilder jimmy = new SentenceBuilder();
			fail(); //this should never run, as the above line should throw to the exception
			
		} catch (Exception e) {
			//do nothing, test passes
		}
	}
	
	/**
	 * Test next with file: tuebadz_1-50-utf8.export
	 * expect a non-empty arrayList to be returned
	 */
	public void testNext() {
		
		try {
			SentenceBuilder test = new SentenceBuilder("tuebadz_1-50-utf8.export");
			if (test.next() == null){
				fail();
			}
		} catch (Exception e) {
			fail();
		}	
	}
	
	/**
	 * Test hasNext with file: tuebadz_1-50-utf8.export
	 * expect a boolean to be returned, true
	 */
	public void testHasNext() {
		
		try {
			SentenceBuilder test = new SentenceBuilder("tuebadz_1-50-utf8.export");
			assertTrue(test.hasNext());
			
		} catch (Exception e) {
			fail();
		}	
	}
}
