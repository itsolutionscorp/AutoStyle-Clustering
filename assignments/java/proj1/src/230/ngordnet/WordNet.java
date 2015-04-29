package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<String, HashSet<Integer>> syns = new HashMap<String, HashSet<Integer>>();
    private HashMap<Integer, String[]> ids = new HashMap<Integer, String[]>();
    private Digraph hypos;
    private int numV;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In reader = new In(synsetFilename);
        numV = 0;
        while (reader.hasNextLine()) {
            String[] ar = (reader.readLine()).split(",");
            //System.out.println(ar[0]+" || "+ar[1]);
            int val = Integer.parseInt(ar[0]);
            
            String[] sep = (ar[1]).split(" ");
            ids.put(val, sep);
            
            for (int i = 0; i < sep.length; i = i + 1) {
                if (syns.containsKey(sep[i])) {
                    (syns.get(sep[i])).add(val);
                } else {
                    HashSet<Integer> calids = new HashSet<Integer>();
                    calids.add(val);
                    syns.put(sep[i], calids);
                }
            }
            numV = numV + 1;
        }

        In hyporeader = new In(hyponymFilename);
        hypos = new Digraph(numV);
        while (hyporeader.hasNextLine()) {
            String[] nums = (hyporeader.readLine()).split(",");
            Integer supers = Integer.parseInt(nums[0]);
            for (int i = 1; i < nums.length; i = i + 1) {
                int blargh = Integer.parseInt(nums[i]);
                hypos.addEdge(supers, blargh);
            }
        }
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return syns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return syns.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> togos = new HashSet<String>();
        Set<Integer> looping = (syns.get(word));
        if (looping == null) {
            return null;
        }
        Set<Integer> synsDesc = GraphHelper.descendants(hypos, looping);
        for (Integer x: synsDesc) {
            String[] checking = ids.get(x);

            for (String boop : checking) {
                togos.add(boop);
            }
        }

        return togos;
    }
}
