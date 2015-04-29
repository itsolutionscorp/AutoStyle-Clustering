package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet  {
    private Digraph graph;
    private int count = 0;
    private HashMap<String, String[]> map = new HashMap<String, String[]>();
    
    
    public WordNet(String synsets, String hyponyms)  {
        In in0 = new In(synsets);
        In in1 = new In(hyponyms);
        while (in0.hasNextLine()) {
            String line = in0.readLine();
            String[] split = line.split(",");
            String id = split[0];
            String[] nounlist = split[1].split(" ");
            map.put(id, nounlist);
            count += 1;
        }
        graph = new Digraph(count);
        while (in1.hasNextLine())  {
            String line = in1.readLine();
            String[] split = line.split(",");
            for (int i = 1; i < split.length; i += 1) {
                graph.addEdge(Integer.parseInt(split[0]) , Integer.parseInt(split[i]));
            }
            
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> keyset = map.keySet();
        for (String key: keyset) {
            String[] nounlist = map.get(key);
            for (String check: nounlist) {
                if (noun.equals(check)) {
                    return true;
                }     
            }
            
        }
        return false;
        
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> keyset = map.keySet();
        Set<String> nounset = new HashSet<String>();
        for (String key: keyset) {
            String[] nounlist = map.get(key);
            for (String check: nounlist) {
                nounset.add(check);
               
            }
            
        }
        return nounset;
        
        
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return null;
        }
        Set<Integer> vSet = new HashSet<Integer>();
        Set<String> keyset = map.keySet();
        Set<String> nounset = new HashSet<String>();
        for (String key: keyset) {
            String[] nounlist = map.get(key);
            for (String check: nounlist) {
                if (word.equals(check)) {
                    Integer newKey = Integer.parseInt(key);
                    vSet.add(newKey);
                }     
            }    
        }
        Set<Integer> hyponymV = GraphHelper.descendants(graph, vSet);
        for (Integer num: hyponymV) {
            String index = Integer.toString(num);
            String[] nounlist = map.get(index);
            for (String check: nounlist) {
                nounset.add(check);
                    
            } 
            
        }
        return nounset;
        
    }

}
