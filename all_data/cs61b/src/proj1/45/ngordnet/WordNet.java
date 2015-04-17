package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;

/*An object that stores the WordNet graph in a 
manner useful for extracting all hyponyms of a word.*/
public class WordNet {
    private HashMap<Integer, String> synsetMap;
    private ArrayList<int[]> hypoRelationsHolder;
    private TreeSet<Integer> vertices;
    private TreeSet<String> nouns;
    private HashMap<String, TreeSet<Integer>> synsetMapReverse; //<word, all corresponding ID's>
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);

        vertices = new TreeSet<Integer>(); 
        hypoRelationsHolder = new ArrayList<int[]>(); 

        while (hyponyms.hasNextLine()) {
            String line = hyponyms.readLine();
            String[] tokens = line.split(","); 
            int[] hypoRelationCup = new int[tokens.length];
            //Creates a unique set of vertices
            for (int i = 0; i < tokens.length; i++) {
                int intToken = Integer.parseInt(tokens[i]);
                vertices.add(intToken);
                hypoRelationCup[i] = intToken;
            }
            hypoRelationsHolder.add(hypoRelationCup);
        }
        g = new Digraph(vertices.size());
        //for every set of relations (CUP)
        for (int i = 0; i < hypoRelationsHolder.size(); i++) {
            int[] hypoRelationCup = hypoRelationsHolder.get(i); 
            for (int j = 0; j < hypoRelationCup.length - 1; j++) {
                g.addEdge(hypoRelationCup[0], hypoRelationCup[j + 1]);
            }
        }
        synsetMap = new HashMap<Integer, String>(vertices.size());
        nouns = new TreeSet<String>();
        synsetMapReverse = new HashMap<String, TreeSet<Integer>>(nouns.size());


        while (synsets.hasNextLine()) {
            String line = synsets.readLine();
            String[] tokens = line.split(","); 
            int hypoID = Integer.parseInt(tokens[0]);
            String synsetGroup = tokens[1];
            synsetMap.put(hypoID, synsetGroup);

            //need to split each synset group into individual words
            String[] words = synsetGroup.split(" ");
            for (int i = 0; i < words.length; i++) {
                nouns.add(words[i]);
                if (!synsetMapReverse.containsKey(words[i])) {
                    TreeSet<Integer> temp = new TreeSet<Integer>();
                    temp.add(hypoID);
                    synsetMapReverse.put(words[i], temp);
                } else {
                    synsetMapReverse.get(words[i]).add(hypoID); 
                }

            }
        }
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }
    /*Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
    If WORD belongs to multiple synsets, return all hyponyms of all of these synsets. 
    See http://goo.gl/EGLoys for an example. Do not include hyponyms of synonyms.*/
    public Set<String> hyponyms(String word) {
        TreeSet<String> returnSet = new TreeSet<String>();
        TreeSet<Integer> hypoIDSet = synsetMapReverse.get(word);
        Set<Integer> neighbors = GraphHelper.descendants(g, hypoIDSet);
        for (int n : neighbors) {
            for (String words : synsetMap.get(n).split(" ")) {
                returnSet.add(words);
            }
        }
        return returnSet;
    }
}
