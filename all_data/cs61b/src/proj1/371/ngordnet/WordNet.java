package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordNet {
    private TreeMap<Integer, String> map;
    private Digraph net;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        In hyp = new In(hyponymFilename);
        int vertices = 0;
        map = new TreeMap();

        while (syn.hasNextLine()) {
            String[] line = syn.readLine().split(",");
            map.put(Integer.parseInt(line[0]), line[1]);
            vertices += 1;
        }
        net = new Digraph(vertices);
        while (hyp.hasNextLine()) {
            String[] line = hyp.readLine().split(",");
            int[] v = new int[line.length];
            for (int x = 0; x < line.length; x++) {
                v[x] = Integer.parseInt(line[x]);
            }
            for (int x = 1; x < v.length; x++) {
                net.addEdge(v[0], v[x]);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> nouns = new TreeSet();
        for (String s : new TreeSet<String>(map.values())) {
            String[] words = s.split(" ");
            for (String t : words) {
                nouns.add(t);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> ids = new TreeSet();
        for (int key : map.keySet()) {
            String[] words = map.get(key).split(" ");
            for (String s : words) {
                if (s.equals(word)) {
                    ids.add(key);
                }
            }
        }
        ids.addAll(GraphHelper.descendants(net, ids));
        TreeSet<String> hn = new TreeSet();
        for (int key : ids) {
            String[] words = map.get(key).split(" ");
            for (String s : words) {
                hn.add(s);
            }
        }
        return hn;
    }
}
