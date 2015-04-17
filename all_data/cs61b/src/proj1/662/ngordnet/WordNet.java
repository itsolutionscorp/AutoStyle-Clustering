package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private Digraph dg;
    private TreeMap<Integer, TreeSet<String>> synsets;
    private int numVertices;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new TreeMap<Integer, TreeSet<String>>();
        numVertices = 0;
        In syns = new In(synsetFilename);
        while (syns.hasNextLine()) {
            numVertices++;
            String line = syns.readLine();
            String[] split1 = line.split(",");
            String[] split2 = split1[1].split(" ");
            TreeSet<String> hs = new TreeSet<String>();
            for (String s: split2) {
                hs.add(s);
            }
            synsets.put(Integer.parseInt(split1[0]), hs);
        }
        dg = new Digraph(numVertices);
        In hyms = new In(hyponymFilename);
        while (hyms.hasNextLine()) {
            String line = hyms.readLine();
            String[] split = line.split("[, ]+");
            int v = Integer.parseInt(split[0]);
            for (int i = 1; i < split.length; i++) {
                dg.addEdge(v, Integer.parseInt(split[i]));
            }
        }
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (TreeSet<String> hs: synsets.values()) {
            for (String s: hs) {
                if (noun.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> set = new HashSet<String>();
        for (TreeSet<String> hs: synsets.values()) {
            for (String s: hs) {
                set.add(s);
            }
        }
        return set;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> set = new HashSet<String>();
        for (Integer id: synsets.keySet()) {
            TreeSet<String> hs = synsets.get(id);
            if (hs.contains(word)) {
                for (String s: hs) {
                    set.add(s);
                }
                TreeSet<Integer> ids = new TreeSet<Integer>();
                ids.add(id);
                Set<Integer> desc = GraphHelper.descendants(dg, ids);
                for (Integer i: desc) {
                    for (String s: synsets.get(i)) {
                        set.add(s);
                    }
                }
            }
        }
        return set;
    }

}
