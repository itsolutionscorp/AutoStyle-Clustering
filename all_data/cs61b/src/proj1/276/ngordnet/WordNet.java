package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class WordNet {
    private In synsets;
    private In hyponyms;
    private Digraph graph;
    private int numSynsets;
    private TreeMap<Integer, String> synsetIndeces;

    public WordNet(String synsetList, String hyponymList) {
        synsets = new In(synsetList);
        hyponyms = new In(hyponymList);
        numSynsets = 0;
        synsetIndeces = new TreeMap<Integer, String>();

        while (synsets.hasNextLine()) {
            List<String> synsLine = Arrays.asList(synsets.readLine().split(","));
            synsetIndeces.put(numSynsets, synsLine.get(1));
            numSynsets += 1;
        }

        graph = new Digraph(numSynsets);

        while (hyponyms.hasNextLine()) {
            List<String> hypoLine = Arrays.asList(hyponyms.readLine().split(","));
            Integer target = Integer.parseInt(hypoLine.get(0));
            for (int i = 1; i < hypoLine.size(); i++) {
                graph.addEdge(target, Integer.parseInt(hypoLine.get(i)));
            }
        }
    }

    private List<String> synsetDecompose(String synset) {
        List<String> words = Arrays.asList(synset.split(" "));
        return words;
    }

    public boolean isNoun(String k) {
        if (nouns().contains(k)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        TreeSet<String> nounz = new TreeSet<String>();
        for (int i = 0; i < numSynsets; i++) {
            List<String> words = synsetDecompose(synsetIndeces.get(i));
            for (String word : words) {
                nounz.add(word);
            }
        }
        return nounz;
    }

    public Set<String> hyponyms(String s) {
        if (!nouns().contains(s)) {
            throw new IllegalArgumentException("graph doesn't contain word");
        }
        TreeSet<Integer> sNumbers = new TreeSet<Integer>();
        for (int i = 0; i < numSynsets; i++) {
            for (String word : synsetDecompose(synsetIndeces.get(i))) {
                if (word.equals(s)) {
                    sNumbers.add(i);
                }
            }
        }
        TreeSet<Integer> dIndeces = (TreeSet<Integer>) GraphHelper.descendants(graph, sNumbers);
        TreeSet<String> descendants = new TreeSet<String>();
        for (int index : dIndeces) {
            String synset = synsetIndeces.get(index);
            List<String> words = synsetDecompose(synset);
            for (String word : words) {
                descendants.add(word);
            }
        }
        return descendants;
    }

}
