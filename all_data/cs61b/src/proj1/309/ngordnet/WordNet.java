package ngordnet;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
//import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, Set<String>> synsets;
    private Map<Integer, String> synsetsDes;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new TreeMap<Integer, Set<String>>(); // synset is Integer
                                                       // followed by word,
                                                       // then des.
        synsetsDes = new TreeMap<Integer, String>();

        In in = new In(synsetFilename);

        String line;
        String[] rawTokens;
        int iVert;
        Set<String> synset;
        String[] wordTokens;

        // reading synset file
        int numLines = 0;
        line = in.readLine();
        while (line != null) {

            rawTokens = line.split(",");
            iVert = Integer.parseInt(rawTokens[0]);

            wordTokens = rawTokens[1].split(" ");
            synset = new TreeSet<String>();
            Collections.addAll(synset, wordTokens);
            synsets.put(iVert, synset);

            synsetsDes.put(iVert, rawTokens[2]);

            line = in.readLine();
            numLines++;
        }

        in.close();

        // reading hyponym file
        in = new In(hyponymFilename);
        hyponyms = new Digraph(numLines);
        line = in.readLine();
        int iVert0;
        while (line != null) {

            rawTokens = line.split(",");
            iVert0 = Integer.parseInt(rawTokens[0]);

            for (int i = 1; i < rawTokens.length; i++) {
                hyponyms.addEdge(iVert0, Integer.parseInt(rawTokens[i]));
            }

            line = in.readLine();
        }

    }

    public boolean isNoun(String noun) {
        Set<Integer> keyset = synsets.keySet();
        Set<String> synset;
        for (Integer iVert : keyset) {
            synset = synsets.get(iVert);
            if (synset.contains(noun)) {
                return true;
            }
        }

        return false;
    }

    public Set<String> nouns() {
        Set<Integer> keyset = synsets.keySet();
        Set<String> allnouns = new TreeSet<String>();
        Set<String> synset;
        for (Integer iVert : keyset) {
            synset = synsets.get(iVert);
            allnouns.addAll(synset);
        }

        return allnouns;
    }

    public Set<String> hyponyms(String word) {
        Set<String> allHyponyms = new TreeSet<String>();

        Set<Integer> wordKeys = getVerts(word);

        // add synonyms
        Set<String> synset;
        for (Integer iVert : wordKeys) {
            synset = synsets.get(iVert);
            allHyponyms.addAll(synset);
        }

        // add hyponyms
        Set<Integer> decendantKeys = GraphHelper
                .descendants(hyponyms, wordKeys);
        for (Integer iVert : decendantKeys) {
            synset = synsets.get(iVert);
            allHyponyms.addAll(synset);
        }

        // allHyponyms.remove(word);

        return allHyponyms;
    }

    private Set<Integer> getVerts(String word) {
        Set<Integer> wordKeys = new TreeSet<Integer>();

        Set<Integer> keyset = synsets.keySet();
        Set<String> synset;
        for (Integer iVert : keyset) {
            synset = synsets.get(iVert);
            if (synset.contains(word)) {
                wordKeys.add(iVert);
            }
        }

        return wordKeys;
    }
}
