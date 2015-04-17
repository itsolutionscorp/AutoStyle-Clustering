package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class WordNet {
    private Digraph g;
    private List<String> words;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        words = new ArrayList<String>();
        int n = 0;

        String line = "";
        String[] tokens = null;

        In synsets = new In(synsetFilename);

        while (synsets.hasNextLine()) {
            line = synsets.readLine();
            tokens = line.split(",");
            words.add(tokens[1]);
            n += 1;
        }

        g = new Digraph(n);

        In hyponyms = new In(hyponymFilename);

        while (hyponyms.hasNextLine()) {
            line = hyponyms.readLine();
            tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i += 1) {
                g.addEdge(id, Integer.parseInt(tokens[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String word : words) {
            String[] w = word.split(" ");
            for (int i = 0; i < w.length; i += 1) {
                if (noun.equals(w[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (String word : words) {
            String[] w = word.split(" ");
            for (int i = 0; i < w.length; i += 1) {
                if (!allNouns.contains(w[i])) {
                    allNouns.add(w[i]);
                }
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
        Set<String> hyponyms = new TreeSet<String>();
        Set<Integer> ids = new TreeSet<Integer>();
        String[] group = null;
        hyponyms.add(word);
        for (int id = 0; id < words.size(); id += 1) {
            group = words.get(id).split(" ");
            for (int i = 0; i < group.length; i += 1) {
                if (word.equals(group[i])) {
                    for (String w : group) {
                        if (!hyponyms.contains(w)) {
                            hyponyms.add(w);
                        }
                    }
                    ids.add(id);
                }
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(g, ids);
        for (Integer d : descendants) {
            group = words.get(d).split(" ");
            for (String w : group) {
                if (!hyponyms.contains(w)) {
                    hyponyms.add(w);
                }
            }
        }
        return hyponyms;
    }
}
