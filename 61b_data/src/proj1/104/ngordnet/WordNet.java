package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;

// Austin Wright helped me decide what data structures to use 
// and helped me debug. 
// He also suggested the comparator in Yearly Record.
// Kevin Lee helped me debug a lot of my code as well.
// Discussed ideas with Jeffrey Li and Brian Ho.
// I did not ever have possesion of anyone's code.

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Set<String> myNounSet = new HashSet<String>();
    private Digraph myDigraph;
    private HashMap<Integer, String> myHashMap = new HashMap<Integer, String>();
    public WordNet(String synsetFilename, String hyponymFilename) {
        In myIn = new In(synsetFilename);
        while (!myIn.isEmpty()) {
            String s = myIn.readLine();
            String[] myArray = s.split(",");
            myHashMap.put(Integer.parseInt(myArray[0]), myArray[1]);
            String[] chill = myArray[1].split(" ");
            for (int i = 0; i < chill.length; i++) {
                myNounSet.add(chill[i]);
            }
        }
        myDigraph = new Digraph(myHashMap.size());

        In myIn2 = new In(hyponymFilename);
        while (!myIn2.isEmpty()) {
            String a = myIn2.readLine();
            String[] myArray2 = a.split(",");
            for (int i = 1; i < (myArray2.length); i++) {
                int d = Integer.parseInt(myArray2[0]);
                myDigraph.addEdge(d, Integer.parseInt(myArray2[i]));
            }
        }
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return myNounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return myNounSet;
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    public Set<String> hyponyms(String word) {
        //int key = 0;
        Set<Integer> newHashSet = new HashSet<Integer>();
        Set<Integer> keys = myHashMap.keySet();
        for (Integer key : keys) {
            String anotherString = myHashMap.get(key);
            String[] splitted = anotherString.split(" ");
            for (int i = 0; i < splitted.length; i++) {
                if (splitted[i].equals(word)) {
                    newHashSet.add(key);
                }
            }
        }

        Set<Integer> stupid = GraphHelper.descendants(myDigraph, newHashSet); //set of integers

        Set<String> strs = new HashSet<String>(stupid.size()); //set of nouns
        for (Integer integer : stupid) {
            String splittedString = myHashMap.get(integer);
            String[] donesplitting = splittedString.split(" ");
            for (int i = 0; i < donesplitting.length; i++) {
                strs.add(donesplitting[i]);
            }
        }
        return strs;
    }
}
