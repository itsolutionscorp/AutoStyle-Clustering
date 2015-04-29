package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;

import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private In synset;
    private In hyponyms;
    private Digraph g;
    private HashMap<Integer, String[]> search;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    /** Help from Jiayuan Hu */
    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synset = new In(synsetFilename);
        this.hyponyms = new In(hyponymFilename);
        HashMap<Integer, String[]> searchTEMP = new HashMap<Integer, String[]>();
        String synsetline = synset.readLine();

        while (synsetline != null) {
            String [] synsetsplit = synsetline.split(",");
            String [] synsetsplit2 = synsetsplit[1].split(" ");
            int synsetkey = Integer.parseInt(synsetsplit[0]);
            searchTEMP.put(synsetkey, synsetsplit2);
            synsetline = synset.readLine();
        }

        Digraph gTEMP = new Digraph(searchTEMP.size());
        String hyponymslist = hyponyms.readLine();

        while (hyponymslist != null) {
            String [] hyponymssplit = hyponymslist.split(",");
            for (int i = 1; i < hyponymssplit.length; i++) {
                gTEMP.addEdge(Integer.parseInt(hyponymssplit[0]), 
                    Integer.parseInt(hyponymssplit[i]));
            }
            hyponymslist = hyponyms.readLine();
        }
        this.g = gTEMP;
        this.search = searchTEMP;
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String[] valuelist : search.values()) {
            // for (int i = 0; i < valuelist.length; i++) {
            for (String word : valuelist) {
                if (noun.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allnouns = new HashSet<String>();
        for (String[] valuelist : search.values()) {
            for (int i = 0; i < valuelist.length; i++) {
                allnouns.add(valuelist[i]);
            }
        }
        return allnouns;
    }

    /* Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms. */
      
    public Set<String> hyponyms(String word) {
        Set<Integer> index = new HashSet<Integer>();
        for (Integer key : search.keySet()) {
            for (int i = 0; i < search.get(key).length; i++) {
                if (word.equals(search.get(key)[i])) {
                    index.add(key);
                }
            }
        }
        Set<Integer> fullindex = GraphHelper.descendants(g, index);

        Set<String> allword = new HashSet<String>();
        for (Integer key : fullindex) {
            for (String verbol : search.get(key)) {
                allword.add(verbol);
            }
        }
        return allword;
    }
    
}
