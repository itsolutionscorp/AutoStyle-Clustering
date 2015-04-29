package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {

    private HashMap<String, LinkedList<Integer>> synMap; 
    private HashMap<Integer, String[]> reverseMap;
    private Digraph dg;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synMap = new HashMap<String, LinkedList<Integer>>();
        reverseMap = new HashMap<Integer, String[]>();

        In in = new In(synsetFilename);
        int numLines = 0;
        while (!in.isEmpty()) {
            String line = in.readLine();
            numLines += 1;
            String[] splits = line.split(","); 

            String[] synset = splits[1].split(" ");
            reverseMap.put(Integer.parseInt(splits[0]), synset);

            for (String word: synset) {        
                LinkedList<Integer> ids = new LinkedList<Integer>();
                ids.add(Integer.parseInt(splits[0]));
                if (synMap.containsKey(word)) {
                    ids.addAll(synMap.get(word));
                }
                synMap.put(word, ids);           
            }

        }

        dg = new Digraph(numLines); 

        In in2 = new In(hyponymFilename);
        while (!in2.isEmpty()) {
            String line = in2.readLine();
            String[] splits = line.split(",");
            int[] vertices = new int[splits.length]; 
            for (int i = 0; i < vertices.length; i += 1) {
                vertices[i] = Integer.parseInt(splits[i]);
            }
            for (int i = 1; i < vertices.length; i += 1) {
                dg.addEdge(vertices[0], vertices[i]);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (synMap.containsKey(noun));
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> vertex = new TreeSet<Integer>();
        for (Integer x: synMap.get(word)) {
            vertex.add(x);
        }
        Set<Integer> vertices = GraphHelper.descendants(dg, vertex);
        TreeSet<String> hyponyms = new TreeSet<String>();
        for (Integer v: vertices) {
            String[] words = reverseMap.get(v);
            for (String w: words) {
                hyponyms.add(w);
            }
        }

        return hyponyms;
    }
}
