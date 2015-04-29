package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

public class WordNet {

    private Map<String, Set<Integer>> synset;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        synset = new TreeMap<String, Set<Integer>>();
        int idCount = 0;
        while (synsetFile.hasNextLine()) {
            idCount += 1;
            String line = synsetFile.readLine();
            String[] parts = line.split(",");
            String[] words = parts[1].split(" ");
            for (int i = 0; i < words.length; i++) {
                if (synset.containsKey(words[i])) {
                    Set<Integer> id = synset.get(words[i]);
                    id.add(Integer.parseInt(parts[0]));
                    synset.put(words[i], id);
                } else {
                    Set<Integer> id = new TreeSet<Integer>();
                    id.add(Integer.parseInt(parts[0]));
                    synset.put(words[i], id);
                }
            }
        }
        g = new Digraph(idCount);
        while (hyponymFile.hasNextLine()) {
            String line = hyponymFile.readLine();
            String[] parts = line.split(",");
            int firstID = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                int nextID = Integer.parseInt(parts[i]);
                g.addEdge(firstID, nextID);
            }
        }
        // System.out.println(g);
    }

    public boolean isNoun(String noun) {
        if (synset.containsKey(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return synset.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymWords = new TreeSet<String>();
        Set<Integer> wordID = synset.get(word);
        Set<Integer> decend =  GraphHelper.descendants(g, wordID);
        for (Integer id : decend) {
            for (String key : synset.keySet()) {
                if (synset.get(key).contains(id)) {
                    hyponymWords.add(key);
                }
            }
        }
        return hyponymWords;
    }
}
