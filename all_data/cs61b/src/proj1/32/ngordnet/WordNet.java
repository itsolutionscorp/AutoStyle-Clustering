package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private int size;
    private int index;
    private String synLine;
    private String[] synElements;
    private String[] synElementsCopy;
    private String hypLine;
    private String[] hypElements;
    private String[] retrievedHyps;
    private String[] hypElementsCopy;
    private TreeSet<String> nounSet = new TreeSet<String>();
    private Digraph diSyn;
    private TreeMap<Integer, String[]> indexTree = new TreeMap<Integer, String[]>();
    private TreeMap<Integer, String[]> indexWordTree = new TreeMap<Integer, String[]>();


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In inSyn = new In(synsetFilename);
        In inHyp = new In(hyponymFilename);
        String[] newArray;
        String[] addedArray;

        // makes map of hyponyms. we find out what words are related
        while (!inHyp.isEmpty()) { 
            hypLine = inHyp.readLine();
            hypElements = hypLine.split(",");
            index = Integer.parseInt(hypElements[0]);
            hypElementsCopy = new String[hypElements.length - 1];
            System.arraycopy(hypElements, 1, hypElementsCopy, 0, hypElements.length - 1);
            if (indexTree.containsKey(index)) {
                int hypLength = hypElementsCopy.length;
                int newLength = hypLength + indexTree.get(index).length;
                addedArray = new String[newLength];
                System.arraycopy(hypElementsCopy, 0, addedArray, 0, hypLength);
                System.arraycopy(indexTree.get(index), 0, addedArray, hypLength, newLength - 1);
                indexTree.put(index, addedArray);
            } else {
                indexTree.put(index, hypElementsCopy);
            }
        }

        while (!inSyn.isEmpty()) { //Find size of the Synset file
            size += 1;
            inSyn.readLine();
        }

        inSyn = new In(synsetFilename);
        diSyn = new Digraph(size); //creates a digraph of size Size.
        
        //Steps through each line of the Synset file. Splits at Commas.
        while (!inSyn.isEmpty()) { 
            synLine = inSyn.readLine(); 
            synElements = synLine.split(",");
            index = Integer.parseInt(synElements[0]);
            retrievedHyps = indexTree.get(index);
            if (retrievedHyps != null) {
                for (String inInt : retrievedHyps) { //creates our DiSyn digraph
                    diSyn.addEdge(index, Integer.parseInt(inInt));
                }
            }

            for (String noun : synElements[1].split(" ")) { //creates our list of total nouns
                indexWordTree.put(index, synElements[1].split(" "));
                nounSet.add(noun);
            } 
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nounSet.isEmpty()) {
            return false;
        }
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    // * Returns the set of all hyponyms of WORD as well as all synonyms of
    //   * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    //   * all of these synsets. See http://goo.gl/EGLoys for an example.
    //   * Do not include hyponyms of synonyms.
      
    public Set<String> hyponyms(String word) {
        TreeSet<String> hyponymSet = new TreeSet<String>();
        TreeSet<Integer> indexSet  = new TreeSet<Integer>();
        Set<Integer> indexes;

        if (nounSet.contains(word)) {
            for (Integer id : indexWordTree.keySet()) {
                retrievedHyps = indexWordTree.get(id);
                if (retrievedHyps != null) {
                    for (String hypWord : retrievedHyps) {
                        if (word.equals(hypWord)) {
                            indexSet.add(id);
                        }
                    }
                }
            }
        
            if (!indexSet.isEmpty()) {
                indexes = GraphHelper.descendants(diSyn, indexSet);
                for (Integer setIndex : indexes) {
                    for (String hypWord : indexWordTree.get(setIndex)) {
                        hyponymSet.add(hypWord);
                    }
                }
            }
        } else {
            return null;
        }
        return hyponymSet;
    }
}

