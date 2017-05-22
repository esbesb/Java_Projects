* A program to find a word ladder between 2 words, where each word in the
 * ladder is a word found in a dictionary.
 * 
 * Reads a file with words and their neighbors into Word objects and
 * creates an ArrayList<Word> dictionary.
 */
package SelfTest;
import java.util.*;
import java.io.*;

// CLONE stacks when you want to make a new stack to change

public class WordLadderSolver {
    
	// INSTANCE VARS
	
    ArrayList<Word> dict;  // Word is an inner class
	public static final String DEFAULT_ENCODING = "UTF-8"; //for buffered reader
    
    
	// CONSTRUCTOR
    /**
     * Construct a WordLadderSolver using the dictionary contained in filename
     * @param filename the name of the dictionary file
     */
    public WordLadderSolver(String filename) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        readFile(filename); //wordlistWithNeighbors4.txt for this example. stored in project folder
    }
    
    /**
     * Read in the dictionary from filename. Store in instance variable dict.
     * Each line in the dictionary contains a word followed by the legitimate words that differ to it by only one char
     */
    private void readFile(String filename) throws FileNotFoundException, UnsupportedEncodingException, IOException {

    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), DEFAULT_ENCODING));
    	
        String line ="";
        String[] wordsOnLine;
        String firstWord;
        ArrayList<String> wordNeighbors;
        dict = new ArrayList<Word>(); // initialize dict to empty list. Will be filled with word objects(string, arrayList)
        
        // fill dict with word objects (string firstword, arraylist neighbors)
        try{
        	while ((line = reader.readLine()) != null) { //line initially will be 1st line, onward, until null at end of file        
//test                System.out.println(line);
                wordsOnLine = line.split("\\s+"); //tokenize around whitespace
                firstWord = wordsOnLine[0].toLowerCase(); 
                wordNeighbors = new ArrayList<String>(); //build the 1-char-diff neighbors list
                
                for (int i=1; i < wordsOnLine.length; i++) { 
                    wordNeighbors.add(wordsOnLine[i].toLowerCase());
                }
                dict.add(new Word(firstWord, wordNeighbors)); //create a Word object and add it to the dictionary
        	}
        } finally {
        	reader.close(); //dictionary built!
            Collections.sort(dict); //sort dictionary (natural ordering) for faster lookup with binary search
        }        
    }
    
    /**
     * Find the word ladder between from and to if possible
     * @param from the starting word
     * @param to the resulting word
     * @return an ArrayList<String> containing the words in the ladder, or
     * null if no ladder could be found
     */
    public Stack<String> solve(String from, String to) {
        
    	Stack<String> solution = null;	 // return value
        Word aWord;           			 // to reference a Word in the dictionary
        int topIndex;        			 // index of top word in the dictionary array
        
     // get indexes of from and to in the dictionary using binary search
        int fromIndex = Collections.binarySearch(dict, new Word(from)); 
        int toIndex = Collections.binarySearch(dict, new Word(to)); 	
        
        if (fromIndex < 0 || toIndex < 0){  //if from and to are not both in the dictionary return null. no ladder is possible
        	return null;
        }
        String fromWord = dict.get(fromIndex).word; //start word
        String toWord = dict.get(toIndex).word;		//end goal
        
        LinkedList<Stack<String>> queue = new LinkedList<Stack<String>>(); //a linked list 'queue' of string stacks. we add and remove as we go
        
        //fill the queue with initial stacks (first and second words). 
        //Each stack contains <from> on the bottom and a neighbor word on top.
        
        ArrayList<String> oneCharDiffs = dict.get(fromIndex).neighbors;
        
        Stack<String> aLadder;
        for(int i=0; i<oneCharDiffs.size(); i++){ 	 //go through the neighbor words
        	aLadder = new Stack<String>();
        	aLadder.push(fromWord); 				 //bottom word
        	aLadder.push(oneCharDiffs.get(i));		 //second (top) word
//    		System.out.println(aLadder); 		 	 //see the initial 2 word stacks
        	
        	if (oneCharDiffs.get(i).equals(toWord)){ //if match, just finish here
        		return aLadder;						 //return this stack, end method
        	} else {
        		queue.offer(aLadder); 				 //if this word is not the target, add the stack to the queue. offer adds to tail
//            	System.out.println(queue);			 //TEST see the queue order
        	}	
        }
        
        
        /*
         * Loop until ladder found or the queue is empty (no ladder exists)
         * Make sure no word is contained twice in the same stack.
         * grab first stack, 
         * look at top word, 
         * get index of it in dict
         * retrieve neighbors for word
         * make a new stack for every neighbor, with the neighbor added on top, and offer to tail of linked list queue,
         * repeat
         */        
        Stack<String> aStack; 						// to reference stacks removed from the queue
        String topWord;     						// to reference top word of a stack
        boolean done = false; 						// control for main loop
        
        while (!queue.isEmpty() && !done) {
            aStack = queue.poll(); 					//returns the head stack from the queue, and deletes it from queue
            
            topWord = aStack.peek(); // peek at the top (most recent) word of this stack
            System.out.println(topWord);
            topIndex = Collections.binarySearch(dict, new Word(topWord)); // find the top Word is in the dictionary (use binary search)
            aWord = dict.get(topIndex); //all the neighbors of the top word
            
            /* 
             * Create a new stack for each of top word's neighbors, with the neighbor on top and add to the queue,
             * but first make sure that the neighbor is not already on the stack.
             * We are done if the neighbor is the ending word in the ladder
             */
            Stack<String> stackCopy;  		 // to store a copy of the base stack
            
	        for (String neighbor : aWord.neighbors) {
	        	
	        	if (!aStack.contains(neighbor)) { //only do anything if the stack doesnt have this word
	        		stackCopy = (Stack<String>) aStack.clone();
	        		stackCopy.push(neighbor);
	        		if (neighbor.equals(toWord)){
	        			done = true;
	        			System.out.println("LADDER: " + stackCopy); //TEST
	        			return stackCopy;
	        		}
	        		queue.offer(stackCopy); //new bigger stack to END of queue, for each eligle neighbor
	        	}
	        	
	        	// WEIRD BUG BELOW - INF LOOP?
//	        	stackCopy = (Stack<String>) aStack.clone(); //base of new stack to add to queue
//
//	        	if (stackCopy.search(neighbor) > 0){ //if the stack already contains this neighbor
//	        		break; //broke from for, back to while, for initiated again. infinite?
//	        	} else { //if it doesnt contain this word
//	        		stackCopy.push(neighbor); //add it to top of stack
//	        		
//	        		if (neighbor.equals(toWord)){ 					//if the top of this stack is our target...
//		        		done = true; 		
//		        		System.out.println("LADDER: " + stackCopy); //TEST
//		        		return stackCopy;							//return the ladder!
//		        	}
//	        		queue.offer(stackCopy); //new bigger stack to END of queue, for each eligle neighbor
//	        	}
	        	
	        	
	        }//end for
        }//end while
        System.out.println("no word ladder possible");
        
        return solution;
    }
    
    
    
    /*
     * Inner class to store a word in the dictionary and its neighbors
     */
    private class Word implements Comparable<Word> {
        private String word;
        private ArrayList<String> neighbors;
        
        public Word(String aWord) {
            word = aWord;
            neighbors = null;
        }
        
        public Word(String aWord, ArrayList<String> theNeighbors) {
            word = aWord;
            neighbors = theNeighbors;
        }
        
        public boolean equals(Object otherObj) {
            if (otherObj == null) {
                return false;
            }
            if (otherObj.getClass() != this.getClass()) {
                return false;
            }
            
            Word otherWord = (Word) otherObj;
            return (this.word.equals(otherWord.word));
        }
        
        public int compareTo(Word otherWord) {
            return this.word.compareTo(otherWord.word);
        }
        
        public String toString() {
            String rval = word + " ";
            for (String s : neighbors) {
                rval += s + " ";
            }
            return rval;
        }
    }
    
    
    /*
     * Demo program that takes 2 words of length 4 as arguments
     * and finds the ladder between them.
     * The args come on the command line
     */
    public static void main(String[] args) {  
        
    	try {
    		WordLadderSolver ladderSolver = new WordLadderSolver("wordlistWithNeighbors4.txt");
    		Stack<String> ladder = ladderSolver.solve("ward", "goat");  //ward, word, wood : ward, card, cord
    		System.out.println(ladder);
    		
    	} catch (FileNotFoundException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	} catch (IOException e) { //IO also handles unsupp encoding
    		System.out.println(e.getMessage());
    		System.exit(0);
    	} 
   }
}
