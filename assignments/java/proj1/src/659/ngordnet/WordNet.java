package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<Integer, Set<String>> idToWord;
    // private HashMap<String, Set<Integer>> idOfWord;
    private Digraph idToHyponyms;
    private Set<String> nouns;

    public WordNet(String synsetFilename, String hyponymFilename) {
        idToWord = new HashMap<Integer, Set<String>>();
        // idToHyponyms = new HashMap<Integer, Set<String>>();
        // idOfWord = new HashMap<String, Set<Integer>>();
        In synReader = new In(synsetFilename);
        In hypoReader = new In(hyponymFilename);
        String synLine, hypoLine;
        String[] items, noun;
        int id;
        Set<String> hyponyms = new HashSet<String>();
        Set<String> words;
        while (synReader.hasNextLine()) {
            words = new HashSet<String>();
            synLine = synReader.readLine();
            items = synLine.split(",");
            id = Integer.parseInt(items[0]);
            noun = items[1].split(" ");
            for (int i = 0; i < noun.length; i += 1) {
                words.add(noun[i]);
            }
            // definition = items[2];
            idToWord.put(id, words);
        }
        this.nouns = new HashSet<String>();
        for (Set<String> n : idToWord.values()) {
            this.nouns.addAll(n);

        }
        String[] ids;
        int hypernymID;
        idToHyponyms = new Digraph(this.nouns.size());
        while (hypoReader.hasNextLine()) {
            hyponyms = new HashSet<String>();
            hypoLine = hypoReader.readLine();
            ids = hypoLine.split(",");
            hypernymID = Integer.parseInt(ids[0]);
            for (int i = 0; i < ids.length; i++) {
                idToHyponyms.addEdge(hypernymID, Integer.parseInt(ids[i]));
            }
        }

    }

    public Set<String> hyponyms(String word) {
        Set<Integer> ids = new HashSet<Integer>();
        Set<Integer> hyponymIDs = new HashSet<Integer>();
        Set<String> hyponyms = new HashSet<String>();
        for (int id : idToWord.keySet()) {
            if (idToWord.get(id).contains(word)) {
                ids.add(id);
            }
        }

        hyponymIDs.addAll(GraphHelper.descendants(idToHyponyms, ids));

        for (int id : hyponymIDs) {
            hyponyms.addAll(idToWord.get(id));
        }

        return hyponyms;

    }

    public boolean isNoun(String noun) {
        return this.nouns().contains(noun);
    }

    public Set<String> nouns() {
        return this.nouns;
    }
}
