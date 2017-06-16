import junit.framework.TestCase;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class BinaryTreeTest extends TestCase {

    private BinaryTree<String> buildBinaryTree() {

        BinaryTree<String> nn1 = new BinaryTree<String>("NN");
        BinaryTree<String> n1 = new BinaryTree<String>("N", nn1, null);
        BinaryTree<String> d1 = new BinaryTree<String>("D");
        BinaryTree<String> np1 = new BinaryTree<String>("NP", d1, n1);

        BinaryTree<String> nn2 = new BinaryTree<String>("NN");
        BinaryTree<String> n2 = new BinaryTree<String>("N", nn2, null);
        BinaryTree<String> d2 = new BinaryTree<String>("D");
        BinaryTree<String> np2 = new BinaryTree<String>("NP", d2, n2);

        BinaryTree<String> v2 = new BinaryTree<String>("V");
        BinaryTree<String> vp2 = new BinaryTree<String>("VP", v2, np2);

        BinaryTree<String> s = new BinaryTree<String>("S", np1, vp2);

        return s;
    }

    /**
     * Test toString method of the BinaryTree class.
     */
    public void testBinaryTreeToString() {
        BinaryTree<String> tree = buildBinaryTree();

        String expected, actual;
        expected = " [S  [NP  D  [N  NN]]  [VP  V  [NP  D  [N  NN]]]]";
        actual = tree.toString();
        assertEquals(expected, actual);
    }

    /**
     * Test mirror method of the BinaryTree class.
     */
    public void testBinaryTreeMirror() {
        BinaryTree<String> tree = buildBinaryTree();
        tree.mirror();

        String expected, actual;
        expected = " [S  [VP  [NP  [N   NN]  D]  V]  [NP  [N   NN]  D]]";
        actual = tree.toString();
        assertEquals(expected, actual);
    }

    /**
     * Test contains method of the BinaryTree class.
     */
    public void testContains1(){
        BinaryTree<String> tree = buildBinaryTree();
        BinaryTreeNode<String> node = new BinaryTreeNode<String>("VP");

        boolean actual = tree.contains(node.element);
        assertEquals(true,actual);
    }

    /**
     * Test contains method of the BinaryTree class for non-existing data.
     */
    public void testContains2(){
        BinaryTree<String> tree = buildBinaryTree();
        BinaryTreeNode<String> node = new BinaryTreeNode<String>("X");

        boolean actual = tree.contains(node.element);
        assertNotSame(true, actual);
    }

    /**
     * Test contains method of the BinaryTree class for existing term.
     */
    public void testContains3(){
        BinaryTree<String> tree = buildBinaryTree();
        BinaryTreeNode<String> node = new BinaryTreeNode<String>("NN");

        boolean actual = tree.contains(node.element);
        assertEquals(true, actual);
    }

    /**
     * Test contains method of the BinaryTree class for existing term as find term.
     */
    public void testContains4(){
        BinaryTree<String> tree = buildBinaryTree();
        BinaryTreeNode<String> node = new BinaryTreeNode<String>("S");

        boolean actual = tree.contains(node.element);
        assertEquals(true, actual);
    }

    /**
     * Test contains method of the BinaryTree class for empty tree.
     */
    public void testContains5(){
        BinaryTree<String> tree = new BinaryTree<>();
        BinaryTreeNode<String> node = new BinaryTreeNode<String>("S");

        boolean actual = tree.contains(node.element);
        assertEquals(false, actual);
    }

    /**
     * Test find method of the BinaryTree class.
     * Expect a String object to be returned
     */
    public void testFind1(){
        BinaryTree<String> tree = buildBinaryTree();
        BinaryTreeNode<String> node = new BinaryTreeNode<String>("NN");

        String expected = "NN";
        String actual = tree.find(node.element);
        assertEquals(expected, actual);
    }

    /**
     * Test find method of the BinaryTree class.
     * Expect an exception to be thrown
     */
    public void testFind2(){
        BinaryTree<String> tree = buildBinaryTree();

        try {
            BinaryTreeNode<String> node = new BinaryTreeNode<String>("X");
            tree.find(node.element); //should throw exception here
            fail();
        } catch (NoSuchElementException e) {
            //do nothing, pass
        }
    }

    /**
     * Test the pre order iterator of the BinaryTree class, by using it to build a string .
     * Expect a match
     */
    public void testPreOrderIterator(){
        BinaryTree<String> tree = buildBinaryTree();
        Iterator iterate = tree.iteratorPreOrder();

        String expected = "S NP D N NN VP V NP D N NN ";
        String actual = "";
        while (iterate.hasNext()){
            actual += iterate.next() + " ";
        }
        assertEquals(expected, actual);
    }

    /**
     * Test the in order iterator of the BinaryTree class, by using it to build a string .
     * Expect a match
     */
    public void testInOrderIterator(){
        BinaryTree<String> tree = buildBinaryTree();
        Iterator iterate = tree.iteratorInOrder();

        String expected = "D NP NN N S V VP D NP NN N ";
        String actual = "";
        while (iterate.hasNext()){
            actual += iterate.next() + " ";
        }
        assertEquals(expected, actual);
    }

    /**
     * Test the post order iterator of the BinaryTree class, by using it to build a string .
     * Expect a match
     */
    public void testPostOrderIterator(){
        BinaryTree<String> tree = buildBinaryTree();
        Iterator iterate = tree.iteratorPostOrder();

        String expected = "D NN N NP V D NN N NP VP S ";
        String actual = "";
        while (iterate.hasNext()){
            actual += iterate.next() + " ";
        }
        assertEquals(expected, actual);
    }
}
