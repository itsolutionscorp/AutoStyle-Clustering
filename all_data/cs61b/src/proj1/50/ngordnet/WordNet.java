package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Abhi Sharma
 */
public class WordNet {

    private HashMap<Integer, Set<String>> synsets;
    private Digraph net;

    /** Creates a WordNet using files from synsetFilename and hyponymFilename */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        synsets = new HashMap<Integer, Set<String>>();
        while (synsetIn.hasNextLine()) {
            String[] line = synsetIn.readLine().split(",");
            int key = Integer.parseInt(line[0]);
            String[] synonyms = line[1].split(" ");
            Set<String> value = new HashSet<String>(Arrays.asList(synonyms));
            synsets.put(key, value);
        }
        net = new Digraph(synsets.size());
        while (hyponymIn.hasNextLine()) {
            String[] line = hyponymIn.readLine().split(",");
            for (String s : Arrays.asList(line).subList(1, line.length)) {
                net.addEdge(Integer.parseInt(line[0]), Integer.parseInt(s));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Set<String> s : synsets.values()) {
            if (s.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> result = new HashSet<String>();
        for (Set<String> s : synsets.values()) {
            result.addAll(s);
        }
        return result;
    }

    //courtesy of http://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
    private static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<T>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    private Set<Integer> getSynsets(String word) {
        Set<Integer> result = new HashSet<Integer>();
        for (Set<String> synset : synsets.values()) {
            if (synset.contains(word)) {
                for (Integer i: getKeysByValue(synsets, synset)) {
                    result.add(i);
                }
            }
        }
        return result;

    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> result = new HashSet<String>();
        Set<Integer> syndices = getSynsets(word);
        for (Integer i: syndices) {
            for (String s : synsets.get(i)) {
                result.add(s);
            }
        }
        for (Integer i : GraphHelper.descendants(net, syndices)) {
            for (String s : synsets.get(i)) {
                result.add(s);
            }
        }
        return result;
    }
}
