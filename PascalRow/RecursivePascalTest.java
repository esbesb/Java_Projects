package SelfTest;
import junit.framework.TestCase;

public class RecursivePascalTest extends TestCase {

	/**
     * Test pascal.
     */
    public void testPascal() {        
        try {
            RecursiveHandshake.pascal(-1);
            fail();
        }
        catch (IllegalArgumentException e) {
            // good
        }
        
        int[] result; 
        int[][] expected = {
            {1},
            {1,1},
            {1,2,1},
            {1,3,3,1},
            {1,4,6,4,1},
            {1,5,10,10,5,1},
            {1,6,15,20,15,6,1}
        };

        for (int row=0; row<expected.length; row++) {
            result = RecursiveHandshake.pascal(row);
            
            for (int i=0; i<expected[row].length; i++) {
                assertEquals("row: " + row + " element: " + i,
                             expected[row][i], result[i]);
            }
        }
    }
    
}
