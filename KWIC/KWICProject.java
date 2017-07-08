/**
 * Created by timday on 7/8/17.
 */

import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;

import java.io.FileInputStream;
import java.util.ArrayList;

public class KWICProgram {

    ArrayList<Sentence> article;
    String[] sentences;


// CONSTRUCTOR
    public KWICProgram(String text) {

        try {
            SentenceModel model = new SentenceModel(new FileInputStream("en-sent.bin"));
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            sentences = sentenceDetector.sentDetect(text); // takes raw text

            article = new ArrayList<Sentence>(sentences.length); // create the article, as series of Sentence objs
            for (String sen : sentences) {
                Sentence obj = new Sentence(sen); //constructor adds the tokens, etc
                article.add(obj);
            }

        } catch (Exception e) {
            System.out.println("file not found. model?");
        }
    }


    public SearchResult searchModelForWord(String searchTerm) {

        SearchResult result = new SearchResult();

        for (Sentence sen : article) {
            String[] tokens = sen.getTokens();
            boolean tokenFoundInSen = false;

            for (int i=0; i<tokens.length; i++) {

                if (searchTerm.equalsIgnoreCase(tokens[i])) {

                    if (!tokenFoundInSen) {
                        result.sentencesWithWord.add(sen.getSentence());
                        tokenFoundInSen = true;
                    }

                    result.countMatches++;
                    String[] tags = sen.getTags();
                    result.posTagsMatches.add(tags[i]);
                    result.posTagsWordsPreceding.add(tags[i-1]);
                    result.posTagsWordsFollowing.add(tags[i+1]);
                }
            }
        }
        return result;
    }


    public void printSearchResults(String word) {

        SearchResult result = searchModelForWord(word);

        if (result != null) {

            System.out.println("Number of matches:  " + result.countMatches);

            for (String sen : result.sentencesWithWord){
                System.out.println(sen);
            }

            System.out.println();
            System.out.print("POS of matches:   ");
            for (String pos : result.posTagsMatches) {
                System.out.print(pos + " ");
            }

            System.out.println();
            System.out.print("POS of previous:  ");
            for (String pos : result.posTagsWordsPreceding) {
                System.out.print(pos + " ");
            }

            System.out.println();
            System.out.print("POS of following: ");
            for (String pos : result.posTagsWordsFollowing) {
                System.out.print(pos + " ");
            }
        }
    }


    private class SearchResult {

        private ArrayList<String> sentencesWithWord;
        int countMatches = 0;
        private ArrayList<String> posTagsMatches;
        private ArrayList<String> posTagsWordsPreceding;
        private ArrayList<String> posTagsWordsFollowing;

        public SearchResult(){
            sentencesWithWord = new ArrayList<>();
            posTagsMatches = new ArrayList<>();
            posTagsWordsPreceding = new ArrayList<>();
            posTagsWordsFollowing = new ArrayList<>();
        }

    }


    public static void main(String[] args){

        String testString = " Tim Day is an Australian student who watches Australian football. " +
                "But he lives in Germany. The time difference means he has to wake up early to watch." +
                " He doesn't mind. He learns about computer science and Australian language tools afterwards. And has a nap.";


        KWICProgram test = new KWICProgram(testString);

        test.printSearchResults("Australian");

        for (Sentence sen : test.article) {
            sen.printTokensWithTags();
        }

    }
}

//  oiginal path::  /Users/timday/Desktop/gitHub/Java2-KWIC-Project
