package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, String[]> synsets = new HashMap<Integer, String[]>();
    private HashMap<Integer, ArrayList<String>> hyponyms = new HashMap<Integer, 
                                                                       ArrayList<String>>();
    private Set<String> nounset = new HashSet<String>();
    private Digraph d;

    public WordNet(java.lang.String synsetFilename,
            java.lang.String hyponymFilename) {
        makeSynsets(synsetFilename);
        makeHyponyms(hyponymFilename);
        d = new Digraph(nounset.size());
        makeDigraph();
    }

    public boolean isNoun(java.lang.String noun) {
        if (nounset.contains(noun)) {
            return true;
        }
        return false;
    }

    public java.util.Set<java.lang.String> nouns() {
        return nounset;
    }

    public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
        Set<Integer> descendants = new HashSet<Integer>();
        Set<String> hyponym = new HashSet<String>();
        Set<Integer> synsetID = new HashSet<Integer>();

        for (int key : synsets.keySet()) {
            for (String id : synsets.get(key)) {
                if (id.equals(word)) {
                    synsetID.add(key);
                }
            }
        }

        descendants = GraphHelper.descendants(d, synsetID);

        for (int key : descendants) {
            for (String id : synsets.get(key)) {
                hyponym.add(id);
            }
        }
        return hyponym;
    }

    private void makeSynsets(java.lang.String synsetFilename) {
        // read in the file
        In readsyn = new In(synsetFilename);
        String synline = readsyn.readLine();

        // loop to fill hashmap
        while (synline != null) {
            String[] synarr = synline.split(",");
            String[] synstrings = Arrays.copyOfRange(synarr, 1, 2);
            int synid = Integer.parseInt(synarr[0]);
            synsets.put(synid, synstrings);

            // make nounset and seperate synonyms
            for (String a : synstrings) {
                String[] asplit = a.split(" ");
                for (int i = 0; i < asplit.length; i++) {
                    nounset.add(asplit[i]);
                }
                synsets.put(synid, asplit);
            }
            synline = readsyn.readLine();
        }
    }

    private void makeHyponyms(java.lang.String hyponymFilename) {
        // read in the file
        In readhyp = new In(hyponymFilename);
        String hypline = readhyp.readLine();

        // loop to fill in hashmap
        while (hypline != null) {
            String[] hyparr = hypline.split(",");
            String[] hypstrings = Arrays.copyOfRange(hyparr, 1, hyparr.length);
            // found Array.asList on stackoverflow
            ArrayList<String> stringList = new ArrayList<String>(
                    Arrays.asList(hypstrings));
            int hypid = Integer.parseInt(hyparr[0]);
            if (hyponyms.get(hypid) == null) {
                hyponyms.put(hypid, stringList);
            } else {
                stringList.addAll(hyponyms.get(hypid));
                hyponyms.put(hypid, stringList);
            }

            hypline = readhyp.readLine();
        }
    }

    private void makeDigraph() {
        // fill in digraph
        for (int key : hyponyms.keySet()) {
            for (String id : hyponyms.get(key)) {
                for (String i : id.split(" ")) {
                    d.addEdge(key, Integer.parseInt(i));
                }
            }
        }
    }
}
