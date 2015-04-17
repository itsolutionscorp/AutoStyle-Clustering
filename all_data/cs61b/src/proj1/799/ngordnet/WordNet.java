package ngordnet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.AbstractMap;
import java.util.Arrays;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private List<Map.Entry<String[], String>> synsets;
    private Map<String, Set<Integer>> nouns;
    private Digraph hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) { 

        synsets = new ArrayList<Map.Entry<String[], String>>();
        nouns = new HashMap<String, Set<Integer>>();
        readSynset(synsetFilename);

        hyponyms = new Digraph(nouns.size());
        readHyponym(hyponymFilename);
        
    }

    private void readSynset(String filename) {

        In synFile = new In(filename);
        while (synFile.hasNextLine()) {

            String[] elements = synFile.readLine().split(",");
            int index = Integer.parseInt(elements[0]);
            String[] synonyms = elements[1].split(" ");
            String gloss = elements[2];
            synsets.add(index, new AbstractMap.SimpleEntry<String[], String>(synonyms, gloss));
            for (String s : synonyms) {
                Set<Integer> ids = nouns.containsKey(s) ? nouns.get(s) : new HashSet<Integer>();
                ids.add(index);
                nouns.put(s, ids);
            }
        }

    }

    private void readHyponym(String filename) {

        In hypFile = new In(filename);
        while (hypFile.hasNextLine()) {

            String[] elements = hypFile.readLine().split(",");
            int hyper = Integer.parseInt(elements[0]);
            for (int i = 1; i < elements.length; i++) {
                hyponyms.addEdge(hyper, Integer.parseInt(elements[i]));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {

        return nouns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        return nouns.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

        Set<String> hypos = new HashSet<String>();
        Set<Integer> ids = GraphHelper.descendants(hyponyms, nouns.get(word));
        for (int id : ids) {
            hypos.addAll(Arrays.asList(synsets.get(id).getKey()));
        }
        return hypos;

    }
}
