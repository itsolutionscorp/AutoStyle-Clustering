package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph digraph;
    private Map<Integer, Set<String>> synsets;
    private Map<String, Set<Integer>> wordToSynsetsMap;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        int numSynsets = 0;
        synsets = new HashMap<Integer, Set<String>>();
        wordToSynsetsMap = new HashMap<String, Set<Integer>>();

        while (synsetIn.hasNextLine()) {
            // Parse each Line
            String line = synsetIn.readLine();
            String[] rawTokens = line.split(",");
            int synsetID = Integer.parseInt(rawTokens[0]);
            String[] nouns = rawTokens[1].split(" ");
            numSynsets += 1;

            Set<String> synset = new TreeSet<String>();
            //Synset ID to synsets
            for (String noun: nouns) {
                synset.add(noun);
            }
            // Associate each set of nouns with their respective group synsetID
            synsets.put(synsetID, synset);

            //Word to synset IDs
            for (String noun : nouns) {
                //Noun to synsetID relation
                if (wordToSynsetsMap.get(noun) == null) {
                    Set<Integer> synsetIDSet = new TreeSet<Integer>();
                    synsetIDSet.add(synsetID);
                    wordToSynsetsMap.put(noun, synsetIDSet);
                } else {
                    wordToSynsetsMap.get(noun).add(synsetID);
                }
            }
        }

        digraph = new Digraph(numSynsets);

        while (hyponymIn.hasNextLine()) {
            //Parse each line
            String line = hyponymIn.readLine();
            String[] rawTokens = line.split(",");
            int synsetID = Integer.parseInt(rawTokens[0]);
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            for (String token : tokens) {
                int synsetHyponymID = Integer.parseInt(token);
                digraph.addEdge(synsetID, synsetHyponymID);
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer synsetID : synsets.keySet()) {
            for (String word : synsets.get(synsetID)) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToSynsetsMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new TreeSet<String>();
        Set<Integer> hyponymSynsetIDs = GraphHelper.descendants(digraph,
                                                                wordToSynsetsMap.get(word));

        //Add all hyponyms of word
        for (Integer synsetID : hyponymSynsetIDs) {
            hyponyms.addAll(synsets.get(synsetID));
        }

        //Add all synonyms of word including multiple synsets
        for (Integer synsetID: wordToSynsetsMap.get(word)) {
            hyponyms.addAll(synsets.get(synsetID));
        }
        return hyponyms;
    }
}

