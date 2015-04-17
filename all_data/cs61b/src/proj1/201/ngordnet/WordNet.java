package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;


public class WordNet extends java.lang.Object {
    private HashMap<String, Integer[]> synMap;
    private HashMap<Integer, String[]> numMap;
    private Digraph graph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synMap = new HashMap<String, Integer[]>();
        numMap = new HashMap<Integer, String[]>();
        In synsetRead = new In(synsetFilename);
        In hyponymRead = new In(hyponymFilename);
        String readBuffer = null;
        while (synsetRead.hasNextLine()) {
            readBuffer = synsetRead.readLine();
            String[] tokens = readBuffer.split(",");
            String[] wordProcessor = tokens[1].split(" ");
            int val = Integer.parseInt(tokens[0]);
            numMap.put(val, wordProcessor);
            for (int i = 0; i < wordProcessor.length; i++) {
                if (synMap.containsKey(wordProcessor[i])) {
                    Integer[] oldVal = synMap.get(wordProcessor[i]);
                    Integer[] newVal = new Integer[oldVal.length + 1];
                    java.lang.System.arraycopy(oldVal, 0, newVal, 0, oldVal.length);
                    newVal[oldVal.length] = val;
                    synMap.put(wordProcessor[i], newVal);
                } else {
                    Integer[] newVal = {val};
                    synMap.put(wordProcessor[i], newVal);
                }
            }
        }
        graph = new Digraph(synMap.size());
        while (hyponymRead.hasNextLine()) {
            readBuffer = hyponymRead.readLine();
            String[] tokens = readBuffer.split(",");
            int vertex0 = Integer.parseInt(tokens[0]);
            int vertex1 = Integer.parseInt(tokens[1]);
            graph.addEdge(vertex0, vertex1);
        }
    }

    public Set<String> hyponyms(String word) {
        HashSet<Integer> val = new HashSet<Integer>();
        val.addAll(Arrays.asList(synMap.get(word)));
        HashSet<Integer> descendants = new HashSet<Integer>();
        descendants.addAll(GraphHelper.descendants(graph, val));
        Iterator<Integer> descendantIter = descendants.iterator();
        HashSet<String> hyponyms = new HashSet<String>();
        while (descendantIter.hasNext()) {
            hyponyms.addAll(Arrays.asList(numMap.get(descendantIter.next())));
        }
        return hyponyms;
    }

    public boolean isNoun(String noun) {
        return synMap.containsKey(noun);
    }

    public Set<String> nouns() {
        return synMap.keySet();
    }
}
