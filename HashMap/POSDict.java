/**
 * this class loads a file that contains a list of words and their POS tags
 * and allows us, with the hasPOS method, to check whether a certain word has a certain tag
 */

import java.io.*;
import java.util.*;

public class POSDict {

    /* INSTANCE VARS */

    private HashMap<String, ArrayList<String>> dict = new HashMap(9000); //used linux wc. ~4500, so make map 2x to avoid collisions
    private BufferedReader inputStream = null;
    private int numUniqueWords = 0; //for my interest
    private int numPosTagsAdded = 0;


    /**
     * Constructor
     * loads the given file and constructs a dictionary in the form of a hash map
     * chaining is dealt with with an array list
     * @param filename
     * @throws IOException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public POSDict(String filename) throws IOException {

        inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));

        String line = "";
        while ((line = inputStream.readLine()) != null) {


            String[] fields = line.split("\\s+");

            if (fields.length > 1) { //to avoid empty lines

                String word = fields[0];
                String posTag = fields[1].toLowerCase(); // tags should be case insensitive

                // if it already has the word, chain the postag to the existing array list
                if (dict.containsKey(word)){
                    dict.get(word).add(posTag);
                    numPosTagsAdded++;

                } else {  // put a new entry to hash map
                    ArrayList<String> list = new ArrayList();
                    list.add(posTag);
                    dict.put(word,list);
                    numUniqueWords++;
                    numPosTagsAdded++;
                }
            }
        } //end while

        inputStream.close(); //close the memory leak
    } //end constructor


    /**
     * hasPOS() checks whether a given word has the given POS tag
     * @param aWord - the word to check
     * @param posTag - the pos to check for aWord
     * @return boolean
     */
    public boolean hasPOS(String aWord,String posTag) {
        ArrayList<String> tags = dict.get(aWord);
        return (!dict.containsKey(aWord)) ? false : tags.contains(posTag.toLowerCase());
    }

    /**
     * The number of unique words
     * @return an int that shows how big the map is
     */
    public int numberOfUniqueWords() {
        return dict.size();
    }

    /**
     * Print all words with their postags
     */
    public void printWords() {
        Iterator it = dict.entrySet().iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    } // end printWords


    public static void main(String[] args) {
        try {
            POSDict dict = new POSDict("dict.dat");
            dict.printWords();
            System.out.println(dict.hasPOS("recast","vb"));  //true
            System.out.println(dict.hasPOS("recast","vbd")); //true
            System.out.println(dict.hasPOS("recast","vbn")); //true
            System.out.println(dict.hasPOS("recast","b"));   //false
            System.out.println(dict.hasPOS("reCAst","vb"));  //false
            System.out.println(dict.hasPOS("Recast","vb"));  //false

            System.out.println(dict.numUniqueWords);
            System.out.println(dict.numPosTagsAdded);
            System.out.println("num words with multiple tags " + (dict.numPosTagsAdded - dict.numUniqueWords));
            System.out.println(dict.numberOfUniqueWords());

        } catch (Exception e){
            System.out.println("exceptional problem");
        }
    } // end main
}
