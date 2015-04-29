package ngordnet;
import edu.princeton.cs.algs4.Digraph;

import java.util.Set;

import edu.princeton.cs.introcs.In;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class WordNet {
    /* friend told me that HashMaps, TreeMaps, TreeSets, and HashSets exist.
     * We never learned about them in class (or at least I don't remember), so I used google
     * to figure out how they work and their respective APIs*/
    private Map<Integer, HashSet<String>> intNoun;
    private Map<String, HashSet<Integer>> nounInt;
    private Digraph graph;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME 
     * Used stackoverflow to figure out how to read in lines
     * */
    public WordNet(String synsetFilename, String hyponymFilename) {
        intNoun = new HashMap<Integer, HashSet<String>>();
        nounInt = new HashMap<String, HashSet<Integer>>();
        In in = new In(synsetFilename);
        while (in.hasNextLine()) {
            String s = in.readLine();
            String word = "";
            String num = "";
            HashSet<String> strings = new HashSet<String>();
            HashSet<Integer> ints = new HashSet<Integer>();
            int i = 0;
            while (s.charAt(i) != ',') { 
                num = s.substring(0, i + 1);
                i += 1;
            }
            int z = i + 1;
            while (s.charAt(z) != ',') {
                if (s.charAt(z) == ' ') {
                    strings.add(word);
                    if (nounInt.containsKey(word)) {
                        ints = nounInt.get(word);    
                    }
                    ints.add(Integer.parseInt(num));
                    nounInt.put(word, ints);
                    ints = new HashSet<Integer>();
                    word = "";
                    i = z; 
                } else {
                    word = s.substring(i + 1, z + 1);
                }
                z += 1;
            }
            strings.add(word);
            if (nounInt.containsKey(word)) {
                ints = nounInt.get(word);    
            }
            ints.add(Integer.parseInt(num));
            intNoun.put(Integer.parseInt(num), strings);
            nounInt.put(word, ints);
        }
        In in2 = new In(hyponymFilename);
        graph = new Digraph(intNoun.size());
        while (in2.hasNextLine()) {
            String s = in2.readLine();
            String num = "";
            String word = "";
            int i = 0;
            while (s.charAt(i) != ',') { 
                num = s.substring(0, i + 1);
                i += 1;
            }
            int z = i + 1;
            while (z < s.length()) {
                if (s.charAt(z) == ',') {
                    graph.addEdge(Integer.parseInt(num), Integer.parseInt(word));
                    word = "";
                    i = z;
                } else {
                    word = s.substring(i + 1, z + 1);
                }
                z += 1;
            }
            graph.addEdge(Integer.parseInt(num), Integer.parseInt(word));
        }
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (nounInt.containsKey(noun));
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounInt.keySet();
    }
    
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> hyponym = new HashSet<String>();
        HashSet<Integer> indexes = nounInt.get(word);
        Set<Integer> descendants = GraphHelper.descendants(graph, indexes);
        for (Integer index: descendants) {
            HashSet<String> strings = intNoun.get(index);
            for (String s: strings) {
                hyponym.add(s);
            }
        }
        return hyponym;
    } 
}
