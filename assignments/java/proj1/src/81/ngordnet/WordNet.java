package ngordnet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    
    private HashMap<Integer, String[]> wordMap;
    private Digraph wordDigraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        /*Initialize wordMap */
        In synsets = new In(synsetFilename);
        wordMap = new HashMap<Integer, String[]>();

        while (synsets.hasNextLine()) {
            String[] currLine = synsets.readLine().split(",");
            Integer id = Integer.parseInt(currLine[0]); 
            String word = currLine[1];
            String[] wordArray = word.split(" ");
            wordMap.put(id, wordArray);
        }

        /* Intialize hyponynms */
        In hyponyms = new In(hyponymFilename);
        ArrayList<Integer> vertices = new ArrayList<Integer>();
  
        while (hyponyms.hasNextLine()) {
            String[] stringVerts = hyponyms.readLine().split(",");
            int[] intVerts = new int[stringVerts.length];
            for (int i = 0; i < intVerts.length; i++) {
                intVerts[i] = Integer.parseInt(stringVerts[i]);
            }
            for (int i = 0; i < intVerts.length; i++) {
                Integer integerVert = new Integer(intVerts[i]);
                if (!vertices.contains(integerVert)) {
                    vertices.add(integerVert);
                }
            }
        }

        int numberVert = vertices.size();
        wordDigraph = new Digraph(numberVert);

        In hyponyms2 = new In(hyponymFilename);
        while (hyponyms2.hasNextLine()) {
            String[] stringVerts = hyponyms2.readLine().split(",");
            int[] intVerts = new int[(stringVerts.length)];
            for (int i = 0; i < intVerts.length; i++) {
                intVerts[i] = Integer.parseInt(stringVerts[i]);
            }
            int subVert = 1;
            while (subVert < intVerts.length) {
                wordDigraph.addEdge(intVerts[0], intVerts[subVert]);
                subVert += 1;
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < wordMap.size(); i++) {
            String[] synsetElements = wordMap.get((Integer) i);
        
            for (String s : synsetElements) {
                if (noun.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> allNouns = new HashSet<String>();
        for (int i = 0; i < wordMap.size(); i++) {
            String[] synsetElements = wordMap.get((Integer) i);

            for (String s : synsetElements) {
                allNouns.add(s); 
            }
        } 
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> ids = new HashSet<Integer>();
        for (int i = 0; i < wordMap.size(); i++) {
            String[] words = wordMap.get(i);
            for (int j = 0; j < words.length; j++) {
                if (word.equals(words[j])) {
                    ids.add(i);
                }
            }
        }
        if (ids == null) {
            throw new IllegalArgumentException("boo");
        }

        Set<Integer> idDescendants = GraphHelper.descendants(wordDigraph, ids);
        Set<String> wordDescendants = new HashSet<String>();

        for (Integer i : idDescendants) {
            String[] words = wordMap.get(i);

            for (int j = 0; j < words.length; j++) {
                wordDescendants.add(words[j]);
            }
        }
        return wordDescendants;
    }
}
