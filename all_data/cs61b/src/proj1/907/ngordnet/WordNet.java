package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph graph;
    private TreeMap<String, TreeSet<Integer>> vertices;
    private TreeMap<Integer, Set<String>> invVertices;
    private TreeSet<Integer> hyperID;

    public WordNet(String synsetFilename, String hyponymFilename) {
        vertices = new TreeMap<String, TreeSet<Integer>>();
        invVertices = new TreeMap<Integer, Set<String>>();
        In synFile = new In(synsetFilename);
        while (synFile.hasNextLine()) {
            String[] line = synFile.readLine().split(",");
            int synID = Integer.parseInt(line[0]);
            String[] synsets = line[1].split(" ");
            TreeSet<String> stringSet = new TreeSet<String>();
            stringSet.add(synsets[0]);
            invVertices.put(synID, stringSet);
            for (String i : synsets) {
                invVertices.get(synID).add(i);
                TreeSet<Integer> wordID = new TreeSet<Integer>();
                wordID.add(synID);
                if (vertices.containsKey(i)) {
                    TreeSet<Integer> prevID = vertices.get(i);
                    for (int each : prevID) {
                        wordID.add(each);
                    }
                }
                vertices.put(i, wordID);
            }
        }

        graph = new Digraph(vertices.size());
        In hypFile = new In(hyponymFilename);
        while (hypFile.hasNextLine()) {
            String[] numLine = hypFile.readLine().split(",");
            int hyper = Integer.parseInt(numLine[0]);
            for (int j = 1; j < numLine.length; j++) {
                graph.addEdge(hyper, Integer.parseInt(numLine[j]));
            }
        }
    }

    public boolean isNoun(String noun) {
        return vertices.containsKey(noun);
    }

    public Set<String> nouns() {
        return vertices.keySet();
    }

    public Set<String> hyponyms(String word) {
        if (!vertices.containsKey(word)) {
            throw new NullPointerException();
        }
        hyperID = new TreeSet<Integer>();
        for (int each : vertices.get(word)) {
            hyperID.add(each);
        }
        Set<String> results = new TreeSet<String>();
        int currentSize = -1;
        while (GraphHelper.descendants(graph, hyperID).size() == currentSize) {
            currentSize = GraphHelper.descendants(graph, hyperID).size();
            for (int every : GraphHelper.descendants(graph, hyperID)) {
                hyperID.add(every);
            }
        }

        for (int k : GraphHelper.descendants(graph, hyperID)) {
            for (String s : invVertices.get(k)) {
                results.add(s);
            }
        }
        return results;
    }
}
