package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap; 
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private In synsets;
    private In hyponyms;
    private String[] synsetLines;
    private String[] hyponymLines;
    private int numVertices = 0;
    private Digraph wordnetDigraph;
    private HashMap<Integer, String[]> wordnetHashMap = new HashMap<Integer, String[]>();
    private HashMap<String, HashSet<Integer>> inVertices = new HashMap<String, HashSet<Integer>>();
    private HashSet<String> allTheNouns = new HashSet<String>();
    
    public WordNet(String synsetFileName, String hyponymFileName) {
        synsets = new In(synsetFileName);
        hyponyms = new In(hyponymFileName);

        synsetLines = synsets.readAllLines();
        hyponymLines = hyponyms.readAllLines();

        wordnetDigraph = new Digraph(synsetLines.length);
        
        for (int i = 0; i < synsetLines.length; i++) {
            // int synsetStart = synsetLines[i].indexOf(',') + 1;
            // int synsetEnd = synsetLines[i].indexOf(',', synsetStart);
            // wordnetHashMap.put(i, synsetLines[i].substring(synsetStart, synsetEnd));
            String[] aSynset = synsetLines[i].split(",")[1].split(" ");
            wordnetHashMap.put(i, aSynset);
            for (int j = 0; j < aSynset.length; j++) {
                String aNoun = aSynset[j];
                allTheNouns.add(aNoun);
                if (inVertices.containsKey(aNoun)) {
                    inVertices.get((aNoun)).add(i);
                } else {
                    inVertices.put(aNoun, new HashSet<Integer>());
                    inVertices.get(aNoun).add(i);
                }
            }
        }

        for (int i = 0; i < hyponymLines.length; i++) {
        //  int hypernymIndexEnd = hyponymLines[i].indexOf(',');
        //  int hyponymID = Integer.parseInt(hyponymLines[i].substring(0, hypernymIndexEnd));
        //  int hyponymIndexStart = hypernymIndexEnd + 1;
        //  int hyponymIndexEnd = hyponymLines[i].indexOf(',', hyponymIndexStart);
        //  while (hyponymIndexStart > 0) {
        //      wordnetDigraph.addEdge(hyponymID, 
        //          Integer.parseInt(hyponymLines[i].substring
        // (hyponymIndexStart, hyponymIndexEnd)));       stylecheck y u see dis
        //      hyponymIndexStart = hyponymIndexEnd + 1;
        //      hyponymIndexEnd = hyponymLines[i].indexOf(',', hyponymIndexStart);
        //  }
        //  Apparently there's a string.split(). shoutout to my TA Alex!
            String[] oneHyponyms = hyponymLines[i].split(",");
            for (int j = 1; j < oneHyponyms.length; j++) {
                int hypernymIndex = Integer.parseInt(oneHyponyms[0]);
                int hyponymIndex = Integer.parseInt(oneHyponyms[j]);
                wordnetDigraph.addEdge(hypernymIndex, hyponymIndex);
            }
        }
    }


    public boolean isNoun(String word) {
        return allTheNouns.contains(word);
    }

    public Set<String> nouns() {
        return allTheNouns;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> thisWordsHyponyms = new HashSet<String>();
        if (this.isNoun(word)) {
            Set<Integer> synsetIDs = inVertices.get(word);
            Set<Integer> hyponymVertices = GraphHelper.descendants(wordnetDigraph, synsetIDs);
            thisWordsHyponyms.add(word);
            for (Integer vertex : hyponymVertices) {
                for (String hyponymssss : wordnetHashMap.get(vertex)) {
                    thisWordsHyponyms.add(hyponymssss);
                }
            }
        }
        return thisWordsHyponyms;
    }
}
