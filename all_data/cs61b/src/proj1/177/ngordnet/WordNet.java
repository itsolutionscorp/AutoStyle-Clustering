package ngordnet;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private TreeMap<Integer, String[]> sFile = new TreeMap<Integer, String[]>();
    private TreeMap<Integer, TreeSet> hFile = new TreeMap<Integer, TreeSet>();
    private In synsetStream;
    private In hypernymStream;
    private TreeMap<?, ?> sNouns = new TreeMap<Object, Object>();
    private TreeSet<String> hypernymSet = new TreeSet<String>();
    private HashSet<Integer> firstsIDset = new HashSet<Integer>();
    private Set<Integer> sIDset = new TreeSet<Integer>();
    private String[] synsetWordStrings;

    private String synsetString;
    private String hypernymString;
    private TreeSet<String> sNounSet = new TreeSet<String>();
    private TreeSet<String> middleSynsetSet = new TreeSet<String>();

    private Digraph theDigraph;

    /* SYNSETS and HYPERNYMS are filenames for the input files. */
    public WordNet(String synsets, String hypernyms) {
        synsetStream = new In(synsets);
        hypernymStream = new In(hypernyms);

        // go through all synsets
        while (synsetStream != null && synsetStream.hasNextLine()) {
            synsetString = synsetStream.readLine();
            String[] synsetStrLine = synsetString.split(",");
            Integer id = Integer.parseInt(synsetStrLine[0]);
            String[] synsetsArray = synsetStrLine[1].split("\\s+");
            sFile.put(id, synsetsArray);
        }
        for (String[] nounSet : sFile.values()) {
            for (String str : nounSet) {
                sNounSet.add(str);
            }
        }
        while (hypernymStream != null && hypernymStream.hasNextLine()) {
            hypernymString = hypernymStream.readLine();
            String[] hyponymArray = hypernymString.split(",");
            Integer synsetID = Integer.parseInt(hyponymArray[0]);
            TreeSet hyponymSet = new TreeSet();
            for (int i = 1; i < hyponymArray.length; i++) {
                Integer hyponymID = Integer.parseInt(hyponymArray[i]);
                if (hFile.containsKey(synsetID)) {
                    hFile.get(synsetID).add(hyponymID);
                } else {
                    hyponymSet.add(hyponymID);
                    hFile.put(synsetID, hyponymSet);
                }
            }

        }
        theDigraph = new Digraph(sFile.size());
        for (Integer key : hFile.keySet()) {
            for (Object curr : hFile.get(key)) {
                theDigraph.addEdge(key, (int) curr);
            }
        }
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return sNounSet;
    }

    /** Returns true if WORD is a noun. WORD may be a collocation. */
    public boolean isNoun(String word) {
        return sNounSet.contains(word);
    }

    /** Returns the set of all hypoynms of WORD. */
    public Set<String> hyponyms(String word) {
        hypernymSet = new TreeSet<String>();
        firstsIDset = new HashSet<Integer>();
        for (String[] synStrings : sFile.values()) {
            for (String synset : synStrings) {
                if (synset.equals(word)) {
                    for (String synset2 : synStrings) {
                        hypernymSet.add(synset2);
                    }
                    for (Integer key : sFile.keySet()) {
                        if (sFile.get(key).equals(synStrings)) {
                            firstsIDset.add(key);
                        }
                    }
                }
            }
        }

        sIDset = GraphHelper.descendants(theDigraph, firstsIDset);
        for (Integer id : sIDset) {
            synsetWordStrings = sFile.get(id);
            for (String word2 : synsetWordStrings) {
                hypernymSet.add(word2);
            }
        }
        return hypernymSet;
    }
}
