package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
// import java.util.TreeSet;
// import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class WordNet {

    private Digraph myGraph;
    private Map<String, Integer> stringToint = new HashMap<String, Integer>();
    private Map<Integer, String> intTostring = new HashMap<Integer, String>();
    private ArrayList<String> nounSet = new ArrayList<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        In synsetFileAgain = new In(synsetFilename);
        //I am the final commit. If you see me, I'm good.
        // I AM the FINAL NOTE.
        // Parses through Synsets and adds the actual word in ArrayList words.
        // Populates stringToint map
        while (synsetFile.hasNextLine()) {
            String currentEntry = synsetFile.readLine();
            String[] spliced = currentEntry.split(",");
            stringToint.put(spliced[1], Integer.parseInt(spliced[0]));
            String[] spliced2 = spliced[1].split(" ");
            for (String words : spliced2) {
                nounSet.add(words);
            }
        }

        myGraph = new Digraph(nounSet.size());

        //Parses through every line of hyponyms and draws relationships using graphs.
        while (hyponymFile.hasNextLine()) {
            String entry = hyponymFile.readLine();
            String[] hSpliced = entry.split(",");
            for (String connections : hSpliced) {
                if (!connections.equals(hSpliced[0])) {
                    myGraph.addEdge(Integer.parseInt(hSpliced[0]), Integer.parseInt(connections));
                }
            }
        }

        // Creates intToString map
        while (synsetFileAgain.hasNextLine()) {
            String mapEntry = synsetFileAgain.readLine();
            String[] spliced3 = mapEntry.split(",");
            intTostring.put(Integer.parseInt(spliced3[0]), spliced3[1]);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> toRet = new HashSet<String>();
        for (String noun : nounSet) {
            toRet.add(noun);
        }
        return toRet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Integer wordID;
        String obtainedID;
        Set<Integer> forGrapherHelper = new HashSet<Integer>();
        Set<Integer> idSet = new HashSet<Integer>();
        Set<String> result = new HashSet<String>();

        // Searches through stringToints to get ID and then throw them into
        // forGrapherHelper
        Set<String> wordSet = stringToint.keySet();
        for (String line: wordSet) {
            String[] spliced = line.split(" ");
            for (String splicedWord : spliced) {
                if (word.equals(splicedWord)) {
                    wordID = stringToint.get(line);
                    forGrapherHelper.add(wordID);
                }
            }
        }

        // Allows me to get the idSet of all the words that I need.
        idSet = GraphHelper.descendants(myGraph, forGrapherHelper);

        // Finds the words associated with the ID's and throws them into
        // Set<String> result.
        if (idSet != null) {
            for (Integer id : idSet) {
                obtainedID = intTostring.get(id);
                String[] multipleIDs = obtainedID.split(" ");
                for (String finalWords : multipleIDs) {
                    result.add(finalWords);
                }
            }
        }
        return result;
    }       
}
