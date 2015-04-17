package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

public class WordNet {
    private HashMap<Integer, HashSet<String>> synsMap;
    private Digraph ourDigraph;
    private Set<Integer> hypSet;
    private Set<String> hypSol;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syns = new In(synsetFilename);
        In hyp = new In(hyponymFilename);
        synsMap = new HashMap<Integer, HashSet<String>>();

        int vertCount = 0;
        //Loop through each line
        while (syns.hasNextLine()) {
            
            // Returns word & Dummy
            HashSet<String> synsets = new HashSet<String>();
            String[] arrayS = syns.readLine().split(",");
            String[] words = arrayS[1].split(" ");
            for (String aWord : words) {
                synsets.add(aWord);
            }
            synsMap.put(Integer.parseInt(arrayS[0]), synsets);
            vertCount += 1;
        }

        ourDigraph = new Digraph(vertCount);

        while (hyp.hasNextLine()) {
            String[] arrayH = hyp.readLine().split(",");
            for (int i = 1; i < arrayH.length; i++) {
                Integer initialVertex = Integer.parseInt(arrayH[0]);
                Integer finalVertex = Integer.parseInt(arrayH[i]);
                ourDigraph.addEdge(initialVertex, finalVertex);

            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        // SOURCE: http://www.java2novice.com/java-collections-and-util/hashmap/all-keys/
        Set<Integer> keys = synsMap.keySet();
        for (Integer key : keys) {
            HashSet<String> nounList = synsMap.get(key);
            if (nounList.contains(noun)) {
                return true;
            }
        }
        return false;

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> nounSet = new HashSet<String>();
        Set<Integer> keys = synsMap.keySet();
        for (Integer key : keys) {
            for (String noun : synsMap.get(key)) {
                nounSet.add(noun);
            }    
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> keys = synsMap.keySet();
        HashSet<Integer> startVerticies = new HashSet<Integer>();
        
        // Gets each key
        for (Integer key : keys) {
            if (key != null) {
                // Gets each Set
                HashSet<String> currNounSet = synsMap.get(key);
                if (currNounSet.contains(word)) {
                    startVerticies.add(key);
                }
            }
        }

        hypSet = GraphHelper.descendants(ourDigraph, startVerticies);
        HashSet<String> solution = new HashSet<String>();
        
        for (Integer child : hypSet) {
            // System.out.println(child);
            for (String myString : synsMap.get(child)) {
                // System.out.println(myString);
                if (child != null) {
                    solution.add(myString);
                }
            }
        }
        return solution;

    }
}
