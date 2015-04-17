package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordNet {
    private Map<Integer, Set> allSyns = new TreeMap<Integer, Set>();
    private Set<String> allNouns = new TreeSet<String>();
    private int numVertices;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In syns = new In(synsetFilename);
        Set<String> tempSyn = new TreeSet<String>();
        Boolean onFirst = true;
        String line;
        String temp;
        int synNum;
        while (!syns.isEmpty()) {
            line = syns.readLine();
            temp = "";
            synNum = 0;
            for (int i = 0; i < line.length(); i += 1) {
                if (line.charAt(i) == ',' && onFirst) {
                    synNum = Integer.parseInt(temp);
                    onFirst = !onFirst;
                    temp = "";
                } else if (line.charAt(i) == ' ') {
                    tempSyn.add(temp);
                    allNouns.add(temp);
                    temp = "";
                } else if (line.charAt(i) == ',') {
                    tempSyn.add(temp);
                    allNouns.add(temp);
                    onFirst = !onFirst;
                    break;
                } else {
                    temp = temp + line.charAt(i);
                }
            }
            allSyns.put(synNum, tempSyn);
            tempSyn = new TreeSet<String>();
        }
        numVertices = ((int) ((TreeMap) allSyns).lastKey()) + 1;

        In hypos = new In(hyponymFilename);
        g = new Digraph(numVertices);
        while (!hypos.isEmpty()) {
            line = hypos.readLine();
            temp = "";
            int first = -1;
            for (int i = 0; i < line.length(); i += 1) {
                if (line.charAt(i) == ',' && onFirst) {
                    first = Integer.parseInt(temp);
                    onFirst = !onFirst;
                    temp = "";
                } else if (line.charAt(i) == ',') {
                    g.addEdge(first, Integer.parseInt(temp));
                    temp = "";
                } else {
                    temp = temp + line.charAt(i);
                }
            }
            g.addEdge(first, Integer.parseInt(temp));
            onFirst = !onFirst;
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < numVertices; i += 1) {
            if (allSyns.get(i).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;
    }

    /* Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> toRet = new TreeSet<String>();
        Set<Integer> wordIndex = new TreeSet<Integer>();
        for (int i = 0; i < numVertices; i += 1) {
            if (allSyns.get(i).contains(word)) {
                wordIndex.add(i);
            }
        }
        Set<Integer> vertices = GraphHelper.descendants(g, wordIndex);
        for (int i : vertices) {
            toRet.addAll(allSyns.get(i));
        }
        return toRet;
    }
}
