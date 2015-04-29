package ngordnet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private Digraph synsets;
    private HashMap<Integer, String> map = new HashMap<>();
    private Set<String> nouns = new HashSet<>();

    public WordNet(String synsetFileName, String hyponymFileName) {
        In synsetFile = new In(synsetFileName);
        In hyponymFile = new In(hyponymFileName);
        String currentLine;
        String[] splitLine;
        String[] splitNouns;

        while (synsetFile.hasNextLine()) {
            currentLine = synsetFile.readLine();
            splitLine = currentLine.split(",");
            map.put(Integer.parseInt(splitLine[0]), splitLine[1]);
            splitNouns = splitLine[1].split(" ");
            for (int i = 0; i < splitNouns.length; i += 1) {
                if (!nouns.contains(splitNouns[i])) {
                    nouns.add(splitNouns[i]);
                }
            }
        }

        synsets = new Digraph(map.size());

        while (hyponymFile.hasNextLine()) {
            currentLine = hyponymFile.readLine();
            splitLine = currentLine.split(",");

            for (int i = 1; i < splitLine.length; i += 1) {
                synsets.addEdge(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[i]));
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
        Set<Integer> id = new HashSet<>();
        String[] parsed;

        for (int x = 0; x < map.size(); x += 1) {
            parsed = map.get(x).split(" ");
            for (int y = 0; y < parsed.length; y += 1) {
                if (word.equals(parsed[y])) {
                    id.add(x);
                }
            }
        }

        Set<String> hyponyms = new HashSet<>();
        Set<Integer> hyponymsId = GraphHelper.descendants(synsets, id);
        String[] listIds;

        for (int x : hyponymsId) {
            listIds = map.get(x).split(" ");
            for (int i = 0; i < listIds.length; i += 1) {
                if (!hyponyms.contains(listIds[i])) {
                    hyponyms.add(listIds[i]);
                }
            }
        }

        return hyponyms;
    }
}
