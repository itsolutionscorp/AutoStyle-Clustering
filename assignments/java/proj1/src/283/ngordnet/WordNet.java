package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.ArrayList;

/** An object that stores the WordNet graph in a manner useful for extracting
  * all hyponyms of a word.
  * @author Aditya Iyengar
  */
public class WordNet {
    private Digraph tree;
    private TreeMap<Integer, ArrayList<String>> wordNetGraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        wordNetGraph = new TreeMap<Integer, ArrayList<String>>();
        String type = synset.readLine();
        while (type != null) {
            splitAndAdd(type);
            type = synset.readLine();
        }
        tree = new Digraph(wordNetGraph.size() + 1);
        String value = hyponym.readLine();
        while (value != null) {
            String[] pieces = value.split(",");
            int[] numbers = new int[pieces.length];
            for (int x = 0; x < numbers.length; x++) {
                numbers[x] = Integer.parseInt(pieces[x]);
            }
            int hypernym = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                tree.addEdge(hypernym, numbers[i]);
            }
            value = hyponym.readLine();
        }
    }

    private void splitAndAdd(String x) {
        String[] parts = x.split(",");
        // Second element is always the list of strings seperated by spaces
        String[] nouns = parts[1].split(" ");
        ArrayList<String> synset = new ArrayList<String>();
        for (String noun : nouns) {
            synset.add(noun);
        }
        int position = Integer.parseInt(parts[0]);
        wordNetGraph.put(position, synset);
        synset = new ArrayList<String>();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer position : wordNetGraph.keySet()) {
            ArrayList<String> synset = wordNetGraph.get(position);
            if (synset.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> setOfNouns = new TreeSet<String>();
        for (Integer position : wordNetGraph.keySet()) {
            ArrayList<String> synset = wordNetGraph.get(position);
            for (String noun : synset) {
                setOfNouns.add(noun);
            }
        }
        return setOfNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymTree = new TreeSet<String>();
        Set<Integer> countTree = new TreeSet<Integer>();
        if (wordNetGraph.keySet() != null && wordNetGraph.keySet().size() != 0) {
            for (Integer key : wordNetGraph.keySet()) {
                ArrayList<String> current = wordNetGraph.get(key);
                if (current.contains(word)) {
                    countTree.add(key);
                }
            }
        }
        Set<Integer> fullTree = GraphHelper.descendants(tree, countTree);
        for (Integer pos : fullTree) {
            ArrayList<String> current = wordNetGraph.get(pos);
            for (String member : current) {
                hyponymTree.add(member);
            }
        }
        return hyponymTree;
    }
}
