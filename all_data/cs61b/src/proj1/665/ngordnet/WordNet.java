package ngordnet;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import edu.princeton.cs.algs4.Digraph;


/** Creates a WordNet of given synsets and hyponym indications
    Can check if a word exists in this wordnet, return set of all words in wordnet
    Can return a set of all hyponyms and synonys of a word in the wordnet **/
public class WordNet {
    private Map<String, Set<Integer>> wordToSynsetIDs;
    private Map<Integer, Synset> idToSynset;
    private Digraph hyponymDigraph;
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            Scanner scanSynset = new Scanner(new BufferedReader(new FileReader(synsetFilename)));
            Scanner scanHyponym = new Scanner(new BufferedReader(new FileReader(hyponymFilename))); 
            createMaps(scanSynset);
            createHyponymDigraph(scanHyponym);
        } catch (FileNotFoundException e) {
            System.out.println("One of the files not found!");
            System.out.println("Try checking the file names or your freaking class path");
        }
    }

    private void createMaps(Scanner s) {
        wordToSynsetIDs = new TreeMap<String, Set<Integer>>();
        idToSynset = new HashMap<Integer, Synset>();
        s.useDelimiter(",");
        while (s.hasNextLine()) {
            int synID = s.nextInt(); //Store synsetID
            String synWords = s.next(); //Store words in Synset
            s.nextLine(); //Throw away definitions

            /*Map the synID to its synset*/
            Synset syn = new Synset(synID, synWords);
            idToSynset.put(synID, syn);

            /*Map each word to it's set of synIDs*/
            Scanner synsetGroupScanner = new Scanner(synWords); 
            synsetGroupScanner.useDelimiter(" ");
            while (synsetGroupScanner.hasNext()) {
                String nextWord = synsetGroupScanner.next();
                if (!wordToSynsetIDs.containsKey(nextWord)) {
                    HashSet<Integer> init = new HashSet<Integer>();
                    init.add(synID);
                    wordToSynsetIDs.put(nextWord, init); 
                } else {
                    wordToSynsetIDs.get(nextWord).add(synID);
                }
            }
        }
    }

    private void createHyponymDigraph(Scanner s) {
        hyponymDigraph = new Digraph(idToSynset.size());
        while (s.hasNextLine()) {
            String hypGroup = s.nextLine();
            Scanner groupScanner = new Scanner(hypGroup);
            groupScanner.useDelimiter(",");
            int hypernymID = Integer.valueOf(groupScanner.next());
            while (groupScanner.hasNext()) {
                hyponymDigraph.addEdge(hypernymID, Integer.valueOf(groupScanner.next()));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToSynsetIDs.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return new HashSet<String>(wordToSynsetIDs.keySet());
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
        * WORD. If WORD belongs to multiple synsets, return all hyponyms of
        * all of these synsets. See http://goo.gl/EGLoys for an example.
        * Do not include hyponyms of synonyms.
        */
    public Set<String> hyponyms(String word) {
        Set<String> returnSet = new HashSet<String>();
        Set<Integer> allSynsets = wordToSynsetIDs.get(word);
        if (allSynsets == null) {
            System.out.println("Word: " + word + " was not found.");
            return null;
        } else {
            Set<Integer> allHyponymSynsets = GraphHelper.descendants(hyponymDigraph, allSynsets);
            allSynsets.addAll(allHyponymSynsets);
            for (Integer synID : allSynsets) {
                Synset syn = idToSynset.get(synID);
                returnSet.addAll(syn.words());
            }
        }
        return returnSet;
    }
    private static class Synset {
        private int synsetID;
        private HashSet<String> words;

        private Synset(int idNumber, String constituentWords) {
            synsetID = idNumber;
            words = new HashSet<String>(Arrays.asList(constituentWords.split(" ")));
        }

        private int synsetID() {
            return synsetID;
        }

        private Set<String> words() {
            return words;
        }

    }
}
