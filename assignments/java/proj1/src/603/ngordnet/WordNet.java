package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    private HashMap<Integer, HashSet<String>> map = new HashMap<Integer, HashSet<String>>();
    private Digraph digraph;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In finSysnset = new In(synsetFilename); // Input Stream created and File associated to it.
        In finHyponym = new In(hyponymFilename); // Input Stream Created and File associated to it.
        int counter = 0;
        int idKey;

        while (finSysnset.hasNextLine()) {
            HashSet<String> wordSet = new HashSet<String>();
            String line = finSysnset.readLine();
            String[] splittedLine = line.split(",");
            idKey = Integer.parseInt(splittedLine[0]);
            String synonymString = splittedLine[1];
            String[] synonymStringSplitted = synonymString.split(" ");
            for (int i = 0; i < synonymStringSplitted.length; i++) {
                wordSet.add(synonymStringSplitted[i]);
            }
            map.put(idKey, wordSet);
            counter++; // for digraph
        }

        digraph = new Digraph(counter);

        //Reading and Parsing through "hyponymFilename"
        while (!finHyponym.isEmpty()) {
            String line = finHyponym.readLine();
            String[] splittedLine = line.split(",");
            idKey = Integer.parseInt(splittedLine[0]);
            for (int i = 1; i < splittedLine.length; i++) {
                digraph.addEdge(idKey, Integer.parseInt(splittedLine[i]));
            }
        }
    } 

    /* Returns true if NOUN is a word in some synset.*/
    // TIP: Every sysnset is a noun
    public boolean isNoun(String noun) {
        HashSet<String> tempSet;
        boolean flag = false;
        for (Integer k : map.keySet()) {
            tempSet = map.get(k);
            if (tempSet.contains(noun)) {
                flag = true;
            }
        }
        return flag;
    }

    /* Returns the set of all nouns. */
    //TIP: Return the set of all values in the map XX
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        HashSet<String> tempSet;
        for (Integer k : map.keySet()) {
            tempSet = map.get(k);
            for (String n : tempSet) {
                nounSet.add(n);
            }
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    /* Find Key of word from HashMap. Find it in the Hyponyms.txt file. 
     * return set of all associated values */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymsSet = new HashSet<String>();
        HashSet<String> tempSet;
        HashSet<Integer> wordKeySet = new HashSet<Integer>();
        for (Integer k : map.keySet()) {
            tempSet = map.get(k);
            if (tempSet.contains(word)) {
                wordKeySet.add(k);
            }
        }
        Set<Integer> keys = GraphHelper.descendants(digraph, wordKeySet);
        for (Integer k : keys) {
            tempSet = map.get(k);
            for (String w : tempSet) {
                hyponymsSet.add(w);
            }
        }
        return hyponymsSet;
    }
}
