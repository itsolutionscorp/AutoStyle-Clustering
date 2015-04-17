package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Set;
import java.util.Map;

import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private In synset;
    private In hyponym;

    private HashSet<String> nounsSet = new HashSet();
    private Map<Integer, Set> nounsMap;
    private Digraph nounsDigraph;
    private int numVertix;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename);

        nounsMap = createMap(synset);
        nounsDigraph = createDiagraph(hyponym, numVertix);

    }


    private Map<Integer, Set> createMap(In syn) {
        HashMap<Integer, Set> returnMap = new HashMap(numVertix);
        while (syn.hasNextLine()) {
            String line = syn.readLine();
            numVertix += 1;
            String[] threeParts = line.split(",");

            String[] synLineArray = threeParts[1].split(" ");
            HashSet<String> synlineSet = new HashSet(numVertix);

            for (String add: synLineArray) {
                synlineSet.add(add);
                nounsSet.add(add);
            }
            returnMap.put(Integer.parseInt(threeParts[0]), synlineSet);
        }
        return returnMap;
    }


    private Digraph createDiagraph(In hyp, int n) {
        Digraph a = new Digraph(n);
        while (hyp.hasNextLine()) {
            String line = hyp.readLine();
            String[] splitLine = line.split(",");
            for (String x: splitLine) {
                if (!splitLine[0].equals(x)) {
                    a.addEdge(Integer.parseInt(splitLine[0]), Integer.parseInt(x));
                }
            }
        }
        return a;
    }
       
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounsSet.contains((Object) noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return (Set) nounsSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set key = nounsMap.keySet();
        HashSet<Integer> highlightKey = new HashSet(numVertix);
        for (Object a: key) {
            if (nounsMap.get(a).contains((Object) word)) {
                highlightKey.add((Integer) a);
            }
        }

        Set syn = GraphHelper.descendants(nounsDigraph, highlightKey);
        HashSet result = new HashSet(numVertix);
        for (Object n: syn) {
            for (Object m: nounsMap.get((Integer) n)) {
                result.add((String) m);
            }
        }
        return result;
    }

}


