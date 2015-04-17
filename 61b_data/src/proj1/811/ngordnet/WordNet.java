package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {
    private ArrayList<String>[] synsets;
    private String[] definitions;
    private Digraph di;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Synsets first
        In in = new In(synsetFilename);
        String[] synsetsRaw = in.readAllLines();        // Contains an array of all the lines

        // Tokenize the last line to find the number of synsets
        StringTokenizer tok1 = new StringTokenizer(synsetsRaw[synsetsRaw.length - 1], ",");
        int numSynsets = Integer.parseInt((String) tok1.nextElement()) + 1;

        synsets = (ArrayList<String>[]) new ArrayList[numSynsets];
        for (int i = 0; i < numSynsets; i++) {
            synsets[i] = new ArrayList<String>();       // Initializes all the array lists
        }

        definitions = new String[numSynsets];

        for (int i = 0; i < numSynsets; i++) {
            String line = synsetsRaw[i];
            StringTokenizer tok = new StringTokenizer(line, ",");       // Tokenizes the entire line
            tok.nextElement();      // Throw out the number
            String words = (String) tok.nextElement();
            String definition = (String) tok.nextElement();
            // Tokenize just the list of words in this synset
            StringTokenizer wordTok = new StringTokenizer(words, " ");
            // Add all the words to the synset
            while (wordTok.hasMoreTokens()) {
                synsets[i].add((String) wordTok.nextElement());
            }

            definitions[i] = definition;
        }

        // Hyponym paths
        In in2 = new In(hyponymFilename);
        String[] hyponymsRaw = in2.readAllLines();

        // Digraph to store connections
        // Values in digraph correspond to array indices
        di = new Digraph(numSynsets);

        // Read in actual connections
        for (int i = 0; i < hyponymsRaw.length; i++) {
            StringTokenizer hyp = new StringTokenizer(hyponymsRaw[i], ",");
            int e1 = Integer.parseInt((String) hyp.nextElement());
            while (hyp.hasMoreTokens()) {
                int e2 = Integer.parseInt((String) hyp.nextElement());
                di.addEdge(e1, e2);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < synsets.length; i++) {
            for (int j = 0; j < synsets[i].size(); j++) {
                if (synsets[i].get(j).equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> toReturn = new TreeSet<String>();

        for (int i = 0; i < synsets.length; i++) {
            for (int j = 0; j < synsets[i].size(); j++) {
                toReturn.add(synsets[i].get(j));
            }
        }
        return toReturn;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new TreeSet<String>();
        ArrayList<Integer> synID = getSynIDs(word);
        for (int i = 0; i < synID.size(); i++) {
            ArrayList<String> curSyn = synsets[synID.get(i)];
            for (int j = 0; j < curSyn.size(); j++) {
                toReturn.add(curSyn.get(j));        
            }
        }

        for (int i = 0; i < synID.size(); i++) {
            Iterable<Integer> hyps = di.adj(synID.get(i));
            for (Integer hypID : hyps) {
                synID.add(hypID);
                ArrayList<String> curHyp = synsets[hypID];

                for (int j = 0; j < curHyp.size(); j++) {
                    toReturn.add(curHyp.get(j));
                }
            }
        }
        return toReturn;
    }

    private ArrayList<Integer> getSynIDs(String word) {
        ArrayList<Integer> synID = new ArrayList<Integer>();
        // Determine the synset ID(s)
        for (int i = 0; i < synsets.length; i++) {
            for (int j = 0; j < synsets[i].size(); j++) {
                if (synsets[i].get(j).equals(word)) {
                    synID.add(i);
                }
            }
        }

        return synID;
    }
}



