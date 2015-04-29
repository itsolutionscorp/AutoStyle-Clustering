package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Set;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;

public class WordNet {
    private Map<Integer, String[]> synset; 
    private Set<String> wordSet;
    private Digraph d;
    private String[] words;
    private String[] words1;
    private In input2;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new TreeMap<Integer, String[]>();
        wordSet = new TreeSet<String>();
        In input0 = new In(synsetFilename);
        In input = new In(synsetFilename);
        input2 = new In(synsetFilename);
        String[] in = input0.readAllLines();
        words = new String[in.length];
        while (input.hasNextLine()) {
            String[] inputs = input.readLine().split(",");
            int id = Integer.parseInt(inputs[0]);
            String[] inputt = inputs[1].split(" ");
            synset.put(id, inputt);
            for (String str: inputt) {
                wordSet.add(str);
            }
        }

        d = new Digraph(in.length);
        In input1 = new In(hyponymFilename);
        while (input1.hasNextLine()) {
            String[] inputs1 = input1.readLine().split(",");
            int h = Integer.parseInt(inputs1[0]);
            for (int i = 1; i < inputs1.length; i++) {
                int x = Integer.parseInt(inputs1[i]);
                d.addEdge(h, x);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nSet = new TreeSet();
        while (input2.hasNextLine()) {
            words1 = input2.readLine().split(",");
            String[] temp1 = words1[1].split(" ");
            for (int m = 0; m < temp1.length; m++) {
                nSet.add(temp1[m]);
            }
        }
        return nSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIDs = new TreeSet<Integer>();
        Set<String> output = new TreeSet<String>();
        for (Map.Entry<Integer, String[]> t : synset.entrySet()) {
            if (Arrays.asList(t.getValue()).contains(word)) {
                synsetIDs.add(t.getKey());
                output.addAll(Arrays.asList(t.getValue()));
            }
        }
        synsetIDs = GraphHelper.descendants(d, synsetIDs);
        Iterator<Integer> id1 = synsetIDs.iterator();
        while (id1.hasNext()) {
            output.addAll(Arrays.asList(synset.get(id1.next())));
        }
        return output;
    }

}
