package ngordnet;

import java.util.Arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

// use of Digraph,In comes from jar files in lib. 
// Digraph,In in package so as not to cause error in eclipse

// use stdlib.jar
// use algs4.jar

public class WordNet {
    private In synsetfile;
    private In hyponymfile;
    private HashMap<String, String[]> body = new HashMap<String, String[]>();
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetfile = new In(synsetFilename);
        hyponymfile = new In(hyponymFilename);
        // can print out String from synsetfile.printLine() here
        // somereason not in WordNetTests.java ask TA about this?
        while (!synsetfile.isEmpty()) {
            String line = synsetfile.readLine();
            // System.out.println(line); I see that this prints out correctly

            String[] splitline = line.split(",");
            String[] nouns = splitline[1].split(" ");
            body.put(splitline[0], nouns);

        }
        // create Digraph with body.size() verticies at this point?
        g = new Digraph(body.size());
        while (!hyponymfile.isEmpty()) {
            String line2 = hyponymfile.readLine();
            // System.out.println(line2);

            String[] splitline2 = line2.split(",", 2);
            String[] nouns2 = splitline2[1].split(",");
            for (int i = 0; i < nouns2.length; i++) {
                g.addEdge(Integer.parseInt(splitline2[0]),
                        Integer.parseInt(nouns2[i]));
            }

        }

    }

    // * Returns true if NOUN is a word in some synset. (Thereby is it a noun)
    // */
    public boolean isNoun(String noun) {
        if (noun.split(" ").length == 1) {
            for (String s : body.keySet()) {
                String[] temparray = body.get(s);
                if (Arrays.asList(temparray).contains(noun)) {
                    return true;
                }
            }
        }
        return false;

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        Set<String> returnset = null;

        for (String s : body.keySet()) {
            Set<String> mySet = new HashSet<String>(Arrays.asList(body.get(s)));
            if (returnset == null) {
                returnset = mySet;
            }
            returnset.addAll(mySet);
        }
        return returnset;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        // check is noun
        // take index of word, find all descendents, take synset at each one
        Set<Integer> idsofword = new HashSet<Integer>();

        if (isNoun(word)) {
            for (String s : body.keySet()) {
                if (Arrays.asList(body.get(s)).contains(word)) {
                    idsofword.add(Integer.parseInt(s));
                }
            }
            Set<String> toreturn = null;
            Set<Integer> idsofwhatsneeded = GraphHelper.descendants(g,
                    idsofword);
            for (Integer h : idsofwhatsneeded) {
                Set<String> mySet = new HashSet<String>(Arrays.asList(body
                        .get(String.valueOf(h))));
                if (toreturn == null) {
                    toreturn = mySet;
                }
                toreturn.addAll(mySet);
            }
            return toreturn;
        }
        return null;
    }

}
