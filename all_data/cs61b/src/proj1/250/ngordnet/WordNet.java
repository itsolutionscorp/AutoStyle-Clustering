package ngordnet;

import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private TreeMap<Integer, TreeSet<String>> synsetMap = new TreeMap<Integer, TreeSet<String>>();
    private Digraph d;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);

        int synsetCount = 0;
        while (synsetIn.hasNextLine()) {
            String[] synset = synsetIn.readLine().split(",");
            int synsetID = Integer.parseInt(synset[0]);
            TreeSet<String> words = getWords(synset[1]);
            synsetMap.put(synsetID, words);
            synsetCount++;
        }

        d = new Digraph(synsetCount);
        while (hyponymIn.hasNextLine()) {
            String[] hyponym = hyponymIn.readLine().split(",");
            int hyponymSynsetID = Integer.parseInt(hyponym[0]);
            for (int i = 1; i < hyponym.length; i++) {
                d.addEdge(hyponymSynsetID, Integer.parseInt(hyponym[i]));
            }
        }
    }

    public Set<String> hyponyms(String word) {
        /* Returns all of a particular word's hyponyms. */
        TreeSet<String> h = new TreeSet<String>();
        TreeSet<Integer> w = new TreeSet<Integer>();
        for (Integer i : synsetMap.keySet()) {
            if (synsetMap.get(i).contains(word)) {
                w.add(i);
            }
        }
        TreeSet<Integer> synsetIDs = (TreeSet<Integer>) (GraphHelper.descendants(d, w));
        for (Integer i : synsetIDs) {
            h.addAll(synsetMap.get(i));
        }
        return h;
    }

    public boolean isNoun(String noun) {
        for (Integer i : synsetMap.keySet()) {
            if (synsetMap.get(i).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        /* Returns all of the words in the WordNet. */
        TreeSet<String> n = new TreeSet<String>();
        for (Integer i : synsetMap.keySet()) {
            n.addAll(synsetMap.get(i));
        }
        return n;
    }

    private TreeSet<String> getWords(String synset) {
        String[] rawWords = synset.split(" ");
        TreeSet<String> words = new TreeSet<String>();
        for (int i = 0; i < rawWords.length; i++) {
            words.add(rawWords[i]);
        }
        return words;
    }
}
