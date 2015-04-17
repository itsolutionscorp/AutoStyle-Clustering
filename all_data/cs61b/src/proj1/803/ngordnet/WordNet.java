package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;

public class WordNet {

    private Map<Integer, Set<String>> synsets;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        String[] synsetLines = synsetIn.readAllLines();
        synsets = new TreeMap<Integer, Set<String>>();
        for (String line : synsetLines) {
            int firstDelimiter = line.indexOf(",");
            int id = Integer.parseInt(line.substring(0, firstDelimiter));
            Set<String> wordSet = new TreeSet<String>();
            int secondDelimiter = line.indexOf(",", firstDelimiter + 1);
            String wordLine = line.substring(firstDelimiter + 1, secondDelimiter);
            In wordIn = new In(new Scanner(wordLine));
            String[] words = wordIn.readAllStrings();
            for (String word : words) {
                wordSet.add(word);
            }
            synsets.put(id, wordSet);
        }

        In hyponymIn = new In(hyponymFilename);
        String[] hyponymLines = hyponymIn.readAllLines();
        hyponyms = new Digraph(synsetLines.length);
        for (String line : hyponymLines) {
            int firstDelimiter = line.indexOf(",");
            int from = Integer.parseInt(line.substring(0, firstDelimiter));
            String otherVerticesLine = line.substring(firstDelimiter);
            otherVerticesLine = otherVerticesLine.replace(',', ' ');
            In vertexIn = new In(new Scanner(otherVerticesLine));
            int[] otherVertices = vertexIn.readAllInts();
            for (int i : otherVertices) {
                hyponyms.addEdge(from, i);
            }

        }
    }

    public boolean isNoun(String noun) {
        Set<Integer> keys = synsets.keySet();
        for (Integer i : keys) {
            if (synsets.get(i).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nouns = new TreeSet<String>();
        Set<Integer> keys = synsets.keySet();
        for (Integer i : keys) {
            Set<String> words = synsets.get(i);
            for (String w : words) {
                nouns.add(w);
            }

        }
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        //error check that word is a noun
        if (!isNoun(word)) {
            return null;
        }
        Set<String> h = new TreeSet<String>();
        //first lets find where the word exists in synsets map
        Set<Integer> indices = new TreeSet<Integer>();
        Set<Integer> keys = synsets.keySet();
        for (Integer i : keys) {
            if (synsets.get(i).contains(word)) {
                indices.add(i);
            }
        }
        //add word, synonyms
        for (Integer i : indices) {
            Set<String> synonyms = synsets.get(i);
            for (String s : synonyms) {
                h.add(s);
            }
        }
        //add edges and descendent stuff
        Set<Integer> descendants = GraphHelper.descendants(hyponyms, indices);
        for (Integer i : descendants) {
            Set<String> words = synsets.get(i);
            for (String w : words) {
                h.add(w);
            }
        }
        return h;
    }
}
