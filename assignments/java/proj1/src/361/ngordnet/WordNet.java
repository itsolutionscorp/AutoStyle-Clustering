package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, HashSet<String>> synsets = new HashMap<Integer, HashSet<String>>();
    private HashMap<HashSet<String>, HashSet<Integer>> synsetsR = 
                                                new HashMap<HashSet<String>, HashSet<Integer>>();
    private Set<String> nouns = new HashSet<String>();
    private Digraph hyponymsGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        // Creates a WordNet using files form SYNSETFILENAME and 
        //HYPONYMFILENAME
        In synsetsText = new In(synsetFilename);
        In hyponymsText = new In(hyponymFilename);

        String line;

        //Reading synsets.txt and creating synsets
        line = synsetsText.readLine();

        while (line != null) {
            String[] tokens = line.split(",");

            int id = Integer.parseInt(tokens[0]); //For adding to synsets
            String[] words = tokens[1].split(" "); //For adding to nouns
            HashSet<String> synset = new HashSet<String>();

            for (int i = 0; i < words.length; i++) {
                nouns.add(words[i]);
                synset.add(words[i]);
            }
            synsets.put(id, synset);

            if (synsetsR.containsKey(synset)) {
                HashSet<Integer> ids = synsetsR.get(synset);
                ids.add(id);
            } else {
                HashSet<Integer> ids = new HashSet<Integer>();
                ids.add(id);
                synsetsR.put(synset, ids);
            }

            line = synsetsText.readLine();
        }

        //Reading hyponyms.txt and creating hyponym
        hyponymsGraph = new Digraph(synsets.size());

        line = hyponymsText.readLine();
        while (line != null) {
            String[] tokens = line.split(",");

            int id = Integer.parseInt(tokens[0]);

            //Adds the edges to a specific synset
            for (int i = 1; i < tokens.length; i++) {
                hyponymsGraph.addEdge(id, Integer.parseInt(tokens[i]));
            }

            line = hyponymsText.readLine();
        }
    }

    public Set<String> hyponyms(String word) {
        Set<String> words = new HashSet<String>();
        Set<Integer> idsWithWord = new HashSet<Integer>();

        for (HashSet<String> synset : synsetsR.keySet()) {
            if (synset.contains(word)) {
                //Add synonyms, if there are any
                for (String wordToAdd : synset) {
                    words.add(wordToAdd);
                }

                //Gets the wanted id from synsetsR
                HashSet<Integer> synsetIDs = synsetsR.get(synset);
                for (Integer id : synsetIDs) {
                    idsWithWord.add(id);
                }
            }
        }

        //Get hyponyms of this synset
        Set<Integer> ids = GraphHelper.descendants(hyponymsGraph, idsWithWord);

        //Add hyponyms to words
        for (Integer id : ids) {
            Set<String> synsetOfId = synsets.get(id);
            for (String addWord : synsetOfId) {
                words.add(addWord);
            }
        }
        return words;
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }

}
