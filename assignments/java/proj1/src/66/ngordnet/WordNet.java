package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.HashMap;


public class WordNet {

    /** MAPPING1 is a Hashmap with synset id numbers as the keys and the 
      * corresopnding sets of Strings as the values. */
    private HashMap<Integer, TreeSet<String>> mapping1;

    /** MAPPING2 is a Hashmap with Strings as the keys and the corresopnding 
      * sets of synset id numbers as the values. */
    private HashMap<String, TreeSet<Integer>> mapping2;

    /** D is a Digraph that connects hyponyms together using HYPONYMFILENAME. */
    private Digraph d;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        mapping1 = new HashMap<Integer, TreeSet<String>>();
        mapping2 = new HashMap<String, TreeSet<Integer>>();
        In in = new In(synsetFilename);
        int value = 0;
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] words = line.split(",");
            value = Integer.parseInt(words[0]);
            String[] synonyms = words[1].split(" ");
            TreeSet<String> strings = new TreeSet<String>();
            for (int i = 0; i < synonyms.length; i++) {
                strings.add(synonyms[i]);
                if (mapping2.get(synonyms[i]) == null) {
                    TreeSet<Integer> originalInt = new TreeSet<Integer>();
                    originalInt.add(value);
                    mapping2.put(synonyms[i], originalInt); 
                } else {
                    mapping2.get(synonyms[i]).add(value);
                }
            }
            mapping1.put(value, strings);
        }
        d = new Digraph(value + 1);
        In in2 = new In(hyponymFilename);
        while (in2.hasNextLine()) {
            String line = in2.readLine();
            String[] words = line.split(",");
            int[] ints = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                ints[i] = Integer.parseInt(words[i]);
            }
            for (int i = 1; i < ints.length; i++) {
                d.addEdge(ints[0], ints[i]);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return mapping2.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return mapping2.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        GraphHelper g = new GraphHelper();
        TreeSet<String> s = new TreeSet<String>();
        if (mapping2.containsKey(word)) {
            TreeSet<Integer> integers = mapping2.get(word);
            integers.addAll(g.descendants(d, integers));
            Integer[] integerArray = integers.toArray(new Integer[0]);
            for (int i = 0; i < integerArray.length; i++) {
                s.addAll(mapping1.get(integerArray[i]));
            }
        }
        return s;
    }
}
