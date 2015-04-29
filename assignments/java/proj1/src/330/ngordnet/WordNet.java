package ngordnet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private HashMap<Integer, String> map = new HashMap<>();
    private Set<String> nouns = new HashSet<>();
    private Digraph synsets;

    public WordNet(String synsetFileName, String hyponymFileName) {
        In synsetFile = new In(synsetFileName);
        In hyponymFile = new In(hyponymFileName);
        String[] currentLine;
        String[] splitNouns;

        while (!synsetFile.isEmpty()) {
            currentLine = synsetFile.readLine().split(",");
            map.put(Integer.parseInt(currentLine[0]), currentLine[1]);
            splitNouns = currentLine[1].split(" ");
            for (int i = 0; i < splitNouns.length; i += 1) {
                if (!nouns.contains(splitNouns[i])) {
                    nouns.add(splitNouns[i]);
                }
            }
        }
        synsets = new Digraph(map.size());
        while (!hyponymFile.isEmpty()) {
            currentLine = hyponymFile.readLine().split(",");

            for (int i = 1; i < currentLine.length; i++) {
                synsets.addEdge(Integer.parseInt(currentLine[0]), Integer.parseInt(currentLine[i]));
            }
        }
    }

    public Set<String> nouns() {
        return nouns;
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> hyponyms(String word) {
        String[] parsed;
        Set<Integer> id = new HashSet<>();

        for (int x = 0; x < map.size(); x += 1) {
            parsed = map.get(x).split(" ");
            for (int y = 0; y < parsed.length; y += 1) {
                if (word.equals(parsed[y])) {
                    id.add(x);
                }
            }
        }

        Set<String> hyponyms = new HashSet<>();
        Set<Integer> hyponymsid = GraphHelper.descendants(synsets, id);
        String[] listids;

        for (int x : hyponymsid) {
            listids = map.get(x).split(" ");
            for (int i = 0; i < listids.length; i += 1) {
                if (!hyponyms.contains(listids[i])) {
                    hyponyms.add(listids[i]);
                }
            }
        }

        return hyponyms;
    }
}

