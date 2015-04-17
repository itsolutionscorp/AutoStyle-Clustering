package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

public class WordNet {
    private TreeMap<Integer, TreeSet<String>>  synsetMap;
    private TreeMap<Integer, TreeSet<Integer>> hyponymMap;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetInput = new In(synsetFilename);
        synsetMap = new TreeMap<Integer, TreeSet<String>>();

        while (synsetInput.hasNextLine()) {
            String[] segments =  synsetInput.readLine().split(",");
            Integer index = new Integer(segments[0]);

            TreeSet<String> synset = new TreeSet<String>();
            for (String synonym: segments[1].split(" ")) {
                synset.add(synonym);
            }
            synsetMap.put(index, synset);
        }

        In hyponymInput = new In(hyponymFilename);
        hyponymMap = new TreeMap<Integer, TreeSet<Integer>>();

        while (hyponymInput.hasNextLine()) {
            String[] segments =  hyponymInput.readLine().split(",");
            Integer index = new Integer(segments[0]);
            
            if (hyponymMap.get(index) == null) {
                TreeSet<Integer> hyponymIndexes = new TreeSet<Integer>();
                for (int i = 1; i < segments.length; i++) {
                    hyponymIndexes.add(new Integer(segments[i]));
                }
                hyponymMap.put(index, hyponymIndexes);
            } else {
                TreeSet<Integer> existingHyponymIndexes = hyponymMap.get(index);
                for (int i = 1; i < segments.length; i++) {
                    existingHyponymIndexes.add(new Integer(segments[i]));
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (TreeSet<String> synset: synsetMap.values()) {
            for (String noun: synset) {
                allNouns.add(noun);
            }
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> allHyponyms = new TreeSet<String>();
        for (Integer hyponymKey: hyponymMap.keySet()) {
            if (synsetMap.get(hyponymKey).contains(word)) {
                allHyponyms.addAll(childrenOfHyponyms(hyponymKey));
            }
        }
        for (TreeSet<String> synset: synsetMap.values()) {
            if (synset.contains(word)) {
                allHyponyms.addAll(synset);
            }
        }
        return allHyponyms;
    }

    private Set<String> childrenOfHyponyms(Integer hyponymIndex) {
        Set<String> allHyponyms = new TreeSet<String>();
        if (hyponymMap.get(hyponymIndex) != null) {
            for (Integer index: hyponymMap.get(hyponymIndex)) {
                allHyponyms.addAll(synsetMap.get(index));
                allHyponyms.addAll(childrenOfHyponyms(index));
            }
        }
        return allHyponyms;
    }
}
