package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

public class WordNet {
    private HashMap<Integer, HashSet<String>> synset;
    private Digraph dg;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In in1 = new In(synsetFilename);
        synset = new HashMap<Integer, HashSet<String>>();
        while (in1.hasNextLine()) {
            String line = in1.readLine();
            String[] splitLine = line.split(",");
            int id = Integer.parseInt(splitLine[0]);
            HashSet<String> s = new HashSet<String>(Arrays.asList(splitLine[1].split(" ")));
            synset.put(id, s);
        }
        
        In in2 = new In(hyponymFilename);
        dg = new Digraph(synset.size());
        while (in2.hasNextLine()) {
            String line = in2.readLine();
            String[] splitLine = line.split(",");
            int sID = Integer.parseInt(splitLine[0]);
            HashSet<Integer> s = new HashSet<Integer>();
            for (int i = 1; i < splitLine.length; i += 1) {
                s.add(Integer.parseInt(splitLine[i]));
            }
            for (Integer i : s) {
                dg.addEdge(sID, i);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (nouns().contains(noun));
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> toReturn = new HashSet<String>();
        for (Integer k : synset.keySet()) {
            toReturn.addAll(synset.get(k));
        }
        return toReturn;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        //edge case
        if (!isNoun(word)) {
            return new HashSet<String>();
        }
        Set<String> toReturn = new HashSet<String>();
        Set<Integer> vertices = new HashSet<Integer>();
        for (Integer k : synset.keySet()) {
            if (synset.get(k).contains(word)) {
                vertices.add(k); 
            }
        }
        //gets all of the descendants of the vertices with the word
        Set<Integer> descendants = GraphHelper.descendants(dg, vertices);
        for (Integer v : descendants) {
            toReturn.addAll(synset.get(v));
        }
        return toReturn;
    }
}
