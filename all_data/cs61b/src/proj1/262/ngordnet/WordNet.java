package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;

public class WordNet {
    private Map<Integer, List<String>> map;
    private Digraph graph;
    private In readerS;
    private In readerH;
    private int size;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        size = 0;
        map = new HashMap<Integer, List<String>>();
        readerS = new In(synsetFilename);
        readerH = new In(hyponymFilename);
        while (readerS.hasNextLine()) {
            String line = readerS.readLine();
            String[] stuff = line.split(",");
            int id = Integer.parseInt(stuff[0]);
            String[] synonyms = stuff[1].split(" ");
            List<String> syns = new ArrayList<String>();
            for (String syn : synonyms) {
                syns.add(syn);
            }
            String definition = stuff[2];
            map.put(id, syns);
            size += 1;
        }
        graph = new Digraph(size);
        while (readerH.hasNextLine()) {
            String line = readerH.readLine();
            String[] stuff = line.split(",");
            int first = Integer.parseInt(stuff[0]);
            for (int i = 1; i < stuff.length; i++) {
                graph.addEdge(first, Integer.parseInt(stuff[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<Integer> keys = map.keySet();
        for (int i : keys) {
            List<String> synonyms = map.get(i);
            if (synonyms.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> result = new TreeSet<String>();
        Collection<List<String>> values = map.values();
        for (List<String> l : values) {
            result.addAll(l);
        }
        return result;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> result = new TreeSet<String>();
        Set<Integer> keys = map.keySet();
        Set<Integer> synsetIds = new HashSet<Integer>();
        for (int i : keys) {
            List<String> synonyms = map.get(i);
            if (synonyms.contains(word)) {
                synsetIds.add(i);
                result.addAll(synonyms);
            }
        }
        Set<Integer> hypIds = GraphHelper.descendants(graph, synsetIds);
        for (int key : hypIds) {
            result.addAll(map.get(key));
        }
        return result;
    }
}
