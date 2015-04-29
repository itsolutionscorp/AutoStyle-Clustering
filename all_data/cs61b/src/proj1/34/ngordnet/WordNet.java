package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
 
public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<String, TreeSet<Integer>> ids = new HashMap<String, TreeSet<Integer>>();
    private HashMap<Integer, TreeSet<String>> synsets = new HashMap<Integer, TreeSet<String>>();
    private Digraph directedGraph;
 
    public WordNet(String synsetFilename, String hyponymFilename) {
        In readSynsets = new In(synsetFilename);
        In readHyponyms = new In(hyponymFilename);
        int synsetsLength = 0;

        while (readSynsets.hasNextLine()) {
            synsetsLength += 1;
            String line = readSynsets.readLine();
            String[] synsetArray = line.split(",");
            int id = Integer.parseInt(synsetArray[0]);
            TreeSet<String> nouns = new TreeSet<String>();
            for (String noun : synsetArray[1].split(" ")) {
                TreeSet<Integer> idents = ids.get(noun);
                nouns.add(noun);
                if (idents != null) {
                    idents.add(id);
                } else {
                    TreeSet<Integer> nounIds = new TreeSet<Integer>();
                    nounIds.add(id);
                    ids.put(noun, nounIds);
                }
            }
            synsets.put(id, nouns);
        }
        
        directedGraph = new Digraph(synsetsLength);
        while (readHyponyms.hasNextLine()) {
            String[] hyponymArray = readHyponyms.readLine().split(",");
            for (int i = 1; i < hyponymArray.length; i = i + 1) {
                directedGraph.addEdge(Integer.parseInt(hyponymArray[0]),
                    Integer.parseInt(hyponymArray[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return ids.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return ids.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        TreeSet<String> hyponymSet = new TreeSet<String>();
        Set<Integer> hyponymIds = GraphHelper.descendants(directedGraph, ids.get(word));
        for (Integer id : hyponymIds.toArray(new Integer[hyponymIds.size()])) {
            hyponymSet.addAll(synsets.get(id));
        }
        return hyponymSet;
    }
}
