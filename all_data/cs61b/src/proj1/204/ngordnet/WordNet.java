package ngordnet;

import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private String synsetFile;
    private String hyponymFile;
    private Set<String> nouns = new HashSet<String>();
    private HashMap<Integer, String> synsets = new HashMap<Integer, String>();
    private Digraph dataset;
    private int vertices = 0;

    public WordNet(String s, String h) {
        synsetFile = s;
        hyponymFile = h;

        In inSynset = new In(synsetFile);
        In inHyponym = new In(hyponymFile);

        while (inSynset.hasNextLine()) {
            String lineSynset = inSynset.readLine();

            vertices += 1; // Each word in Synset file is a vertices.

            if (lineSynset != null) {
                String[] rawSynset = lineSynset.split(",");
                String[] rawSynsetSpace = rawSynset[1].split(" ");

                synsets.put(Integer.parseInt(rawSynset[0]), rawSynset[1]);

                if (rawSynsetSpace.length == 1) {
                    nouns.add(rawSynsetSpace[0]);
                } else {
                    for (String element : rawSynsetSpace) {
                        nouns.add(element);
                    }
                }
            }
        }

        // Constructs a Digraph
        dataset = new Digraph(vertices);

        while (inHyponym.hasNextLine()) {
            String lineHyponym = inHyponym.readLine();
            String[] rawHyponym = lineHyponym.split(",");

            if (rawHyponym.length == 2) {
                dataset.addEdge(Integer.parseInt(rawHyponym[0]),
                        Integer.parseInt(rawHyponym[1]));
            }

            if (rawHyponym.length > 2) {
                for (int x = 0; x < rawHyponym.length - 1; x++) {
                    dataset.addEdge(Integer.parseInt(rawHyponym[0]),
                            Integer.parseInt(rawHyponym[x + 1]));
                }
            }
        }

    }

    public boolean isNoun(String noun) {
        if (nouns.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> hyponyms(String word) {
        int id = 0;
        Set<Integer> ids = new TreeSet<Integer>();
        Set<String> result = new HashSet<String>();

        for (Object key : synsets.keySet()) {

            String[] split = synsets.get(key).split(" ");

            for (int x = 0; x < split.length; x++) {
                if (word.equals(split[x])) {
                    id = (Integer) key;
                    ids.add(id);
                    for (String element : split) {
                        result.add(element);
                    }
                }
            }

        }

        Set<Integer> descendant = GraphHelper.descendants(dataset, ids);

        for (Integer i : descendant) { // iterates through the synset ids
            String hypo = synsets.get(i); // gets the word that matches the
                                          // synset
            String[] splitHypo = hypo.split(" "); // splits the synsets
            for (int x = 0; x < splitHypo.length; x++) {
                if (!result.contains(splitHypo[x])) {
                    result.add(splitHypo[x]);
                }
            }

        }

        return result;
    }

    public Set<String> nouns() {
        return nouns;
    }
}
