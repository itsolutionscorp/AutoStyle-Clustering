package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {

    private HashMap<Integer, HashSet<String>> synsets;
    private Digraph hyponyms;
    private int count;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetStream = new In(synsetFilename);
        synsets = new HashMap<Integer, HashSet<String>>();
        while (!synsetStream.isEmpty()) {
            String[] line = synsetStream.readLine().split(",");
            HashSet<String> synset = new HashSet<String>();
            for (String e : line[1].split(" ")) {
                synset.add(e);
            }
            synsets.put(Integer.parseInt(line[0]), synset);
            count += 1;
        }
        
        In hyponymStream = new In(hyponymFilename);
        hyponyms = new Digraph(count);
        while (!hyponymStream.isEmpty()) {
            String[] line = hyponymStream.readLine().split(",");
            for (int i = 1; i < line.length; i++) {
                hyponyms.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
            }
        }
    }


    public boolean isNoun(String word) {
        for (int i = 0; i < synsets.size(); i++) {
            if (synsets.get(i).contains(word)) {
                return true;
            }
        }
        return false;
    }

    //ADT includes iterator that works on strings
    public Set<String> hyponyms(String word) {
        Set<Integer> ids = new HashSet<Integer>();
        for (int i = 0; i < synsets.size(); i++) {
            if (synsets.get(i).contains(word)) {
                ids.add(i);
            }
        }

        Set<Integer> hyponymIds = GraphHelper.descendants(hyponyms, ids);
        HashSet<String> output = new HashSet<String>();
        for (Integer e : hyponymIds) {
            output.addAll(synsets.get(e));
        }
        return output;
    }


    public Set<String> nouns() {
        HashSet<String> output = new HashSet<String>();
        for (int i = 0; i < synsets.size(); i++) {
            output.addAll(synsets.get(i));
        }
        return output;
    }

}
