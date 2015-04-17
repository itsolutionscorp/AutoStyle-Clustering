package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashMap;

public class WordNet {
    private TreeMap<Integer, Set<Integer>> mappedHyponyms = new TreeMap<Integer, Set<Integer>>();
    private HashMap<String, Set<Integer>> mappedSynsetStrKey = new HashMap<String, Set<Integer>>();
    private HashMap<Integer, Set<String>> mappedSynsetIntKey = new HashMap<Integer, Set<String>>();
    private HashMap<Set<String>, Set<Integer>> synsetKey = new HashMap<Set<String>, Set<Integer>>();

    /* Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In inSyn = new In(synsetFilename);
        In inHypo = new In(hyponymFilename);
        mapSynset(inSyn);
        mapHyponyms(inHypo);

    }

    /* Returns hyponyms of word in a Set<String>. */
    public Set<String> hyponyms(String word) {
        Digraph g = new Digraph(mappedSynsetIntKey.size());

        for (Integer hypoKey : mappedHyponyms.keySet()) {
            buildDescendents(hypoKey, mappedHyponyms.get(hypoKey), g);
        }

        Set<String> setHyponym = new TreeSet<String>();
        Set<Integer> integer = new TreeSet<Integer>();
        for (Set<String> elem : mappedSynsetIntKey.values()) {
   
            if (elem.contains(word)) {
                integer.add(getKeyfromValue(mappedSynsetIntKey, elem));
                setHyponym.addAll(elem);
            }
        }

        for (Integer in : GraphHelper.descendants(g, integer)) {
            for (String str : mappedSynsetIntKey.get(in)) {
                setHyponym.add(str);
            }
        }
        setHyponym.add(word);
        return setHyponym;
    }

    private <K, V> K getKeyfromValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /* Returns true if noun is a noun. */
    public boolean isNoun(String noun) {
        if (nouns().contains(noun)) {
            return true;
        }
        return false;
    }

    /* returns a Set<String> of all nouns in a WordNet object. */
    public Set<String> nouns() {
        Set<String> listOfNouns = new TreeSet<String>();
        for (String keys : mappedSynsetStrKey.keySet()) {
            listOfNouns.add(keys);    
        }
        return listOfNouns;
    }

    /* Maps Synset with String Keys and Integer Keys. */
    private void mapSynset(In input) {
        while (input.hasNextLine()) {
            Set<String> string = new TreeSet<String>();
            
            String line = input.readLine();

            String[] tokens = line.split(",");
            String[] synsetWords = tokens[1].split(" ");

            Set<Integer> integers = new TreeSet<Integer>();
            integers.add(Integer.parseInt(tokens[0]));

            for (int i = 0; i < synsetWords.length; i++) {
                string.add(synsetWords[i]);
                if (!mappedSynsetStrKey.containsKey(synsetWords[i])) {
                    mappedSynsetStrKey.put(synsetWords[i], integers);

                } else {
                    integers.addAll(mappedSynsetStrKey.get(synsetWords[i]));
                    mappedSynsetStrKey.put(synsetWords[i], integers);
                }
            }
            if (!synsetKey.containsKey(string)) {
                synsetKey.put(string, integers);
            } else {
                integers.addAll(synsetKey.get(string));
                synsetKey.put(string, integers);
            }
            mappedSynsetIntKey.put(Integer.parseInt(tokens[0]), string);
        }
    }

    private void buildDescendents(int a, Set<Integer> set, Digraph graph) {
        for (Integer elem : set) {
            graph.addEdge(a, elem);
        }
    }

    /* Maps Hyponyms */
    private void mapHyponyms(In input) {
        String delim = ",";
        String[] tokens;
        String line;

        while (input.hasNextLine()) {
            line = input.readLine();
            tokens = line.split(delim);
            Set<Integer> integers = new TreeSet<Integer>();
            for (int i = 0; i < tokens.length; i++) {
                integers.add(Integer.parseInt(tokens[i]));
                if (mappedHyponyms.containsKey(Integer.parseInt(tokens[0]))) {
                    integers.addAll(mappedHyponyms.get(Integer.parseInt(tokens[0])));
                    mappedHyponyms.put(Integer.parseInt(tokens[0]), integers);
                } else {
                    mappedHyponyms.put(Integer.parseInt(tokens[0]), integers);
                }
            }
            mappedHyponyms.put(Integer.parseInt(tokens[0]), integers);
        }
    }
}
