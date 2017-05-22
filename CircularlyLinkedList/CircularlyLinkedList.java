package SelfTest;



/**
A class to store and manipulate generic circularly linked lists
*/
public class CircularlyLinkedList<T> {
   
	//references to nodes, not data
   private ListNode cur; 
   private ListNode prev;
   private int size; 
   
   /**
    *  Default constructor.  Creates an empty list
    */
   public CircularlyLinkedList() {
       cur = null;
       prev = null;
       size = 0;
   }
   
   /**
    * Get the size of the list.
    * @return the number of elements in this list.
    */
   public int size() {
       return size;
   }
   
   /**
    *  Removes all of the elements from this list.
    */
   public void clear() {
       cur = null;
       prev = null;
       size = 0;
   }
   
   /**
    *  Add an element after current. After this call, current
    *  is the element added.
    *  @param newData the data to add
    */
   
   public void add(T newData) {
	   
	   ListNode newNode = new ListNode(newData);
	   
       if (cur == null) {  // add first element to the list
    	   cur = newNode;
    	   cur.link = cur; //points to itself
    	   prev = cur; //prev data and link are the same node - only 1

       } else {           // add element after current to existing list
    	   
    	   newNode.link = cur.link; //give newNode the link to the first node
    	   prev = cur; //current node is now the previous node
    	   cur = newNode; //newNode is now the current node
    	   prev.link = cur; //prev now points to the new node (which has adopted the pointer to the start node)    	   
       }
       size++; // increment size of the list
   }
   
   /**
    *  Get the data at the current node in this list.
    *  @return data at current.
    *  @throws CircularlyLinkedListException if the list is empty
    */
   public T getCurrent() throws CircularlyLinkedListException {
       if (cur == null) {
    	   throw new CircularlyLinkedListException("empty list");
       }
       return cur.data;
   }
   
   /**
    *  Advance n positions in this list.  After this call, 
    *  current is n positions farther along the list.
    *  @param n the number of positions to advance
    *  @throws CircularlyLinkedListException if the list is empty
    */
   public void advance(int n) throws CircularlyLinkedListException {
       
       if (cur == null) {
    	   throw new CircularlyLinkedListException("no advance, empty list");
    	   
       } else {
    	   for (int i=0; i<n; i++){
    		   //advance one place
    		   prev = cur;
    		   cur = cur.link; //because the link in cur links to the next. so this advanes us
    	   }
       }

   }
   
   /**
    *  Advance to element in this list, testing for equality
    *  using the equals method.  After this call, the node
    *  containing element is current.
    *  @param element the element to advance to
    *  @throws CircularlyLinkedListException if the element is not in the list.
    */
   public void advance(T element) throws CircularlyLinkedListException {
       
       if (size == 0) {
    	   throw new CircularlyLinkedListException();
       } else {
    	   
       }

   }
   
   /**
    *  Remove the current node in this list and return the element
    *  removed.  After this call, current is the element after the
    *  removed element.
    *  @return the deleted element
    *  @throws CircularlyLinkedListException if the list is empty
    */
   public T remove() throws CircularlyLinkedListException {
       
       /****************  To Do  *************/

       // this is a stub - so that we can compile during the test phase
       return null;
   }
   
   /**
    *  Get a string representation of this list, starting at
    *  the current element.
    *  @return a string representation of this list.
    */
   public String toString() {
       
       String rval = "";
       ListNode position = cur;
       
       for (int i=0; i<size; i++) {
           rval += position.data + " ";
           position = position.link;
//           System.out.println("size count is: " + i); //TEST
       }
//       System.out.println("size is: " + size); //TEST
       return rval;
   }
   
   
   
   private class ListNode {
       private T data;
       private ListNode link;
       
       public ListNode() {
           data = null;
           link = null;
       }
       public ListNode(T newData) {
           data = newData;
           link = null;
       }
       public ListNode(T newData, ListNode aLink) {
           data = newData;
           link = aLink;
       }
   }
   
   // TEST DEMO
   public static void main(String[] args) {
	   CircularlyLinkedList<String> list = new CircularlyLinkedList<String>();
	   
	   list.add("a");
	   list.add("b");
	   list.add("c");
	   list.add("d");
	   System.out.println(list.toString());
	   
	   try {
		   list.advance(7);
	   } catch (CircularlyLinkedListException e){
		   
	   }
	   System.out.println(list.toString());
   }
}
