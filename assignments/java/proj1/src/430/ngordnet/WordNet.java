package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph wnGraph;
    private Map<Integer, Set<String>> idToNouns = new HashMap<Integer, Set<String>>();
    private Map<String, Set<Integer>> nounToIds = new HashMap<String, Set<Integer>>();

    /**
     * Constructor
     * 
     * @param synsetFilename
     * @param hyponameFilename
     */

    public WordNet(String synsetFilename, String hyponameFilename) {
        // initialize wnGraph
        In input = new In(synsetFilename);
        int numberOfVs = (input.readAllLines()).length;
        input.close();
        wnGraph = new Digraph(numberOfVs);

        loadITN(synsetFilename);
        loadNTI();
        loadGraph(hyponameFilename);

    }

    /**
     * Loads the idToNouns Map
     * 
     * @param synsetFilename
     */
    private void loadITN(String synsetFilename) {

        In input = new In(synsetFilename);
        while (input.hasNextLine()) {
            String[] line = input.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            String[] nouns = line[1].split(" ");
            HashSet<String> nounsSet = new HashSet<String>();
            int i = 0;
            while (i < nouns.length) {
                nounsSet.add(nouns[i]);
                i += 1;
            }

            idToNouns.put(id, nounsSet);

        }
    }

    /**
     * Loads nounToIds Map by reading through idToNouns -- if same nouns with
     * different ids found, it will add it to a set associated with that id
     */
    private void loadNTI() {

        Set<Integer> ids = idToNouns.keySet();
        for (int i : ids) {
            for (String noun : idToNouns.get(i)) {
                if (nounToIds.containsKey(noun)) {
                    nounToIds.get(noun).add(i);
                } else {
                    HashSet<Integer> idSet = new HashSet<Integer>();
                    idSet.add(i);
                    nounToIds.put(noun, idSet);
                }
            }
        }

    }

    /**
     * Loads DiGhraph wnGraph
     * 
     * @param hyponameFilename
     */
    private void loadGraph(String hyponameFilename) {
        In input = new In(hyponameFilename);
        while (input.hasNextLine()) {
            String[] line = input.readLine().split(",");
            int hypernym = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i += 1) {
                wnGraph.addEdge(hypernym, Integer.parseInt(line[i]));
            }

        }

    }

    public boolean isNoun(String noun) {

        return nounToIds.containsKey(noun);

    }

    public Set<String> nouns() {

        return nounToIds.keySet();

    }

    public Set<String> hyponyms(String word) {
        Set<String> retSet = new HashSet<String>();
        if (isNoun(word)) {
            Set<Integer> descends = GraphHelper.descendants(wnGraph,
                    nounToIds.get(word));
            for (int i : descends) {
                retSet.addAll(idToNouns.get(i));
            }

            return retSet;
        } else {
            return null;
        }
    }
}
