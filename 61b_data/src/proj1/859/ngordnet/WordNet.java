package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Map<Integer, Synset> synsetMapper = new HashMap();
    private Digraph digraph;
    private Set<String> synSet = new HashSet<String>(); // set of all nouns
    private Map<Integer, Set<String>> hyponymSet = new HashMap<Integer, Set<String>>();
    private Map<String, ArrayList<Integer>> reverseHyponymSet;
    private HashSet<String> subsynSet;
    private In hyponymInput;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        reverseHyponymSet = new HashMap<String, ArrayList<Integer>>();

        In synsetInput = new In(synsetFilename);
        hyponymInput = new In(hyponymFilename);
        /** Add stuff to synset */
        while (synsetInput.hasNextLine()) {

            String synKey = synsetInput.readLine();
            String[] tempVals = synKey.split(","); // splits synsets.txt

            int key = Integer.parseInt(tempVals[0]);

            String[] synsets = tempVals[1].split(" "); // grabs all nouns
            Set<String> synTemp = new HashSet<String>(); // grab each item
            for (String i : synsets) {

                /** adds each noun to the WHOLE SET OF NOUNS */
                synSet.add(i.toString());

                /** adds each noun to a temp set and reset after each iteration. */
                synTemp.add(i.toString());

                ArrayList<Integer> keyList = new ArrayList<>();
                keyList.add(key);
                if (reverseHyponymSet.containsKey(i)) {
                    reverseHyponymSet.get(i).add(key);
                } else {
                    reverseHyponymSet.put(i, keyList);
                }
            }

            Synset synsetTemp = new Synset(key, synTemp, null);

            synsetMapper.put(key, synsetTemp);
            /** Map string to key */
            for (String i : tempVals) { // go through each number in each line
                subsynSet = new HashSet<String>(synsetMapper.get(key).synset);
                hyponymSet.put(key, subsynSet);
            }

        }
        digraph = new Digraph(synsetMapper.size());
        /** adds stuff to digraph */
        while (hyponymInput.hasNextLine()) {
            String hypoKey = hyponymInput.readLine();
            String[] hypoKeys = hypoKey.split(",");
            String key = hypoKeys[0];
            int keyToInt = Integer.parseInt(key);

            for (String each : hypoKeys) {
                if (each == key) { // you don't want to map two of the same
                    continue;
                }
                int edge = Integer.parseInt(each);
                digraph.addEdge(keyToInt, edge);
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synSet;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        /**
         * Build a set to go from String -> int Int needs to be an ArrayList.
         */
        ArrayList<Integer> temp = reverseHyponymSet.get(word);
        /**
         * create a new set with just (word) param
         */
        Set<Integer> digraphHelperSet = new HashSet<Integer>();

        for (int i : temp) { // Iterate through the ArrayList of word.
            digraphHelperSet.add(i);
        }
        /** this grabs the descendants to (word) param */
        digraphHelperSet = GraphHelper.descendants(digraph, digraphHelperSet);

        Set<String> ret = new HashSet<String>();
        for (int i : digraphHelperSet) {
            ret.addAll(synsetMapper.get(i).synset);
        }
        return ret;
    }

    private class Synset {
        int key;
        Set<String> synset;
        String definition;

        public Synset(int k, Set<String> syn, String def) {
            key = k;
            synset = syn;
            definition = def;
        }

    }

}
