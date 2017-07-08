import opennlp.tools.lemmatizer.Lemmatizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import opennlp.tools.lemmatizer.LemmatizerModel;

import java.io.FileInputStream;

/**
 * Created by timday on 7/8/17.
 */
public class Sentence {

    private String sentence;
    private String[] tokens;
    private String[] tags;
//    private String[] lemmas;


    public Sentence(String sen) {
        this.sentence = sen;
        this.loadTokens();
        this.loadPOS();
//        this.loadLemma();

    }

    public String getSentence() {
        return sentence;
    }
    public String[] getTokens() {
        return tokens;
    }
    public String[] getTags() {
        return tags;
    }


    private void loadTokens() {
        try {
            TokenizerModel model = new TokenizerModel(new FileInputStream("en-token.bin"));
            Tokenizer tokenizer = new TokenizerME(model);
            tokens = tokenizer.tokenize(sentence); //ins var

        } catch (Exception e) {
            System.out.println("Token Load Error");
        }
    }

    private void loadPOS() {
        try {
            POSModel model = new POSModel(new FileInputStream("en-pos-maxent.bin"));
            POSTaggerME tagger = new POSTaggerME(model);

            tags = tagger.tag(tokens);

        } catch (Exception e) {
            System.out.println("POS Tag Load Error");
        }
    }

//    private void loadLemma() {
//        try {
//            LemmatizerModel model = new LemmatizerModel(new FileInputStream(""));
//
//
//        } catch (Exception e) {
//            System.out.println("Lemma Load Error");
//        }
//    }


    public void printTokens() {
        for (String tok : tokens) {
            System.out.println(tok);
        }
    }

    public void printTags() {
        for (String tag : tags) {
            System.out.println(tag);
        }
    }

    public void printTokensWithTags() {
        for (int i=0; i<tokens.length; i++) {

            System.out.println("TOKEN: " + tokens[i] + "\t\t\tTAG: " + tags[i]);
        }
    }


    public static void main(String[] args) {
        Sentence test = new Sentence("This here is a sentence.");
        test.printTokensWithTags();

//        test.printTags();

    }
}
