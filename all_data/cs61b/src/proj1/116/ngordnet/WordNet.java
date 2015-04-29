//Cite Austin Wright who shared his idea about the implementation of hyponyms

package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.algs4.Digraph;

public class WordNet  {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Set<String> mySet = new HashSet<String>();
   
    private  Map<Integer, String> hypomap = new HashMap<Integer, String>();
    private Digraph newDigraph = null;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In newIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        
    
        while (newIn.hasNextLine()) {
            String newString = newIn.readLine();
            String[] tempString = newString.split(",");
            String[] tempString2 = tempString[1].split(" ");
            
            hypomap.put(Integer.parseInt(tempString[0]), tempString[1]);
            if (tempString2.length > 1) {
                mySet.add(tempString2[0]);
                for (int g = 1; g < tempString2.length; g++) {
                    mySet.add(tempString2[g]);
                }
            } else {
                mySet.add(tempString[1]);
            }
        }
        newDigraph = new Digraph(hypomap.size());

        while (hyponymIn.hasNextLine()) {
            String newString2 = hyponymIn.readLine();
            String[] tempString3 = newString2.split(",");
            for (int i = 1; i < tempString3.length; i++) {
                newDigraph.addEdge(Integer.parseInt(tempString3[0]), 
                    Integer.parseInt(tempString3[i]));
            }
        }
        
  
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String eachString : mySet) {
            //System.out.println(eachString);
            if (eachString.equals(noun)) {
                return true;
            }
            
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return mySet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        /*
        String matchNumber = null;
        for(String key: hypomap.keySet()){
        if(key.equals(word)){
            matchNumber = hypomap.get(key);
        }
    }
    return null;

*/
        Set<Integer> newHash = new HashSet<Integer>();
        Set<Integer> key = hypomap.keySet();
        for (Integer keyInt: key) {
            String[] tempArray = (hypomap.get(keyInt)).split(" ");
            for (int i = 0; i < tempArray.length; i++) {
                if (tempArray[i].equals(word)) {
                    newHash.add(keyInt);
                }
            }
        }   
        Set<Integer> newGraph = GraphHelper.descendants(newDigraph, newHash);
        Set<String> newGraphString = new HashSet<String>(newGraph.size());
    
        for (int k : newGraph) {
            String[] tempArray2 = (hypomap.get(k)).split(" ");
            for (int j = 0; j < tempArray2.length; j++) {
                newGraphString.add(tempArray2[j]);
            }
        }
     //return newGraph;
        return newGraphString;
    }
}

