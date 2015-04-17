package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.io.FileNotFoundException;

public class WordNet {
    private HashMap<Integer, HashSet<String>> synsets = new HashMap<Integer, HashSet<String>>();
    private Digraph g;
    /** create two data structures:
      * 1. Map<ID, Set(Word(s))>? implements ID and synsets
      * 2. Digraph implements relation
      */
    public WordNet(String synsetFilename, String hyponymFilename) {
    /** create a digraph
      * synsets are represented by IDs
      */
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);

        try {
            if ((!synsetFile.exists()) || (!hyponymFile.exists())) {
                throw new FileNotFoundException();
            } else {
                while (synsetFile.hasNextLine()) {
                    HashSet<String> wordsinset = new HashSet<String>();
                    String line = synsetFile.readLine();
                    String[] rawTokens = line.split(",");
                    int id = Integer.parseInt(rawTokens[0]);
                    String[] wordsinarr = rawTokens[1].split(" ");
                    for (int i = 0; i < wordsinarr.length; i++) {
                        wordsinset.add(wordsinarr[i]);
                    }
                    synsets.put(id, wordsinset);
                }

                g = new Digraph(synsets.size());
                while (hyponymFile.hasNextLine()) {
                    String line1 = hyponymFile.readLine();
                    String[] rawTokens1 = line1.split(",");
                    int hyper = Integer.parseInt(rawTokens1[0]);
                    for (int j = 1; j < rawTokens1.length; j++) {
                        g.addEdge(hyper, Integer.parseInt(rawTokens1[j]));
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
    }

    public Set<String> hyponyms(String word) {
    /** use graphhelper to get the descendants
      * find out the IDs associated with this word
      * call graphhelper on the set of these IDs and the digraph
        to get the set of descendants
      */
        Set<Integer> numset = new HashSet<Integer>();
        Set<String> hyposet = new HashSet<String>();

        for (int i = 0; i < synsets.size(); i++) {
            if (synsets.get(i).contains(word)) {
                numset.add(i);
            }
        }
        Set<Integer> desset = GraphHelper.descendants(g, numset);
        for (int j : desset) {
            for (String item : synsets.get(j)) {
                hyposet.add(item);
            }
        }
        return hyposet;
    }

    public boolean isNoun(String noun) {
        return this.nouns().contains(noun);
    }

    public Set<String> nouns() {
    // return an iterable
        Set<String> rset = new HashSet<String>();
        for (int i = 0; i < synsets.size(); i++) {
            HashSet<String> items = synsets.get(i);
            for (String item : items) {
                rset.add(item);
            }
        }
        return rset;
    }
}
