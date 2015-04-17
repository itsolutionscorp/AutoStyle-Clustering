package ngordnet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, String[]> idToSynset = new HashMap<Integer, String[]>();
    private Map<String, Set<Integer>> synsetToId = new HashMap<String, Set<Integer>>();
    private Digraph idRelation;
    private int numSynsets = 0;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypoFile = new In(hyponymFilename);
        createMapAndSet(synFile);
        idRelation = new Digraph(numSynsets);
        createDigraph(hypoFile);
    }

    private void createMapAndSet(In file) {
        while (file.hasNextLine()) {
            String currLine = file.readLine();
            String[] eachColumn = currLine.split(",");

            int id = Integer.parseInt(eachColumn[0]);
            String synset = eachColumn[1];
            String[] synsetArr = synset.split(" ");

            idToSynset.put(id, synsetArr);

            for (int i = 0; i < synsetArr.length; i++) {
                if (synsetToId.containsKey(synsetArr[i])) {
                    synsetToId.get(synsetArr[i]).add(id);
                } else {
                    Set<Integer> idList = new HashSet<Integer>();
                    idList.add(id);
                    synsetToId.put(synsetArr[i], idList);
                }
            }
            numSynsets += 1;
        }
    }

    private void createDigraph(In file) {
        while (file.hasNextLine()) {
            String currLine = file.readLine();
            String[] eachColumn = currLine.split(",");

            int hyper = Integer.parseInt(eachColumn[0]);

            for (int i = 1; i < eachColumn.length; i++) {
                int hypo = Integer.parseInt(eachColumn[i]);
                idRelation.addEdge(hyper, hypo);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsetToId.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetToId.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> hyperIDs = synsetToId.get(word);
        Set<Integer> hypoIDs = GraphHelper.descendants(idRelation, hyperIDs);
        return idToHypo(hypoIDs);
    }

    private Set<String> idToHypo(Set<Integer> hypoIDs) {
        Set<String> hyponyms = new HashSet<String>();
        for (int id : hypoIDs) {
            String[] words = idToSynset.get(id);
            for (int i = 0; i < words.length; i++) {
                hyponyms.add(words[i]);
            }
        }
        return hyponyms;

    }

}
