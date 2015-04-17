package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.TreeMap;

public class WordNet {
    private TreeMap<String, TreeSet<Integer>> wordMapping;
    private ArrayList<String[]> words;
    private Digraph graph;
    public WordNet(String synsetFilename, String hyponymFilename) {
        wordMapping = new TreeMap<String, TreeSet<Integer>>();
        words = new ArrayList<String[]>();
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        int i = 0;
        while (synset.hasNextLine()) {
            String[] line = synset.readLine().split(",");
            String[] synWords = line[1].split(" ");
            for (String str : synWords) {
                TreeSet<Integer> t = wordMapping.get(str);
                if (t == null) {
                    t = new TreeSet<Integer>();
                }

                t.add(i);
                wordMapping.put(str, t);
            }
            words.add(synWords);
            i++;
        }
        graph = new Digraph(words.size());
        while (hyponym.hasNextLine()) {
            String[] line = hyponym.readLine().split(",");
            int edge = Integer.parseInt(line[0]);
            for (i = 1; i < line.length; i++) {
                graph.addEdge(edge, Integer.parseInt(line[i]));
            }
        }
        synset.close();
        hyponym.close();
    }

    public Set<String> hyponyms(String word) {
        Set<String> relatedWords = new TreeSet<String>();
        if (!wordMapping.containsKey(word)) {
            return relatedWords;
        }
        TreeSet<Integer> vertex = wordMapping.get(word);

        Set<Integer> verticies = GraphHelper.descendants(graph, vertex);
        for (Integer i : verticies) {
            for (String str : words.get(i)) {
                relatedWords.add(str);
            }
        }
        return relatedWords;
    }

    public boolean isNoun(String noun) {
        return wordMapping.containsKey(noun);
    }

    public Set<String> nouns() {
        return wordMapping.keySet();
    }
}
