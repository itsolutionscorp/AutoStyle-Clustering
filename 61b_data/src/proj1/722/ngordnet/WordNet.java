package ngordnet;

import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph digraph;
    private TreeMap<String, HashSet<Integer>> wordAsKey;
    private TreeMap<Integer, String> idAsKey;
    private TreeMap<Integer, Integer[]> hyponyms;

    // Assisted, and received assistance from, Jayanth Devarajan in only the
    // constructor.
    // Ideas were shared, diagrams were drawn, and concepts were explained, but
    // absolutely
    // No code was shared.
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In in = new In(synsetFilename);
        In hypoIn = new In(hyponymFilename);
        int vertices = 0;

        while (!in.isEmpty()) {
            String curLine = in.readLine();
            String[] tokens = curLine.split(",");
            int id = Integer.parseInt(tokens[0]);
            String curSyn = tokens[1];
            String[] synElem = curSyn.split(" ");
            try {
                idAsKey.put(id, curSyn);
            } catch (NullPointerException treeUninstantiated) {
                idAsKey = new TreeMap<Integer, String>();
                idAsKey.put(id, curSyn);
            }

            for (int i = 0; i < synElem.length; i += 1) {
                try {
                    HashSet<Integer> checkKey = wordAsKey.get(synElem[i]);
                    checkKey.add(id);
                    wordAsKey.put(synElem[i], checkKey);
                } catch (NullPointerException keyNotInTree) {
                    HashSet<Integer> inthashset = new HashSet<Integer>();
                    inthashset.add(id);
                    try {
                        wordAsKey.put(synElem[i], inthashset);
                    } catch (NullPointerException treeNotInstantiated) {
                        wordAsKey = new TreeMap<String, HashSet<Integer>>();
                        wordAsKey.put(synElem[i], inthashset);
                    }
                }
            }
            vertices += 1;
        }

        while (!hypoIn.isEmpty()) {
            String currentLine = hypoIn.readLine();
            String[] tmp = currentLine.split(",");
            int hyper = Integer.parseInt(tmp[0]);
            Integer[] hypos = new Integer[tmp.length - 1];
            for (int k = 0; k < hypos.length; k += 1) {
                hypos[k] = Integer.parseInt(tmp[k + 1]);
            }
            try {
                if (!hyponyms.containsKey(hyper)) {
                    hyponyms.put(hyper, hypos);
                } else {
                    Integer[] tmpstorage = hyponyms.get(hyper);
                    Integer[] newstorage = new Integer[tmpstorage.length
                            + hypos.length];
                    System.arraycopy(tmpstorage, 0, newstorage, 0,
                            tmpstorage.length);

                    for (int g = tmpstorage.length, h = 0; g < newstorage.length; g += 1, h += 1) {
                        newstorage[g] = hypos[h];
                    }
                    hyponyms.put(hyper, newstorage);
                }
            } catch (NullPointerException hyponymsNotInstantiated) {
                hyponyms = new TreeMap<Integer, Integer[]>();
                hyponyms.put(hyper, hypos);
            }
        }

        digraph = new Digraph(vertices + 1);
        Set<Integer> hypernyms = hyponyms.keySet();
        for (Integer hyperID : hypernyms) {
            for (Integer hypo : hyponyms.get(hyperID)) {
                this.digraph.addEdge(hyperID, hypo);
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordAsKey.get(noun) != null;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return this.wordAsKey.keySet();
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new HashSet<String>();
        if (!wordAsKey.containsKey(word)) {
            return toReturn;
        }
        HashSet<Integer> wid = wordAsKey.get(word);

        Set<Integer> tmp = GraphHelper.descendants(digraph, wid);
        for (Integer i : tmp) {
            String[] tmpdescendants = idAsKey.get(i).split(" ");
            for (String des : tmpdescendants) {
                toReturn.add(des);
            }
        }
        return toReturn;
    }
}
