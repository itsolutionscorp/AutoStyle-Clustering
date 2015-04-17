package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private Digraph edges;
    private Map<String, Set<Integer>> words;
    private Map<Integer, Set<String>> ids;

    public WordNet(String synsetFilename, String hyponymFilename) {

        // first we create two maps to connect nouns and ids
        In synsets = new In(synsetFilename);
        words = new HashMap<String, Set<Integer>>();
        ids = new HashMap<Integer, Set<String>>();
        while (synsets.hasNextLine()) {
            String nextLine = synsets.readLine();
            String[] splitLine = nextLine.split(",");
            int id = Integer.parseInt(splitLine[0]);

            String[] nouns = splitLine[1].split(" ");

            Set<String> nounSet = new HashSet<String>();

            for (String noun : nouns) {
                nounSet.add(noun);
                Set<Integer> curr = words.get(noun);
                if (curr == null) {
                    curr = new HashSet<Integer>();
                    curr.add(id);
                    words.put(noun, curr);
                } else {
                    curr.add(id);
                }
            }


            ids.put(id, new HashSet<String>(nounSet));
        }




        In hyponyms = new In(hyponymFilename);
        edges = new Digraph(words.size());
        while (hyponyms.hasNextLine()) {
            String[] nextLine = hyponyms.readLine().split(",");
            int start = Integer.parseInt(nextLine[0]);
            for (int i = 1; i < nextLine.length; i += 1) {
                edges.addEdge(start, Integer.parseInt(nextLine[i]));
            }
        }

    }

    public java.util.Set<String> hyponyms(String word) {
        Set<Integer> descendants = GraphHelper.descendants(edges, words.get(word));
        Set<String> result = new HashSet<String>();
        for (Integer i : descendants) {
            for (String s : ids.get(i)) {
                result.add(s);
            }
        }
        return result;
        
    }

    public boolean isNoun(String noun) {
        return words.containsKey(noun);
    }

    public java.util.Set<String> nouns() {
        return words.keySet();
    }
}
