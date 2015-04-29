package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, Set<String>> synsets = new HashMap<Integer, Set<String>>();
    private HashMap<Integer, Set<Integer>> hyponyms = new HashMap<Integer, Set<Integer>>();

    public WordNet(String synsetFile, String hyponymFile) {
        In syns = new In(synsetFile);
        while (syns.hasNextLine()) {
            Set<String> synSet = new HashSet<String>();
            String line = syns.readLine();
            String[] lines = line.split(",");
            int id = Integer.parseInt(lines[0]);
            String[] synWords = lines[1].split(" ");
            for (int i = 0; i < synWords.length; i++) {
                synSet.add(synWords[i]);
            }
            synsets.put(id, synSet);
        }
        syns.close();
        In hyps = new In(hyponymFile);
        while (hyps.hasNextLine()) {
            Set<Integer> hypSet = new HashSet<Integer>();
            String line = hyps.readLine();
            String[] lines = line.split(",");
            int id = Integer.parseInt(lines[0]);
            for (int i = 1; i < lines.length; i++) {
                hypSet.add(Integer.parseInt(lines[i]));
            }
            if (hyponyms.containsKey(id)) {
                hypSet.addAll(hyponyms.get(id));
            }
            hyponyms.put(id, hypSet);
        }
        hyps.close();
    }

    public boolean isNoun(String noun) {
        for (int i = 0; i < synsets.size(); i++) {
            Set<String> s = synsets.get(i);
            if (s.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (int i = 0; i < synsets.size(); i++) {
            Set<String> s = synsets.get(i);
            nouns.addAll(s);
        }
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Digraph g = new Digraph(synsets.size());
        for (int i = 0; i < synsets.size(); i++) {
            if (!hyponyms.containsKey(i) || hyponyms.get(i) == null) {
                continue;
            } else {
                Set<Integer> ints = hyponyms.get(i);
                for (int j : ints) {
                    g.addEdge(i, j);
                }
            }
        }
        Set<String> hyps = new HashSet<String>();
        Set<Integer> synKeys = synsets.keySet();
        Set<Integer> hypKeys = new HashSet<Integer>();
        for (int i : synKeys) {
            Set<String> s = synsets.get(i);
            if (s.contains(word)) {
                hyps.addAll(s);
                hypKeys.add(i);
            }
        }
        hypKeys.addAll(GraphHelper.descendants(g, hypKeys));
        for (int i : hypKeys) {
            if (hyponyms.get(i) == null) {
                continue;
            } else {
                for (int j : hyponyms.get(i)) {
                    hyps.addAll(synsets.get(j));
                }
            }
        }
        return hyps;
    }
}
