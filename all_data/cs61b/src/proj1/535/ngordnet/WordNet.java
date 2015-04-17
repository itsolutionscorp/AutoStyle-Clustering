package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
public class WordNet {
    private HashMap<Integer, String[]> synIds = new HashMap<Integer, String[]>(); 
    private HashMap<String, TreeSet<Integer>> nouns2ids = new HashMap<String, TreeSet<Integer>>();
    private Digraph connections;
    private int vertices;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        
        // Parse synset file and create two Hashmaps from synset id to words and vice versa
        while (synsets.hasNextLine()) {
            String[] line = synsets.readLine().split(","); // String array of one line
            Integer synId = Integer.parseInt(line[0]); // Syn id
            String[] synNouns = line[1].split(" ");  // String array of synset nouns
            synIds.put(synId, synNouns);
            for (String str : synNouns) {
                if (!nouns2ids.containsKey(str)) {
                    TreeSet<Integer> temp = new TreeSet<Integer>();
                    nouns2ids.put(str, temp);
                }
                nouns2ids.get(str).add(synId);  
            }
            vertices += 1;
        }

        // Parse hyponyms file and create Digraph
        connections = new Digraph(vertices);
        while (hyponyms.hasNextLine()) {
            String[] line = hyponyms.readLine().split(",");
            Integer hypersetId = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i += 1) {
                connections.addEdge(hypersetId, Integer.parseInt(line[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns2ids.keySet().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns2ids.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> result = new TreeSet<String>();
        Set<Integer> wordIds = nouns2ids.get(word); 
        Set<Integer> allwordIds = GraphHelper.descendants(connections, wordIds); 
        for (Integer synsetId : allwordIds) {
            for (String words : synIds.get(synsetId)) {
                result.add(words);
            }
        }
        return result;
    }

}
