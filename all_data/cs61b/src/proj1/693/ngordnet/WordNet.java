package ngordnet;

import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.HashSet;

public class WordNet {
    private Digraph d;
    private Set<String> n;
    private HashSet<HashMap<String, Integer>> h;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        int size = dsetsize(hyponymFilename);
        d = new Digraph(size);
        n = new HashSet<String>();
        h = new HashSet<HashMap<String, Integer>>();
        dedgevertex(hyponymFilename, d);
        setnoun(synsetFilename, n);
        setsynsets(synsetFilename, h);
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return n.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return n;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return null;
        }
        TreeSet<Integer> halfresult = new TreeSet<Integer>();
        getint(word, halfresult);
        Set<Integer> gh = GraphHelper.descendants(d, halfresult);
        Set<String> realresult = new HashSet<String>();
        for (HashMap<String, Integer> t : h) {
            for (Integer x : gh) {
                if (t.containsValue(x)) {
                    for (int a = 0; a < t.size(); a++) {
                        String i = (String) t.keySet().toArray()[a];
                        realresult.add(i);
                    }
                }
            }
        }
        return realresult;
    }

    /* get the Integer value of word from synsets*/
    private TreeSet<Integer> getint(String word, TreeSet<Integer> nlist) {
        for (HashMap<String, Integer> t : h) {
            Integer i = t.get(word);
            if (i != null) {
                nlist.add(i);
            }
        }
        return nlist;
    }

    /*set synsets*/
    private void setsynsets(String synsetFilename, HashSet<HashMap<String, Integer>> hs) {
        In s = new In(synsetFilename);
        while (s.hasNextLine()) {
            String[] inputs = s.readLine().split(",");
            HashMap<String, Integer> a = new HashMap<String, Integer>();
            Integer hyp = Integer.parseInt(inputs[0]);
            String[] annoyinginput = inputs[1].split("\\s");
            for (int j = 0; j < annoyinginput.length; j++) {
                a.put(annoyinginput[j], hyp);
            } 
            hs.add(a);   
        } 
    }
    

    /* set nouns*/
    private void setnoun(String synsetFilename, Set<String> no) {
        In s = new In(synsetFilename);
        while (s.hasNextLine()) {
            String[] inputs = s.readLine().split(",");
            String[] annoyinginput = inputs[1].split("\\s");
            for (int j = 0; j < annoyinginput.length; j++) {
                no.add(annoyinginput[j]);
            }    
        }
    }

    /* set size of digraph*/
    private int dsetsize(String hyponymFilename) {
        In hyp = new In(hyponymFilename);
        int c = 0;
        while (hyp.hasNextLine()) {
            String[] out = hyp.readLine().split(",");
            for (int i = 0; i < out.length; i++) {
                if (Integer.parseInt(out[i]) > c) {
                    c = Integer.parseInt(out[i]);
                }
            }
        }
        return c + 1;
    }

    /* set hyponyms of digraph*/
    private void dedgevertex(String hyponymFilename, Digraph di) {
        In hyp = new In(hyponymFilename);
        while (hyp.hasNextLine()) {
            String[] inputs = hyp.readLine().split(",");        
            for (int i = 1; i < inputs.length; i++) {
                di.addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[i]));
            }
        }
    }   
}
