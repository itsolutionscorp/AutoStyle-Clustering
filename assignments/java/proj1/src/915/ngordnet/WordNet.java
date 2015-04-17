package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class WordNet {
   /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    
    private Map<String, List<String>> map;
    private Map<List<String>, String> reverseMap;
    private int size = 0;
    private Set<String> nounsList;
    private Digraph digraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
    
        map = new HashMap<String, List<String>>();
        nounsList = new HashSet<String>();


        In inS = new In(synsetFilename);
        In inH = new In(hyponymFilename);

        while (inS.hasNextLine()) {     
            String s = inS.readLine(); 
            List<String> val = new ArrayList<String>();
            String[] splitComma = s.split(",");
            String[] splitSpace = splitComma[1].split(" ");
            for (int i = 0; i < splitSpace.length; i += 1) {
                nounsList.add(splitSpace[i]);
                val.add(splitSpace[i]);
            }
            map.put(splitComma[0], val);
            size += 1;
        }

        digraph = new Digraph(size);
        while (inH.hasNextLine()) {     
            String s = inH.readLine(); 
            String[] splitComma = s.split(",");
            for (int n = 1; n < splitComma.length; n += 1) {
                digraph.addEdge(Integer.parseInt(splitComma[0]), Integer.parseInt(splitComma[n]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounsList.contains(noun);
    } 


    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounsList;
    } 

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> keysHyp = new TreeSet<Integer>();
        Set<String> hyponyms = new HashSet<String>();

        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            List<String> value = e.getValue();
            int key = Integer.parseInt(e.getKey());

            if (value.contains(word)) {
                keysHyp.add(key);
                for (int n = 0; n < value.size(); n += 1) {
                    String values = value.get(n);
                    hyponyms.add(values);
                }
            }
        }

        Set<Integer> hyptree = GraphHelper.descendants(digraph, keysHyp);
        
        for (int num : hyptree) {
            List<String> temp = map.get(Integer.toString(num));

            for (int n = 0; n < temp.size(); n += 1) {
                String values = temp.get(n);
                hyponyms.add(values);
            }
        }

        return hyponyms;
    }
}
