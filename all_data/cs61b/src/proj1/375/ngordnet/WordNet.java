package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Arrays;

public class WordNet {

    private Map<Integer, String[]> nouns = new HashMap<Integer, String[]>();
    private Map<String, Integer[]> id = new HashMap<String, Integer[]>();
    private Digraph hyponyms;

  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In all = new In(synsetFilename);
        while (!all.isEmpty()) {
            String line = all.readLine();
            String[] rawTokens = line.split(",");
            Integer key = Integer.parseInt(rawTokens[0]);
            String[] vals = rawTokens[1].split(" ");
            nouns.put(key, vals); //adds ID keys and synset noun values to nouns map
            for (int i = 0; i < vals.length; i += 1) {
                if (!id.containsKey(vals[i])) {
                    Integer[] firstID = {key};
                    id.put(vals[i], firstID);
                } else {
                    Integer[] tempID = id.get(vals[i]);
                    Integer[] pointer = id.get(vals[i]);
                    pointer = new Integer[tempID.length + 1];
                    for (int m = 0; m < tempID.length; m += 1) {
                        pointer[m] = tempID[m];
                    }
                    pointer[tempID.length] = key;
                    id.put(vals[i], pointer);
                } 
            }
        }

        hyponyms = new Digraph(id.size() - 1);
        In hyp = new In(hyponymFilename);
        while (!hyp.isEmpty()) {
            String temp = hyp.readLine();
            String[] rawInt = temp.split(",");
            Integer intKey = Integer.parseInt(rawInt[0]);
            Integer[] intVals = new Integer[rawInt.length - 1];
            for (int k = 1; k < rawInt.length; k += 1) {
                intVals[k - 1] = Integer.parseInt(rawInt[k]);
            }
            for (int a = 0; a < intVals.length; a += 1) {
                hyponyms.addEdge(intKey, intVals[a]);
            }
        }
    }

  /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return id.containsKey(noun);
    }

  /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return id.keySet();
    }

  /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        Set<Integer> vertices = new HashSet<Integer>(Arrays.asList(id.get(word)));
        Set<Integer> ids = GraphHelper.descendants(hyponyms, vertices);
        Iterator<Integer> it = ids.iterator();
        Set<String> returnString = new HashSet<String>();
        for (int i = 0; i < ids.size(); i += 1) {
            String[] nounsList = nouns.get(it.next());
            for (int j = 0; j < nounsList.length; j += 1) {
                returnString.add(nounsList[j]);
            }
        }
        return returnString;
    }
}
