package ngordnet;

import edu.princeton.cs.algs4.DirectedDFS;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.introcs.In;

import java.util.TreeMap;
import java.util.Iterator;

public class WordNet {
    private String[] intt;
    private String[] words;
    private In in5;
    private String[] wordset;
    private In in6;
    private TreeMap<Integer, Set<String>> map;
    private TreeMap<String, Set<Integer>> map2;
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In in = new In(synsetFilename);
        In in2 = new In(synsetFilename);
        In in4 = new In(synsetFilename);
        String[] p = in4.readAllLines();
        wordset = new String[p.length];
        while (in.hasNextLine()) {
            intt = in.readLine().split(",");
            int x = Integer.parseInt(intt[0]);
            wordset[x] = intt[1];

        }
        map = new TreeMap();
        map2 = new TreeMap();

        while (in2.hasNextLine()) {

            words = in2.readLine().split(",");
            String[] nm = words[1].split(" ");
            Set<String> words1 = new TreeSet();
            for (int k = 0; k < nm.length; k++) {

                words1.add(nm[k]);
            }
            int numb = Integer.parseInt(words[0]);
            map.put(numb, words1);
            String[] splitword = words[1].split(" ");
            for (int sk = 0; sk < splitword.length; sk++) {
                if (map2.containsKey(splitword[sk])) {
                    map2.get(splitword[sk]).add(Integer.parseInt(words[0]));
                } else {
                    Set<Integer> newvalue = new TreeSet();
                    newvalue.add(Integer.parseInt(words[0]));
                    map2.put(splitword[sk], newvalue);
                }
            }
        }

        In in3 = new In(hyponymFilename);

        in5 = new In(synsetFilename);
        in6 = new In(synsetFilename);

        g = new Digraph(p.length);
        while (in3.hasNextLine()) {
            intt = in3.readLine().split(",");

            for (int dk = 1; dk < intt.length; dk++) {

                int a = Integer.parseInt(intt[0]);
                int b = Integer.parseInt(intt[dk]);
                g.addEdge(a, b);
            }

        }

    }

    private static Set<Integer> descendants(Digraph G, Set<Integer> synsetIDs) {
        DirectedDFS dfdp = new DirectedDFS(G, synsetIDs);
        TreeSet<Integer> reachable = new TreeSet<Integer>();

        for (int i = 0; i < G.V(); i += 1) {
            if (dfdp.marked(i)) {
                reachable.add(i);
            }
        }
        return reachable;
    } /* Returns true if NOUN is a word in some synset. */

    public boolean isNoun(String noun) {

        for (int k = 0; k < wordset.length; k++) {
            String[] str = wordset[k].split(" ");

            for (int m = 0; m < str.length; m++) {

                if (str[m].equals(noun)) {
                    return true;
                }

            }

        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        Set<String> s = new TreeSet();
        while (in6.hasNextLine()) {

            words = in6.readLine().split(",");
            String[] strr = words[1].split(" ");

            for (int j = 0; j < strr.length; j++) {

                s.add(strr[j]);
            }
        }

        return s;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> s = new TreeSet<String>();
        Set<Integer> id = map2.get(word);
        Set<Integer> moreid = descendants(g, id);
        
        Iterator<Integer> xxx = moreid.iterator();
        while (xxx.hasNext()) {
            s.addAll(map.get(xxx.next()));
        }
        return s;

    }
}
