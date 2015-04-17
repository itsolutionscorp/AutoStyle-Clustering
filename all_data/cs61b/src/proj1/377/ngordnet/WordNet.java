package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;

public class WordNet extends Object {
    private Digraph nDigraph;
    // ArrayList<String> nouns; // ArrayList<TreeSet<String>> nouns;
    private Map<Integer, ArrayList<String>> nouns;
    private int numNouns;
    // int lineCount = 0;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        File synset = new File(synsetFilename);
        File hyponym = new File(hyponymFilename);
        nouns = new HashMap<Integer, ArrayList<String>>();
        try {
            Scanner synsetSC = new Scanner(synset);
            while (synsetSC.hasNextLine()) {
                String line = synsetSC.nextLine();
                String[] splitWCommas = line.split(",");   
                String[] middleSet = splitWCommas[1].split(" ");
                ArrayList<String> strings = new ArrayList<String>(); 
                for (int i = 0; i < middleSet.length; i += 1) {
                    strings.add(middleSet[i]);
                } 
                int index = Integer.parseInt(splitWCommas[0]);     //Need to store synonyms..
                nouns.put(index, strings);               //ArrayList of TreeSet!!!!
                numNouns += 1;
            }
            synsetSC.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }
        try {
            nDigraph = new Digraph(numNouns);
            Scanner hyponymSC = new Scanner(hyponym);
            while (hyponymSC.hasNextLine()) {
                String line = hyponymSC.nextLine();
                String[] split = line.split(",");
                for (int i = 1; i < split.length; i += 1) {
                    int v = Integer.parseInt(split[0]);
                    int w = Integer.parseInt(split[i]);
                    nDigraph.addEdge(v, w);
                }
            }
            hyponymSC.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Object o : nouns.keySet()) {
            for (int i = 0; i < nouns.get(o).size(); i++) {
                if (nouns.get(o).get(i).equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> toReturn = new TreeSet<String>();
        for (Integer i : nouns.keySet()) {
            for (int j = 0; j < nouns.get(i).size(); j++) {
                toReturn.add(nouns.get(i).get(j));
            }
        }
        return toReturn;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> wordID = new TreeSet<Integer>();
        int toAdd;
        for (Object o : nouns.keySet()) {
            for (int i = 0; i < nouns.get(o).size(); i++) {
                if (nouns.get(o).get(i).equals(word)) {
                    toAdd = (int) o;
                    wordID.add(toAdd);
                }
            }
        }
        // wordID.add(nouns.indexOf(word));
        Set<Integer> synsetID = GraphHelper.descendants(nDigraph, wordID);
        Set<String> hyponymSet = new TreeSet<String>();
        for (Integer id : synsetID) {
            for (int i = 0; i < nouns.get(id).size(); i++) {
                String toAddString = nouns.get(id).get(i);
                hyponymSet.add(toAddString);
            }
        }
        return hyponymSet;
    }
}
