package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Iterator;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<Integer, String[]> byNum = new HashMap<Integer, String[]>();
    private HashMap<String, Set<Integer>> byWord = new HashMap<String, Set<Integer>>();
    private Set<String> nouns = new TreeSet<String>();
    private Digraph graph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);

        int size = 0;

        while (synsets.hasNextLine()) {
            String line = synsets.readLine(); 
            String[] items = line.split(","); 
            String[] synonyms = items[1].split(" ");

            byNum.put(Integer.parseInt(items[0]), synonyms);

            for (String word : synonyms) {
                nouns.add(word);
                if (byWord.get(word) != null) {
                    Set<Integer> oldSet = byWord.get(word);
                    oldSet.add(Integer.parseInt(items[0]));
                    byWord.put(word, oldSet); 
                } else {
                    Set<Integer> newSet = new TreeSet<Integer>();
                    newSet.add(Integer.parseInt(items[0]));
                    byWord.put(word, newSet);
                }
            }

            size += 1;
        }

        graph = new Digraph(size);

        while (hyponyms.hasNextLine()) {
            String line = hyponyms.readLine();
            String[] items = line.split(",");

            for (int i = 1; i < items.length; i++) {
                graph.addEdge(Integer.parseInt(items[0]), Integer.parseInt(items[i]));
            }
        }
    }

    public boolean isNoun(String noun) {
        Iterator<String> n = nouns.iterator();
        while (n.hasNext()) {
            if (noun.equals(n.next())) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new TreeSet<String>();
        Set<Integer> sah = GraphHelper.descendants(graph, byWord.get(word));
        for (Integer id : sah) {
            for (String w : byNum.get(id)) {
                hyponyms.add(w);
            }   
        }
        return hyponyms;
    }

}
