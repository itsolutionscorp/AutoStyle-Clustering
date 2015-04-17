package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Map<Integer, Set<String>> mapSyn;
    private Digraph hyponymDigraph;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        mapSyn = new TreeMap<Integer, Set<String>>();
        while (synset.hasNextLine()) {
            String[] line = synset.readLine().split(",");
            int key = Integer.parseInt(line[0]);
            String[] synonyms = line[1].split(" ");
            Set<String> setSyn = new TreeSet<String>(Arrays.asList(synonyms));
            mapSyn.put(key, setSyn);
        }
        hyponymDigraph = new Digraph(mapSyn.size());
        while (hyponym.hasNextLine()) {
            String[] line = hyponym.readLine().split(",");
            int key = Integer.parseInt(line[0]);
            String[] hyponyms = Arrays.copyOfRange(line, 1, line.length);
            for (int i = 0; i < hyponyms.length; i++) {
                hyponymDigraph.addEdge(key, Integer.parseInt(hyponyms[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int key : mapSyn.keySet()) {
            if (mapSyn.get(key).contains(noun)) {
                return true;
            }
        } 
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (int key : mapSyn.keySet()) {
            allNouns.addAll(mapSyn.get(key));
        } 
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> keys = new TreeSet<Integer>();
        Set<String> hypoSet = new TreeSet<String>();
        for (int key : mapSyn.keySet()) {
            if (mapSyn.get(key).contains(word)) {
                keys.add(key);
            }
        }
        Set<Integer> newSyns = new TreeSet<Integer>();
        for (int synsetID : keys) {
            Set<Integer> hypoID = new TreeSet<Integer>();
            Set<Integer> parentSet = new TreeSet<Integer>();
            parentSet.add(synsetID);
            hypoID.addAll(GraphHelper.descendants(hyponymDigraph, parentSet));
            if (hypoID != null) {
                for (Integer syn: hypoID) {
                    newSyns.add(syn);
                }
            }
        }
        Set<Integer> allKeys = new TreeSet<Integer>();
        allKeys.addAll(keys);
        allKeys.addAll(newSyns);
        for (int key : allKeys) {
            hypoSet.addAll(mapSyn.get(key));
        }
        return hypoSet;
    }
}
