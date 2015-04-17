/** 
 * @author Ianto Xi
 * This file reads in files into a simple WordNet. 
 * Inspiration to use Scanner from In.java from hw1.
 */
package ngordnet; 

import edu.princeton.cs.algs4.Digraph; 
import java.util.HashMap; 
import java.util.Set; 
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList; 

import java.util.Scanner;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream; 
import java.io.LineNumberReader;

import java.io.IOException;

public class WordNet {

    private ArrayList<String> synSet; 
    private HashMap<String, TreeSet<Integer>> nouns; 
    private Digraph relations; 

    private static final String CHARSET_NAME = "UTF-8";

    //Constructor for a WordNet. Requires two filenames.
    public WordNet(String synSetFileName, String hypoNymFileName) {
        try {
            int numSynSets = lineNumbers(synSetFileName);
            synSet = new ArrayList<String>(numSynSets);
            nouns = new HashMap<String, TreeSet<Integer>>(numSynSets);
    
            relations = new Digraph(numSynSets);  
            
            readSynSet(synSetFileName);
            readHypoNym(hypoNymFileName);
        } catch (IOException e) {
            throw new RuntimeException(e); 
        }      
    }

    //Reads through the synSet file and fills in SYNSET and NOUNS
    //Helps to construct WordNet
    private void readSynSet(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename), CHARSET_NAME); 
        String line;
        int id; 
        String words;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine(); 
            id = Integer.parseInt(csvParse(line, 0)); 
            words = csvParse(line, 1);
            synSet.add(id, words);
            addToNouns(id, words);
        }
    }

    //Reads through HypoNym file and deposits relations into Digraph
    //Helps to construct WordNet
    private void readHypoNym(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename), CHARSET_NAME); 
        scanner.useDelimiter(",");
        String[] nyms; 
        int hypernym;
        while (scanner.hasNextLine()) {
            nyms = scanner.nextLine().split(","); 
            hypernym = Integer.parseInt(nyms[0]);
            for (int i = 1; i < nyms.length; i += 1) {
                relations.addEdge(hypernym, Integer.parseInt(nyms[i]));
            }
        }
    }

    public Set<String> nouns() {
        return nouns.keySet();
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    //Returns a list of hyponyms of a given word, including the word itself and any synonyms. 
    public Set<String> hyponyms(String word) { 
        Set<Integer> hypoIDs = GraphHelper.descendants(relations, nouns.get(word));

        Set<String> allHyponyms = new HashSet<String>(); 
        for (int id : hypoIDs) {
            for (String wd : synSet.get(id).split(" ")) {
                allHyponyms.add(wd);
            }
        }
        return allHyponyms; 
    }

    //Given a string with one or more words separated by a space, 
    //  adds all of them to the nouns set under a int key, ID. 
    private void addToNouns(int id, String words) {
        for (String word : words.split(" ")) {
            TreeSet<Integer> ids = nouns.get(word);
            if (ids == null) {
                ids = new TreeSet<Integer>();
            }
            ids.add(id);
            nouns.put(word, ids);
        }
    }

    //Given a line of CSV, returns the ith column
    private String csvParse(String line, int i) {
        String[] columns = line.split(",");
        if (i > columns.length - 1) {
            throw new IllegalArgumentException(); 
        }
        return columns[i];
    }

    //Returns the int number of lines in a file of a given string FILENAME. Inspiration from 
    //http://www.technicalkeeda.com/java/how-to-count-total-number-of-lines-of-file-using-java.  
    private int lineNumbers(String filename) throws IOException {
        File file = new File(filename); 

        LineNumberReader reader = new LineNumberReader(
                                  new InputStreamReader(
                                  new FileInputStream(file), CHARSET_NAME)); 
        reader.skip(Long.MAX_VALUE); 
        int lines = reader.getLineNumber() + 1; 
        reader.close(); 

        return lines; 
    }
}
