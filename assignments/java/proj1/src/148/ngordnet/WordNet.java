package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set; 
import java.util.LinkedHashSet;
import java.util.Arrays;

public class WordNet extends Object {
    private HashMap<Integer, Set<String>> synsetMap;
    private Set<String> nounSet;
    private Digraph synonyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetMap = new HashMap<Integer, Set<String>>();
        nounSet = new LinkedHashSet<String>();
        In inSynset = new In(synsetFilename);
        String[] allSynsets = inSynset.readAllLines();
        for (String each : allSynsets) {
            String[] synsetLine = each.split(",");
            Integer setKey = Integer.parseInt(synsetLine[0]);
            String[] setStr = synsetLine[1].split(" ");
            Set<String> synsetSet = new LinkedHashSet<String>(Arrays.asList(setStr));
            synsetMap.put(setKey, synsetSet);
            nounSet.addAll(synsetSet);
        }
        synonyms = new Digraph(synsetMap.size());
        In inHyponym = new In(hyponymFilename);
        String[] allHyponyms = inHyponym.readAllLines();
        for (String each : allHyponyms) {
            String[] hypoList = each.split(",");
            Integer hyperNum = Integer.parseInt(hypoList[0]);
            for (String descend : hypoList) {
                if (Integer.parseInt(descend) != hyperNum) {
                    synonyms.addEdge(hyperNum, Integer.parseInt(descend));
                }
            }
        }
    }

    /* Returns the set of all nouns. */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /**Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
    If WORD belongs to multiple synsets, return all hyponyms of all of these synsets. 
    See http://goo.gl/EGLoys for an example. Do not include hyponyms of synonyms. */
    public Set<String> hyponyms(String word) {
        Set<Integer> wordSynsets = new LinkedHashSet<Integer>();
        for (int key : synsetMap.keySet()) {
            if (synsetMap.get(key).contains(word)) {
                wordSynsets.add(key);
            }
        }
        Set<Integer> vertices = GraphHelper.descendants(synonyms, wordSynsets);
        Set<String> hyponyms = new LinkedHashSet<String>();
        for (int vertex : vertices) {
            for (String hyponym : synsetMap.get(vertex)) {
                hyponyms.add(hyponym);
            }
        }
        return hyponyms;
    }
}
