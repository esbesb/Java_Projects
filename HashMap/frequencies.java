/*
* this program was assigned in raw incomplete form by Universität Tübingen Data Structures and Algorithms class SS 2017
* the assignment was completed by Tim Day
*/

import java.io.*;
import java.util.*;

public class Frequencies {

    private HashMap<String, Integer> wf = new HashMap(3000); //added 3000 as estimate
    private HashMap<Integer, ArrayList<String>> fcluster = new HashMap();
    private int maxOccurrences = 0;
    private int numberOfWordTokens = 0;

    private BufferedReader inputStream = null;

    public static void main(String[] args) throws FileNotFoundException {

        try {
            Frequencies frequencies = new Frequencies("KurtTucholsky.txt");
//        frequencies.printFrequencies();
            System.out.println("");
            System.out.println("Highest frequency: " + frequencies.getMaxOccurrences());
            System.out.println("");
            System.out.println("MOST FREQUENT WORDS:");
            frequencies.printMostFrequentWords(35);
        } catch (Exception e){
            System.out.println("exceptional");
        }

    }

    /**
     * Constructor
     *
     * @param filename
     * @throws FileNotFoundException
     */
    public Frequencies(String filename) throws IOException, FileNotFoundException, UnsupportedEncodingException {

        inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));

        String line = "";
        while ((line = inputStream.readLine()) != null) {
            //break line into words around spaces
            line = deleteMarks(line);
            String[] fields = line.split("\\s+");

            for (String word : fields) {
                word = word.toUpperCase();

                //if it already has this key (the word), increment the value by one
                // if it doesnt, add the word, with value 1

                if (word.length() > 0){ //because space delimited punctuation ( ... or :- ) will otherwise count
                    numberOfWordTokens++;
                    if (wf.containsKey(word)){
                        int freq = wf.get(word);
                        freq++;
                        wf.replace(word, freq);

                    } else {
                        wf.put(word, 1); //putIfAbsent extra check, more expensive?
                    }
                }
            }
        } //end wf build, while

        // build the fcluster
        //key: number of appearances as key
        //value: array list of all words with that number of appearance

        Iterator it = wf.entrySet().iterator();
        //entrySet acts on a hashmap and returns a set of the key value pairs
        //iterator is a set method that returns an iterator

        while (it.hasNext()){

            Map.Entry<String, Integer> me = (Map.Entry<String,Integer>) it.next();
            String key = me.getKey();
            Integer value = me.getValue();

            if (value > maxOccurrences){
                maxOccurrences = value; //track freq of most common word
            }
            if (fcluster.containsKey(value)) { //making wf values into fcluster keys. if alreay has this freq as value
                fcluster.get(value).add(key); //add is a method on the arraylist, to add the new word
            } else {
                ArrayList<String> list = new ArrayList();
                list.add(key);
                fcluster.put(value, list);
            }
        }
    } // end constructor

    public String deleteMarks(String word) {
        word = word.replace(".", "");
        word = word.replace(",", "");
        word = word.replace("?", "");
        word = word.replace("!", "");
        word = word.replace("(", "");
        word = word.replace(")", "");
        word = word.replace("»", "");
        word = word.replace("«", "");
        word = word.replace(":", "");
        word = word.replace(";", "");

        return word;
    }

    /**
     * The number of different words
     * @return
     */
    public int numberOfDifferentWords() {
        return wf.size();
    }

    /**
     * The absolute number of all words in the text
     * @return
     */
    public int numberOfWordTokens() {
        return numberOfWordTokens;
    }

    /**
     * Returns the maximum frequency (i.e., the frequency of
     * the most-frequent word in the text)
     */
    public int getMaxOccurrences() {
        return maxOccurrences;
    }

    /**
     * Print all words and their number of appearance
     */
    public void printFrequencies() {

        Iterator it = wf.entrySet().iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    /**
     * Print words (and their frequencies) that appear at least "frequency"
     * times in the text.
     * @param frequency the number of occurrencs that the words should have at least
     */
    public void printMostFrequentWords(int frequency) {

        Iterator it = wf.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String, Integer> me = (Map.Entry<String, Integer>) it.next();
            int value = me.getValue();
            if (value > frequency) {
                System.out.println(me);
            }
        }
    }

    /**
     * Gives back an ArrayList which contains all words with the number of appearance of frequency
     * @param number
     * @return
     */
    public ArrayList<String> getCluster(int number) {
        return fcluster.get(number);
    } 

    /**
     * The number of appearance of word
     * @param word
     * @return
     */
    public int frequencyOfAWord(String word) {
        return wf.get(word);
    }
} // class
