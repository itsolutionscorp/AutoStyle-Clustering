package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

/** An object that stores the WordNet graph in a manner useful for extracting
 *  all hyponyms of a word.
 *  @author Regina Xu
 */
public class WordNet {
    private Map<Integer, Entry> synset = new HashMap<Integer, Entry>();
    private Digraph hyponym;

    /** Constructor that reads in two files and stores them as synset and hyponym
     *  with Map and Digraph ADTs, respectively.
     *  Cite: credits to Richard Q. for discussing with me the ADTs to use for storing synsets. 
     * 
     *  @param file0   file containing list of noun synsets
     *  @param file1   file containing list of hyponyms
     */
    public WordNet(String file0, String file1) {
        try {
            Scanner sc0 = new Scanner(new File(file0));
            Scanner sc1 = new Scanner(new File(file1));            
            while (sc0.hasNext()) {
                String[] parts = sc0.nextLine().split(",");
                int val = Integer.parseInt(parts[0]);
                synset.put(val, new Entry(val, parts[1], parts[2]));
            }
            hyponym = new Digraph(synset.size());
            while (sc1.hasNext()) {
                String[] parts = sc1.nextLine().split(",");
                int val = Integer.parseInt(parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    int num = Integer.parseInt(parts[i]);
                    hyponym.addEdge(val, num);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    /** Checks if the parameter NOUN is inside the list of synsets.
     *  
     *  @param noun  a word
     *  @return      true if NOUN exists in the noun synsets, false otherwise
     */
    public boolean isNoun(String noun) {
        if (noun == null) {
            return false;
        }
        for (Entry val : synset.values()) {
            for (String s : val.words) {
                if (noun.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns all nouns as a set of strings that are in the synset.
     *
     *  @return  all nouns in list of noun synsets.
     */
    public Set<String> nouns() {
        Set<String> s = new HashSet<>();
        for (Entry value : synset.values()) {
            for (String str : value.words) {
                s.add(str);
            }
        }
        return s;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     *  If WORD belongs to multiple synsets, return all hyponyms of all of these
     *  synsets. Does not include hyponyms of synonyms.
     *
     *  @param word    noun whose hyponyms are returned
     *  @return        set of string noun hyponyms and word itself    
     */
    public Set<String> hyponyms(String word) {
        Set<String> mySet = new HashSet<String>();
        Set<Integer> num = new TreeSet<Integer>();
        /* Find key corresponding to WORD. */
        for (Integer i : synset.keySet()) {
            for (int j = 0; j < ((synset.get(i)).words).length; j++) {
                if (word.equals(((synset.get(i)).words)[j])) {
                    num.add(i);
                }
            }
        }
        /* Use GraphHelper's descendants method to get and store descendants of word. */
        Set<Integer> ids = GraphHelper.descendants(hyponym, num);
        
        /* Add corresponding descendents to mySet. */
        for (int i : ids) {
            for (int j = 0; j < ((synset.get(i)).words).length; j++) {
                mySet.add(((synset.get(i)).words)[j]);
            }
        }
        mySet.add(word);
        return mySet;
    }

    /** Represents one node containing the key-value pairs in the synset. */
    private class Entry {
        private int id;
        private String[] words;
        private String gloss;
    
        /** Constructor to set class variables to their corresponding inputs.
         *
         *  @param id    the synset id
         *  @param w     the synonym set as a string
         *  @param g     the dictionary definition of the synset or gloss
         */
        public Entry(int i, String w, String g) {
            id = i;
            words = w.split(" ");
            gloss = g;
        }
    }  
}
