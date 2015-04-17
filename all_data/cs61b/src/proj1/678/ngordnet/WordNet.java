package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph syn;
    private Map<Integer, String> wordId;
    private Map<String, Integer> inverseId;
    private In synFile;
    private In hypFile;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synFile = new In(synsetFilename);
        hypFile = new In(hyponymFilename);
        wordId = new HashMap<Integer, String>();
        inverseId = new HashMap<String, Integer>();
        int vInitial = 0;

        while (synFile.hasNextLine()) {
            String l = synFile.readLine();
            String[] line = l.split(",");
            int id = Integer.parseInt(line[0]);
            String word = line[1];
            wordId.put(id, word);
            String[] wordSplit = word.split(" ");
            for (int i = 0; i < wordSplit.length; i += 1) {
                inverseId.put(wordSplit[i], id);
            }
            vInitial += 1;
        }

        syn = new Digraph(vInitial);
        
        while (hypFile.hasNextLine()) {
            String[] line = hypFile.readLine().split(",");
            int parent = Integer.parseInt(line[0]);

            for (int i = 1; i < line.length; i += 1) {
                int child = Integer.parseInt(line[i]);
                syn.addEdge(parent, child);
            }

        }

    }

    // // // /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> check = nouns();
        boolean rv = check.contains(noun);
        return rv;
    }

    // // /* Returns the set of all nouns. */
    public Set<String> nouns() {
        // // Set<Integer> zero = new TreeSet<Integer>();
        Set<String> rv = new TreeSet<String>();
        for (int i = 0; i < wordId.size(); i += 1) {
            String[] currSyn = wordId.get(i).split(" ");

            for (int j = 0; j < currSyn.length; j += 1) {
                String addWord = currSyn[j];
                rv.add(addWord);
            }
        }
        return rv;
    }

    // // /** Returns the set of all hyponyms of WORD as well as all synonyms of
    // // * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    // // * all of these synsets. See http://goo.gl/EGLoys for an example.
    // // * Do not include hyponyms of synonyms.
    // // */
    public Set<String> hyponyms(String word) {
        Set<String> rv = new TreeSet<String>();
        Set<Integer> checkID = new TreeSet<Integer>();
        
        for (int j = 0; j < wordId.size(); j += 1) {
            String[] currVals = wordId.get(j).split(" ");
            
            for (int k = 0; k < currVals.length; k += 1) {
                
                if (currVals[k].equals(word)) {
                    checkID.add(j);
                    break;
                }
            }
        }

        Set<Integer> hypID = GraphHelper.descendants(syn, checkID);

        for (int currID : hypID) {
            String currWord = wordId.get(currID);
            String[] words = currWord.split(" ");

            for (int i = 0; i < words.length; i += 1) {
                String addWord = words[i];
                rv.add(addWord);
            }
        }
        return rv;
    }
}
