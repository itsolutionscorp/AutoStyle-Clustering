package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordNet {
    //Holds hyponym connections
    private Digraph net;

    //Holds words and their ID's
    private Map<Integer, Set<String>> synsets = new HashMap<Integer, Set<String>>();
    private Map<String, Set<Integer>> wordID = new HashMap<String, Set<Integer>>();
    private Set<String> nouns = new HashSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        //New in files for reading lines
        In fileSynset = new In(synsetFilename);
        In fileHyponym = new In(hyponymFilename);

        //Reads in synset file and counts words for Digraph
        int count = 0;
        while (!fileSynset.isEmpty()) {
            String[] temp = fileSynset.readLine().split(",");
            Set<String> synonyms = new HashSet<String>();
            Integer id = Integer.parseInt(temp[0]);
            for (String s : temp[1].split(" ")) {
                synonyms.add(s);
                nouns.add(s);
                if (!wordID.containsKey(s)) {
                    wordID.put(s, new HashSet<Integer>());
                }
                wordID.get(s).add(id);
            }
            synsets.put(id, synonyms);
            count++;
        }

        //Reads in hyponyms and constructs digraph
        net = new Digraph(count);
        while (!fileHyponym.isEmpty()) {
            String[] connections = fileHyponym.readLine().split(",");
            int root = Integer.parseInt(connections[0]);
            for (int i = 1; i < connections.length; i += 1) {
                net.addEdge(root, Integer.parseInt(connections[i]));
            }
        }
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        //creates a a set integers for the ID of word.
        if (!wordID.containsKey(word)) {
            return Collections.emptySet();
        }
        Set<Integer> id = wordID.get(word);

        Set<Integer> hyponymIDs = GraphHelper.descendants(net, id);
        Set<String> hyponyms = new HashSet<String>();
        hyponymIDs.addAll(id);
        for (Integer i : hyponymIDs) {
            if (synsets.containsKey(i)) {
                hyponyms.addAll(synsets.get(i));   
            }
        }
        return hyponyms;
    }
}
