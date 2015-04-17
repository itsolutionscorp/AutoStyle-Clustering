package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class WordNet {
    private Digraph h;
    private HashMap<Integer, ArrayList<String>> s;
    private int hSize;
    private HashMap<String, ArrayList<Integer>> inv;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        In hTemp = new In(hyponymFilename);
        while (hTemp.hasNextLine()) {
            String line = hTemp.readLine();
            String[] numbers = line.split(",");
            for (int i = 0; i < numbers.length; i++) {
                int val = Integer.parseInt(numbers[i]);
                if (val >= this.hSize) {
                    this.hSize = val + 1;
                }
            }
        }
        this.h = new Digraph(hSize);
        this.s = new HashMap<Integer, ArrayList<String>>();
        this.inv = new HashMap<String, ArrayList<Integer>>();
        while (hyponymIn.hasNextLine()) {
            String line = hyponymIn.readLine();
            String[] parts = line.split(",");
            for (int i = 1; i < parts.length; i++) {
                this.h.addEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[i]));
            }
        }
        while (synsetIn.hasNextLine()) {
            String line = synsetIn.readLine();
            String[] parts = line.split(",");
            ArrayList<String> wordlist = new ArrayList<String>();
            String[] synonyms = parts[1].split(" ");
            for (int i = 0; i < synonyms.length; i++) {
                wordlist.add(i, synonyms[i]);
            }
            this.s.put(Integer.parseInt(parts[0]), wordlist);
            for (int i = 0; i < synonyms.length; i++) {
                if (!this.inv.containsKey(synonyms[i])) {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(Integer.parseInt(parts[0]));
                    this.inv.put(synonyms[i], temp);
                } else {
                    this.inv.get(synonyms[i]).add(Integer.parseInt(parts[0]));
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (inv.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return inv.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> rset = new HashSet<String>(); //return set
        TreeSet<Integer> temp = new TreeSet<Integer>(inv.get(word));
        Set<Integer> children = GraphHelper.descendants(this.h, temp);
        for (Integer i : children) {
            ArrayList<String> words = s.get(i);
            for (int j = 0; j < words.size(); j++) {
                rset.add(words.get(j));
            }
        }
        return rset;
    }
}
