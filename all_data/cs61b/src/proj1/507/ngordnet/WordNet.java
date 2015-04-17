package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private In syns;
    private In hypo;
    private HashMap<Integer, HashSet<String>> idword = new HashMap<>();
    private Digraph beauty;
    private Integer counter = 0;

    public WordNet(String synsetFilename, String hyponymFilename) {
        syns = new In(synsetFilename);
        hypo = new In(hyponymFilename);

        while (syns.hasNextLine()) {
            String s = syns.readLine();
            String[] ret = s.split(",");
            int id = Integer.parseInt(ret[0]);
            String word = ret[1];
            HashSet<String> nounz = new HashSet<>();

            String[] splitted = word.split("\\s+");
            if (splitted.length > 1) {
                for (String w : splitted) {
                    nounz.add(w);
                }
            } else {
                nounz.add(word);
            }
            idword.put(id, nounz);
            counter = counter + 1;         
        }

        beauty = new Digraph(counter);
        
        while (hypo.hasNextLine()) {
            String h = hypo.readLine();
            String[] ok = h.split(",");
            if (ok.length > 2) {
                for (int i = 1; i < ok.length; i++) {
                    beauty.addEdge(Integer.parseInt(ok[0]), Integer.parseInt(ok[i]));
                }
            } 
            String hyper = ok[0];
            String hyponym = ok[1];
            beauty.addEdge(Integer.parseInt(hyper), Integer.parseInt(hyponym));
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer key : idword.keySet()) {
            HashSet theset = idword.get(key);
            if (theset.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> allnouns = new HashSet<String>();
        for (Integer key : idword.keySet()) {
            HashSet sushi = idword.get(key);
            allnouns.addAll(sushi);
        }
        return allnouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

        HashSet<Integer> synsids = new HashSet<Integer>();
        HashSet<String> pho = new HashSet<>();

        for (Integer i : idword.keySet()) {
            if (idword.get(i).contains(word)) {
                synsids.add(i);
            }
        }

        Set<Integer> children = GraphHelper.descendants(beauty, synsids);
        HashSet<String> rethypo = new HashSet<String>();
        for (Integer index : children) {
            HashSet<String> pizza = idword.get(index);
            rethypo.addAll(pizza);
        }

        return rethypo;
    }
}
