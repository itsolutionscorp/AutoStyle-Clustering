/** WordNet class, reads synsets and hyponyms
 *  @author Arshi Aggarwal
 */
package ngordnet;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private HashMap<Integer, String[]> map;
    private Digraph dihypo;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        map = new HashMap<Integer, String[]>();
        In file = new In(synsetFilename);
        while (file.hasNextLine()) {
            String[] line = file.readLine().split(",");
            map.put(Integer.parseInt(line[0]), line[1].split(" "));
        }

        In file2 = new In(hyponymFilename);
        dihypo = new Digraph(map.size());
        while (file2.hasNextLine()) {
            String[] line2 = file2.readLine().split(",");
            Integer key = Integer.parseInt(line2[0]);
            int i = 1;
            while (i < line2.length) {
                dihypo.addEdge(key, Integer.parseInt(line2[i]));
                i++;
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (Integer key: map.keySet()) {
            for (String s: map.get(key)) {
                nouns.add(s);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets.
      */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return new HashSet<String>();
        }

        Set<Integer> synsetIDs = new HashSet<Integer>();
        for (Integer key: map.keySet()) {
            for (String s: map.get(key)) {
                if (word.equals(s)) {
                    synsetIDs.add(key);
                }
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(dihypo, synsetIDs);
        Set<String> hyponyms = new HashSet<String>();
        for (Integer i: descendants) {
            for (String s: map.get(i)) {
                hyponyms.add(s);
            }
        }
        return hyponyms;
    }
}
