package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

public class WordNet {
    private ArrayList<HashSet<String>> nounsArray = new ArrayList<HashSet<String>>();
    private ArrayList<String> descriptions = new ArrayList<String>();
    private Digraph di;
    /* Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In sysFile = new In(synsetFilename);
        In nymFile = new In(hyponymFilename);

        while (sysFile.hasNextLine()) {
            String line = sysFile.readLine();
            String[] seperatedValues = line.split(",");
            String[] words = seperatedValues[1].split(" ");
            HashSet<String> hash = new HashSet<String>(Arrays.asList(words));
            nounsArray.add(hash);
            descriptions.add(seperatedValues[2]);
        }
        
        di = new Digraph(descriptions.size()); //describes relations

        while (nymFile.hasNextLine()) { //read hyponym file
            String line = nymFile.readLine();
            String[] lineNums = line.split(",");
            int addTo = Integer.parseInt(lineNums[0]);
            for (int i = 1; i < lineNums.length; i++) {
                int addend = Integer.parseInt(lineNums[i]);
                di.addEdge(addTo, addend);
            }
        }
    } 

    /* Returns true if NOUN is a word in some synset.*/
    public boolean isNoun(String noun) {
        for (HashSet<String> set: nounsArray) {
            if (set.contains(noun)) {
                return true; 
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> toReturn = new HashSet<String>();
        for (HashSet<String> hash: nounsArray) {
            toReturn.addAll(hash);
        }
        return toReturn;
    }
    
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    private Set<Integer> descendants(Digraph G, Set<Integer> synsetIDs) {
        DirectedDFS dfdp = new DirectedDFS(G, synsetIDs);
        TreeSet<Integer> reachable = new TreeSet<Integer>();
        for (int i = 0; i < G.V(); i += 1) {
            if (dfdp.marked(i)) {
                reachable.add(i);
            }
        }
        return reachable;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> toReturn = new HashSet<String>();
        HashSet<String> curr;
        for (int i = 0; i < nounsArray.size(); i++) {
            curr = nounsArray.get(i);
            if (curr.contains(word)) {
                toReturn.addAll(curr);
                HashSet<Integer> something = new HashSet<Integer>();
                something.add(i);
                Set<Integer> desc = descendants(di, something);
                for (int j : desc) {
                    toReturn.addAll(nounsArray.get(j));
                }
            }  
        }
        return toReturn;
    }
}
