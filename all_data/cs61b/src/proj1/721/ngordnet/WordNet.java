package ngordnet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;

public class WordNet {
  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, ArrayList<String>> synset;
    private Digraph hyponym;
    private In sFileString;
    private In hFileString;
    private ArrayList<Integer> hypoList;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new HashMap<Integer, ArrayList<String>>();
        sFileString = new In(synsetFilename);
        hFileString = new In(hyponymFilename);
        while (sFileString.hasNextLine()) {
            String line = sFileString.readLine();
            String[] lineList = line.split(",");
            ArrayList<String> words = new ArrayList<String>();
            for (String word : lineList[1].split("\\s+")) {
                words.add(word);
            }
            synset.put(Integer.parseInt(lineList[0]), words);
        }
        hyponym = new Digraph(synset.keySet().size());
        while (hFileString.hasNextLine()) {
            String line2 = hFileString.readLine();
            String[] lineList = line2.split(",");
            for (int i = 1; i < lineList.length; i += 1) { 
                hyponym.addEdge(Integer.parseInt(lineList[0]), 
                                Integer.parseInt(lineList[i]));
            }
        }
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> nouns = this.nouns();
        for (String n : nouns) {
            if (noun.equals(n)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> nouns = new HashSet<String>();
        Set<Integer> keySet = synset.keySet();
        for (Integer key : keySet) {
            ArrayList<String> valList = synset.get(key); 
            for (String val : valList) {
                nouns.add(val);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> hyp = new TreeSet<Integer>();
        Set<Integer> keySet = synset.keySet();
        for (Integer key : keySet) {
            ArrayList<String> valList = synset.get(key);
            for (String val : valList) {
                if (word.equals(val)) {
                    hyp.add(key);
                }
            }
        }
        Set<Integer> indexSet = GraphHelper.descendants(hyponym, hyp);
        HashSet<String> result = new HashSet<String>();
        for (Integer i : indexSet) {
            ArrayList<String> valList2 = synset.get(i);
            for (String val : valList2) {
                result.add(val);
            }
        }
        return result;
    }
}



