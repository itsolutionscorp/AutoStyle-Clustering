package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;

/** Creates a WordNet class.
 *  @author Ruomeng (Michelle) Yang
 *  Worked with Dannver Wu
 */

public class WordNet {
    private In synsetFilename;
    private In hyponymFilename;
    private Digraph hyponymsDia;
    private TreeMap<Integer, Set<String>> synsetsMap;
    private TreeSet<String> nounSet;

    /* SYNSETS and HYPONYMS are filenames for the input files. */
    public WordNet(String synsets, String hyponyms) {
        synsetFilename = new In(synsets);
        hyponymFilename = new In(hyponyms);
        synsetsMap = new TreeMap<Integer, Set<String>>();
        nounSet = new TreeSet<String>();

        String splitBy = ",";
        String line;
        String[] parsed;
        while (!synsetFilename.isEmpty()) {
            line = synsetFilename.readLine();
            parsed = line.split(splitBy);
            String[] allNouns = parsed[1].split(" ");
            for (String s : allNouns) {
                nounSet.add(s);
            }
            synsetsMap.put(Integer.valueOf(parsed[0]), toSet(allNouns));
        }

        hyponymsDia = new Digraph(synsetsMap.size());

        int id;
        while (!hyponymFilename.isEmpty()) {
            line = hyponymFilename.readLine();
            parsed = line.split(splitBy);
            id = Integer.valueOf(parsed[0]);
            for (int c = 0; c < parsed.length; c++) {
                hyponymsDia.addEdge(id, Integer.valueOf(parsed[c]));
            }
        }
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /* Returns true if WORD is a noun. WORD may be a collocation. */
    public boolean isNoun(String word) {
        return nouns().contains(word);
    }

    /* Returns the set of all hypoynms and synonyms of WORD.
     *  If WORD belongs to multiple synsets, returns all hyponyms of
     *  all these synsets. Hyponyms of synonyms are not included. */
    public Set<String> hyponyms(String word) {
        Set<String> result = new TreeSet<String>();
        Set<Integer> synsetIDs = findSynsetIDs(word);
        Set<Integer> ids = GraphHelper.descendants(hyponymsDia, synsetIDs);
        for (Integer id : ids) {
            Set<String> nounList = synsetsMap.get(id);
            for (String s : nounList) {
                result.add(s);
            }
        }
        return result;
    }

    /* Returns a set of integers for correct vertices */
    private Set<Integer> findSynsetIDs(String word) {
        Set<Integer> sids = new TreeSet<Integer>();
        if (!isNoun(word)) {
            return sids;
        }
        for (Integer i : synsetsMap.keySet()) {
            Set<String> nounList = synsetsMap.get(i);
            for (String s : nounList) {
                if (s.equals(word)) {
                    sids.add(i);
                }
            }
        }
        return sids;
    }

    /* Returns a set of an array */
    private Set<String> toSet(String[] words) {
        TreeSet<String> result = new TreeSet<String>();
        for (String w : words) {
            result.add(w);
        }
        return result;
    }
}
