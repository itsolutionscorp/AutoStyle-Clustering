package ngordnet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME **/
    
    private TreeMap<Integer, TreeSet<String>> synsets;
    private TreeMap<Integer, TreeSet<Integer>> hyponyms;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetin = new In(synsetFilename);      
        In hyponymin = new In(hyponymFilename);
       

        synsets = new TreeMap<Integer, TreeSet<String>>();
        while (synsetin.hasNextLine()) {
            String current = synsetin.readLine();
            Integer currkey = Integer.parseInt(current.split(",")[0]);
            String nouns = current.split(",")[1];
            TreeSet<String> wordlist = new TreeSet<String>();
            for (String word : nouns.split(" ")) {
                wordlist.add(word);
            }
            synsets.put(currkey, wordlist);
        }
        /** Digraph and all GraphHelper related references derive from provided files */
        g = new Digraph(synsets.size());
        hyponyms = new TreeMap<Integer, TreeSet<Integer>>();
        while (hyponymin.hasNextLine()) {
            String current = hyponymin.readLine();
            Integer currkey = Integer.parseInt(current.split(",")[0]);
            TreeSet<Integer> values = new TreeSet<Integer>();
            for (int i = 1; i < current.split(",").length; i++) {
                values.add(Integer.parseInt(current.split(",")[i]));
            }
            hyponyms.put(currkey, values);
            for (int v : values) {
                g.addEdge(currkey, v);
            }
        }
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (TreeSet<String> v : synsets.values()) {
            for (String word : v) {
                if (noun.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allnouns = new TreeSet<String>();
        for (TreeSet<String> nounlist : synsets.values()) {
            for (String word : nounlist) {
                allnouns.add(word);
            }
        }
        return allnouns;
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */

    public Set<String> hyponyms(String words) {
        Set<String> allhyponyms = new TreeSet<String>();
        Set<Integer> direct = GraphHelper.descendants(g, idLookup(words));
        for (int k : direct) {
            for (String s : synsets.get(k)) {
                allhyponyms.add(s);
            } 
        }
        return allhyponyms;
    }
    
    /** Custom ID lookup function used in creating hyponym list */
    private Set<Integer> idLookup(String word) {
        Set<Integer> allIds = new TreeSet<Integer>();
        for (int k : synsets.keySet()) {
            for (String v : synsets.get(k)) {
                if (word.equals(v)) {
                    allIds.add(k);    
                }
            }  
        }
        return allIds; 
    }
    
}
