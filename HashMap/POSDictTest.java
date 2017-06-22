/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
import junit.framework.TestCase;

public class POSDictTest extends TestCase {

    /**
     * test hasPOS() (and also the constructor)
     * for the word "recast" and the pos "vb"
     * expect true
     */
    public void testWordRecast1() {
        System.out.println("recast, vb");
        try {
            POSDict dict = new POSDict("dict.dat");
            assertTrue(dict.hasPOS("recast","vb"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * test hasPOS()
     * for the word "recast" and the pos "vbn"
     * expect true
     */
    public void testWordRecast2() {
        System.out.println("recast, vbn");
        try {
            POSDict dict = new POSDict("dict.dat");
            assertTrue(dict.hasPOS("recast","vbn"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * test hasPOS()
     * for the word "recast" and the pos "vbd"
     * expect true
     */
    public void testWordRecast3() {
        System.out.println("recast, vbd");
        try {
            POSDict dict = new POSDict("dict.dat");
            assertTrue(dict.hasPOS("recast","vbd"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * test hasPOS()
     * for the word "recast" and the pos "d"
     * expect false
     */
    public void testWordRecast4() {
        System.out.println("recast, vbd");
        try {
            POSDict dict = new POSDict("dict.dat");
            assertFalse(dict.hasPOS("recast","d"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * test hasPOS() - uppercase pos. posTags SHOULD NOT be case sensitive
     * for the word "recast" and the pos "VB"
     * expect true
     */
    public void testWordRecastUpperCaseArg1() {
        System.out.println("recast, vbd");
        try {
            POSDict dict = new POSDict("dict.dat");
            assertTrue(dict.hasPOS("recast","VB"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * test hasPOS() - uppercase word. words SHOULD be case sensitive
     * for the word "recast" and the pos "VB"
     * expect false
     */
    public void testWordRecastUpperCaseArg2() {
        System.out.println("reCAst, VB");
        try {
            POSDict dict = new POSDict("dict.dat");
            assertFalse(dict.hasPOS("reCAst","VB"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * test hasPOS() for word with single tag
     * for the word "misinterpreted" and the pos "vbd"
     * expect true
     */
    public void testWordMisinterpreted() {
        System.out.println("misinterpreted, VbD");
        try {
            POSDict dict = new POSDict("dict.dat");
            assertTrue(dict.hasPOS("misinterpreted","VbD"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * test hasPOS() for word with single tag, with an incorrect partial tag argument
     * for the word "misinterpreted" and the pos "vb" (it has only tag vbd)
     * expect false
     */
    public void testWordMisinterpreted2() {
        System.out.println("misinterpreted, vb");
        try {
            POSDict dict = new POSDict("dict.dat");
            assertFalse(dict.hasPOS("misinterpreted","vb"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
