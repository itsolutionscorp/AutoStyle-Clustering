package ngordnet;

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In; //?

public class WordNet {
    private Digraph dg;
    private Synset[] mysets; // each set as an object
    private Set<String> nouns; // list of all the nouns
    
    private class Synset {
        int id;
        String[] nouns;
        String def;
    
        public Synset(String line) {
            String[] parts = line.split(",");
            id = Integer.parseInt(parts[0]);
            nouns = parts[1].split(" ");
            def = parts[2];
        }
    }
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In sFile = new In(synsetFilename);
        In hFile = new In(hyponymFilename);
        String[] synsets = sFile.readAllLines();
        String[] hyponyms = hFile.readAllLines();
        
        // connect the graph
        dg = new Digraph(synsets.length);
        for (String line : hyponyms) {
            String[] nyms = line.split(",");
            int begin = Integer.parseInt(nyms[0]);
            for (int i = 1; i < nyms.length; ++i) {
                dg.addEdge(begin, Integer.parseInt(nyms[i]));
            }
        }
        
        // make synset list
        mysets = new Synset[synsets.length];
        for (int i = 0; i < synsets.length; ++i) {
            mysets[i] = new Synset(synsets[i]);
        }
        
        // make nouns set
        nouns = new TreeSet<String>();
        for (Synset s : mysets) {
            for (String n : s.nouns) {
                nouns.add(n);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        // Improve a bit so less hackish
        Set<String> hyponyms = new TreeSet<String>();
        Set<Integer> ids = new TreeSet<Integer>(); // ids of synsets that contain word

        // synonyms
        for (Synset set : mysets) {
            List<String> ns = Arrays.asList(set.nouns);
            if (ns.contains(word)) {
                hyponyms.addAll(ns);
                ids.add(set.id);
            }
        }
        
        // hyponyms
        Set<Integer> a = GraphHelper.descendants(dg, ids);
        a.addAll(ids);
        for (Integer i : a) {
            hyponyms.addAll(Arrays.asList(mysets[i].nouns));
        }
        return hyponyms;
    }
}
