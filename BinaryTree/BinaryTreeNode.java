/**
 * A class which represents a node in a binary tree
 */
class BinaryTreeNode<T> {
    T element;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    /**
     Creates a new tree node with the specified data.
     @param - data the data object to add
     */
    public BinaryTreeNode (T dataObj) {
        element = dataObj;
        left = null;
        right = null;
    }

    /**
     * Creates a new tree node with the specified data.
     * @param - data the data object to add
     * @param left the left node
     * @param right the right node
     */
    public BinaryTreeNode (T dataObj, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        element = dataObj;
        this.left = left;
        this.right = right;
    }

    /**
     * Checks whether the node is a leaf node
     * @return true if this node is a leaf
     */
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    /**
     * Returns a String representation of the content of this node
     * @return a String representation of this node
     */
    public String toString() {
        return element.toString();
    }

    /**
     * Test if this node is equal to another node.
     * They are equal if the elements are equal.
     * @return true if the elements are equal
     */
    public boolean equals(Object otherObj) {
        if (otherObj == null)
            return false;
        if (getClass() != otherObj.getClass())
            return false;

        BinaryTreeNode otherNode = (BinaryTreeNode) otherObj;
        return element.equals(otherNode.element);
    }
}
