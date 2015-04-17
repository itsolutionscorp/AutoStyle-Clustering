package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private HashMap<String, HashSet<Integer>> wordMap;
    private HashMap<Integer, ArrayList<String>> idMap;
    private Digraph wordGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        wordMap = new HashMap<String, HashSet<Integer>>();
        idMap = new HashMap<Integer, ArrayList<String>>();
        String[] lineArray, words;
        HashSet<Integer> lineList;
        ArrayList<String> stringList;
        In sysnetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        while (sysnetIn.hasNextLine()) {
            lineArray = sysnetIn.readLine().split(",");
            words = lineArray[1].split(" ");
            int id = Integer.parseInt(lineArray[0]);
            stringList = new ArrayList<String>();
            for (String word : words) {
                stringList.add(word);
                if (!wordMap.containsKey(word)) {
                    lineList = new HashSet<Integer>();
                    lineList.add(id);
                    wordMap.put(word, lineList);
                } else {
                    lineList = wordMap.get(word);
                    lineList.add(id);
                }
            }
            idMap.put(id, stringList);
        }
        wordGraph = new Digraph(idMap.size());
        while (hyponymIn.hasNextLine()) {
            lineArray = hyponymIn.readLine().split(",");
            int id = Integer.parseInt(lineArray[0]);
            for (int j = 1; j < lineArray.length; j++) {
                int hyponymId = Integer.parseInt(lineArray[j]);
                wordGraph.addEdge(id, hyponymId);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        if (!wordMap.containsKey(word)) {
            return null;
        } else {
            HashSet<String> wordSet = new HashSet<String>();
            for (Integer id : GraphHelper.descendants(wordGraph, wordMap
                    .get(word))) {
                ArrayList<String> synonyms = idMap.get(id);
                for (String synonym : synonyms) {
                    wordSet.add(synonym);
                }
            }
            return wordSet;
        }
    }

    public boolean isNoun(String noun) {
        return wordMap.containsKey(noun);
    }

    public Set<String> nouns() {
        return wordMap.keySet();
    }
}
