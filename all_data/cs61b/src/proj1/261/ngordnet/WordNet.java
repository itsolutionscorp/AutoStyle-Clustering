package ngordnet;
import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<String, Set<String>> synsetHashmap = new HashMap<String, Set<String>>();
    private HashMap<String, Set<String>> hyponymHashmap = new HashMap<String, Set<String>>();
    private HashSet<String> subSynsetHashset;
    // private HashSet<String> subHyponymHashset = new HashSet<String>();
    private Digraph wordDigraph;
    private In readsynset;
    private In readhyponym;

    public WordNet(String synsetFilename, String hyponymFilename) {
        readsynset = new In(synsetFilename);
        readhyponym = new In(hyponymFilename);
        while (!readsynset.isEmpty()) {
            String synset = readsynset.readLine();
            String[] synsetArrayList = synset.split(",");
            subSynsetHashset = new HashSet<String>();
            for (String esubsynset:synsetArrayList[1].split(" ")) {
                subSynsetHashset.add(esubsynset);
            }
            synsetHashmap.put(synsetArrayList[0], subSynsetHashset);
        }
        wordDigraph = new Digraph(synsetHashmap.size());
        while (!readhyponym.isEmpty()) {
            String hyponym = readhyponym.readLine();
            String[] hyponymArrayList = hyponym.split(",");
            for (int i = 1; i < hyponymArrayList.length; i++) {
                int s1 = Integer.parseInt(hyponymArrayList[0]);
                int s2 = Integer.parseInt(hyponymArrayList[i]);
                wordDigraph.addEdge(s1, s2);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String keysSynsetHashmap:synsetHashmap.keySet()) {
            if (synsetHashmap.get(keysSynsetHashmap) != null) {
                if (synsetHashmap.get(keysSynsetHashmap).contains(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> resultset = new HashSet<String>();
        for (String keysSynsetHashmap: synsetHashmap.keySet()) {
            for (String e: synsetHashmap.get(keysSynsetHashmap)) {
                resultset.add(e);
            }
        }
        return resultset; 
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> synsetIDs = new HashSet();
        HashSet<String> resultHyponyms = new HashSet();
        for (String keyOld:synsetHashmap.keySet()) {
            if (synsetHashmap.get(keyOld).contains(word)) {
                synsetIDs.add(Integer.parseInt(keyOld));
            }
        }
        Set<Integer> setDescendants = GraphHelper.descendants(wordDigraph, synsetIDs);
        for (Integer inte:setDescendants) {
            for (String stringDescendants:synsetHashmap.get(Integer.toString(inte))) {
                resultHyponyms.add(stringDescendants);
            }
        }
        return resultHyponyms;
    }
}
