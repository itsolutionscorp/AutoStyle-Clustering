package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

/**
  * @author Dannver Wu
  * Worked with Michelle Yang
  */

public class WordNet {
    private Map<Integer, String> synsetsMap = new TreeMap<Integer, String>();
    private TreeSet<String> allNouns;
    private Digraph wn;

    /* Construct a Wordnet */
    public WordNet(String synsets, String hyponyms) {
        In synsetsFile = new In(synsets);
        In hyponymsFile = new In(hyponyms);
        Integer index;
        String line;
        String[] entry;
        while (!synsetsFile.isEmpty()) {
            line = synsetsFile.readLine();
            entry = line.split(",");
            index = new Integer(entry[0]);
            synsetsMap.put(index, entry[1]);
        }
        wn = new Digraph(synsetsMap.size());
        String[] relationships;
        while (!hyponymsFile.isEmpty()) {
            line = hyponymsFile.readLine();
            relationships = line.split(",");
            index = new Integer(relationships[0]);
            for (int h = 0; h < relationships.length; h += 1) {
                wn.addEdge(index, new Integer(relationships[h]));
            }
        }
    }

    /* Return true if WORD is a noun */
    public boolean isNoun(String word) {
        return nouns().contains(word);
    }

    /* Return a set of all nouns */
    public Set<String> nouns() {
        if (allNouns == null) {
            allNouns = new TreeSet<String>();
            String[] parts;
            Collection<String> vals = synsetsMap.values();
            for (String n : vals) {
                addNouns(n, allNouns);
            }
            // for (Integer i : synsetsMap.keySet()) {
            //     addNouns(synsetsMap.get(i), allNouns);
            // }
        }
        return allNouns;
    }

    /* Return a set of all hyponyms of WORD */
    public Set<String> hyponyms(String word) {
        Set<String> words = new TreeSet<String>();
        if (isNoun(word)) {
            Set<Integer> descendants = GraphHelper.descendants(wn, getVertices(word));
            String synset;
            String[] hyponyms;
            for (Integer i : descendants) {
                synset = synsetsMap.get(i);
                addNouns(synset, words);
            }
        }
        return words;
    }

    /* Add the parts of SYNSET into WORDS */
    private void addNouns(String synset, Set<String> words) {
        String [] parts = synset.split(" ");
        for (String n : parts) {
            words.add(n);
        }
    }

    /* Get the index of all the synsets with WORD */
    private Set<Integer> getVertices(String word) {
        Set<Integer> index = new TreeSet();
        String synset;
        String[] parts;
        if (!isNoun(word)) {
            return null;
        } else {
            Collection<Integer> keys = synsetsMap.keySet();
            for (Integer i : keys) {
                synset = synsetsMap.get(i);
                parts = synset.split(" ");
                for (String part : parts) {
                    if (word.equals(part)) {
                        index.add(i);
                    }
                }
            }
        }
        return index;
    }
}
