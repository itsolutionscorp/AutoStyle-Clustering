package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

public class WordNet {
    private Map<Integer, Set<String>> sContainer = new HashMap<Integer, Set<String>>();
    private Set<String> nouns = new HashSet<String>(); // Stores all the nouns
                                                       // in synset file
    private Set<String> temp = new HashSet<String>(); // temporarily store a set
                                                      // of nouns
    private Set<Integer> intSet = new TreeSet<Integer>();
    private String oneLine, theNouns;
    private String[] sArray, sNouns;
    private int[] iArray;
    private int index;
    private int size = 0;
    private Digraph g;
    private In ins;
    private In inh;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        ins = new In(synsetFilename);
        inh = new In(hyponymFilename);
        oneLine = ins.readLine();

        while (oneLine != null) {
            sArray = oneLine.split(",");
            index = Integer.parseInt(sArray[0]);
            theNouns = sArray[1];
            sNouns = theNouns.split(" ");
            for (int i = 0; i < sNouns.length; i++) {
                temp.add(sNouns[i]);
                nouns.add(sNouns[i]);
            }
            sContainer.put(index, temp);
            oneLine = ins.readLine();
            temp = new HashSet<String>();
            size = size + 1;
        }

        oneLine = inh.readLine();
        g = new Digraph(size);

        while (oneLine != null) {
            sArray = oneLine.split(",");
            iArray = new int[sArray.length];
            for (int i = 0; i < sArray.length; i++) {
                iArray[i] = Integer.parseInt(sArray[i]);
            }
            for (int j = 1; j < iArray.length; j++) {
                g.addEdge(iArray[0], iArray[j]);
            }
            oneLine = inh.readLine();
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

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        temp = new HashSet<String>();
        intSet = new TreeSet<Integer>();
        for (int i : sContainer.keySet()) {
            // System.out.println(sContainer.get(i));
            if (sContainer.get(i).contains(word)) {

                intSet.add(i);
            }
        }
        if (intSet.size() == 0) {
            return null;
        }
        Set<Integer> intSet2 = new TreeSet<Integer>();
        intSet2 = GraphHelper.descendants(g, intSet);
        for (int j : intSet2) {
            temp.addAll(sContainer.get(j));
        }
        return temp;
    }
}
