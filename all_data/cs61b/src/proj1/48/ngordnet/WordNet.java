package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    private Digraph d;
    private List<String[]> synsets = new ArrayList<String[]>();
    private List<String> definitions = new ArrayList<String>();
    private List<Integer[]> hyps = new ArrayList<Integer[]>();
    public WordNet(String synsetFilename, String hyponymFilename)  {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        
        int position = 0;
        String[] toSplit;
        String[] toSplitAgain;
        while (synsetIn.hasNextLine()) {
            toSplit = synsetIn.readLine().split(",");
            toSplitAgain = toSplit[1].split(" ");
            position = Integer.parseInt(toSplit[0]);
            synsets.add(position, toSplitAgain);
            definitions.add(position, toSplit[2]);
        }

        String[] parts;
        List<Integer> hypID;
        Integer[] ints;
        while (hyponymIn.hasNextLine()) {
            hypID = new ArrayList<Integer>();
            parts = hyponymIn.readLine().split(",");
            for (String a : parts) {
                hypID.add(Integer.parseInt(a));
            }
            ints = new Integer[hypID.size()];
            ints = hypID.toArray(ints);
            hyps.add(ints);
        }

        d = new Digraph(synsets.size());

        for (Integer[] a : hyps) {
            for (int i = 1; i < a.length; i += 1) {
                d.addEdge(a[0], a[i]);
            }
        }


    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String[] a : synsets) {
            for (String b : a) {
                if (b.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (String[] a : synsets) {
            for (String b : a) {
                allNouns.add(b);
            }
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        int counter = 0;
        List<Integer> indices = new ArrayList<Integer>();

        Set<String> allHyponyms = new HashSet<String>();

        Set<Integer> fullIndices = new HashSet<Integer>();


        //find all synset IDs with word
        for (String[] a : synsets) {
            for (String b : a) {
                if (b.equals(word)) {
                    indices.add(counter);
                }
            }
            counter += 1;
        }


        if (indices.isEmpty()) {
            return null;
        }

        //find all indices of hyponyms
        for (Integer i : indices) {
            fullIndices.add(i);
        }

        fullIndices = GraphHelper.descendants(d, fullIndices);

        //put all synsets of correct IDs into a Set
        for (Integer i : fullIndices) {
            for (String str : synsets.get(i)) {
                allHyponyms.add(str);
            }
        }
        
        allHyponyms.add(word);

        return allHyponyms;
    }
}

