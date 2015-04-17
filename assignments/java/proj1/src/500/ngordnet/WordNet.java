package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {

    private String synsetFileName;
    private String hyponymFileName;
    private HashMap<Integer, String[]> synsets;
    private Digraph hyponymGraph;

    public WordNet(String s, String h) {
        synsetFileName = s;
        hyponymFileName = h;

        synsets = new HashMap<Integer, String[]>();
        In in = new In(synsetFileName);
        String line;
        String[] tokens;
        String[] words;

        //fill in the synset map with all the ids, synsets
        while (!in.isEmpty()) {
            line = in.readLine();
            tokens = line.split(",");
            words = tokens[1].split(" ");
            synsets.put(Integer.parseInt(tokens[0]), words);
        }

        hyponymGraph = new Digraph(synsets.size());
        In i = new In(hyponymFileName);
        int j = 1;

        //fill in the Digraph with hyponym relations
        while (!i.isEmpty()) {
            j = 1;
            line = i.readLine();
            tokens = line.split(",");
            while (j < tokens.length) {
                hyponymGraph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[j]));
                j += 1;
            }
        }
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        Set<Integer> keys = synsets.keySet();
        HashSet<String> nouns = new HashSet<String>();
        String[] nounList;
        for (Integer key: keys) {
            int i = 0;
            nounList = synsets.get(key);
            while (i < nounList.length) {
                nouns.add(nounList[i]);
                i += 1;
            }
        }
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> hyponymSet = new HashSet<String>();
        Set<Integer> indices = getKeys(word);
        Set<Integer> hyponymVertices = GraphHelper.descendants(hyponymGraph, indices);
        for (int vertex: hyponymVertices) {
            addSynsetMembers(vertex, hyponymSet);
        }
        for (int index: indices) {
            addSynsetMembers(index, hyponymSet);
        }
        return hyponymSet;
    }

    private void addSynsetMembers(int key, HashSet<String> base) {
        String[] members = synsets.get(key);
        int i = 0;
        while (i < members.length) {
            base.add(members[i]);
            i += 1;
        }
    }

    private Set<Integer> getKeys(String word) {
        Set<Integer> keys = synsets.keySet();
        HashSet<Integer> ancestors = new HashSet<Integer>();
        String[] nounList;
        for (Integer key: keys) {
            int i = 0;
            nounList = synsets.get(key);
            while (i < nounList.length) {
                if (word.equals(nounList[i])) {
                    ancestors.add(key);
                } 
                i += 1;
            }
        }
        return ancestors;
    }


}
