package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class WordNet {

    private HashMap<Integer, String[]> sysMap = new HashMap<Integer, String[]>();
    private HashMap<Integer, String[]> hypMap = new HashMap<Integer, String[]>();
    private Digraph diGraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In sysFile = new In(synsetFilename);
        In hypFile = new In(hyponymFilename);
        String line = new String("0");
        String line2 = new String("0");
        String[] sysString = new String[3];
        String[] hypStr = new String[100];
        while ((line = sysFile.readLine()) != null) {
            sysString = line.split(",");
            sysMap.put(Integer.parseInt(sysString[0]), sysString[1].split(" "));
        }
        diGraph = new Digraph(sysMap.size());
        while ((line2 = hypFile.readLine()) != null) {
            hypStr = line2.split(",");
            String[] values = new String[hypStr.length];
            for (int i = 1; i < hypStr.length; i++) {
                values[i - 1] = hypStr[i];
                diGraph.addEdge(Integer.parseInt(hypStr[0]), Integer.parseInt(hypStr[i]));
            }
            hypMap.put(Integer.parseInt(hypStr[0]), values);
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String[] values: sysMap.values()) {
            for (int i = 0; i < values.length; i++) {
                if (values[i].equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet();
        for (String[] values: sysMap.values()) {
            for (int i = 0; i < values.length; i++) {
                allNouns.add(values[i]);
            }
        }
        return allNouns;
    }

    private Set<String> stringParse(String str) {
        String[] splitWord = str.split(" ");
        Set<String> strSet = new HashSet<String>(Arrays.asList(splitWord));
        return strSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIn = new TreeSet<Integer>();
        for (Map.Entry<Integer, String[]> entry: sysMap.entrySet()) {
            if (Arrays.asList(entry.getValue()).contains(word)) {
                synsetIn.add(entry.getKey());
            }
        }
        Set<Integer> allHyps = GraphHelper.descendants(diGraph, synsetIn);
        Set<String> hypWords = new TreeSet<String>();
        for (Integer hypID: allHyps) {
            hypWords.addAll(Arrays.asList(sysMap.get(hypID)));
        }
        return hypWords;
    }
}
