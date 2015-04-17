package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private TreeMap<Integer, ArrayList<String>> synsets;
    private Digraph hyponyms; 

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new TreeMap<Integer, ArrayList<String>>();
        makeSynset(synsetFilename);
        hyponyms = new Digraph(synsets.size() + 1);
        makeHyponym(hyponymFilename);
    }

    private void makeSynset(String synsetFilename) {
        In inputData = new In(synsetFilename);
        String line = inputData.readLine();
        while (line != null) {
            String[] elemOfLine = line.split(",");
            String[] syns = elemOfLine[1].split(" ");
            ArrayList<String> synList = new ArrayList<String>();
            int idNumber = Integer.parseInt(elemOfLine[0]);
            for (String k : syns) {
                synList.add(k);
            }
            synsets.put(idNumber, synList);
            synList = new ArrayList<String>();
            line = inputData.readLine();
        }
    }

    private void makeHyponym(String hyponymFilename) {
        In inputData = new In(hyponymFilename);
        String line = inputData.readLine();
        while (line != null) {
            String[] elemOfLine = line.split(",");
            int[] hyponymsList = new int[elemOfLine.length];
            for (int j = 0; j < elemOfLine.length; j++) {
                hyponymsList[j] = Integer.parseInt(elemOfLine[j]);
            }
            int idNumber = hyponymsList[0];
            for (int k = 0; k < elemOfLine.length; k++) {
                if (k != 0) {
                    hyponyms.addEdge(idNumber, hyponymsList[k]);
                }
            }
            line = inputData.readLine();
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer i : synsets.keySet()) {
            if (synsets.get(i).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        for (Integer i : synsets.keySet()) {
            for (String k : synsets.get(i)) {
                allNouns.add(k);
            }
        }
        return allNouns;
    }

     /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> allNounsInSynset = new TreeSet<String>();
        Set<Integer> positions = new TreeSet<Integer>();
        Set<Integer> savedValues = new TreeSet<Integer>();
        for (Integer i : synsets.keySet()) {
            if (synsets.get(i).contains(word)) {
                savedValues.add(i);
                positions = GraphHelper.descendants(hyponyms, savedValues);
                for (Integer k : positions) {
                    for (String j : synsets.get(k)) {
                        allNounsInSynset.add(j);
                    }
                }
            }
            savedValues = new TreeSet<Integer>();
        }
        return allNounsInSynset;
    }
}
