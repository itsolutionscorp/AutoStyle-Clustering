package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.HashMap;

public class WordNet {
    private Map<String, Set<Integer>> nounMap; //maps nouns to their synset id's
    private Map<Integer, Set<String>> idMap; //maps id's to a set of their nouns
    private Digraph graph;
    private Map<Integer, String> defs; //maps synset id's to their gloss

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */ 
    public WordNet(String synsetFilename, String hyponymFilename) {
        nounMap = new HashMap<String, Set<Integer>>(100);
        idMap = new TreeMap<Integer, Set<String>>();
        defs = new TreeMap<Integer, String>();

        In synsetsFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        String line;
        String[] data;
        int id;
        TreeSet<String> nouns;

        while (!synsetsFile.isEmpty()) {
            line = synsetsFile.readLine();
            data = line.split(","); //[id, synset, gloss]
            id = Integer.parseInt(data[0]);
            nouns = new TreeSet<String>(Arrays.asList(data[1].split(" "))); 
            // ^source: http://stackoverflow.com/questions/3064423/java-easily-convert-array-to-set
            idMap.put(id, nouns);
            defs.put(id, data[2]);
            for (String noun: nouns) {
                if (nounMap.containsKey(noun)) {
                    nounMap.get(noun).add(id);
                } else {
                    TreeSet<Integer> single = new TreeSet<Integer>();
                    single.add(id);
                    nounMap.put(noun, single);
                }
            }
        }

        graph = new Digraph(idMap.size());
        while (!hyponymFile.isEmpty()) {
            line = hyponymFile.readLine();
            data = line.split(","); //[id, *direct_hyponyms]
            id = Integer.parseInt(data[0]);
            for (int i = 1; i < data.length; i++) {
                graph.addEdge(id, Integer.parseInt(data[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> words = new TreeSet<String>();
        if (!isNoun(word)) {
            words.add(word);
            return words;
        }
        Set<Integer> ids = nounMap.get(word);
        Set<Integer> hyponyms = GraphHelper.descendants(graph, ids); //grab id's of all hyponyms
        for (int i: ids) { //for each synset that the word belongs to
            words.addAll(idMap.get(i)); //add all the synonyms in that synset
        }
        for (int h: hyponyms) { //add all the hyponyms
            words.addAll(idMap.get(h));
        }
        return words;
    }
}
