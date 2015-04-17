package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordNet {
    private Digraph net;
    private Map<Integer, Synset> synsets;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new HashMap<Integer, Synset>();
        In synsetFile = new In(synsetFilename);
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            Synset syn = new Synset(line);
            synsets.put(syn.getId(), syn);
        }
        net = new Digraph(synsets.size());
        In hyponymFile = new In(hyponymFilename);
        for (String line : hyponymFile.readAllLines()) {
            String[] splitLine = line.split(",");
            int synID = Integer.parseInt(splitLine[0]);
            for (int i = 1; i < splitLine.length; i++) {
                net.addEdge(synID, Integer.parseInt(splitLine[i]));
            }
        }
    }

    public Set<String> nouns() {
        Set<String> out = new HashSet<String>();
        for (Synset s : synsets.values()) {
            out.addAll(s.getSynset());
        }
        return out;
    }

    public boolean isNoun(String word) {
        return (nouns().contains(word));
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> syns = new HashSet<Integer>();
        for (Synset s : synsets.values()) {
            if (s.getSynset().contains(word)) {
                syns.add(s.getId());
            }
        }
        Set<Integer> related = GraphHelper.descendants(net, syns);
        Set<String> out = new HashSet<String>();
        for (int i : related) {
            out.addAll(synsets.get(i).getSynset());
        }
        return out;
    }
}
