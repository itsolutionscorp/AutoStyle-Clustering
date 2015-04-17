package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
//style scrubbed
public class WordNet {
    private Digraph graph;
    private HashMap<Integer, HashSet<String>> lookup;

    private void loadSynset(String fname) throws FileNotFoundException {
        lookup = new HashMap<Integer, HashSet<String>>();
        Scanner s = new Scanner(new File(fname));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            Scanner ls = new Scanner(line).useDelimiter(",");
            int key = ls.nextInt();
            Scanner ws = new Scanner(ls.next());
            HashSet<String> value = new HashSet<String>();
            while (ws.hasNext()) {
                value.add(ws.next());
            }
            lookup.put(key, value);
        }
    }

    private void loadHyponyms(String fname) throws FileNotFoundException {
        graph = new Digraph(lookup.size());
        Scanner s = new Scanner(new File(fname));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            Scanner ls = new Scanner(line).useDelimiter(",");
            int papa = ls.nextInt();
            while (ls.hasNextInt()) {
                int bebe = ls.nextInt();
                graph.addEdge(papa, bebe);
            }
        }
    }

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            loadSynset(synsetFilename);
            loadHyponyms(hyponymFilename);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (HashSet<String> hss : lookup.values()) {
            if (hss.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> total = new HashSet<String>();
        for (HashSet<String> hss : lookup.values()) {
            for (String s : hss) {
                total.add(s);
            }
        }
        return total;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> res = new HashSet<String>();
        HashSet<Integer> rel = new HashSet<Integer>();
        for (Integer i : lookup.keySet()) {
            HashSet<String> hss = lookup.get(i);
            if (hss.contains(word)) {
                union(res, hss);
                rel.add(i);
            }
        }
        Set<Integer> hypoInds = GraphHelper.descendants(graph, rel);
        for (Integer i : hypoInds) {
            union(res, lookup.get(i));
        }
        return res;
    }

    private static <T> void union(HashSet<T> base, HashSet<T> addition) {
        for (T t : addition) {
            base.add(t);
        }
    }
}
