package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

public class WordNet {
    private ArrayList<Synset> synsets;
    private Digraph hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename)  {
        synsets = new ArrayList<Synset>();
        In synsetFile = new In(synsetFilename);
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] tokens = line.split(",");

            int id = Integer.parseInt(tokens[0]);
            String[] synonymTokens = tokens[1].split(" ");
            TreeSet<String> synonyms = new TreeSet<String>();
            for (int i = 0; i < synonymTokens.length; i++) {
                synonyms.add(synonymTokens[i]);
            }
            String definition = tokens[2];

            synsets.add(new Synset(id, synonyms, definition));
        }
        synsetFile.close();

        hyponyms = new Digraph(synsets.size());
        In hyponymFile = new In(hyponymFilename);
        while (hyponymFile.hasNextLine()) {
            String line = hyponymFile.readLine();
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                hyponyms.addEdge(id, Integer.parseInt(tokens[i]));
            }
        }
        hyponymFile.close();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Synset synset : synsets) {
            if (synset.getSynonyms().contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new TreeSet<String>();
        for (Synset synset : synsets) {
            nouns.addAll(synset.getSynonyms());
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> wordHyponyms = new TreeSet<String>();
        for (Synset synset : synsets) {
            if (synset.getSynonyms().contains(word)) {
                Set<Integer> id = new TreeSet<Integer>();
                id.add(synset.getID());
                Set<Integer> hyponymIDs = GraphHelper.descendants(hyponyms, id);
                for (Integer i : hyponymIDs) {
                    wordHyponyms.addAll(synsets.get(i).getSynonyms());
                }
            }
        }
        return wordHyponyms;
    }
}
