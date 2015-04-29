package ngordnet;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    
    private Digraph wordnet;
    private TreeMap<Integer, Set<String>> mapOfIDs;

    public WordNet(String synsetFilename, String hyponymFilename)  {
        In synset = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        ArrayList<String> dataStrings = new ArrayList<String>();
        
        while (synset.hasNextLine()) {
            dataStrings.add(synset.readLine());
        }
        
        Digraph g = new Digraph(dataStrings.size());
        mapOfIDs = new TreeMap<Integer, Set<String>>();
        for (int i = 0; i < dataStrings.size(); i++) {
            String[] csv = dataStrings.get(i).split(",");
            int key = Integer.parseInt(csv[0]);
            TreeSet<String> values = new TreeSet<String>();
            for (String word: csv[1].split(" ")) {
                values.add(word);
            }
            mapOfIDs.put(key, values);
        }
        
        while (hyponyms.hasNextLine()) {
            String[] connections = hyponyms.readLine().split(",");
            int start = Integer.parseInt(connections[0]);
            for (int i = 1; i < connections.length; i++) {
                g.addEdge(start, Integer.parseInt(connections[i]));
            }
        }
        
        wordnet = g;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int key: mapOfIDs.keySet()) {
            for (String word: mapOfIDs.get(key)) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> toReturn = new TreeSet<String>();
        for (int key: mapOfIDs.keySet()) {
            for (String word: mapOfIDs.get(key)) {
                toReturn.add(word);
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
        TreeSet<Integer> rootSynsets = new TreeSet<Integer>();
        for (int key: mapOfIDs.keySet()) {
            for (String val: mapOfIDs.get(key)) {
                if (val.equals(word)) {
                    rootSynsets.add(key);
                }
            }
        }
        Set<Integer> hyponymIDs = GraphHelper.descendants(wordnet, rootSynsets);
        TreeSet<String> toReturn = new TreeSet<String>();
        for (int k: hyponymIDs) {
            for (String w: mapOfIDs.get(k)) {
                toReturn.add(w);
            }
        }
        
        return toReturn;
    }
}
