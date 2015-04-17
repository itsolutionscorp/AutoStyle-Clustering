package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Iterator;

public class WordNet {
      //Do these need to be private? or is no modifier alright?
    private Digraph synsetHyponymGraph;
    private HashMap<Integer, HashSet<String>> synsetHashMap;
    
      /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
      
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        int numSynsets = 0;
        synsetHashMap = new HashMap<Integer, HashSet<String>>();
        while (synsets.hasNextLine()) {
            readSynset(synsets.readLine());
            numSynsets += 1;
        }
        synsetHyponymGraph = new Digraph(numSynsets);
        while (hyponyms.hasNextLine()) {
            readHyponym(hyponyms.readLine());
        }
    }

      /* Reads an individual line of a Synsets file*/
    private void readSynset(String synset) {
        StringTokenizer tokenProducer = new StringTokenizer(synset, ",");
        int key = Integer.parseInt(tokenProducer.nextToken());
        StringTokenizer valueProducer = new StringTokenizer(tokenProducer.nextToken(), " ");
        HashSet<String> values = new HashSet<String>();
        while (valueProducer.hasMoreTokens()) {
            values.add(valueProducer.nextToken());
        }
        synsetHashMap.put(key, values);
    }

      /* Reads an individual line of hyponyms file*/
    private void readHyponym(String hyponym) {
        StringTokenizer tokenProducer = new StringTokenizer(hyponym, ", ");
        int head = Integer.parseInt(tokenProducer.nextToken());
        int tail;
        while (tokenProducer.hasMoreTokens()) {
            tail = Integer.parseInt(tokenProducer.nextToken());
            synsetHyponymGraph.addEdge(head, tail);
        }
    }
      /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<Integer> keys = synsetHashMap.keySet();
        for (int key : keys) {
            for (String synonym : synsetHashMap.get(key)) {
                if (synonym.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

      /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> nouns = new HashSet<String>();
        Set<Integer> keys = synsetHashMap.keySet();
        for (int key : keys) {
            for (String synonym : synsetHashMap.get(key)) {
                nouns.add(synonym);
            }
        }
        return nouns;
    }
      /** Returns the set of all hyponyms of WORD as well as all synonyms of
        * WORD. If WORD belongs to multiple synsets, return all hyponyms of
        * all of these synsets. See http://goo.gl/EGLoys for an example.
        * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> keys = getKeys(word);
        HashSet<String> hyponyms = new HashSet<String>();
        for (int key : keys) {
            getSynonyms(key, hyponyms);
        }
        return hyponyms;
    }

      /* takes in a set of strings and a key
       * adds all words of synset corresponding to key to the set
       * for each hyponym recursively calls getSynonyms
       */
    private void getSynonyms(int key, HashSet<String> hyponymSet) {
        for (String synonym : synsetHashMap.get(key)) {
            hyponymSet.add(synonym);
        }
        Iterable<Integer> hyponymIterable = synsetHyponymGraph.adj(key);
        Iterator<Integer> hyponymIterator = hyponymIterable.iterator();
        while (hyponymIterator.hasNext()) {
            getSynonyms(hyponymIterator.next(), hyponymSet);
        }
    }

      /* Retrieves a set of integers.
       * each integer is an index of a synset in which word appears in synsetHashMap
       */
    private HashSet<Integer> getKeys(String word) {
        HashSet<Integer> keys = new HashSet<Integer>();
        Set<Integer> allKeys = synsetHashMap.keySet();
        for (int key : allKeys) {
            for (String synonym : synsetHashMap.get(key)) {
                if (word.equals(synonym)) {
                    keys.add(key);
                }
            }
        }
        return keys;
    }  
}
