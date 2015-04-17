
package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private Map<Integer, Set<String>> indexMap;
    private Map<String, Set<Integer>> wordMap;
    private Set<String> nouns;
    private String[] synsets;
    private String[] hyponyms;
    private Digraph net;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In readSynsets = new In(synsetFilename);
        In readHyponyms = new In(hyponymFilename);
        synsets = readSynsets.readAllLines();
        hyponyms = readHyponyms.readAllLines();
        net = new Digraph(synsets.length);
        indexMap = new HashMap(synsets.length);
        wordMap = new HashMap(synsets.length);
        nouns =  new HashSet();

        for (String h: hyponyms) {
            String[] line = h.split(",");
            int current =  Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                net.addEdge(current, Integer.parseInt(line[i]));
            }
        }

        for (String s: synsets) {
            String[] index = s.split(",");
            Integer current = Integer.parseInt(index[0]);
            String[] currwords = index[1].split(" ");
            Set<String> words = new HashSet();
            for (String word: currwords) {
                nouns.add(word);
                words.add(word);
                if (!wordMap.containsKey(word)) {
                    HashSet<Integer> indices = new HashSet();
                    indices.add(current);
                    wordMap.put(word, indices);
                } else {
                    wordMap.get(word).add(current);
                }
            }
            indexMap.put(current, words);
        }
    }

    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> hypo = new HashSet();
            for (Integer i: wordMap.get(word)) {
                hypo.add(i);
            }
            hypo = GraphHelper.descendants(net, hypo);
            Set<String> words = new HashSet();
            for (Integer currint: hypo) {
                for (String currword: indexMap.get(currint)) {
                    words.add(currword);
                }
            }
            return words;
        }
        return null;
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        return wordMap.keySet();
    }

}
