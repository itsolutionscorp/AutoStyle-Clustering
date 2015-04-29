package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

public class WordNet {
    private HashMap<String, TreeSet<Integer>> hyponymMap;
    private HashMap<Integer, TreeSet<String>> synsetMap;
    private TreeSet<String> nouns;
    private Digraph g;

    


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetsLines = new In(synsetFilename);
        In hyponymsLines = new In(hyponymFilename);
        String[] synsets = synsetsLines.readAllLines();
        String[] hyponyms = hyponymsLines.readAllLines();
        synsetMap = new HashMap<Integer, TreeSet<String>>();
        hyponymMap = new HashMap<String, TreeSet<Integer>>();
        nouns = new TreeSet<String>();
        int hypoPairNum = 0;
        for (int i = 0; i < synsets.length; i++) {
            String[] content = synsets[i].split(",");
            int repr = Integer.parseInt(content[0]);
            String[] wordString = content[1].split(" ");
            TreeSet<String> synonymSet = new TreeSet<String>();
            for (int j = 0; j < wordString.length; j++) {
                synonymSet.add(wordString[j]);
            }
            synsetMap.put(repr, synonymSet);

        }

        for (Integer key: synsetMap.keySet()) {
            nouns.addAll(synsetMap.get(key));
        }
        hypoPairNum = nouns.size();
        

        g = new Digraph(hypoPairNum);

        for (int i = 0; i < hyponyms.length; i++) {
            String[] intString = hyponyms[i].split(",");
            TreeSet<Integer> hyponymSet = new TreeSet<Integer>();
            int initial = Integer.parseInt(intString[0]);

            for (int j = 1; j < intString.length; j++) {
                hyponymSet.add(Integer.parseInt(intString[j]));
                g.addEdge(initial, Integer.parseInt(intString[j]));

            }
            for (String ele: synsetMap.get(initial)) {
                hyponymMap.put(ele, hyponymSet);
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns; 

    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> synsetInt = new TreeSet<Integer>();
        Set<Integer> hyponymInt = new HashSet<Integer>();
        Set<String> wordSet = new HashSet<String>();
        wordSet.add(word);
        for (Integer key: synsetMap.keySet()) {
            if ((synsetMap.get(key)).contains(word)) {
                synsetInt.add(key);
                hyponymInt.add(key);
            }
        }
        
        if (!synsetInt.isEmpty()) {
            hyponymInt.addAll(GraphHelper.descendants(g, synsetInt));
            for (Integer ele: hyponymInt) {
                wordSet.addAll(synsetMap.get(ele));
            }
        }
        return wordSet;

    }
}
