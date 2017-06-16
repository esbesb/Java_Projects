import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * A class that contains methods for implementing and manipulating a Binary Tree
 * @param <T> a generic object
 */
public class BinaryTree<T> {

    BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Creates a binary tree with a binary tree node as its root
     * @param aRoot the root of the tree
     */
    BinaryTree(BinaryTreeNode<T> aRoot) {
        root = aRoot;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     * @param element the root of the new tree
     */
    public BinaryTree (T element) {
        root = new BinaryTreeNode<T> (element);
    }

    /**
     * Constructs a binary tree from the two specified binary trees.
     * @param element the root of the new tree
     * @param leftSubtree the left branch
     * @param rightSubtree the right branch
     */
    public BinaryTree (T element, BinaryTree<T> leftSubtree,
                       BinaryTree<T> rightSubtree) {

        root = new BinaryTreeNode<T> (element);

        if (leftSubtree != null) {
            root.left = leftSubtree.root;
        } else {
            root.left = null;
        }

        if (rightSubtree != null) {
            root.right = rightSubtree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Removes the left subtree of this binary tree.
     */
    public void removeLeftSubtree() {
        if (root != null) {
            root.left = null;
        }
    }

    /**
     * Removes the right subtree of this binary tree.
     */
    public void removeRightSubtree() {
        if (root != null) {
            root.right = null;
        }
    }

    /**
     * Deletes all nodes from this binary tree.
     */
    public void removeAllElements() {
        root = null;
    }

    /**
     * Check if the tree is empty.
     * @return true if this binary tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Turn this tree into a mirror image of itself
     */
    public void mirror() {
        mirror(root);
    }

    /**
     * Helper method for mirror().
     * @param node - the node to be mirrored
     */
    private void mirror(BinaryTreeNode<T> node) {

        if (node == null) {
            return;
        }

        // mirror left and right subtrees
        mirror(node.left);
        mirror(node.right);

        // swap left and right subtrees
        BinaryTreeNode<T> tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    /**
     * Check if the tree contains the target element.
     * @param targetElement the element to look for
     * @return true if this tree contains the target element
     */
    public boolean contains (T targetElement) {
        return containsHelper(targetElement, root);
    }

    /**
     * helper method for contains
     */
    private boolean containsHelper(T targetElement, BinaryTreeNode<T> further) {

        if (further == null){  //if null, no match, return false
            return false;
        }

        if (further.element.equals(targetElement)){  //return true if match found
            return true;
        }

        // if no match and has children, go through each subtree recursively
        boolean tmp = containsHelper(targetElement, further.left); //go left subtree

        if (tmp == false) { //and right
            tmp = containsHelper(targetElement, further.right);
        }
        return tmp;
    }

    /**
     * Find and return an element in the tree
     * @param targetElement the element to look for
     * @return a reference to the specified target element if it is
     * found in this binary tree.
     * @throws NoSuchElementException if the target element is not found
     * in the binary tree.
     */
    public T find(T targetElement) throws NoSuchElementException {

        if (!contains(targetElement)) {
            throw new NoSuchElementException("Element Not Found");
        }
        return findHelper(targetElement, root).element;
    }

    private BinaryTreeNode<T> findHelper(T targetElement, BinaryTreeNode<T> further) {

        if (further == null){
            return null;
        }

        if (further.element.equals(targetElement)){
            return further;
        }

        // if no match and has children, go through each subtree recursively
        BinaryTreeNode<T> tmp = findHelper(targetElement, further.left);

        if (tmp == null) {
            tmp = findHelper(targetElement, further.right);
        }
        return tmp; // a node
    }



    /**
     * Generate a string representation of the tree
     * as a bracketed expression
     * @return a bracketed representation of this binary tree.
     */
    public String toString() {
        return substructureToString(root);
    }

    /**
     * Helper method for generating a bracketed representation of a BinaryTreeNode and its child nodes.
     */
    private String substructureToString(BinaryTreeNode<T> top) {
        if (top == null) {
            return "";
        }

        StringBuilder representation = new StringBuilder();

        if (top.isLeaf()) {
            // It's a leaf. Just add the element to the string
            representation.append(" " + top.element);
        }
        else {
            // It's an internal node. Add a left parenthesis,
            // then the children (left then right),
            // then the right parenthesis
            representation.append(
                    " [" + top.element
                            + " " + substructureToString(top.left)
                            + " " + substructureToString(top.right)
                            + "]"
            );
        }
        //removes additional whitespace characters of nodes that have only a single child node
        return representation.toString().replace(" ]", "]");
    }


    /**
     * Creates an iterator which performs an in-order traversal on this binary tree.
     * @return an in-order iterator on this tree.
     */
    public Iterator<T> iteratorInOrder() {
        return new InOrder(root); //instance of class InOrder inherits from Iterator
    }

    /**
     * Creates an iterator which performs a pre-order traversal on this binary tree.
     * @return a pre-order iterator on this tree.
     */
    public Iterator<T> iteratorPreOrder() {
        return new PreOrder(root);
    }

    /**
     * Creates an iterator which performs a post-order traversal on this binary tree.
     * @return a post-order iterator on this tree.
     */
    public Iterator<T> iteratorPostOrder() {
        return new PostOrder(root);
    }


    /*
     * An inner class for the PreOrder binary tree traversal iterator
     * builds a linkedList to act as a queue, then moves through it
     */
    private class PreOrder implements Iterator {
        LinkedList<BinaryTreeNode> queue; //store the objects in correct sequence for this traversal

        //constructor, takes the root node as arg
        public PreOrder(BinaryTreeNode a){
            queue = new LinkedList<BinaryTreeNode>();
            buildStack(root);
        }

        // helper method, to traverse the tree and build the appropriately ordered queue (linkedlist)
        private void buildStack(BinaryTreeNode<T> focusNode){ //root left right
            if (focusNode != null) {
                queue.add(focusNode);
                buildStack(focusNode.left);
                buildStack(focusNode.right);
            }
        }
        // move through the queue
        public Object next(){
            Object el = queue.poll().element;
            return el;
        }
        // check if the queue has another item
        public boolean hasNext(){
            return !queue.isEmpty();
        }
    } //end preorder class


    /*
     * An inner class for the In-Order binary tree traversal iterator
     * builds a linkedList to act as a queue, then moves through it
     */
    private class InOrder implements Iterator {
        LinkedList<BinaryTreeNode> queue; //store the objects in correct sequence for this traversal

        //constructor, takes the root node as arg
        public InOrder(BinaryTreeNode a){
            queue = new LinkedList<BinaryTreeNode>();
            buildQueueIn(root);
        }
        // helper method, to traverse the tree and build the appropriately ordered queue (linkedlist)
        private void buildQueueIn(BinaryTreeNode<T> focusNode){ //root left right
            if (focusNode != null) {
//                System.out.println(focusNode); //test
                buildQueueIn(focusNode.left);
                queue.add(focusNode);
                buildQueueIn(focusNode.right);
            }
        }
        // move through the queue
        public Object next(){
            Object el = queue.poll().element;
            return el;
        }
        // check if the queue has another item
        public boolean hasNext(){
            return !queue.isEmpty();
        }
    } //end inorder class


    /*
     * An inner class for the In-Order binary tree traversal iterator
     * builds a linkedList to act as a queue, then moves through it
     */
    private class PostOrder implements Iterator {
        LinkedList<BinaryTreeNode> queue; //store the objects in correct sequence for this traversal

        // constructor. takes root as arg and traverses
        public PostOrder(BinaryTreeNode a){
            queue = new LinkedList<BinaryTreeNode>();
            buildQueuePost(root);
        }
        // helper method, to traverse the tree and build the appropriately ordered queue (linkedlist)
        private void buildQueuePost(BinaryTreeNode<T> focusNode){ //root left right
            if (focusNode != null) {
                buildQueuePost(focusNode.left);
                buildQueuePost(focusNode.right);
                queue.add(focusNode);
            }
        }
        // move through the queue
        public Object next(){
            Object el = queue.poll().element;
            return el;
        }
        // check if the queue has another item
        public boolean hasNext(){
            return !queue.isEmpty();
        }
    } //end PostOrder class
}
