package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private In synsetFile;
    private In hyponymFile;
    private Map<Integer, String> wordMap;
    private Digraph hypGraph;
    private Set<String> synsets;
    private ArrayList<String> initSynset;
    private ArrayList<String> initHyponyms;

    public WordNet(String synsetFileName, String hyponymFileName) {
        synsetFile = new In(synsetFileName);
        hyponymFile = new In(hyponymFileName);
        initSynset = new ArrayList<String>();
        initHyponyms = new ArrayList<String>();
        synsets = new HashSet<String>();
        wordMap = new HashMap<Integer, String>();

        while (synsetFile.hasNextLine()) {
            initSynset.add(synsetFile.readLine());
        }

        for (String line : initSynset) {
            String[] temp = line.split(",");
            wordMap.put(Integer.parseInt(temp[0]), temp[1]);
            String[] words = temp[1].split(" ");
            for (String s : words) {
                synsets.add(s);
            }
        }

        while (hyponymFile.hasNextLine()) {
            initHyponyms.add(hyponymFile.readLine());
        }

        hypGraph = new Digraph(this.nouns().size());

        for (String line : initHyponyms) {
            String[] temp = line.split(",");
            for (int x = 1; x < temp.length; x++) {
                hypGraph.addEdge(Integer.parseInt(temp[0]),
                        Integer.parseInt(temp[x]));
            }
        }
    }

    public Set<String> nouns() {
        return synsets;
    }

    public Set<String> hyponyms(String word) {
        Set<String> returned = new HashSet<String>();
        Set<String> withSpace = new HashSet<String>();
        Set<Integer> wordIDs = new HashSet<Integer>();
        for (Integer i : wordMap.keySet()) {
            if (wordMap.get(i).contains(word)) {
                wordIDs.add(i);
            }
        }

        Set<Integer> ids = GraphHelper.descendants(hypGraph, wordIDs);

        for (Integer id : ids) {
            withSpace.add(wordMap.get(id));
        }

        for (String w : withSpace) {
            if (w.contains(" ")) {
                String[] temp = w.split(" ");
                for (String t : temp) {
                    returned.add(t);
                }
            } else {
                returned.add(w);
            }
        }
        return returned;
    }

    public boolean isNoun(String noun) {
        for (String s : this.nouns()) {
            if (s.contains(noun)) {
                return true;
            }
        }
        return false;
    }
}
