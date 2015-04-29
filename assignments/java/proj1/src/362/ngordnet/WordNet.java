package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<String, ArrayList<Integer>> synKeys = new HashMap<String, ArrayList<Integer>>();
    private HashMap<Integer, ArrayList<String>> synIDs = new HashMap<Integer, ArrayList<String>>();
    private Digraph edges;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
      * Goes line by line and puts the synset IDs and keys into two HashMaps,
      * one with (word, ArrayList of all corresponding synset ID's) and
      * (synset ID, ArrayList of the synonyms).
      */
    public WordNet(String synsetFilename, String hyponymFilename)  {
        In synFile = new In(synsetFilename);
        String synLine;
        int vertices = 0;

        /** Goes through synset file and puts words and ids into the maps synKeys
          * and synIDs. */
        while (synFile.hasNextLine()) {
            synLine = synFile.readLine();
            String[] synFields = synLine.split(",");
            String[] synonyms = synFields[1].split(" ");
            int synID = Integer.parseInt(synFields[0]);
            vertices += 1;
            for (String synonym : synonyms) {
                if (!synKeys.containsKey(synonym)) {
                    synKeys.put(synonym, new ArrayList<Integer>());
                }
                synKeys.get(synonym).add(synID);
                if (!synIDs.containsKey(synID)) {
                    synIDs.put(synID, new ArrayList<String>());
                }
                synIDs.get(synID).add(synonym);
            }
            
        }

        edges = new Digraph(vertices);
        In hypoFile = new In(hyponymFilename);
        String hypoLine;

        /** Goes through the hyponym file and adds edges into the digraph. */
        while (hypoFile.hasNextLine()) {
            hypoLine = hypoFile.readLine();
            String[] hypoFields = hypoLine.split(",");
            int hyperID = Integer.parseInt(hypoFields[0]);
            for (int i = 1; i < hypoFields.length; i++) {
                edges.addEdge(hyperID, Integer.parseInt(hypoFields[i]));
            }          
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synKeys.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synKeys.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms. */
    public Set<String> hyponyms(String word) {
        TreeSet<String> hypoWords = new TreeSet<String>();
        TreeSet<Integer> wordIDs = new TreeSet<Integer>();
        for (int synID : synKeys.get(word)) {
            wordIDs.add(synID);
        }
        for (int hypoID : GraphHelper.descendants(edges, wordIDs)) {
            for (String hypoWord : synIDs.get(hypoID)) {
                hypoWords.add(hypoWord);
            }
        }
        return hypoWords;
    }
}
