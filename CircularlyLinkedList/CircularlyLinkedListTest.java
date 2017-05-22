package SelfTest;

import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class CircularlyLinkedListTest extends TestCase {

    private CircularlyLinkedList<String> cList;
    
    protected void setUp() {
        cList = new CircularlyLinkedList<String>();
    }
    
    protected void tearDown() {
        cList = null;
    }
    
    /**
     * Test add - 1 element
     */
    public void testAdd1() {
        cList.add("a");
        
        try {
            assertEquals("current element", "a", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }
    
    /**
     * Test add - 2 elements
     */
    public void testAdd2() {
        cList.add("a");
        cList.add("b");
        
        try {
            assertEquals("current element", "b", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }  
    
    /**
     * Test add - 3 elements
     */
    public void testAdd3() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            assertEquals("size", 3, cList.size());
            assertEquals("current element", "c", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }    
        
     /**
     * Test getCurrent - exception
     */
    public void testGetCurrent() {
        try {
            cList.getCurrent();  // try to get from an empty list
            fail();
        }
        catch (CircularlyLinkedListException e) {
            // good
        }
    }   
    
    /**
     * Test toString - 3 elements
     */
    public void testToString() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        assertEquals("toString", "c a b ", cList.toString());
    }    
    
    /**
     * Test advance(int n)
     */
    public void testAdvance1() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            assertEquals("size", 3, cList.size());
            assertEquals("current element", "c", cList.getCurrent());
            
            cList.advance(1);
            assertEquals("advance 1", "a", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }    
        
    /**
     * Test advance(int n)
     */
    public void testAdvance2() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            assertEquals("size", 3, cList.size());
            assertEquals("current element", "c", cList.getCurrent());
            
            cList.advance(2);
            assertEquals("advance 2", "b", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }    
            
    /**
     * Test advance(int n)
     */
    public void testAdvance3() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            assertEquals("size", 3, cList.size());
            assertEquals("current element", "c", cList.getCurrent());
            
            cList.advance(3);
            assertEquals("advance 3", "c", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    } 
    
    /**
     * Test advance(int n)
     */
    public void testAdvance4() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            assertEquals("size", 3, cList.size());
            assertEquals("current element", "c", cList.getCurrent());
            
            cList.advance(8);
            assertEquals("advance 8", "b", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }    
    
     /**
     * Test advance(int n) - exception
     */
    public void testAdvanceEx1() {
        try {
            cList.advance(1);  // try to advance in an empty list
            fail();
        }
        catch (CircularlyLinkedListException e) {
            // good
        }
    }   
    
    /**
     * Test advance(T data)
     */
    public void testAdvance5() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            cList.advance("a");
            assertEquals("advance to a", "a", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }    
    
    /**
     * Test advance(T data)
     */
    public void testAdvance6() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            cList.advance("b");
            assertEquals("advance to b", "b", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }    
    
     /**
     * Test advance(T data) - exception
     */
    public void testAdvanceEx2() {
        try {
            cList.advance("a");  // try to advance in an empty list
            fail();
        }
        catch (CircularlyLinkedListException e) {
            // good
        }
    }   
    
    /**
     * Test remove
     */
    public void testRemove1() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            // should remove "c" and have "a" as current
            assertEquals("remove c", "c", cList.remove());
            assertEquals("remove c", 2, cList.size());
            assertEquals("remove c", "a", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }    
    
    /**
     * Test remove
     */
    public void testRemove2() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            // should remove "c" and have "a" as current
            assertEquals("remove c", "c", cList.remove());
            assertEquals("remove c", 2, cList.size());
            assertEquals("remove c", "a", cList.getCurrent());
            
            // should remove "a" and have "b" as current
            assertEquals("remove a", "a", cList.remove());
            assertEquals("remove a", 1, cList.size());
            assertEquals("remove a", "b", cList.getCurrent());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }    
    
    /**
     * Test remove
     */
    public void testRemove3() {
        cList.add("a");
        cList.add("b");
        cList.add("c");
       
        try {
            // should remove "c" and have "a" as current
            assertEquals("remove c", "c", cList.remove());
            assertEquals("remove c", 2, cList.size());
            assertEquals("remove c", "a", cList.getCurrent());
            
            // should remove "a" and have "b" as current
            assertEquals("remove a", "a", cList.remove());
            assertEquals("remove a", 1, cList.size());
            assertEquals("remove a", "b", cList.getCurrent());
            
            // should remove "b" and size == 0
            assertEquals("remove b", "b", cList.remove());
            assertEquals("remove b", 0, cList.size());
        }
        catch (CircularlyLinkedListException e) {
            fail();
        }
    }  
    
     /**
     * Test remove - exception
     */
    public void testRemoveEx() {
        try {
            cList.remove();  // try to remove from an empty list
            fail();
        }
        catch (CircularlyLinkedListException e) {
            // good
        }
    }   
}
