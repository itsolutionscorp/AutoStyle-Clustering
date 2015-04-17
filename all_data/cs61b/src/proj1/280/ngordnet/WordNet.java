package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    // Hyponym-related.
    private int hyponymCount = 0;
    private HashMap<Integer, HashSet<String>> hypoStorage = new HashMap<Integer, HashSet<String>>();
    private Digraph g;
    private In hypo;

    // Synsets related.
    private HashMap<String, HashSet<Integer>> mapN = new HashMap<String, HashSet<Integer>>();
    private HashMap<Integer, HashSet<String>> mapID = new HashMap<Integer, HashSet<String>>();
    private In syn;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Creates two maps to keep track of synsets.
        String[] entry;
        HashSet<String> curr;
        HashSet<String> synset;
        HashSet<Integer> idList;
        syn = new In(synsetFilename);
        while (!syn.isEmpty()) {
            String line = syn.readLine();
            entry = line.split(",");
            Integer id = Integer.parseInt(entry[0]);
            synset = new HashSet<String>(Arrays.asList(entry[1].split(" ")));
            mapID.put(id, synset);
            for (String s : synset) {
                if (mapN.get(s) != null) {
                    mapN.get(s).add(id);
                } else {
                    idList = new HashSet<Integer>();
                    idList.add(id);
                    mapN.put(s, idList);
                }
            }
        }

        // Creates a digraph from hyponym file.
        hypo = new In(hyponymFilename);
        while (!hypo.isEmpty()) {
            String line = hypo.readLine();
            entry = line.split(",");
            String head = entry[0];
            int header = Integer.parseInt(head);
            curr = new HashSet<String>(Arrays.asList(entry));
            hyponymCount += curr.size();
            curr.remove(head);
            if (hypoStorage.containsKey(header)) {
                hypoStorage.get(header).addAll(curr);
            } else {
                hypoStorage.put(header, curr);
            }
        }

        g = new Digraph(hyponymCount + 1);
        HashSet<Integer> keys = new HashSet<Integer>(hypoStorage.keySet());
        for (Integer key : keys) {
            curr = hypoStorage.get(key);
            for (String v : curr) {
                int k = Integer.parseInt(v);
                g.addEdge(key, k);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return mapN.containsKey(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return mapN.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> result = new HashSet<String>();
        HashSet<Integer> curr = mapN.get(word);
        Set<Integer> num = new HashSet<Integer>();
        for (Integer i : curr) {
            num.add(i);
            for (String p : mapID.get(i)) {
                result.add(p);
            }
        }
        Set<Integer> hyponym = GraphHelper.descendants(g, num);
        for (Integer j : hyponym) {
            if (curr.contains(j)) {
                continue;
            } else {
                for (String s : mapID.get(j)) {
                    result.add(s);
                }
            }
        }
        return result;
    }
}
