package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

public class WordNet {
    private int[] synsetIDs;
    private String[] synsets;
    private TreeSet<String> nouns = new TreeSet<String>();
    private HashMap<String, ArrayList<Integer>> testMap = new HashMap<String, ArrayList<Integer>>();
    private Digraph digraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetInput = new In(synsetFilename);
        In hyponymInput = new In(hyponymFilename);

        String inputString = synsetInput.readAll();
        synsetIDs = new int[inputString.split("\n").length];
        synsets = new String[inputString.split("\n").length];
        for (int i = 0; i < synsetIDs.length; i++) {
            String line = inputString.split("\n")[i];
            synsetIDs[i] = Integer.parseInt(line.split(",")[0]);
            synsets[i] = line.split(",")[1];
            for (String noun : line.split(",")[1].split(" ")) {
                nouns.add(noun);
                ArrayList<Integer> al = new ArrayList<Integer>(5);
                if (testMap.keySet().contains(noun)) {
                    al = testMap.get(noun);
                    al.add(al.size() - 1, i);
                } else {
                    al.add(0, i);
                }
                testMap.put(noun, al);
            }
        }

        digraph = new Digraph(synsetIDs.length);
        inputString = hyponymInput.readAll();
        for (int i = 0; i < inputString.split("\n").length; i++) {
            String line = inputString.split("\n")[i];
            int from = Integer.parseInt(line.split(",")[0]);
            for (int j = 1; j < line.split(",").length; j++) {
                int to = Integer.parseInt(line.split(",")[j]);
                digraph.addEdge(from, to);
            }
        }
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        TreeSet<String> hyponyms = new TreeSet<String>();
        Set<Integer> someSet = new TreeSet<Integer>();
        for (String synset : testMap.keySet()) {
            String[] synsetArray = synset.split(" ");

            if (Arrays.asList(synsetArray).contains(word)) {
                ArrayList<Integer> ids = testMap.get(synset);
                for (int i = 0; i < ids.size(); i++) {
                    someSet.add(ids.get(i));
                }
            }
        }

        Set<Integer> resultSet = GraphHelper.descendants(digraph, someSet);
        for (int i : resultSet) {
            for (String noun : synsets[i].split(" ")) {
                hyponyms.add(noun);
            }
        }
        return hyponyms;
    }
}
