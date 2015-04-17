package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

public class WordNet {

    private HashMap<Integer, String> synsets = new HashMap<Integer, String>();
    private Digraph hypoGraph;
    private In reader;
    private String line, syn;
    private int position, size, hype, hypo;
    private String[] sArray, lArray, nArray;

    public WordNet(String synsetFilename, String hyponymFilename) {
        /** Read synset file first, and store the data in synsets */
        reader = new In(synsetFilename);
        while ((line = reader.readLine()) != null) {
            sArray = line.split(",");
            position = Integer.parseInt(sArray[0]);
            syn = sArray[1];
            synsets.put(position, syn);
            size += 1;
        }
        /** Now we read hyponym file, and store the data in hypoGraph */
        reader = new In(hyponymFilename);
        /** Initialize a new Digraph with "size" */
        hypoGraph = new Digraph(size);
        while ((line = reader.readLine()) != null) {
            sArray = line.split(",");
            hype = Integer.parseInt(sArray[0]);
            for (int i = 1; i < sArray.length; i++) {
                hypo = Integer.parseInt(sArray[i]);
                hypoGraph.addEdge(hype, hypo);
            }
        }
    }

    public boolean isNoun(String word) {
        int i = 0;
        ArrayList<String> qAList = new ArrayList<String>();
        while (i < size) {
            /**
             * use i as key to synsets to get the string, split string and check
             * if word is in the array
             */
            lArray = synsets.get(i).split(" ");
            for (String single : lArray) {
                qAList.add(single);
            }
            if (qAList.contains(word)) {
                return true;
            }
            i += 1;
        }
        return false;
    }

    public Set<String> nouns() {
        int i = 0;
        HashSet<String> nAList = new HashSet<String>();
        while (i < size) {
            lArray = synsets.get(i).split(" ");
            for (String single : lArray) {
                if (!nAList.contains(single)) {
                    nAList.add(single);
                }
            }
            i += 1;
        }
        return nAList;
    }

    public Set<String> hyponyms(String word) {
        int i = 0;
        Set<Integer> hSet = new TreeSet<Integer>();
        HashSet<String> nAList = new HashSet<String>();
        while (i < size) {
            lArray = synsets.get(i).split(" ");
            for (String single : lArray) {
                if (single.equals(word)) {
                    hSet.add(i);
                }
            }
            i += 1;
        }
        Set<Integer> idSet = GraphHelper.descendants(hypoGraph, hSet);
        /** convert id into words and add them to nAlist */
        for (Integer id : idSet) {
            lArray = synsets.get(id).split(" ");
            for (String single : lArray) {
                if (!nAList.contains(single)) {
                    nAList.add(single);
                }
            }
        }
        /** convert nAlist to nArray */
        nAList.add(word);
        return nAList;
    }

}
