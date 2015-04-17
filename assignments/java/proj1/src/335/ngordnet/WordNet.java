package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class WordNet {
    private Set<String> nounsSet = new TreeSet<String>();
    private HashMap<String, Set<Integer>> nounsToID = new HashMap<String, Set<Integer>>();
    private HashMap<Integer, List<String>> idToNouns = new HashMap<Integer, List<String>>();
    private Digraph hypoDigraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In dataSynset = new In(synsetFilename);
        In dataHyponym = new In(hyponymFilename);
        
        while (dataSynset.hasNextLine()) {
            String[] line = dataSynset.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            String[] nouns = line[1].split("\\s+");
            //Construct nounsSet
            for (String syn:nouns) {
                nounsSet.add(syn);
            }
            //Construct idToNouns
            List<String> wordList = Arrays.asList(nouns);
            idToNouns.put(id, wordList);
            //Construct nounsToID
            for (String word : nouns) {
                if (nounsToID.containsKey(word)) {
                    nounsToID.get(word).add(id);
                } else {
                    Set<Integer> idSet = new HashSet<Integer>();
                    idSet.add(id);
                    nounsToID.put(word, idSet);
                }
            }
        }
        hypoDigraph = new Digraph(nounsSet.size());
        String line; String[] indexStr; int parent; int child;
        // Hyponym Digraph
        while (dataHyponym.hasNextLine()) {
            line = dataHyponym.readLine();
            indexStr = line.split(",");
            parent = Integer.parseInt(indexStr[0]);
            for (int i = 1; i < indexStr.length; i++) {
                child = Integer.parseInt(indexStr[i]);
                hypoDigraph.addEdge(parent, child);
            } 
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nounsSet.isEmpty()) {
            return false;
        }
        return nounsSet.contains(noun);
    }

     /* Returns the set of all nouns. */
     // Defensive
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (String word : nounsSet) {
            nouns.add(word);
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    //@cite With help of Anh Thai on GraphHelper
    public Set<String> hyponyms(String word) {
        Set<String> hyponymsSet = new HashSet<String>();
        Set<Integer> vertices = new HashSet<Integer>();
        for (int idSet : nounsToID.get(word)) {
            vertices.add(idSet);
        }
        for (Integer descendant : GraphHelper.descendants(hypoDigraph, vertices)) {
            hyponymsSet.addAll(idToNouns.get(descendant));
        }
        return hyponymsSet;
    }
}
