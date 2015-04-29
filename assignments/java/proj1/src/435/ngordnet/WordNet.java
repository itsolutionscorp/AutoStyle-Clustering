package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Map<String, Set<Integer>> synsetMapWORD;
    private Map<Integer, Set<String>> synsetMapID;
    private Digraph hyponymDigraph;

    public WordNet(String synsetFilename, String hyponymFilename) {


        Map<String, Set<Integer>> synMapWORD = new HashMap<String, Set<Integer>>();
        Map<Integer, Set<String>> synMapID = new HashMap<Integer, Set<String>>();


        In in = new In(synsetFilename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] commaSplit = line.split(",");
            int synsetID = Integer.parseInt(commaSplit[0]);
            String words = commaSplit[1];
            String[] wordsArray = words.split(" ");
            Set<Integer> synIDSet = new HashSet<Integer>();
            Set<String> synWORDSet = new HashSet<String>();
            synIDSet.add(synsetID);

            for (String word: wordsArray) {
                synWORDSet.add(word);
                if (synMapWORD.containsKey(word)) {
                    for (int k: synMapWORD.get(word)) {
                        synIDSet.add(k);
                    }
                }
                synMapWORD.put(word, synIDSet);
            }

            synMapID.put(synsetID, synWORDSet);

        }

        Digraph hyponyms = new Digraph(synMapWORD.size());
        In hyponymTxt = new In(hyponymFilename);
        while (hyponymTxt.hasNextLine()) {
            String hypStr = hyponymTxt.readLine();
            String[] commaSplit = hypStr.split(",");

            for (int k = 1; k < commaSplit.length; k += 1) {
                hyponyms.addEdge(Integer.parseInt(commaSplit[0]), Integer.parseInt(commaSplit[k]));
            }
        }

        synsetMapWORD = synMapWORD;
        synsetMapID = synMapID;
        hyponymDigraph = hyponyms;

    }

    public boolean isNoun(String noun) {
        return synsetMapWORD.containsKey(noun);

    }

    public Set<String> nouns() {
        return synsetMapWORD.keySet();

    }

    public Set<String> hyponyms(String word) {
        HashSet<String> hyponymSet = new HashSet<String>();
        Set<Integer> graphHelperID = new HashSet<Integer>();
        if (synsetMapWORD.containsKey(word)) {
            Set<Integer> wordID = synsetMapWORD.get(word);
            for (int w: wordID) {
                Set<String> tempSet = synsetMapID.get(w);
                if (tempSet.contains(word)) {
                    graphHelperID.add(w);
                }
            }
            Set<Integer> descendants = GraphHelper.descendants(hyponymDigraph, graphHelperID);
            for (int k: descendants) {
                hyponymSet.addAll(synsetMapID.get(k));
            }
        }
        Set<String> returnSet = hyponymSet;
        return returnSet;
    }
}
