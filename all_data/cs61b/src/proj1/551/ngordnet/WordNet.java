package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

public class WordNet {
    private Digraph dgraph;
    private Set<String> nounSet;
    private Map<Integer, Set<String>> idToSynsMap;
    private Map<String, Set<Integer>> wordToIDMap;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        nounSet = new TreeSet<String>();
        // stores ID number and corresponding synset
        idToSynsMap = new TreeMap<Integer, Set<String>>(); 
        // stores word and corresponding ID number(s)
        wordToIDMap = new TreeMap<String, Set<Integer>>(); 

        In synIn = new In(synsetFilename);
        while (synIn.hasNextLine()) {
            String line = synIn.readLine();
            String[] rawTokens = line.split(",");
            int id = Integer.parseInt(rawTokens[0]); 
            // separate words in synset
            String[] synsetTokens = rawTokens[1].split(" "); 

            Set<String> synset = new TreeSet<String>();

            for (String noun : synsetTokens) {             
                synset.add(noun);                               
                nounSet.add(noun);                          
                // if map already contains the word, add id to existing id set  
                if (wordToIDMap.containsKey(noun)) {        
                    wordToIDMap.get(noun).add(id);
                } else {
                    Set<Integer> idSet = new TreeSet<Integer>();
                    idSet.add(id);
                    wordToIDMap.put(noun, idSet);
                }
            }
            idToSynsMap.put(id, synset);
        }
        dgraph = new Digraph(idToSynsMap.size());

        In hypIn = new In(hyponymFilename);
        while (hypIn.hasNextLine()) {
            String line = hypIn.readLine();
            String[] rawTokens = line.split(",");
            int id = Integer.parseInt(rawTokens[0]); 
            String[] hypIDs = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, hypIDs, 0, rawTokens.length - 1);
            
            Set<Integer> hyps = new TreeSet<Integer>();

            for (String hypID : hypIDs) {
                int hypNum = Integer.parseInt(hypID);
                hyps.add(hypNum);
                dgraph.addEdge(id, hypNum);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
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
        Set<String> hypAndSynSet = new TreeSet<String>();

        if (isNoun(word)) {
            Set<Integer> idNums = wordToIDMap.get(word);
            for (int idNum : idNums) {
                Set<String> synset = idToSynsMap.get(idNum);
                for (String syn : synset) {
                    hypAndSynSet.add(syn);
                }
            
                Set<Integer> wordID = new TreeSet<Integer>();
                wordID.add(idNum);
                Set<Integer> hyponyms = GraphHelper.descendants(dgraph, wordID);
                for (int hypID : hyponyms) {
                    Set<String> hypWords = idToSynsMap.get(hypID);
                    for (String hyp : hypWords) {
                        hypAndSynSet.add(hyp);
                    }
                }
            }
        }
        return hypAndSynSet;
    }
}
