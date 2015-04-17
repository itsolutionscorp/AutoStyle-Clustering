package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashSet<String> nounSet;
    private HashMap<Integer, String> synmap;
    private In in;
    private In in2;
    private In in3;
    private HashMap<Integer, ArrayList<String>> hypermap;
    private HashSet<String> hyposet;
    private Digraph d;
    private int counter;
    private ArrayList<Integer> indexkey;

    public WordNet(String synsets, String hypernyms) {
        hyposet = new HashSet<String>();
        synmap = new HashMap<Integer, String>();
        hypermap = new HashMap<Integer, ArrayList<String>>();

        in = new In(synsets);
        while (in.hasNextLine()) {
            ArrayList<Integer> arrayint = new ArrayList<Integer>();
            String synline = in.readLine(); // reads the line
            String[] rawTokens = synline.split(",");
            String[] rawToken = rawTokens[1].split(" ");
            int index = Integer.parseInt(rawTokens[0]);
            synmap.put(index, rawTokens[1]);
        }

        // map of synset ID :word

        in3 = new In(hypernyms);
        while (in3.hasNextLine()) {
            String hypline = in3.readLine(); // reads the line
            String[] lineTokens = hypline.split(",");
            int index = Integer.parseInt(lineTokens[0]);
            ArrayList<String> listString = new ArrayList<String>();
            for (int i = 1; i < lineTokens.length; i++) {
                listString.add(lineTokens[i]);
                counter += 1;
            }
            if (hypermap.containsKey(index)) {
                ArrayList<String> list = new ArrayList<String>(hypermap.get(index));
                list.addAll(listString);
                hypermap.put(index, list);
                counter += 1;
            } else {
                hypermap.put(index, listString);
                counter += 1;
            }
        }

        d = new Digraph(counter);
        in2 = new In(hypernyms);
        while (in2.hasNextLine()) {
            String hypline = in2.readLine(); // reads the line
            String delims = "[,]";
            String[] lineTokens = hypline.split(delims);
            int index = Integer.parseInt(lineTokens[0]);
            for (int i = 1; i < lineTokens.length; i++) {
                int tokens = Integer.parseInt(lineTokens[i]);
                d.addEdge(index, tokens);
            }
        }
        // map of hyponyms: hypernyms

        // Digraph d= new Digraph(hypmap.size()); //go through the digraph
        // recursively
    }

    // makes the first string array ready to be used as a key for mapping

    // creates an array with all of the ID's of the synsets

    public boolean isNoun(String noun) {
        if (this.nouns().contains(noun)) {
            return true;
        }

        return false;
    }

    public Set<String> nouns() {
        nounSet = new HashSet<String>();
        for (int i = 0; i < synmap.size(); i++) {
            Integer key = i;
            String value = synmap.get(key);
            String[] stringList = value.split(" ");
            for (String string : stringList) {
                nounSet.add(string);
            }
        }
        return nounSet;
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymset = new HashSet<String>();
        Set<Integer> hypID = new TreeSet<Integer>();
        Set<Integer> synID = synmap.keySet();
        indexkey = new ArrayList<Integer>();

        for (int integer : synID) {
            String[] thing = synmap.get(integer).split(" ");
            for (String string : thing) {
                if (string.equals(word)) {
                    indexkey.add(integer);
                }
            }
        }
        hypID.addAll(indexkey);
        Set<Integer> graphHelper = GraphHelper.descendants(d, hypID);
        for (int helper : graphHelper) {
            String[] split = synmap.get(helper).split(" ");
            for (String woah : split) {
                hyponymset.add(woah);
            }
        }
        return hyponymset;
    }
}

