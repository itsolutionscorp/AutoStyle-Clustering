package ngordnet;

//Major data sttuctures.
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;

// For reading the files.
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<Integer, String[]> nouns;
    private Digraph hyponymGraph;
    private HashMap<String, ArrayList<Integer>> wordToHyponym;

    private void parseNounData(String nounFile) {
        String[] nounData = readLines(nounFile);
        for (String s : nounData) {
            String[] parts = s.split(",");
            String first = parts[0];
            String second = parts[1];
            //Parse nouns, they may be separated by spaces.
            String[] secondTokens = second.split(" ");
            nouns.put(Integer.parseInt(first), secondTokens);
            for (String tok : secondTokens) {
                ArrayList<Integer> results = new ArrayList<Integer>();
                if (wordToHyponym.containsKey(tok)) {
                    results.addAll(wordToHyponym.get(tok));
                }
                results.add(Integer.parseInt(first));
                wordToHyponym.put(tok, results);
            }
        }
    }

    private void parseHyponymData(String hyponymFile) {
        String[] hyponymData = readLines(hyponymFile);
        for (String s : hyponymData) {
            String[] parts = s.split(",");
            Integer parent = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                hyponymGraph.addEdge(parent, Integer.parseInt(parts[i]));
            }
        }
    }

    private int countLines(String path) {
        In file = new In(path);
        String[] lines = file.readAllLines();
        return lines.length;
    }

    private String[] readLines(String path) {
        In file = new In(path);
        return file.readAllLines();
    }

    public WordNet(String nounsPath, String hyponyms) {
        nouns = new HashMap<Integer, String[]>();
        hyponymGraph = new Digraph(countLines(nounsPath));
        wordToHyponym = new HashMap<String, ArrayList<Integer>>();

        parseNounData(nounsPath);
        parseHyponymData(hyponyms);
    }

    public boolean isNoun(String word) {
        for (String[] str : nouns.values()) {
            for (String s : str) {
                if (s.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (String[] s : nouns.values()) {
            allNouns.addAll(Arrays.asList(s));
        }
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> id = new TreeSet<Integer>(); 
        id.addAll(wordToHyponym.get(word));
        Set<String> theWords = new TreeSet<String>();
        for (Integer i : GraphHelper.descendants(hyponymGraph, id)) {
            theWords.addAll(Arrays.asList(nouns.get(i)));
        }
        return theWords;
    }
}
