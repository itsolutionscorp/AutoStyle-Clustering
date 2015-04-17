package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<String, TreeSet<Integer>> nounID = new HashMap<String, TreeSet<Integer>>();
    private HashMap<Integer, TreeSet<String>> idNoun = new HashMap<Integer, TreeSet<String>>();
    private Digraph graph;

    public WordNet(String synsets, String hypernyms) {
        In inSyn = new In(synsets); 
        In inHyp = new In(hypernyms); 
        String line;
        int count = 0; 
        while ((line = inSyn.readLine()) != null) {
            String[] lineArray = line.split(",");
            int id = Integer.parseInt(lineArray[0]);
            String[] synset = lineArray[1].split(" ");
            count = count + 1;
            TreeSet<String> nouns = new TreeSet<String>();
            for (String noun: synset) {
                TreeSet<Integer> ids = nounID.get(noun);
                nouns.add(noun);
                if (ids == null) {
                    TreeSet<Integer> idsForNoun = new TreeSet<Integer>();
                    idsForNoun.add(id);
                    nounID.put(noun, idsForNoun);
                } else {
                    ids.add(id);
                }
            }
            idNoun.put(id, nouns);
        }
        graph = new Digraph(count);
        while ((line = inHyp.readLine()) != null) {
            String[] lineArray = line.split(",");
            Integer[] hnyms = new Integer[lineArray.length];
            for (int i = 0; i < lineArray.length; i++) {
                hnyms[i] = Integer.parseInt(lineArray[i]);
                if (i != 0) {
                    graph.addEdge(hnyms[0], hnyms[i]);
                }
            }
        }
    }

    public Set<String> nouns() {
        return nounID.keySet();
    }

    public Set<String> hyponyms(String word) {
        TreeSet<String> hyponymsOfWord = new TreeSet<String>();
        if (nounID.containsKey(word)) {
            TreeSet<Integer> ids = nounID.get(word);
            Set<Integer> hypoIdSet = GraphHelper.descendants(graph, ids);
            Integer[] hypoIds = hypoIdSet.toArray(new Integer[hypoIdSet.size()]);
            for (Integer id: hypoIds) {
                hyponymsOfWord.addAll(idNoun.get(id));
            }
        }
        return hyponymsOfWord;
    }

    public boolean isNoun(String word) {
        return nounID.containsKey(word);
    }
}
