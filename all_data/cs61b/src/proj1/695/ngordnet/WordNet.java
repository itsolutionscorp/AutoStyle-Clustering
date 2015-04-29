package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import java.util.HashSet;


public class WordNet {
    private String synsetname;
    private String hypernymname;
    private HashMap<Integer, String> synsethash;
    private int[] arrayofh;
    private String[] rawTokens2;
    private Digraph d;
    private int v = 0;

    // Shared ideas on how to start and read files with Christine Chen (cs61b - Ajb) 
    //and Anokhi Kastia (cs61b - ait)
    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {
        synsetname = synsetFilename;
        hypernymname = hypernymFilename;
        In synsetfile = new In(new File(this.synsetname));
        In hypernymfile = new In(this.hypernymname);
        synsethash = new HashMap<Integer, String>();
        while (!synsetfile.isEmpty()) {
            v = v + 1;
            String line = synsetfile.readLine();
            String[] rawTokens = line.split(",");
            synsethash.put(Integer.parseInt(rawTokens[0]), rawTokens[1]);   
        }
        d = new Digraph(v);

        
        while (!hypernymfile.isEmpty()) {
            String line2 = hypernymfile.readLine();
            rawTokens2 = line2.split(",");
            arrayofh = new int[rawTokens2.length - 1];
            Integer synsetid = Integer.parseInt(rawTokens2[0]);
            for (int i = 1,  j = 0; i < (rawTokens2.length); i = i + 1, j = j + 1) {
                arrayofh[j] = Integer.parseInt(rawTokens2[i]);           
            }
            for (int h : arrayofh) { 
                d.addEdge(synsetid, h);
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<Integer> keys = synsethash.keySet();
        for (Integer key : keys) {
            String[] words = synsethash.get(key).split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(noun)) {
                    return true;
                } 
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        Set<Integer> keys = synsethash.keySet();
        for (Integer key : keys) {
            String[] words = synsethash.get(key).split(" ");
            for (int i = 0; i < words.length; i++) {
                nouns.add(words[i]);
            }
        }
        return nouns;
    }
    // cs61b-adb (Elmer Diaz) helped on hyponyms mehthod.  
    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<Integer> keys = synsethash.keySet();
        Set<Integer> actualkey = new HashSet<Integer>();
        for (Integer key : keys) {
            String[] words = synsethash.get(key).split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word)) {
                    actualkey.add(key);
                }
            }
        }
        Set<String> hyponyms = new HashSet<String>();
        hyponyms.add(word);
        for (Integer w : (GraphHelper.descendants(d, actualkey))) {
            String[] hyponym = synsethash.get(w).split(" ");
            for (int i = 0; i < hyponym.length; i++) {
                hyponyms.add(hyponym[i]);   
            }
        }
        return hyponyms;

    }

}
