package ngordnet;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    private HashMap<Integer, String> synMap;

    private String synsetFile;
    private String hyponymFile;
    private String[] synArray;
    private String[] hypArray;

    private Set<String> tempReturn;

    private In synFileInput;
    private In hypFileInput;

    private Digraph structure;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = synsetFilename;
        hyponymFile = hyponymFilename;
        synFileInput = new In(synsetFile);
        hypFileInput = new In(hyponymFile);
        synMap = new HashMap<Integer, String>();

        while (synFileInput.hasNextLine()) {
            String synFileInput2 = synFileInput.readLine();
            synArray = synFileInput2.split(",");
            int key = Integer.parseInt(synArray[0]);
            String value = synArray[1];
            synMap.put(key, value);
        }

        structure = new Digraph(synMap.size());

        while (hypFileInput.hasNextLine()) {
            String hypFileInput2 = hypFileInput.readLine();
            hypArray = hypFileInput2.split(",");

            int initVertex = Integer.parseInt(hypArray[0]);
            for (int i = 1; i < hypArray.length; i += 1) {
                structure.addEdge(initVertex, Integer.parseInt(hypArray[i]));
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
        for (String values : synMap.values()) {
            String[] valArray = values.split(" ");
            for (String each : valArray) {
                allNouns.add(each);
            }
        }
        return allNouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new HashSet<String>();
        Set<String> preReturn = new HashSet<String>();
        Set<Integer> findDescendants = new HashSet<Integer>();
        Set<Integer> keys = synMap.keySet();

        for (String values : synMap.values()) {
            String[] valArray = values.split(" ");
            for (String each : valArray) {
                if (each.equals(word)) {
                    for (String each2 : valArray) {
                        toReturn.add(each2);
                    }
                }
            }
        }

        for (Integer checkKey : keys) {
            String wordList = synMap.get(checkKey);
            String[] valArray = wordList.split(" ");
            for (String each : valArray) {
                if (each.equals(word)) {
                    findDescendants.add(checkKey);
                }
            }
        }

        Set<Integer> foundDescendants = GraphHelper.descendants(structure,
                findDescendants);

        for (Integer each : foundDescendants) {
            String wordList = synMap.get(each);
            String[] valArray = wordList.split(" ");
            for (String vals : valArray) {
                toReturn.add(vals);
            }
        }

        return toReturn;
    }
}
