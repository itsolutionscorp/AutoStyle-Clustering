package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class WordNet {

    private HashMap<String, HashSet<Integer>> stringToId;
    private HashMap<Integer, String[]> idToSynset;
    private HashMap<String, HashSet<String>> wordToHyponyms;
    private Digraph graph;

    public WordNet(String file1, String file2) {
        In synsets;
        In hyponyms;
        String line;
        int vertices;
        int id;
        String[] streamArr;
        String[] hypoStream;
        String[] synsetStream;
        synsets = new In(file1);
        hyponyms = new In(file2);
        streamArr = synsets.readAllLines();
        vertices = streamArr.length;
        graph = new Digraph(vertices);
        hypoStream = hyponyms.readAllLines();
        stringToId = new HashMap<String, HashSet<Integer>>();
        idToSynset = new HashMap<Integer, String[]>();
        for (int i = 0; i < streamArr.length; i++) {
            id = Integer.parseInt(streamArr[i].split(",")[0]);
            String[] synsetArr = streamArr[i].split(",")[1].split(" ");
            idToSynset.put(id, synsetArr);
            for (int j = 0; j < synsetArr.length; j++) {
                String word = synsetArr[j];
                if (stringToId != null && stringToId.containsKey(word)) {
                    stringToId.get(word).add(id);
                } else {
                    HashSet<Integer> tempSet = new HashSet<Integer>();
                    tempSet.add(id);
                    stringToId.put(word, tempSet);
                }
            }
        }
        for (int i = 0; i < hypoStream.length; i++) {
            int firstId = Integer.parseInt(hypoStream[i].split(",")[0]);
            Integer[] hypIds = new Integer[hypoStream[i].split(",").length - 1];
            for (int j = 1; j < hypoStream[i].split(",").length; j++) {
                graph.addEdge(firstId, Integer.parseInt(hypoStream[i].split(",")[j]));
            }
        }

    }

    public boolean isNoun(String noun) { 
        if (stringToId == null) {
            return false;
        }
        return stringToId.containsKey(noun);
    }

    public Set<String> nouns() {
        if (stringToId == null) {
            return null;
        }
        return stringToId.keySet();
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> hypoSet = new HashSet<String>();
        HashSet<Integer> ids = stringToId.get(word);
        Set<Integer> idsOfHypo = GraphHelper.descendants(graph, ids);
        for (int id: idsOfHypo) {
            String[] synsetArr2 = idToSynset.get(id);
            for (String hyponym : synsetArr2) {
                hypoSet.add(hyponym);
            }
        }
        return hypoSet;
    }

}
