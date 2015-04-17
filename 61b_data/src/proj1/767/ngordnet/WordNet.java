package ngordnet; 
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class WordNet {
    private String sF; 
    private String hF;
    private In readableSynsetFilename;  
    private In readableHyponymFilename; 
    private TreeMap<Integer, String> idSynset; 
    private Digraph d; 

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        sF = synsetFilename; 
        hF = hyponymFilename; 
        idSynset = new TreeMap<Integer, String>(); 
        readableSynsetFilename = new In(synsetFilename); 
        String[] sentences = readableSynsetFilename.readAllLines();
        for (String s : sentences) { 
            String[] parts = s.split(",");
            int key = Integer.parseInt(parts[0]);  
            String value = parts[1];
            idSynset.put(key, value); 
        } 
        readableHyponymFilename = new In(hyponymFilename); 
        String[] hyponymSentences = readableHyponymFilename.readAllLines(); 
        d = new Digraph(idSynset.size());
        for (String s2 : hyponymSentences) {
            String[] parts2 = s2.split(",");
            int parent = Integer.parseInt(parts2[0]); 
            for (int i = 1; i < parts2.length; i += 1) {
                d.addEdge(parent, Integer.parseInt(parts2[i])); 
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> nouns = nouns(); 
        return nouns.contains(noun); 
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>(); 
        for (Integer key : idSynset.keySet()) {
            String[] part3 = idSynset.get(key).split(" ");
            for (String p : part3) {
                nouns.add(p); 
            } 
        } 
        return nouns; 
    }

    // * Returns the set of all hyponyms of WORD as well as all synonyms of
    //   * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    //   * all of these synsets. See http://goo.gl/EGLoys for an example.
    //   * Do not include hyponyms of synonyms.
      
    public Set<String> hyponyms(String word) {
        Set<String> children = new HashSet<String>(); 
        Set<Integer> vertices; 
        Set<Integer> parents = new HashSet<Integer>(); 
        for (Integer key : idSynset.keySet()) {
            String[] parts4 = idSynset.get(key).split(" ");
            if (Arrays.asList(parts4).contains(word)) {
                parents.add(key);
            }
        }  
        vertices = GraphHelper.descendants(d, parents);
        for (Integer k : idSynset.keySet()) {
            if (vertices.contains(k)) {
                String[] parts5 = idSynset.get(k).split(" ");
                Set<String> mySet = new HashSet<String>(Arrays.asList(parts5));
                children.addAll(mySet);
            }
        }
        return children;   
    }
}
