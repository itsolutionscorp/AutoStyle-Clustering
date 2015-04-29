package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private In synIn;
    private In hypIn;
    private String synsetsFile;
    private String hyponymsFile;
    private HashSet<String> allHyp;
    private HashSet<String> allNouns;
    private HashMap<String, ArrayList<String>> hypList;
    private HashMap<String, ArrayList<String>> z;
    private String [] words;
    private String [] spaces;
    private String [] seperatedIndexes;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsets, String hyponyms) {
        allNouns = new HashSet<String>();
        allHyp = new HashSet<String>();
        z = new HashMap<String, ArrayList<String>>();
        hypList = new HashMap<String, ArrayList<String>>();
        synsetsFile = synsets;
        hyponymsFile = hyponyms;
        synIn = new In(synsetsFile);
        hypIn = new In(hyponymsFile);
        String wholeLine = "";
        String indexesHyp = "";
        while (!hypIn.isEmpty()) { 
            indexesHyp = hypIn.readLine();
            seperatedIndexes = indexesHyp.split(",");
            if (hypList.containsKey(seperatedIndexes[0])) {
                for (int i = 1; i < seperatedIndexes.length; i += 1) { 
                    hypList.get(seperatedIndexes[0]).add(seperatedIndexes[i]);
                }
            } else {
                hypList.put(seperatedIndexes[0], new ArrayList<String>());
                for (int i = 1; i < seperatedIndexes.length; i += 1) { 
                    hypList.get(seperatedIndexes[0]).add(seperatedIndexes[i]);
                }
            }
        }
        while (!synIn.isEmpty()) { 
            wholeLine = synIn.readLine();
            words = wholeLine.split(",");
            spaces = words[1].split(" ");
            z.put(words[0], new ArrayList<String>());
            for (String s : spaces) { 
                z.get(words[0]).add(s);
                allNouns.add(s);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> hy = new HashSet<String>();
        HashSet<String> allIndexes = new HashSet<String>();
        Set<Integer> inde = new TreeSet<Integer>();
        Set<Integer> indexes = new TreeSet<Integer>();
        Digraph d = new Digraph(z.size());
        for (String x : z.keySet()) {
            for (int i = 0; i < z.get(x).size(); i++) {
                if (z.get(x).get(i).equals(word)) {
                    inde.add(Integer.parseInt(x));
                }
            }
        }
        for (Integer v : inde) {
            if (z.containsKey(v.toString())) {
                for (int g = 0; g < z.get(v.toString()).size(); g++) {
                    hy.add(z.get(v.toString()).get(g));
                }
            }
        }
        for (String k : hypList.keySet()) {
            for (int i = 0; i < hypList.get(k).size(); i++) {
                d.addEdge(Integer.parseInt(k), Integer.parseInt(hypList.get(k).get(i)));
            }
        }
        indexes = GraphHelper.descendants(d, inde);
        if (inde.size() > 0) {
            for (Integer l : indexes) {
                if (hypList.containsKey(l.toString())) {
                    for (int g = 0; g < hypList.get(l.toString()).size(); g++) {
                        for (int m = 0; m < z.get(hypList.get(l.toString()).get(g)).size(); m++) {
                            hy.add(z.get(hypList.get(l.toString()).get(g)).get(m));
                        }
                    }
                }
            }
        }
        return hy;
    }
}
