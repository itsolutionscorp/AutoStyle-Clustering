package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.TreeMap;

public class WordNet {
    private Map<Integer, Set<String>> data;
    private Digraph words;
    private Set<String> storeNouns;

    public WordNet(String synsetFilename, String hyponymFilename) {
        data = new TreeMap<Integer, Set<String>>();
        storeNouns = new TreeSet<String>();
        int vertices = 0;
        In synset = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        String[] synsetArray = synset.readAllLines();
        String[] hyponymArray = hyponyms.readAllLines();
        words = new Digraph(synsetArray.length);
        for (String string : hyponymArray) {
            String[] temp = string.split(",");
            int sInt = Integer.parseInt(temp[0]);
            for (int i = 1; i < temp.length; i++) {
                words.addEdge(sInt, Integer.parseInt(temp[i]));
            }
        }
        for (String string : synsetArray) {
            String[] temp = string.split(",");
            int sInt = Integer.parseInt(temp[0]);
            String[] synsetContents = temp[1].split(" ");
            TreeSet<String> synsetStuff = new TreeSet<String>();
            for (String string2 : synsetContents) {
                synsetStuff.add(string2);
            }
            data.put(sInt, synsetStuff);
            storeNouns.addAll(synsetStuff);
        }
    }

    public boolean isNoun(String str) {
        return storeNouns.contains(str);
    }

    public Set<String> nouns() {
        return storeNouns;
    }
    public Set<String> hyponyms(String str) {
        Set<Integer> integers = new TreeSet<Integer>();
        for (Map.Entry<Integer, Set<String>> entry : data.entrySet()) {
            if (entry.getValue().contains(str)) {
                integers.add(entry.getKey());
            }
        }
        Set<Integer> allIntegers = GraphHelper.descendants(words, integers);
        Set<String> allStrings = new TreeSet<String>();
        for (int i : allIntegers) {
            allStrings.addAll(data.get(i));
        }
        return allStrings;
    }
}
