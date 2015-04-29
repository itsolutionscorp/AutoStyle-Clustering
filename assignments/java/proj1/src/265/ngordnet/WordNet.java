package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.util.List;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private List<Set<String>> synsets;
    private Digraph hyponyms;
    private Set<String> words;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new ArrayList<Set<String>>();
        words = new TreeSet<String>();
        In synReader = new In(synsetFilename);
        while (synReader.hasNextLine()) {
            String line = synReader.readLine();
            String[] items = line.split(",");
            Set<String> temp = new TreeSet<String>();
            for (String word : items[1].split(" ")) {
                temp.add(word);
                words.add(word);
            }
            synsets.add(temp);

        }
        hyponyms = new Digraph(synsets.size());
        In hypReader = new In(hyponymFilename);
        while (hypReader.hasNextLine()) {
            String line = hypReader.readLine();
            String[] items = line.split(",");
            int root = Integer.parseInt(items[0]);
            for (int i = 1; i < items.length; i += 1) {
                int leaf = Integer.parseInt(items[i]);
                hyponyms.addEdge(root, leaf);
            }
        }
    }

    public boolean isNoun(String noun) {
        if (words.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return words;
    }

    public Set<String> hyponyms(String noun) {
        Set<Integer> index = new TreeSet<Integer>();
        for (int i = 0; i < synsets.size(); i += 1) {
            if (synsets.get(i).contains(noun)) {
                index.add(i);
            }
        }
        Set<Integer> leaves = GraphHelper.descendants(hyponyms, index);
        Set<String> output = new TreeSet<String>();
        for (Integer x: leaves) {
            output.addAll(synsets.get(x));
        }
        return output;
    }
}
