package ngordnet;

import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph dgraph;
    private String[][] map;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetin = new In(synsetFilename);
        In hyponymin = new In(hyponymFilename);

        String[] synsets = synsetin.readAllLines();
        String[] hyponyms = hyponymin.readAllLines();

        map = new String[synsets.length][];

        int counter = 0;
        for (String line : synsets) {
            String[] p = line.split(",");
            String[] n = p[1].split(" ");
            map[counter] = n;
            counter += 1;
        }

        dgraph = new Digraph(synsets.length);

        for (String line : hyponyms) {
            String[] p = line.split(",");
            int s = Integer.parseInt(p[0]);
            for (int i = 1; i < p.length; i++) {
                dgraph.addEdge(s, Integer.parseInt(p[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String[] line : map) {
            for (String word : line) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>(); 
        for (String[] line : map) {
            for (String word : line) {
                nounSet.add(word);
            }
        }
        return nounSet;
    }

  /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymSet = new HashSet<String>();
        Set<Integer> des = new HashSet<Integer>();
        int counter = 0;
        for (String[] line : map) {
            for (String noun : line) {
                if (noun.equals(word)) {
                    des.add(counter);
                    for (Integer i : GraphHelper.descendants(dgraph, des)) {
                        des.add(i);
                    }
                    for (Integer i : des) {
                        for (String h : map[i]) {
                            hyponymSet.add(h);
                        }
                    }
                }
            }
            counter += 1;
        }
        return hyponymSet;
    }
}

