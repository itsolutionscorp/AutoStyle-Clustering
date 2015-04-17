package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.Arrays;

public class WordNet {

    private Map<Integer, String[]> synset; 
    private Digraph digraph;
    private Set<String> wordSet;


    public WordNet(String synsetFilename, String hypernymFilename) {
        wordSet = new TreeSet<String>();
        synset = new TreeMap<Integer, String[]>();
        In synsetTxt = new In(synsetFilename);
        while (synsetTxt.hasNextLine()) {
            String file = synsetTxt.readLine();
            String[] splitted = file.split(",");
            int index = Integer.parseInt(splitted[0]);
            String[] splittedWords = splitted[1].split(" ");
            synset.put(index, splittedWords);
            for (String s: splittedWords) {
                wordSet.add(s);
            }
        }
        synsetTxt.close();

        digraph = new Digraph(synset.size());
        In hyponymTxt = new In(hypernymFilename);
        while (hyponymTxt.hasNextLine()) {
            String file = hyponymTxt.readLine();
            String[] splitted = file.split(",");
            int id = Integer.parseInt(splitted[0]);
            for (int i = 1; i < splitted.length; i++) {
                digraph.addEdge(id, Integer.parseInt(splitted[i]));
            }
        }
        hyponymTxt.close();
    }

    public boolean isNoun(String noun) {
        return wordSet.contains(noun);
    }

    public Set<String> nouns() {
        return wordSet;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIDs = new TreeSet<Integer>();
        Set<String> res = new TreeSet<String>();
        for (Map.Entry<Integer, String[]> t : synset.entrySet()) {
            if (Arrays.asList(t.getValue()).contains(word)) {
                synsetIDs.add(t.getKey());
                res.addAll(Arrays.asList(t.getValue()));
            }
        }
        synsetIDs = GraphHelper.descendants(digraph, synsetIDs);
        for (int id : synsetIDs) {
            res.addAll(Arrays.asList(synset.get(id)));
        }
        return res;
    }
}
