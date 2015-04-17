package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

/**
 * Created by Ankit on 3/5/2015.
 * Credit to Zhongxia Yan for helping me work through figuring out the ADTs I've used here
 */
public class WordNet {
    private ArrayList<HashSet<String>> synsetList = new ArrayList<HashSet<String>>();
    private HashMap<String, HashSet<Integer>> wordLookup = new HashMap<String, HashSet<Integer>>();
    private Digraph g;


    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);

        /*synsetWords temporary holder - list of words in a single synset
         * synsetLine temporary holder for list of elements in a single line of the synset file
         */
        String[] synsetWords;
        String[] synsetLine = new String[3];
        String a;
        int f = 0;
        while (synsetIn.hasNextLine()) {
            a = synsetIn.readLine();
            synsetLine = a.split(",");
            synsetWords = synsetLine[1].split(" ");
            f = Integer.parseInt(synsetLine[0]);
            synsetList.add(f, new HashSet<String>(Arrays.asList(synsetWords)));
            for (String b : synsetWords) {
                if (wordLookup.get(b) != null) {
                    wordLookup.get(b).add(f);
                } else {
                    HashSet<Integer> init = new HashSet<Integer>();
                    init.add(f);
                    wordLookup.put(b, init);
                }
            }

        }
        g = new Digraph(synsetList.size());
        In hyponymIn = new In(hyponymFilename);
        String d;
        String[] linker;
        while (hyponymIn.hasNextLine()) {
            d = hyponymIn.readLine();
            linker = d.split(",");
            for (int i = 1; i < linker.length; i++)  {
                g.addEdge(Integer.parseInt(linker[0]), Integer.parseInt(linker[i]));
            }

        }
    }

    public Set<String> nouns() {
        return wordLookup.keySet();
    }

    public boolean isNoun(String noun) {
        if (wordLookup.get(noun) != null) {
            return true;
        }
        return false;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> a = GraphHelper.descendants(g, wordLookup.get(word));
        HashSet<String> hyponyms = new HashSet<String>();
        for (Integer i : a) {
            hyponyms.addAll(synsetList.get(i));
        }
        return hyponyms;
    }
}
