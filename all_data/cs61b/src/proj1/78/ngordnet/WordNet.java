package ngordnet;

import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.TreeMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
   @author Chaz Chamberlin
   WordNet class to store hyponyms and synsets
*/

public class WordNet {
    private TreeMap<Integer, Set<String>> sysnets;
    private Set<String> nouns;
    private Digraph hypDigraph;
    
    public WordNet(String sysnetFileName, String hyponymFileName) {
        sysnets = new TreeMap<Integer, Set<String>>();
        nouns = new TreeSet<String>();
        readSysnets(sysnetFileName);
        readHyponyms(hyponymFileName);
    }

    private void readSysnets(String fileName) {
        In input = new In(fileName);
        String currentLine;
        String[] tokens;
        int id;
        String definition;
        TreeSet<String> currentNouns;

        while ((currentLine = input.readLine()) != null) {
            tokens = currentLine.split(",");
            currentNouns = new TreeSet<String>();            
            id = Integer.parseInt(tokens[0]);
            for (String noun : tokens[1].split(" ")) {
                currentNouns.add(noun);
            }
            definition = tokens[2];
            sysnets.put(id, currentNouns); // we forget the definition here
        }
    }

    private void readHyponyms(String fileName) {
        In input = new In(fileName);
        hypDigraph = new Digraph(sysnets.size());
        String[] tokens;
        int hypernym;
        String currentLine;
        while ((currentLine = input.readLine()) != null) {
            tokens = currentLine.split(",");
            hypernym = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                hypDigraph.addEdge(hypernym, Integer.parseInt(tokens[i]));
            }
        }
    }

    public Set<String> nouns() {
        if (nouns.size() != 0) { // already been filled
            return nouns;
        }
        int size = sysnets.size();
        Set<String> currentNouns;
        for (int i = 0; i < size; i += 1) {
            currentNouns = sysnets.get(i);
            nouns.addAll(currentNouns);
        }
        return nouns;
    }

    public Set<String> hyponyms(String hypernym) {
        Set<Integer> id = getIDs(hypernym);
        Set<Integer> hyponymIDs = GraphHelper.descendants(hypDigraph, id);
        Set<String> hyponyms = new TreeSet<String>();
        
        for (Map.Entry<Integer, Set<String>> entry : sysnets.entrySet()) {
            if (hyponymIDs.contains(entry.getKey())) {
                hyponyms.addAll(entry.getValue());
            }
        }
        return hyponyms;
        
    }

    private Set<Integer> getIDs(String hypernym) {
        Set<Integer> ids = new TreeSet<Integer>();
        for (Map.Entry<Integer, Set<String>> entry : sysnets.entrySet()) {
            if (entry.getValue().contains(hypernym)) {
                ids.add(entry.getKey());
            }
        }
        return ids;
    }

    public boolean isNoun(String word) {
        return nouns().contains(word);        
    }
                        
}
