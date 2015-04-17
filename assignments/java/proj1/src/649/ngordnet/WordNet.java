package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

/** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
public class WordNet {
    // Maps String noun --> Set<Integer> IDs
    private HashMap<String, Set<Integer>> nouns;
    // Maps Integer ID --> Set<String> synsets 
    private HashMap<Integer, Set<String>> synsetForIndex;
    private Digraph d;

    public WordNet(String synsetFilename, String hyponymFilename) {
        nouns = new HashMap<String, Set<Integer>>();
        synsetForIndex = new HashMap<Integer, Set<String>>();
        processWords(new In(synsetFilename));
        d = new Digraph(nouns.keySet().size()); 
        processHyponyms(new In(hyponymFilename));
    }

    private void processWords(In in) {
        String[] linez = in.readAllLines();
        int id = 0;
        for (String line : linez) {
            // synset is between the commas
            int comma1 = line.indexOf(',') + 1; // don't want to include comma
            int comma2 = line.indexOf(',', comma1);
            Scanner synset = new Scanner(line.substring(comma1, comma2));

            // words are the synset words that the ID points to
            HashSet<String> words = new HashSet<String>(); 
            while (synset.hasNext()) {
                String n = synset.next();
                words.add(n); 
                if (!nouns.containsKey(n)) {
                    // is not in the map - add a new key-value
                    HashSet<Integer> synIDs = new HashSet<Integer>();
                    synIDs.add(id);
                    nouns.put(n, synIDs);
                } else { 
                    // already have the word - add it to the set of synset IDs it's in
                    nouns.get(n).add(id);
                }
            }
            synsetForIndex.put(id, words);
            id++;
        }
    }

    private void processHyponyms(In in) {
        String[] linez = in.readAllLines();
        for (String line : linez) {
            Scanner s = new Scanner(line);
            s.useDelimiter(",");
            Integer id = new Integer(s.nextInt()); // first int is synset id

            while (s.hasNextInt()) {
                int node = s.nextInt();
                d.addEdge(id, node);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns()  {
        return nouns.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!nouns.containsKey(word)) {
            return null;
        }
        Set<Integer> ids = GraphHelper.descendants(d, nouns.get(word));
        HashSet<String> hyps = new HashSet<String>();
        for (Integer i : ids) {
            for (String str : (Set<String>) synsetForIndex.get(i)) {
                hyps.add(str);
            }
        }
        return hyps;
    }
}
