package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

//Looked on stackoverflow for different types of ADT's and their methods.

public class WordNet {

    private Scanner synset;
    private Scanner hyponym;
    private HashMap<Integer, ArrayList<String>> synsetMap;
    private HashSet<String> nouns;
    private Digraph hyponymDigraph;

    //Looked on stack overflow on scanners and their functionality
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            synset = new Scanner(new File(synsetFilename));
            hyponym = new Scanner(new File(hyponymFilename));
        } catch (FileNotFoundException f) {
            throw new RuntimeException("No synset or hyponym file found!");
        }
        synsetMap = new HashMap<Integer, ArrayList<String>>();
        nouns = new HashSet<String>();

        //Deals with synset file
        while (synset.hasNextLine()) {
            String sLine = synset.nextLine();
            String[] split = sLine.split(",");
            String[] svals = split[1].split(" ");
            ArrayList<String> allStr = new ArrayList<String>();
            for (int i = 0; i < svals.length; i++) {
                allStr.add(svals[i]);
                nouns.add(svals[i]);
            }
            int id = Integer.parseInt(split[0]);
            synsetMap.put(id, allStr);
        }
        hyponymDigraph = new Digraph(synsetMap.size());

        //Deals with hyponyms file
        while (hyponym.hasNextLine()) {
            String hLine = hyponym.nextLine();
            String[] h = hLine.split(",");
            int id = Integer.parseInt(h[0]);
            for (int i = 1; i < h.length; i++) {
                int desc = Integer.parseInt(h[i]);
                hyponymDigraph.addEdge(id, desc);
            }
        }
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }

    //Small debugging help from Megan Zhu on this method
    public Set<String> hyponyms(String word) {
        Set<Integer> allKeys = synsetMap.keySet();
        HashSet<Integer> descNums = new HashSet<Integer>();
        for (Integer i : allKeys) {
            if (synsetMap.get(i).contains(word)) {
                descNums.add(i);
            }
        }
        Set<Integer> hyponymKeys = GraphHelper.descendants(hyponymDigraph, descNums);
        HashSet<String> hypos = new HashSet<String>();
        for (Integer i : hyponymKeys) {
            ArrayList<String> stringSet = synsetMap.get(i);
            for (String s : stringSet) {
                hypos.add(s);
            }
        }
        return hypos;
    }
}
