package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    /** 
    * Digraph(vertices)  Map(key=synset.id, 
    * value =synsets.nouns)
    *
    *
    */
    private HashMap<String, HashSet<Integer>> nounMap;
    private HashMap<Integer, HashSet<String>> idMap;
    private Digraph graph;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);

        idMap = new HashMap<Integer, HashSet<String>>();
        nounMap = new HashMap<String, HashSet<Integer>>();
        String buffer;
        String[] contents;
        Integer key;
        String[] nouns;
        HashSet<String> setOfNouns;
        while (synset.hasNextLine()) {
            buffer = synset.readLine();
            contents = buffer.split(",");
            key = Integer.parseInt(contents[0].trim());
            contents[1] = contents[1].trim();
            nouns = contents[1].split(" ");
            setOfNouns = new HashSet<String>(Arrays.asList(nouns));
            idMap.put(key, setOfNouns);
            for (String noun : nouns) {
                if (nounMap.containsKey(noun)) {
                    nounMap.get(noun).add(key);
                } else {
                    HashSet<Integer> newSet = new HashSet<Integer>();
                    newSet.add(key);
                    nounMap.put(noun, newSet);
                }
            }
        }
        graph = new Digraph(idMap.size());
        In hyponym = new In(hyponymFilename);
        Integer hypo;
        while (hyponym.hasNextLine()) {
            buffer = hyponym.readLine();
            contents = buffer.split(",");
            key = Integer.parseInt(contents[0].trim());
            for (int i = 1; i < contents.length; i++) {
                hypo = Integer.parseInt(contents[i].trim());
                graph.addEdge(key, hypo);
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
        HashSet<Integer> ids = nounMap.get(word);
        if (ids == null) {
            return new HashSet<String>();
        }
        Set<Integer> descIds = GraphHelper.descendants(graph, ids);
        HashSet<String> allHyponyms = new HashSet<String>();
        for (Integer id : descIds) {
            Set<String> nouns = idMap.get(id);
            allHyponyms.addAll(nouns);
        }
        return allHyponyms;
    }
}
