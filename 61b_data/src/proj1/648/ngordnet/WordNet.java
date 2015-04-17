package ngordnet;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<Integer, HashSet<String>> map;
    private Digraph d;
    /* Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */


    public WordNet(String synsetFilename, String hyponymFilename) {
        map = new HashMap<Integer, HashSet<String>>();
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        while (synsetFile != null && synsetFile.hasNextLine()) {
            String str = synsetFile.readLine();
            String[] lst = str.split(",");
            HashSet<String> nouns = new HashSet<String>();
            for (String s : lst[1].split("\\s+")) { // Stack Overflow for split portion
                nouns.add(s);
            }
            map.put(Integer.parseInt(lst[0]), nouns);
        }
        d = new Digraph(map.keySet().size());
        while (hyponymFile != null && hyponymFile.hasNextLine()) {
            String str = hyponymFile.readLine();
            String[] lst = str.split(",");
            int hyper = Integer.parseInt(lst[0]);
            for (int i = 1; i < lst.length; i++) {
                d.addEdge(hyper, Integer.parseInt(lst[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> temp = nouns();
        if (temp != null && temp.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> set = new HashSet<String>();
        Set<Integer> keys = map.keySet();
        for (Integer i : keys) {
            Set<String> nouns = map.get(i);
            for (String str : nouns) {
                set.add(str);
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
        Set<Integer> hyponyms = new HashSet<Integer>();
        Set<String> toReturn = new HashSet<String>();
        if (isNoun(word)) {
            for (Integer i : map.keySet()) {
                HashSet<String> set = map.get(i);
                if (set.contains(word)) {
                    hyponyms.add(i);
                }
            }
            GraphHelper g = new GraphHelper();
            hyponyms = g.descendants(d, hyponyms);
            for (Integer i : hyponyms) {
                HashSet<String> set = map.get(i);
                for (String str : set) {
                    toReturn.add(str);
                }
            }
            return toReturn;
        } else {
            return toReturn;
        }
    }
}
