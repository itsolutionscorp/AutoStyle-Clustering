package ngordnet;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<String, HashSet<Integer>> hyponymSet;
    private HashSet<String> nounSet;
    private Digraph g;
    private HashMap<Integer, String[]> wordMap;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        hyponymSet = new HashMap<String, HashSet<Integer>>();
        nounSet = new HashSet<String>();

        In synsetSc = new In(synsetFilename);
        In hyponymSc = new In(hyponymFilename);

        wordMap = new HashMap<Integer, String[]>();

        while (synsetSc.hasNextLine()) {
            String line = synsetSc.readLine();
            String[] lineSplit = line.split(",");
            Integer id = Integer.valueOf(lineSplit[0]);
            String[] wordSplit = lineSplit[1].split(" ");
            wordMap.put(id, wordSplit);

            for (String s : wordSplit) {
                nounSet.add(s);
            }
        }

        g = new Digraph(wordMap.size());

        while (hyponymSc.hasNextLine()) {
            String line = hyponymSc.readLine();
            String[] lineSplit = line.split(",");
            Integer id = Integer.valueOf(lineSplit[0]);

            for (int i = 1; i < lineSplit.length; i++) {
                g.addEdge(id, Integer.valueOf(lineSplit[i]));
            }

        }
        for (Map.Entry<Integer, String[]> root : wordMap.entrySet()) {
            for (String rootStr : root.getValue()) {
                if (!hyponymSet.containsKey(rootStr)) {
                    hyponymSet.put(rootStr, new HashSet<Integer>());
                }
                hyponymSet.get(rootStr).add(root.getKey());
            }
        }
        // for (Map.Entry<Integer, String[]> root : wordMap.entrySet()) {
        //     HashSet<String> synonyms = new HashSet<String>(Arrays.asList(root.getValue()));
        //     synonyms.addAll(getHyponyms(root.getKey(), wordMap, g));
        //     for (String rootStr : root.getValue()) {
        //         if (hyponymSet.containsKey(rootStr)) {
        //             hyponymSet.get(rootStr).addAll(synonyms);
        //         } else {
        //             hyponymSet.put(rootStr, synonyms);
        //         }
        //     }
        // }        
    }

    // private Set<String> getHyponyms(Integer id, HashMap<Integer, String[]> wordMap,
    //                                      Digraph g) {
    //     HashSet<String> result = new HashSet<String>();
    //     if (g.outdegree(id) != 0) {
    //         for (Integer num : g.adj(id)) {
    //             result.addAll(Arrays.asList(wordMap.get(num)));
    //             result.addAll(getHyponyms(num, wordMap, g));
    //         }
    //     }
        
    //     return result;
    // }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> result = new HashSet<String>();
        for (Integer i : GraphHelper.descendants(g, hyponymSet.get(word))) {
            result.addAll(Arrays.asList(wordMap.get(i)));
        }
        return result;
    }
}
