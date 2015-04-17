package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Digraph graph;
    private Map<Integer, String> idToSynset;
    private Map<String, Set<Integer>> wordToIds;

    public WordNet(String synsetsTextFile, String hyponymsTextFile) {

        idToSynset = new TreeMap<Integer, String>();
        wordToIds = new TreeMap<String, Set<Integer>>();

        In input = new In(synsetsTextFile);
        int numOfVertices = 0;

        while (input.hasNextLine()) {
            String s = input.readLine();
            String[] line = s.split(",");

            int address = Integer.parseInt(line[0]);
            
            String[] synString = line[1].split(" ");
            Set<Integer> ids = new TreeSet<Integer>();
            idToSynset.put(address, line[1]);

            for (String word : synString) {
                if (isNoun(word)) {
                    ids = wordToIds.get(word);
                    ids.add(address);
                    wordToIds.put(word, ids);
                } else {
                    ids.add(address);
                    wordToIds.put(word, ids);
                }
                ids = new TreeSet<Integer>();
            }

            numOfVertices++;
        }

        input = new In(hyponymsTextFile);
        graph = new Digraph(numOfVertices);

        while (input.hasNextLine()) {
            String line = input.readLine();
            String[] stringNums = line.split(",");
            int vertex = Integer.parseInt(stringNums[0]);

            for (int i = 1; i < stringNums.length; i++) {
                int nextVertex = Integer.parseInt(stringNums[i]);
                graph.addEdge(vertex, nextVertex);
            }
        }
    }

    public boolean isNoun(String word) {
        return nouns().contains(word);
    }

    public Set<String> nouns() {
        return wordToIds.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> ids = wordToIds.get(word);
        Set<Integer> synsetIds = GraphHelper.descendants(graph, ids);
        Set<String> hyponyms = new TreeSet<String>();

        for (Integer id : synsetIds) {
            String[] words = idToSynset.get(id).split(" ");
            for (String wd : words) {
                hyponyms.add(wd);
            }
        }

        return hyponyms;
    }
}
