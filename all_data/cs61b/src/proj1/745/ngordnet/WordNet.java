package ngordnet;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;

/*
 *  @author Allen Zeng
 *  Method Signatures provided by Josh Hug
 */

public class WordNet {

    // Synsets
    private Map<Integer, List<String>> vertices = new HashMap<Integer, List<String>>();
    // Synsets reverse mapping. HashSet is used here due to constructor line-by-line file input.
    private Map<String, Set<Integer>> rvertices = new HashMap<String, Set<Integer>>();
    // Hyponyms
    private Map<Integer, List<Integer>> edges = new HashMap<Integer, List<Integer>>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        if (!synsetIn.exists()) {
            System.out.println("Could not open " + synsetFilename);
        }

        In hyponymIn = new In(hyponymFilename);
        if (!hyponymIn.exists()) {
            synsetIn.close();
            System.out.println("Could not open " + hyponymFilename);
        }

        // Fill in vertices and rvertices
        while (synsetIn.hasNextLine()) {
            String line = synsetIn.readLine();

            // Obtains ID of synset line
            int indexFirstComma = line.indexOf(",");
            int id = Integer.parseInt(line.substring(0, indexFirstComma));

            // Obtains Synonyms of synset line
            // Definitions of Synonyms discarded
            line = line.substring(indexFirstComma + 1);
            line = line.substring(0, line.indexOf(","));
            String[] tokens = line.split("\\s");

            // Entry vertex
            vertices.put(id, Arrays.asList(tokens));

            // Entry rvertices
            for (String s : tokens) {
                // If key-string already exists, add synset id to its value set.
                if (rvertices.containsKey(s)) {
                    rvertices.get(s).add(id);
                    continue;
                }
                // New mutatable Hashset
                Set<Integer> sid = new HashSet<Integer>();
                sid.add(id);
                rvertices.put(s, sid);
            }
        }

        // Fill in edges
        while (hyponymIn.hasNextLine()) {
            String line = hyponymIn.readLine();

            // Splits line into an array of Strings
            String[] tokens = line.split(",");

            // Obtains ID of synset
            int id = Integer.parseInt(tokens[0]);

            // Obtains Hyponyms of synset
            int[] hyponyms = new int[tokens.length - 1];
            for (int i = 1; i < tokens.length; i++) {
                hyponyms[i - 1] = Integer.parseInt(tokens[i]);
            }

            // Entry
            List<Integer> hyponymsList = new ArrayList<Integer>();
            // Check for pre-existing arrow
            if (edges.containsKey(id)) {
                for (Integer i : edges.get(id)) {
                    hyponymsList.add(i);        // SOMEHWERE SELFREFERNECE ERROR
                }
            }
            for (int i = 0; i < hyponyms.length; i++) {
                hyponymsList.add(hyponyms[i]);
            }
            edges.put(id, hyponymsList);
        }

        synsetIn.close();
        hyponymIn.close();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Map.Entry<String, Set<Integer>> e: rvertices.entrySet()) {
            if (e.getKey().equals(noun)) {
                return true;
            }
        }

        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return rvertices.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    // As shown in WordNetDemo, hyponym(word) contains word.
    public Set<String> hyponyms(String word) {
        // Set to be returned
        Set<String> returnSet = new HashSet<String>();

        // Set of ids to which word belongs
        Set<Integer> wordKey = rvertices.get(word);

        // Case bad word
        if (wordKey == null) {
            return null;
        }


        // Obtain synonyms
        for (Integer i : wordKey) {
            // Go to each synset id group, get their set of synonyms
            List<String> synonyms = vertices.get(i);

            // Case no synonym in set? Word is synonym of word,
            // therefore word is not in set. Bad word handled above.

            for (String s : synonyms) {
                // Add all synonyms to the returnSet
                returnSet.add(s);
            }

        }

        // Obtain hyponyms
        for (Integer i : wordKey) {
            // Recursively adds all hypo-hyponyms of each hypo-synset to returnSet
            Set<String> hyponymsSet = hypohyponyms(i);

            if (hyponymsSet == null) {
                continue;
            }
            returnSet.addAll(hyponymsSet);
        }
        return returnSet;
    }

    // Helper for hyponyms, with synonym-finding block removed.
    private Set<String> hypohyponyms(Integer hypoId) {
        // Set to be returned
        Set<String> returnSet = new HashSet<String>();

        // At each synset to which hypoId points to, get set of hyponyms
        List<Integer> hyponymKeys = edges.get(hypoId);

        // Case no hyponym to said word, i.e. edges.get(hypoId) returns null
        if (hyponymKeys == null) {
            return null;
        }

        for (Integer i : hyponymKeys) {
            // At each set of hypohyponyms, get their respective synset
            List<String> hyponyms = vertices.get(i);
            for (String s : hyponyms) {
                returnSet.add(s);
            }

            // Recursively adds all hypohyponyms of each hypo-synset to returnSet
            Set<String> hypohyponymsSet = hypohyponyms(i);

            if (hypohyponymsSet == null) {
                continue;
            }
            returnSet.addAll(hypohyponymsSet);
        }
        return returnSet;
    }
}
