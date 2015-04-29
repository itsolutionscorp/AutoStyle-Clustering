
package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Set;



public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private TreeMap<Integer, String[]> synmap = new TreeMap<Integer, String[]>();
    private TreeMap<Integer, Integer[]> hypmap = new TreeMap<Integer, Integer[]>();
    private Digraph total;
    private int vertices = 0; 

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hypons = new In(hyponymFilename);

        while (!synsets.isEmpty()) {
            vertices += 1;
            String line = synsets.readLine();
            String[] items = line.split(",");
            String[] synonyms = items[1].split(" ");
            synmap.put(Integer.parseInt(items[0]), synonyms);
        }

        total = new Digraph(vertices);

        while (!hypons.isEmpty()) {
            String line = hypons.readLine();
            String[] tokens = line.split(",");
            Integer[] nums = new Integer[tokens.length];
            for (int i = 0; i < tokens.length; i += 1) {
                nums[i] = Integer.parseInt(tokens[i]);
            }
            Integer[] hymonyms = new Integer[nums.length - 1];
            for (int i = 1; i < nums.length; i += 1) {
                total.addEdge(nums[0], nums[i]);
                hymonyms[i - 1] = nums[i];
            }
            hypmap.put(nums[0], hymonyms);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < vertices; i += 1) {
            String[] set = synmap.get(i);
            if (set == null) {
                return false;
            }
            if (Arrays.asList(set).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> result = new TreeSet<String>();
        for (int i = 0; i < vertices; i += 1) {
            String[] set = synmap.get(i);
            for (String word: set) {
                result.add(word);
            }
        }
        return result;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Integer[] idnums = new Integer[vertices];
        Arrays.fill(idnums, -1);
        TreeSet<Integer> hypitems = new TreeSet<Integer>();
        TreeSet<String> result = new TreeSet<String>();
        for (int i = 0; i < vertices; i += 1) {
            String[] set = synmap.get(i);
            if (Arrays.asList(set).contains(word)) {
                idnums[i] = i;
                for (String item: set) {
                    if (word != item) {
                        result.add(item);
                    }
                }
            }
        }
        for (int i = 0; i < idnums.length; i += 1) {
            if (idnums[i] != -1) {
                hypitems.add(idnums[i]);
            }
        }
        Set<Integer> lastitems = GraphHelper.descendants(total, hypitems);
        for (Integer x: lastitems) {
            String[] corresponding = synmap.get(x);
            for (String term: corresponding) {
                result.add(term);
            }
        }
        return result;
    }
}
