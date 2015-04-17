package ngordnet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private Digraph nounGraph;
    /*
     * A Map from a word w to a set containing all nouns that are synonymous
     */
    private HashMap<String, Set<Integer>> nouns;
    /* A Map from a word w's id to a set containing all of its synonyms. */
    private HashMap<Integer, Set<String>> synSets;

    public WordNet(String nounPath, String hypPath) {

        nouns = new HashMap<String, Set<Integer>>();
        synSets = new HashMap<Integer, Set<String>>();

        BufferedReader br;
        String line;

        try {

            // Read in Nouns
            br = new BufferedReader(new FileReader(nounPath));
            line = br.readLine();
            while (line != null) {

                // Put a synSet in for corresponding id

                String[] fields = line.split(",");
                String[] synonyms = fields[1].split(" ");

                // Put the synset for the corresponding id
                Set<String> synSet = new HashSet<String>(
                        Arrays.asList(synonyms));
                int id = Integer.parseInt(fields[0]);
                synSets.put(id, synSet);

                // For each synonym, add this id to its set of ids
                for (String synonym : synSet) {
                    if (!nouns.containsKey(synonym)) {
                        nouns.put(synonym, new HashSet<Integer>());
                    }
                    nouns.get(synonym).add(id);
                }

                line = br.readLine();

            }

            br.close();

            // Read in Hypnonyms

            nounGraph = new Digraph(nouns.size());

            br = new BufferedReader(new FileReader(hypPath));
            line = br.readLine();
            while (line != null) {

                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                for (int i = 1; i < fields.length; i++) {
                    nounGraph.addEdge(id, Integer.parseInt(fields[i]));
                }

                line = br.readLine();
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Set<String> nouns() {
        return nouns.keySet();
    }

    public boolean isNoun(String word) {
        return nouns.containsKey(word);
    }

    public Set<String> hyponyms(String word) {

        Set<String> hyponyms = new HashSet<String>();

        if (!isNoun(word)) {
            return hyponyms;
        }

        Set<Integer> descendants = GraphHelper.descendants(nounGraph,
                nouns.get(word));

        for (Integer id : descendants) {
            hyponyms.addAll(synSets.get(id));
        }

        return hyponyms;
    }

}
