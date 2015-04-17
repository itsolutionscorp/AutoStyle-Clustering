package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    private Digraph net;
    private HashMap<Integer, String> idToSynset;
    private HashMap<String, Set<Integer>> wordToid;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synIn = new In(synsetFilename);
        In hypIn = new In(hyponymFilename);
        
        idToSynset = new HashMap<Integer, String>();
        wordToid = new HashMap<String, Set<Integer>>();

        String line;
        String[] arr;

        int id;
        String synset;
        
        while (synIn.hasNextLine()) {
            line = synIn.readLine();
            arr = line.split(",");

            id = Integer.parseInt(arr[0]);
            synset = arr[1];

            idToSynset.put(id, synset);
            
            String[] words = synset.split(" ");
            
            for (String word : words) {
                if (!wordToid.containsKey(word)) {
                    Set<Integer> ids = new HashSet<Integer>();
                    ids.add(id);
                    wordToid.put(word, ids);
                } else {
                    wordToid.get(word).add(id);
                }
            }
        }
        synIn.close();

        net = new Digraph(idToSynset.size());

        int start;
        int end;

        while (hypIn.hasNextLine()) {
            line = hypIn.readLine();
            arr = line.split(",");

            start = Integer.parseInt(arr[0]);

            for (int i = 1; i < arr.length; i += 1) {
                end = Integer.parseInt(arr[i]);
                net.addEdge(start, end);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToid.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToid.keySet();
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<Integer> idSet = new HashSet<Integer>();
        idSet = wordToid.get(word);
        idSet.addAll(GraphHelper.descendants(net, idSet));
        
        Set<String> hyponyms = new HashSet<String>();
        for (int id : idSet) {
            String synset = idToSynset.get(id);
            Set<String> words = new HashSet<String>();
            for (String w : synset.split(" ")) {
                words.add(w);
            }
            hyponyms.addAll(words);
        }
        
        return hyponyms;
    }
}
