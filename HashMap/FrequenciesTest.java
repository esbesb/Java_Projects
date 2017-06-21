/*
* this program was provided by Universität Tübingen SS 2017 Data Structures and Algorithms class
* to test the hashing assignment completed in frequencies.java
*/
import java.util.ArrayList;
import junit.framework.TestCase;

public class FrequenciesTest extends TestCase {

    public void testGetCluster() {
        System.out.println("getCluster");
        try {
            Frequencies instance = new Frequencies("KurtTucholsky.txt");
            ArrayList expResult = new ArrayList();

            expResult.add("NICHT");
            expResult.add("IN");

            ArrayList result = instance.getCluster(25);
            assertEquals(expResult, result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testFrequencyOfAWord() {
        System.out.println("frequencyOfAWord");
        try {
            Frequencies instance = new Frequencies("KurtTucholsky.txt");
            int expResult = 20;
            int result = instance.frequencyOfAWord("MIT");
            assertEquals(expResult, result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testNumberOfDifferentWords() {
        System.out.println("numberOfDifferentWords");
        try {
            Frequencies instance = new Frequencies("KurtTucholsky.txt");
            int expResult = 929;
            int result = instance.numberOfDifferentWords();
            assertEquals(expResult, result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testNumberOfWordTokens() {
        System.out.println("numberOfWordTokens");
        try {
            Frequencies instance = new Frequencies("KurtTucholsky.txt");
            int expResult = 2192;
            int result = instance.numberOfWordTokens();
            assertEquals(expResult, result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void testGetMaxOccurrences() {
        System.out.println("getMaxOccurrences");
        try {
            Frequencies instance = new Frequencies("KurtTucholsky.txt");
            int expResult = 75;
            int result = instance.getMaxOccurrences();
            assertEquals(expResult, result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
