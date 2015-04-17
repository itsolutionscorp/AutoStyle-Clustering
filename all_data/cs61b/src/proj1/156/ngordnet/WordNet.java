package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;

public class WordNet {
    private HashMap<Integer, HashSet<String>> synset = new HashMap<Integer, HashSet<String>>();
    private Digraph hyponyms;
    private int numVertices;

    /* Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        readSynset(synsetFilename);
        readHyponyms(hyponymFilename);
    }

    /* Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms. */
    public Set<String> hyponyms(String word) {
        HashSet<String> theseHyponyms = new HashSet<String>();
        HashSet<String> thisSynset;
        if (isNoun(word)) {
            theseHyponyms.add(word);
            for (int i : synset.keySet()) {
                thisSynset = synset.get(i);
                if (thisSynset.contains(word)) {
                    theseHyponyms.addAll(thisSynset); // adding synonyms in synset
                    addHyponymsRecursive(i, thisSynset, theseHyponyms);
                }
            }
        }
        return theseHyponyms;
    }

    private void addHyponymsRecursive(int index, HashSet<String> set, HashSet<String> hyp) {
        for (int vertex : hyponyms.adj(index)) { // adding the hypnonyms
            hyp.addAll(synset.get(vertex));
            addHyponymsRecursive(vertex, synset.get(vertex), hyp);
        }
    }

    public boolean isNoun(String noun) {
        Set<String> nounSet = nouns();
        return nounSet.contains(noun);
    }

    public Set<String> nouns() {
        HashSet<String> nounSet = new HashSet<String>();
        HashSet<String> thisSynset;
        for (int i : synset.keySet()) {
            thisSynset = synset.get(i);
            for (String str : thisSynset) {
                nounSet.add(str);
            }
        }
        return nounSet;
    }


    private void readHyponyms(String filename) {
        hyponyms = new Digraph(numVertices);
        String thisLine;
        String[] subString;
        In in = new In(filename);
        while (in.hasNextLine()) {
            thisLine = in.readLine();
            subString = thisLine.split(",");
            for (int i = 1; i < subString.length; i++) {
                hyponyms.addEdge(Integer.parseInt(subString[0]), Integer.parseInt(subString[i]));
            }
        }
    }

    private void readSynset(String filename) {
        In in = new In(filename);
        String thisLine;
        String[] subStrings;
        String synsetSubString;
        int currentInt;
        HashSet<String> synsetSet;
        while (in.hasNextLine()) {
            thisLine = in.readLine();
            subStrings = thisLine.split(",");
            currentInt = Integer.parseInt(subStrings[0]);
            synsetSubString = subStrings[1];
            synsetSet = new HashSet<String>(Arrays.asList(synsetSubString.split(" ")));
            // Thanks to platzhirsch on StackOverflow:
            // http://stackoverflow.com/questions/11387844/splitting-a-large-string-into-set-items
            synset.put(currentInt, synsetSet);
        }
        numVertices = synset.size();
    }
}
