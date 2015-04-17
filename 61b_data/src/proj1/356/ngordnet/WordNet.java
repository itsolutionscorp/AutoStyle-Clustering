//sandy shao
package ngordnet;

import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.DirectedDFS;

import java.util.ArrayList;

public class WordNet {

    private TreeMap<Integer, String> synsetmap = new TreeMap<Integer, String>();
    private Digraph hypograph;
    private TreeSet<String> synset = new TreeSet<String>();


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetfile = new In(synsetFilename);
        In hyponymfile = new In(hyponymFilename);
        In hyponymfile1 = new In(hyponymFilename);
        int hypographsize = 0;


      
        //synset map//
        while (synsetfile.hasNextLine()) {
            String [] idsynset = synsetfile.readLine().split(",");
            int key = Integer.parseInt(idsynset[0]);
            String synset1 = idsynset[1];
            String [] sList = synset1.split(" ");
            for (String s : sList) {
                synset.add(s);
            }
            synsetmap.put(key, synset1);
        }
        //hyponym digraph//
        String [] hyponym = hyponymfile.readAllStrings();
        hypographsize = hyponym.length * hyponym.length;

        this.hypograph = new Digraph(hypographsize);
      
        while (hyponymfile1.hasNextLine()) {
            String [] hyponum = hyponymfile1.readLine().split(",");
            int numlength = hyponum.length; 
            
            int firstnum = Integer.parseInt(hyponum[0]); 
            int counter = 1;
            while (counter < numlength) {
                int secondnum = Integer.parseInt(hyponum[counter]);
                hypograph.addEdge(firstnum, secondnum);
                counter += 1;
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String s : synset) {
            if (s.equals(noun)) {
                return true;
            }
        }
        return false;
    }


    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synset;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    private Set<Integer> keyValue(String value) {
        Set<Integer> key = synsetmap.keySet();
        Set<Integer> newK = new TreeSet<Integer>();
        for (Integer i : key) {
            String val = synsetmap.get(i);
            if (val.equals(value)) {
                newK.add(i);
            }
        }
        return newK;
    }


    public Set<String> hyponyms(String word) {
        TreeSet<String> idset = new TreeSet<String>();
        
        Set<Integer> idset1 = keyValue(word);

        DirectedDFS dfdp = new DirectedDFS(hypograph, idset1);
        TreeSet<Integer> reachable = new TreeSet<Integer>();

        for (int i = 0; i < hypograph.V(); i += 1) {
            if (dfdp.marked(i)) {
                reachable.add(i);
            }
        }
        ArrayList<String> noun = new ArrayList<String>();
        for (int id : reachable) {
            noun.add(synsetmap.get(id));
            for (String s : noun) {
                synset.add(s);
            }
        }

        return synset; 
    }
}
