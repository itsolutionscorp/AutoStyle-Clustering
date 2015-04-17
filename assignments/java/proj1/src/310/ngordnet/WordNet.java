package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;
// GRADE //

public class WordNet {
    private ArrayList<String> synsetsList = new ArrayList<String>();
    private Integer numsyn;
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In nounsynsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);

        while (nounsynsets.hasNextLine()) {
            String line = nounsynsets.readLine();
            String[] rawTokens = line.split(",");
            Integer id = Integer.parseInt(rawTokens[0]);
            this.numsyn = id + 1;
            String synset = rawTokens[1];
            synsetsList.add(rawTokens[1]);
        }

        this.graph = new Digraph(numsyn);
        while (hyponyms.hasNextLine()) {
            String line = hyponyms.readLine();
            String[] rawTokens = line.split(",");
            Integer synid = Integer.parseInt(rawTokens[0]);
            for (int i = 1; i < rawTokens.length; i++) {
                Integer synid2 = Integer.parseInt(rawTokens[i]);
                this.graph.addEdge(synid, synid2);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        TreeSet<String> a = new TreeSet<String>();
        for (String s: synsetsList) {
            String[] tokens = s.split(" ");
            for (String t: tokens) {
                a.add(t);
            }
        }
        return a.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> a = new TreeSet<String>();
        for (String s: synsetsList) {
            String[] tokens = s.split(" ");
            for (String t: tokens) {
                a.add(t);
            }
        }
        return a;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> wordlocation = new TreeSet<Integer>();
        TreeSet<String> hyponyms = new TreeSet<String>();
        if (isNoun(word)) {
            Integer place = 0;
            for (Integer i = 0; i < synsetsList.size(); i++) {
                String[] words = synsetsList.get(i).split(" ");
                if (Arrays.asList(words).contains(word)) {
                    place = i;
                    wordlocation.add(place);
                }
            }
            Set<Integer> find = GraphHelper.descendants(graph, wordlocation);
            for (Integer x: find) {
                String[] tokens = synsetsList.get(x).split(" ");
                for (String s: tokens) {
                    hyponyms.add(s);
                }
            }
        }
        return hyponyms;
    }
}
