package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.LinkedHashMap;

public class WordNet {
    private LinkedHashMap<Integer, Set<String>> synsets;
    private LinkedHashMap<String, Set<Integer>> nouns;
    private Digraph hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFName, String nounFName) {
        In synsetFile = new In(synsetFName);
        In hyponymFile = new In(nounFName);

        synsets = new LinkedHashMap<Integer, Set<String>>();
        nouns = new LinkedHashMap<String, Set<Integer>>();

        int sizeOfHyponyms = 0;

        /* Read synset file */
        while (synsetFile.hasNextLine()) {

            /** Set of strings containing all words that correspond to the 
              * given id */
            Set<String> synonyms = new TreeSet<String>();

            /* Read line, seperate fields, and get id # */
            String line = synsetFile.readLine();
            line = removeDefinition(line);
            String[] tokens = stringToArray(line);
            Integer id = Integer.parseInt(tokens[0]);

            /** Add each word on synset file line to synset map
              * using the id # as the key and a set of strings as the values */
            for (int i = 1; i < tokens.length; i++) {
                String word = tokens[i];

                /** Either add word to a set in the map nouns if set already exists
                  * or create a new set and add the id, set pair to nouns */
                if (isNoun(word)) {
                    Set<Integer> idSynset = nouns.get(word);
                    idSynset.add(id);
                } else {
                    Set<Integer> idSynset = new TreeSet<Integer>();
                    idSynset.add(id);
                    nouns.put(word, idSynset);
                }
                synonyms.add(word);
            }
            sizeOfHyponyms++;
            synsets.put(id, synonyms);
        }

        hyponyms = new Digraph(sizeOfHyponyms);

        /* Read hyponym file */
        while (hyponymFile.hasNextLine()) {
            Set<String> wordsHyponyms = new TreeSet<String>(); 

            /* Read line, seperate fields, and get id # */
            String line = hyponymFile.readLine();
            String[] tokens = stringToArray(line);
            Integer id = Integer.parseInt(tokens[0]);

            /* Add edges from hypernym to each hyponym */
            for (int i = 1; i < tokens.length; i++) {
                hyponyms.addEdge(id, Integer.parseInt(tokens[i]));
            }
        }

        synsetFile.close();
        hyponymFile.close();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String noun) {
        Set<Integer> hyponymsIdSet;
        Set<String> result = new TreeSet<String>();
        hyponymsIdSet = GraphHelper.descendants(hyponyms, nouns.get(noun));
        for (Integer i: hyponymsIdSet) {
            result.addAll(synsets.get(i));
        }
        return result;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /** Helper method to tokenize the string. */
    private String[] stringToArray(String s) {
        s = s.replace(",", " ");
        return s.split("\\s+");
    }

    /** Helper method to remove the definition from the
      * line read from synsets. */
    private String removeDefinition(String s) {
        int index = 1;
        for (int numOfCommas = 0; numOfCommas < 2; numOfCommas++) {
            index = s.indexOf(",", index);
            index++;
        }
        return s.substring(0, index);
    }
}
