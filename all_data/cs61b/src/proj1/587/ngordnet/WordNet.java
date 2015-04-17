package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class WordNet {

    private List<Set<String>> synsets;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {

        synsets = new ArrayList<Set<String>>();

        In synIn = new In(synsetFilename);
        while (synIn.hasNextLine()) {
            String[] nextSynset = synIn.readLine().split(",")[1].split(" ");
            Set<String> synset = new TreeSet<String>();
            for (String word : nextSynset) {
                synset.add(word);
            }
            synsets.add(synset);
        }

        hyponyms = new Digraph(synsets.size());

        In hypIn = new In(hyponymFilename);
        while (hypIn.hasNextLine()) { //cycle through all the hyponym relationships
            String[] nextHyponyms = hypIn.readLine().split(",");
            int index = Integer.parseInt(nextHyponyms[0]);
            for (int i = 1; i < nextHyponyms.length; i += 1) {
                hyponyms.addEdge(index, Integer.parseInt(nextHyponyms[i]));
            }
        }
    }

    public boolean isNoun(String noun) {
        for (Set<String> synset : synsets) {
            if (synset.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nouns = new TreeSet<String>();
        for (Set<String> synset : synsets) {
            nouns.addAll(synset);
        }
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> indicies = new TreeSet<Integer>();

        for (int i = 0; i < synsets.size(); i += 1) {
            if (synsets.get(i).contains(word)) {
                indicies.add(i);
            }
        }

        Set<String> words = new TreeSet<String>();

        Iterator<Integer> iter = GraphHelper.descendants(hyponyms, indicies).iterator();
        while (iter.hasNext()) {
            words.addAll(synsets.get(iter.next()));
        }

        return words;
    }

}
