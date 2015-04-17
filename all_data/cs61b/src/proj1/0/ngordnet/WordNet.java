package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, HashSet<String>> idKeys;
    private HashMap<String, HashSet<Integer>> wordKeys;
    private HashMap<Integer, HashSet<Integer>> relations;
    private Digraph digraph;
    private HashSet<String> uniqueNouns;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        idKeys = new HashMap<Integer, HashSet<String>>();
        wordKeys = new HashMap<String, HashSet<Integer>>();
        relations = new HashMap<Integer, HashSet<Integer>>();
        uniqueNouns = new HashSet<String>(idKeys.size());

        In synReader = new In(synsetFilename);
        In hypoReader = new In(hyponymFilename);

        int vertices = 0;

        while (synReader.hasNextLine()) {
            String line = synReader.readLine();
            String[] data = line.split(",");
            int id = Integer.parseInt(data[0]);
            String allWords = data[1];

            String[] words = allWords.split(" ");

            for (int i = 0; i < words.length; i++) {
                uniqueNouns.add(words[i]);

                if (!idKeys.containsKey(id)) {
                    idKeys.put(id, new HashSet<String>());            
                }
                idKeys.get(id).add(words[i]);

                if (!wordKeys.containsKey(words[i])) {
                    wordKeys.put(words[i], new HashSet<Integer>());
                }
                wordKeys.get(words[i]).add(id);         
            }

            vertices += 1;
        }


        digraph = new Digraph(vertices);

        while (hypoReader.hasNextLine()) {
            String line = hypoReader.readLine();
            String[] data = line.split(",");
            int synId = Integer.parseInt(data[0]);

            for (int i = 1; i < data.length; i++) {
                int hypoId = Integer.parseInt(data[i]);
                digraph.addEdge(synId, hypoId);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return uniqueNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return uniqueNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> setIDs = new HashSet<Integer>();
        HashSet<Integer> synonymIDs = wordKeys.get(word);
        // setIDs.addAll(findHyponyms(setIDs));

        // ArrayList<Integer> a = ArrayList<Integer>(GraphHelper.descendants(digraph, setIDs));
        setIDs.addAll(GraphHelper.descendants(digraph, synonymIDs));
        setIDs.addAll(synonymIDs);

        HashSet<String> hyponymSet = new HashSet<String>();
        for (int i : setIDs) {
            HashSet<String> addToSet = idKeys.get(i);
            // add all the words in the HashSet corresponding to sysnet id
            for (String s : addToSet) {
                hyponymSet.add(s);
            }
        }
        return hyponymSet;
    }
}

