package ngordnet;

import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Scanner in;
    private Map<Integer, String[][]> synsetsMap;
    private Map<Integer, ArrayList<Integer>> hyponymsMap;
    private Set<Integer> synsetIDs;
    private Digraph digraph;

    public WordNet(String synsets, String hyponyms) {
        this.importSynsets(synsets);
        this.importHyponyms(hyponyms);
        this.createDigraph();
    }

    /**
     * Imports the synsets file and stores the data in a Map with
     * id:{synset,def} pair.
     * 
     * @param synsets
     */
    private void importSynsets(String synsets) {
        this.synsetsMap = new HashMap<Integer, String[][]>();
        this.synsetIDs = new HashSet<Integer>();
        try {
            this.in = new Scanner(new File(synsets));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNextLine()) {
            String line = this.in.nextLine();
            String[] components = line.split(",");
            int id = Integer.parseInt(components[0]);
            String[][] synsetAndDef = { components[1].split(" "),
                { components[2] } };
            this.synsetsMap.put(id, synsetAndDef);
            this.synsetIDs.add(id);
        }
        this.in.close();
    }

    private void importHyponyms(String hyponyms) {
        this.hyponymsMap = new HashMap<Integer, ArrayList<Integer>>();
        try {
            this.in = new Scanner(new File(hyponyms));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (this.in.hasNextLine()) {
            String line = this.in.nextLine();
            String[] components = line.split(",");
            int id = Integer.parseInt(components[0]);
            ArrayList<Integer> hyponymIDs = new ArrayList<Integer>();
            for (int i = 1; i < components.length; i++) {
                hyponymIDs.add((Integer.parseInt(components[i])));
            }
            if (!this.hyponymsMap.containsKey(id)) {
                this.hyponymsMap.put(id, hyponymIDs);
            } else {
                this.hyponymsMap.get(id).addAll(hyponymIDs);
            }
        }
    }

    private void createDigraph() {
        this.digraph = new Digraph(this.synsetsMap.size());
        for (Entry<Integer, ArrayList<Integer>> e : this.hyponymsMap.entrySet()) {
            for (int i = 0; i < e.getValue().size(); i++) {
                this.digraph.addEdge(e.getKey(), e.getValue().get(i));
            }
        }
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> idsOfWord = new HashSet<Integer>();
        Set<String> wordSynset = new HashSet<String>();
        Set<Integer> idsOfHyps = new HashSet<Integer>();
        Set<String> hypsToReturn = new HashSet<String>();
        // get ID of word
        // also get synonyms of word
        for (int key : this.synsetsMap.keySet()) {
            String[] synset = this.synsetsMap.get(key)[0];
            for (String syn : synset) {
                if (syn.equals(word)) {
                    idsOfWord.add(key);
                    wordSynset.addAll(Arrays.asList(synset));
                }
            }
        }
        hypsToReturn.addAll(wordSynset);
        idsOfHyps = GraphHelper.descendants(this.digraph, idsOfWord);
        // get actual hyponyms
        for (int idOfHyp : idsOfHyps) {
            hypsToReturn.addAll(Arrays.asList(this.synsetsMap.get(idOfHyp)[0]));
        }
        return hypsToReturn;
    }

    public boolean isNoun(String noun) {
        for (int key : this.synsetsMap.keySet()) {
            String[] synonyms = this.synsetsMap.get(key)[0];
            for (String syn : synonyms) {
                if (syn.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        HashSet<String> nounsToReturn = new HashSet<String>();
        for (int key : this.synsetsMap.keySet()) {
            String[] synonyms = this.synsetsMap.get(key)[0];
            for (String syn : synonyms) {
                nounsToReturn.add(syn);
            }
        }
        return nounsToReturn;
    }
}
