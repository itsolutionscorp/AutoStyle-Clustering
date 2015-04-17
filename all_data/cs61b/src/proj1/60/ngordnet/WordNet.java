package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {
    
    private Digraph graph;
    private TreeMap<String, ArrayList<Integer>> wordToSynsetIDs;
    private TreeSet<String> nounsSet;
    private ArrayList<Synset> synsetList;
        
    public WordNet(String synsetFilename, String hyponymFilename) {
        wordToSynsetIDs = new TreeMap<String, ArrayList<Integer>>();
        nounsSet = new TreeSet<String>();
        synsetList = new ArrayList<Synset>();

        int lines = parseSynsetList(synsetFilename);
        graph = new Digraph(lines); 
        parseHyponymList(hyponymFilename);
    }

    public Set<String> hyponyms(String word) {
        Set<String> resultSet = new TreeSet<String>();
        Set<Integer> synsetIDs = new TreeSet<Integer>();

        synsetIDs.addAll(wordToSynsetIDs.get(word));

        // First add all synonyms
        for (Integer synsetID: synsetIDs) {
            resultSet.addAll(synsetList.get(synsetID).nouns());
        }

        // Then add all descendants
        Set<Integer> descendants = GraphHelper.descendants(graph, synsetIDs);
        
        for (Integer synsetID: descendants) {
            resultSet.addAll(synsetList.get(synsetID).nouns());
        }

        return resultSet;
    }

    public boolean isNoun(String noun) {
        return nounsSet.contains(noun);
    }

    public Set<String> nouns() {
        return nounsSet;
    }

    private int parseSynsetList(String fileName) {
        In in = new In(fileName);
        int lines = 0;

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
        
            int id = Integer.parseInt(tokens[0]);
            String[] nouns = tokens[1].split(" ");
            String definition = tokens[2];

            Synset syn = new Synset(id, nouns, definition);
            synsetList.add(syn);

            for (int i = 0; i < nouns.length; i++) {
                nounsSet.add(nouns[i]);
                
                ArrayList<Integer> itemsList = wordToSynsetIDs.get(nouns[i]);
                if (itemsList == null) {
                    itemsList = new ArrayList<Integer>();
                    itemsList.add(id);
                    wordToSynsetIDs.put(nouns[i], itemsList);
                } else {
                    itemsList.add(id);
                }
            }
            
            lines += 1;
        }

        return lines;
    }

    private void parseHyponymList(String fileName) {
        In in = new In(fileName);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            
            int parent = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                int child = Integer.parseInt(tokens[i]);
                graph.addEdge(parent, child);
            }
        }
    }
}
