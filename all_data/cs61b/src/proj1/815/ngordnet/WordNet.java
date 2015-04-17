/*----------------------------------------------------------------
 *  Author:        Alex Yang
 *  Written:       3/4/2015
 *  Last updated:  3/4/2015  
 *----------------------------------------------------------------*/
package ngordnet;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/** Class file that reads data from WordNet and queries hyponyms of words */
public class WordNet {

    private HashMap<String, TreeSet<Integer>> wordIdMap = new HashMap<String, TreeSet<Integer>>();
    private HashMap<Integer, TreeSet<String>> idWordMap = new HashMap<Integer, TreeSet<String>>();
    private Digraph graph;

    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     * 
     * @param synsetFilename
     *            contains synsets
     * @param hyponymFilename
     *            contains hyponyms
     */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetInput = new In(synsetFilename);
        In hyponymInput = new In(hyponymFilename);
        // Read data from Synset files and build up wordIdMap and the idWordMap.
        String line;
        int temp = 0;
        while ((line = synsetInput.readLine()) != null) {
            temp = temp + 1;
            String[] lineField = line.split(",");
            // StdOut.println(line);
            int synId = Integer.parseInt(lineField[0]);
            String[] synset = lineField[1].split(" ");
            TreeSet<String> stringset = new TreeSet<String>();
            for (String word : synset) {
                TreeSet<Integer> idset = wordIdMap.get(word);
                if (idset == null) {
                    idset = new TreeSet<Integer>();
                    wordIdMap.put(word, idset);
                }
                idset.add(synId);
                stringset.add(word);
            }
            idWordMap.put(synId, stringset);
        }
        // read data from hyponymfile and create a hyponym graph
        graph = new Digraph(temp);
        while ((line = hyponymInput.readLine()) != null) {
            String[] lineField = line.split(",");
            // StdOut.println(line);
            int sourceid = Integer.parseInt(lineField[0]);
            for (int i = 1; i < lineField.length; i++) {
                graph.addEdge(sourceid, Integer.parseInt(lineField[i]));
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordIdMap.containsKey(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordIdMap.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        if (!wordIdMap.containsKey(word)) {
            throw new IllegalArgumentException();
        }
        // step1: find all the synset ids of this word
        TreeSet<Integer> idset = wordIdMap.get(word);
        // step2: given ids, find all the id's descendants
        Set<Integer> returnset = GraphHelper.descendants(graph, idset);
        // step3: use HashMap to convert ids to string of words
        TreeSet<String> finalset = new TreeSet<String>();
        for (Integer i : returnset) {
            TreeSet<String> temp = idWordMap.get(i);
            finalset.addAll(temp);
        }
        return finalset;
    }
}
