/**
 * Course:      Data Structures and Algorithms for Language Processing SS 2017
 * Assignment:  lab 3, exercise 2. 12.05.2017
 * Author:      Timothy Day
 * Description: This is a JUnit Test Case class for XMLChecker 
 *
 * Honor Code:  I pledge that this program represents my own work.
 *		I received help from:
 * 		no one
 *		in designing and debugging my program.
 */

import junit.framework.TestCase;

public class XMLCheckerTest extends TestCase {

	String test = "";
	XMLChecker checker = new XMLChecker();
	
	/**
     * Test for if first token in String is a tag
     * - no opening <
     * expect false
     */
    public void testBadFirstTag1() {
    	test = "a> <b> banana </a> </b>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for if first token in String is a tag
     * - opening tag is of syntax <tag/> (a single empty tag), when there is more content
     * expect false
     */
    public void testBadFirstTag2() {
    	test = "<a/> <b> banana </a> </b>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test an xml doc that opens with a no-content tag that is not the root tag
     * expect false
     */
    public void testBadFirstTag3() {
    	test = "<empty/><a><b>trout<empty/><c>more content here</c></b></a>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for xml with no white space anywhere
     * expect true
     */
    public void testForNoSpaces() {
    	test = "<a><b>trout<empty/></b></a>";
        assertTrue(checker.wellformedXML(test));
    }
    
    /**
     * Test for xml with white space in content
     * expect true
     */
    public void testWithSpacesInContent() {
    	test = "<a><b> 	 trout <empty/>  </b></a>";
        assertTrue(checker.wellformedXML(test));
    }
    
    /**
     * Test for xml with white space in tags
     * expect false
     */
    public void testWithSpacesInTags() {
    	test = "<a ><b> 	 trout <empty/>  </b></a>";
        assertFalse(checker.wellformedXML(test));
    }
    
	/**
     * Test for when tags are nested incorrectly
     * expect false
     */
    public void testMuddledNesting() {
    	test = "<a> <b> banana </a> </b>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test including single empty content tag
     * expect true
     */
    public void testEmptyContentTag() {
    	test = "<a><b>trout<empty/><c>more content here</c></b></a>";
        assertTrue(checker.wellformedXML(test));
    }
    
    /**
     * Test that all open tags are closed
     * expect false
     */
    public void testTooManyOpeningTags() {
    	test = "<a><b>trout<empty/><c>more content here</c></b>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test a single tag xml doc
     * expect true
     */
    public void testNoContentTagAsRoot() {
    	test = "<a/>";
        assertTrue(checker.wellformedXML(test));
    }
    
    
    /**
     * Test an xml doc that closes with a tag that is not the root tag
     * expect true
     */
    public void testNotRootAtEnd() {
    	test = "<a><b>trout<empty/><c>more content here</c></b></a><empty/>";
        assertFalse(checker.wellformedXML(test));
    }

    /**
     * Test for when a '<' was omitted
     * expect false
     */
    public void testTagTypo1() {
    	test = "<a>b>trout<empty/><c>more content here</c></b></a><empty/>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for when a '>' was omitted
     * expect false
     */
    public void testTagTypo2() {
    	test = "<a><btrout<empty/><c>more content here</c></b></a><empty/>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for when an extra '>' was added
     * expect false
     */
    public void testTagTypo3() {
    	test = "<a>><b>trout<empty/><c>more content here</c></b></a><empty/>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for when an extra '<' was added
     * expect false
     */
    public void testTagTypo4() {
    	test = "<a><<b>trout<empty/><c>more content here</c></b></a><empty/>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for tag with attribute
     * expect true
     */
//    public void testWithAttributes() {
//    	test = "<a><b att=\"attribute\">trout<empty/><c>more content here</c></b></a>";
//        assertTrue(checker.wellformedXML(test));
//    }

    
    /**
     * Test for when two tags are opened and closed sequentially
     * expect false
     */
    public void testMoreNesting() {
    	test = "<a><b>piano</b><c>trumpet</c></a>";
        assertTrue(checker.wellformedXML(test));
    }
    
    /**
     * Test for when two tags are opened and closed sequentially
     * expect false
     */
    public void testNoAngleBrackets() {
    	test = "a";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for when two tags are opened and closed sequentially
     * expect false
     */
    public void testEmptyString() {
    	test = "";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for when two tags are opened and closed sequentially
     * expect false
     */
    public void testInputAfterRootClose() {
    	test = "<a></a>abc";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for when two tags are opened and closed sequentially
     * expect false
     */
    public void testTwoSlashTags() {
    	test = "</a/>";
        assertFalse(checker.wellformedXML(test));
    }
    
    /**
     * Test for when two tags are opened and closed sequentially
     * expect false
     */
    public void testDoubleRoot() {
    	test = "<a></a><a></a>";
        assertFalse(checker.wellformedXML(test));
    }
}

