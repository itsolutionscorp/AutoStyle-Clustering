package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
 */
public class WordNet {
    private Map<Integer, Synset> idToWordMap;
    private Map<String, Set<Integer>> wordToIDMap;

    private Digraph wordGraph;
    private Set<String> nouns = new HashSet<String>();

    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     */
    public WordNet(String synsetFilename, String hyponymFilename) {

        //Set for the synsets, then assemble the hyponym graph from the synset
        idToWordMap = new HashMap<Integer, Synset>();
        wordToIDMap = new HashMap<String, Set<Integer>>();

        In synsetIn = new In(synsetFilename);


        while (synsetIn.hasNextLine()) {
            String line = synsetIn.readLine();

            String[] rawTokens = line.split(",");
            String[] wordArray = rawTokens[1].split(" ");

            Set<String> words = new HashSet<String>(Arrays.asList(wordArray));
            Synset currentLine = new Synset(Integer.parseInt(rawTokens[0]), words, rawTokens[2]);

            idToWordMap.put(currentLine.getId(), currentLine);

            for (String word : words) {
                Set<Integer> ids = new HashSet<Integer>();
                ids.add(currentLine.getId());
                if (wordToIDMap.containsKey(word)) {
                    ids.addAll(wordToIDMap.get(word));
                }
                wordToIDMap.put(word, ids);
            }
            nouns.addAll(words);
        }

        //Now assembles this into the digraph
        int verticieCount = 0;

        //Extract each hyponym line
        Set<ArrayList<Integer>> lines = new HashSet<ArrayList<Integer>>();
        In hyponymIn = new In(hyponymFilename);

        while (hyponymIn.hasNextLine()) {
            String line = hyponymIn.readLine();
            String[] rawTokens = line.split(",");

            ArrayList<Integer> hyponyms = new ArrayList<Integer>();

            for (String token : rawTokens) {
                hyponyms.add(Integer.parseInt(token));
                verticieCount++;
            }

            lines.add(hyponyms);
        }

        wordGraph = new Digraph(verticieCount);

        for (ArrayList<Integer> line : lines) {
            for (int i = 1; i < line.size(); i++) {
                wordGraph.addEdge(line.get(0), line.get(i));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    private Set<String> crawlDescendant(Set<Integer> head, Set<String> accumulated) {
        Set<Integer> probe = GraphHelper.descendants(wordGraph, head);
        Set<String> acc = accumulated;

        for (Integer id : head) {
            acc.addAll(idToWordMap.get(id).getSynset());
        }
        if (probe.equals(head)) {
            return accumulated;
        }
        return crawlDescendant(GraphHelper.descendants(wordGraph, head), acc);
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> ids = getIds(word);
        return crawlDescendant(ids, new HashSet<String>());
    }

    private Set<Integer> getIds(String word) {
        return wordToIDMap.get(word);
    }

    private class Synset {
        private int id;
        private Set<String> synset;
        private String def;

        public Synset(int idIn, Set<String> synsetIn, String defIn) {
            id = idIn;
            synset = synsetIn;
            def = defIn;
        }

        public int getId() {
            return id;
        }

        public Set<String> getSynset() {
            return synset;
        }

        public String getDef() {
            return def;
        }

        public boolean contains(String toFind) {
            return synset.contains(toFind);
        }
    }
}
