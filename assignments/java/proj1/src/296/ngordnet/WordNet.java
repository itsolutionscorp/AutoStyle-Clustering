package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashSet<String> setSynonyms = new HashSet<String>(); // synonyms stored in set
    private HashSet<Integer> integers = new HashSet<Integer>(); // integers mapped from a string
    // Map from string to integers on Digraph
    private HashMap<String, HashSet<Integer>> nouns = 
            new HashMap<String, HashSet<Integer>>(); 
    // Map from integers on Digraph to strings
    private HashMap<Integer, HashSet<String>> synsets = 
            new HashMap<Integer, HashSet<String>>();
    private Digraph hierarchy;
    private Integer tempIndex = new Integer(0); // index of digraph

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In reader = new In(synsetFilename);
        String[] tempArray;  // read comma splited line
        String[] synonyms; // String of synonyms
        while (reader.hasNextLine()) {
            setSynonyms = new HashSet<String>();
            tempArray = reader.readLine().split(",");
            tempIndex = Integer.parseInt(tempArray[0]);
            synonyms = tempArray[1].split(" ");
            for (String s : synonyms) {
                setSynonyms.add(s);
                if (nouns.containsKey(s)) {
                    nouns.get(s).add(tempIndex);
                } else { 
                    integers = new HashSet<Integer>();
                    integers.add(tempIndex);
                    nouns.put(s, integers);
                }
            }
            synsets.put(tempIndex, setSynonyms);
        }
        hierarchy = new Digraph(tempIndex + 1);
        reader = new In(hyponymFilename);
        Integer parent;
        HashSet<Integer> children = new HashSet<Integer>();
        while (reader.hasNextLine()) {
            tempArray = reader.readLine().split(",");
            parent = Integer.parseInt(tempArray[0]);
            for (int i = 1; i < tempArray.length; i++) {
                hierarchy.addEdge(parent, Integer.parseInt(tempArray[i]));
            }
            children.clear();
        }
    }   

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
    /** case where word is not in hyponyms? */
        HashSet<String> descendants = new HashSet<String>();
        HashSet<Integer> wordLocations = nouns.get(word);
        Set<Integer> targets = GraphHelper.descendants(hierarchy, wordLocations);
        for (Integer i : targets) {
            descendants.addAll(synsets.get(i));
        }
        return descendants;
    }

}
