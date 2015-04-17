package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /* SYNSETS and HYPERNYMS are filenames for the input files. */
    private HashMap<Integer, String[]> ian = new HashMap<Integer, String[]>();
    private ArrayList<ArrayList> hypoMap = new ArrayList<ArrayList>();
    private ArrayList<Integer> hypos = null;
    private Digraph g;

    public WordNet(String synsetsfilename, String hyponymsfilename) {
        In nouns = new In(synsetsfilename);
        In hyponyms = new In(hyponymsfilename);

        while (nouns.hasNextLine()) {
            String[] splitNouns = nouns.readLine().split(",");
            Integer id = Integer.parseInt(splitNouns[0]);
            String[] words = splitNouns[1].split(" ");
            for (int i = 0; i < words.length; i = i + 1) {
                ian.put(id, words);
            }
        }

        while (hyponyms.hasNextLine()) {
            String[] splitHyponyms = hyponyms.readLine().split(",");
            Integer hypernym = Integer.parseInt(splitHyponyms[0]);
            for (int i = 1; i < splitHyponyms.length; i = i + 1) {
                Integer hyponym = Integer.parseInt(splitHyponyms[i]);
                hypos = new ArrayList<Integer>();
                hypos.add(hypernym);
                hypos.add(hyponym);
                hypoMap.add(hypos);
            }
        }

        g = new Digraph(ian.size());
        for (int i = 0; i < hypoMap.size(); i = i + 1) {
            int head = (int) hypoMap.get(i).get(0);
            int pointer = (int) hypoMap.get(i).get(1);
            g.addEdge(head, pointer);
        }

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (Integer i: ian.keySet()) {
            for (int j = 0; j < ian.get(i).length; j = j + 1) {
                allNouns.add(ian.get(i)[j]);
            }
        }
        return allNouns;
    }

    /** Returns true if WORD is a noun. WORD may be a collocation. */
    public boolean isNoun(String word) {
        for (String s: nouns()) {
            if (s.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /** Returns the set of all hypoynms of WORD. */
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> top = new ArrayList<Integer>();
        for (Integer k: ian.keySet()) {
            for (Integer t = 0; t < ian.get(k).length; t = t + 1) {
                if (ian.get(k)[t].equals(word)) {
                    top.add(k);
                }
            }
        }
        Set<Integer> temp = new HashSet<Integer>();
        for (Integer x: top) {
            temp.add(x);
        }
        Set<String> resultWord = new HashSet<String>();
        Set<Integer> resultSet = GraphHelper.descendants(g, temp);
        for (Integer q: resultSet) {
            for (int j = 0; j < ian.get(q).length; j = j + 1) {
                resultWord.add(ian.get(q)[j]);
            }
        }
        return resultWord;
    }
}


