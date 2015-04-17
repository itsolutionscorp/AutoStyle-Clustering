
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    private HashMap<String, HashSet<Integer>> wordToIDs;
    private HashMap<Integer, HashSet<String>> idToWords;
    private Digraph graph;
    public WordNet(String synsetFile, String hyponymsFile) {
        In synsets = new In(synsetFile);
        In hyponyms = new In(hyponymsFile);

        //create hasMap
        wordToIDs = new HashMap<String, HashSet<Integer>>();
        idToWords = new HashMap<Integer, HashSet<String>>();
        while (synsets.hasNextLine()) {
            String[] sLine = synsets.readLine().split(",");
            int id = Integer.parseInt(sLine[0]);
            String[] nouns = sLine[1].split(" ");
            for (int i = 0; i < nouns.length; i += 1) {
            /*Add id -> word to idToWords */
                if (!idToWords.containsKey(id)) {
                    HashSet<String> set = new HashSet<String>();
                    set.add(nouns[i]);
                    idToWords.put(id, set);
                } else {
                    HashSet<String> set = idToWords.get(id);
                    set.add(nouns[i]);
                    idToWords.put(id, set);
                }
            /*Add word -> id to wordToIDs*/
                //not in map
                if (!wordToIDs.containsKey(nouns[i])) {
                    HashSet<Integer> set = new HashSet<Integer>();
                    set.add(id);
                    wordToIDs.put(nouns[i], set);
                } else {
                    HashSet<Integer> set = wordToIDs.get(nouns[i]);
                    set.add(id);
                    wordToIDs.put(nouns[i], set);
                }
            }
        }
        //create Digraph
        graph = new Digraph(idToWords.size());
        while (hyponyms.hasNextLine()) {
            String [] hLine = hyponyms.readLine().split(",");
            int first = Integer.parseInt(hLine[0]);
            for (int i = 1; i < hLine.length; i += 1) {
                int rest = Integer.parseInt(hLine[i]);
                graph.addEdge(first, rest);
            }
        }
    }

    public Set<String> nouns() {
        return wordToIDs.keySet();
    }

    public boolean isNoun(String n) {
        return wordToIDs.containsKey(n);
    }

    public Set<String> hyponyms(String str) {
        Set<Integer> s = wordToIDs.get(str);
        Set<Integer> ids = GraphHelper.descendants(graph, s);
        HashSet<String> returnSet = new HashSet<String>();
        for (int i: ids) {
            Set<String> setOfWords = idToWords.get(i);
            for (String word : setOfWords) {
                returnSet.add(word);
            }
        }
        return returnSet;
    }
}
