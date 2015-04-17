package ngordnet;

import edu.princeton.cs.algs4.Digraph;    // to represent DiGraph arrows
import java.util.HashSet;                 // using HashSet as an ADT
import java.util.Set;
import java.util.ArrayList;               // using ArrayList as an ADT
import java.util.HashMap;                 // using HashMap as an ADT
import static org.junit.Assert.*;         // testing
import org.junit.Test;                    // testing
import java.io.File;                      // reading in txt file
import java.util.Scanner;                 // reading in txt file
import java.io.FileNotFoundException;     // reading in txt file
import java.util.TreeSet;

/** Read in text files and store content as Abstract Data Types. 
 *  @author Alex Dombrowski
 */

public class WordNet {

    private HashMap<Integer, ArrayList<Integer>> hyponymMap = 
                                    new HashMap<Integer, ArrayList<Integer>>();
    private HashMap<Integer, ArrayList<String>> synsetMap = 
                                    new HashMap<Integer, ArrayList<String>>();
    private HashSet<String> nounSet = new HashSet<String>(); 
    private int numberOfSynsets = 0;
    private Digraph g;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        File hyponymPath = new File(hyponymFilename);
        Scanner s;
        try {
            s = new Scanner(hyponymPath);
            while (s.hasNextLine()) {
                String currLine = s.nextLine();
                hyponymParse(currLine);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find " + hyponymFilename);
        } 
        File synsetPath = new File(synsetFilename);
        try {
            s = new Scanner(synsetPath);
            while (s.hasNextLine()) {
                String currLine = s.nextLine();
                synsetParse(currLine);
                numberOfSynsets += 1;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find " + synsetFilename);
        } 
        g = new Digraph(numberOfSynsets);
        Set<Integer> keys = hyponymMap.keySet();
        for (Integer k : keys) {
            ArrayList<Integer> al = hyponymMap.get(k);
            for (Integer i : al) {
                g.addEdge(k, i);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> synIDs = new TreeSet<Integer>();
        Set<Integer> synKeys = synsetMap.keySet();
        for (Integer id : synKeys) {
            if (synsetMap.get(id).contains(word)) {
                synIDs.add(id);
            }
        }
        Set<Integer> hypIDs = GraphHelper.descendants(g, synIDs);
        Set<String> result = new TreeSet<String>();

        for (Integer i : hypIDs) {
            ArrayList<String> al = synsetMap.get(i);
            result.addAll(al);
        }
        return result;
    }

// ----------------------------------------------------------------------------
    /* Helper Methods */
// ----------------------------------------------------------------------------
    

    /* Dummy constructor for testing purposes */
    private WordNet() {
    }


    /* Given a line from synsets.txt and the current synsetMap, update the map
     * by adding a new key value pair, where the key is the synset id and the
     * value is the synset stored as an ArrayList<String>.
     */
    private void synsetParse(String line) {
        String[] tokens = line.split(",");
        Integer key = Integer.parseInt(tokens[0]);
        ArrayList<String> al = new ArrayList<String>();
        String[] words = tokens[1].split(" ");
        for (String w : words) {
            al.add(w);
            nounSet.add(w);     // build nounSet at the same time
        }
        synsetMap.put(key, al);
    }


    /* Given a line from hyponyms.txt and the current hyponymMap, update the map
     * by adding a new key value pair, where the key is the hyponym id and the
     * value is an ArrayList<Integer> of direct hyponyms. Checks if key from line
     * is already in map before making new key-value pair.
     */
    private void hyponymParse(String line) {
        String[] tokens = line.split(",");
        Integer key = Integer.parseInt(tokens[0]);
        ArrayList<Integer> al = new ArrayList<Integer>();
        if (tokens.length > 1) {
            for (int i = 1; i < tokens.length; i++) {
                al.add(Integer.parseInt(tokens[i]));
            }    
        }
        if (hyponymMap.keySet().size() != 0 && hyponymMap.keySet().contains(key)) {
            hyponymMap.get(key).addAll(al);
        } else {
            hyponymMap.put(key, al);    
        } 
    }


// ----------------------------------------------------------------------------
    /* Testing */
// ----------------------------------------------------------------------------

    public static class TestWordNet {

        //@Test
        public void testConstructor() {
            // Choose different .txt files and verify the output visually
            WordNet wn = new WordNet("./p1data/wordnet/synsets11.txt",
                                 "./p1data/wordnet/hyponyms11.txt");
            System.out.println(wn.hyponymMap.entrySet());
            System.out.println(wn.synsetMap.entrySet());
            for (String s : wn.nounSet) {
                System.out.println(s);
            }
            System.out.println(wn.g);
        }

        @Test
        public void testHyponymParse() {
            String line = "0,1,2";
            WordNet wn = new WordNet();
            wn.hyponymParse(line); 
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(1);
            al.add(2);
            assertEquals(al, wn.hyponymMap.get(0));
            assertEquals(1, wn.hyponymMap.keySet().size());
            assertTrue(wn.hyponymMap.keySet().contains(0));
            //System.out.println(wn.hyponymMap.entrySet()); 

            line = "4,2";
            wn.hyponymParse(line); 
            al = new ArrayList<Integer>();
            al.add(2);
            assertEquals(al, wn.hyponymMap.get(4));
            assertEquals(2, wn.hyponymMap.keySet().size());
            assertTrue(wn.hyponymMap.keySet().contains(4));            
            //System.out.println(wn.hyponymMap.entrySet()); 

            line = "4,3";
            wn.hyponymParse(line); 
            //System.out.println(wn.hyponymMap.entrySet());
        }

        @Test
        public void testSynsetParse() {
            String line = "10,dog black_lab air_bud,dummy";
            WordNet wn = new WordNet();
            wn.synsetParse(line); 
            ArrayList<String> al = new ArrayList<String>();
            al.add("dog");
            al.add("black_lab");
            al.add("air_bud");
            assertEquals(al, wn.synsetMap.get(10));
            assertEquals(1, wn.synsetMap.keySet().size());
            assertTrue(wn.synsetMap.keySet().contains(10));
            //System.out.println(wn.synsetMap.entrySet());
            //for (String s : wn.nounSet) {
                //System.out.println(s);
            //}

            line = "5,cat,dummy";
            wn.synsetParse(line); 
            //System.out.println(wn.synsetMap.entrySet());
            //for (String s : wn.nounSet) {
            //    System.out.println(s);
            //}
        }

        @Test
        public void testIsNoun() {
            WordNet wn = new WordNet("./p1data/wordnet/synsets11.txt",
                                 "./p1data/wordnet/hyponyms11.txt");
            assertTrue(wn.isNoun("jump"));
            assertTrue(wn.isNoun("leap"));
            assertTrue(wn.isNoun("nasal_decongestant"));
            assertFalse(wn.isNoun("Hello"));

            wn = new WordNet("./p1data/wordnet/synsets14.txt",
                                 "./p1data/wordnet/hyponyms14.txt");
            assertTrue(wn.isNoun("natural_event"));
            assertTrue(wn.isNoun("conversion"));
            assertFalse(wn.isNoun("Hello"));
        }

        @Test 
        public void testNouns() {
            WordNet wn = new WordNet("./p1data/wordnet/synsets11.txt",
                                 "./p1data/wordnet/hyponyms11.txt");
            HashSet<String> allNouns = new HashSet<String>();
            allNouns.add("augmentation");
            allNouns.add("nasal_decongestant");
            allNouns.add("change");
            allNouns.add("action");
            allNouns.add("actifed");
            allNouns.add("antihistamine");
            allNouns.add("increase");
            allNouns.add("descent");
            allNouns.add("parachuting");
            allNouns.add("leap");
            allNouns.add("demotion");
            allNouns.add("jump");
            assertEquals(allNouns, wn.nounSet);
        }

        //@Test
        public void testHyponyms() {
            // Choose different .txt and WORD for hyponym(WORD)
            WordNet wn = new WordNet("./p1data/wordnet/synsets11.txt",
                                 "./p1data/wordnet/hyponyms11.txt");
            Set<String> s = wn.hyponyms("increase");
            for (String string : s) {
                System.out.println(string);
            }
        }

        //@Test
        public void testFullData() {
            // Choose different .txt files and verify the output visually
            WordNet wn = new WordNet("./p1data/wordnet/synsets.txt",
                                 "./p1data/wordnet/hyponyms.txt");
            //System.out.println(wn.hyponymMap.entrySet());
            //System.out.println(wn.synsetMap.entrySet());
            //for (String s : wn.nounSet) {
            //    System.out.println(s);
            //}
            //assertEquals(11, wn.numberOfSynsets);
            //System.out.println(wn.g);
            Set<String> s = wn.hyponyms("animal");
            for (String string : s) {
                System.out.println(string);
            }
        }



        public static void runTests() {
            jh61b.junit.textui.runClasses(TestWordNet.class);
        }
    }
}
