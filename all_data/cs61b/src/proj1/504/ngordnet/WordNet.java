package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    
    /* Holds a word and its synsets */ 
    private HashMap<String, ArrayList<Synset>> words; 
    /* Holds a word's ID and its synset */
    private HashMap<Integer, Synset> matches; 
    private Digraph digraph;

    /** Creates a WordNet using SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        words = new HashMap<String, ArrayList<Synset>>();
        matches = new HashMap<Integer, Synset>();

        In synsetFile = new In(synsetFilename);
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] parts = line.split(",");
            int iD = Integer.parseInt(parts[0]);
            String[] synonyms = parts[1].split(" ");
            /* Creates a synset object with the ID and words 
             * in the current line */
            Synset current = new Synset(iD, synonyms);
            matches.put(iD, current);

            for (String word : synonyms) {
                if (!words.containsKey(word)) {
                    ArrayList<Synset> arraySynset = new ArrayList<Synset>();
                    arraySynset.add(current);
                    words.put(word, arraySynset);
                } else {
                    ArrayList<Synset> arraySynset = words.get(word);
                    arraySynset.add(current);
                }
            }
        }
        digraph = new Digraph(matches.size());

        In hyponymFile = new In(hyponymFilename);
        while (hyponymFile.hasNextLine()) {
            String line = hyponymFile.readLine();
            String[] parts = line.split(",");
            /* Sets the first number as the hypernym, gets the Synset
               that corresponds to the hypernym ID number */
            int idNum = Integer.parseInt(parts[0]);
            /* Goes through the rest of the numbers and gets 
               their corresponding Synsets */
            for (int i = 1; i < parts.length; i++) {
                int iD = Integer.parseInt(parts[i]);
                digraph.addEdge(idNum, iD);
            }
        }
    }

    /** Returns true if  NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return words.containsKey(noun);
    } 

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return words.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. if  WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for  an example.
      * Do not include hyponyms of synonyms. */
    public Set<String> hyponyms(String word) {
        ArrayList<Synset> wordSynsets = words.get(word);
        Set<Integer> ids = new HashSet<Integer>();
        Set<String> returns = new HashSet<String>();

        for (Synset s : wordSynsets) {
            int id = s.getID();
            ids.add(id);
            returns.addAll(Arrays.asList(s.getWords()));
        }

        Set<Integer> vertex = GraphHelper.descendants(digraph, ids);
        for (Integer v : vertex) {
            returns.addAll(Arrays.asList(matches.get(v).getWords()));
        }
        return returns;
    }
}
