package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.nio.file.InvalidPathException;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph hyp;
    private HashMap<Integer, String[]> syn;
    private HashSet<String> words = new HashSet<String>();
    private int synCount = 0;

    /** Creates a WordNet using files from synsetFile 
    and hyponymFile */
    public WordNet(String synsetFile, String hyponymFile) {
        
        //Initializing the synsets:
        syn = new HashMap<Integer, String[]>(100);
        In sFile = new In(synsetFile);
        if (!sFile.exists()) { 
            throw new InvalidPathException(synsetFile, "File Not Found"); 
        }

        while (sFile.hasNextLine()) {
            String line = sFile.readLine();
            String[] rawTokens = line.split(",");
            Integer key = Integer.parseInt(rawTokens[0]);
            String[] value = rawTokens[1].split(" ");
            syn.put(key, value);
            synCount += 1;
            for (int i = 0; i < value.length; i++) {
                words.add(value[i]);
            }
        }

        sFile.close();

        //Initializing the hyponyms:
        hyp = new Digraph(synCount);
        In hFile = new In(hyponymFile);
        if (!hFile.exists()) {
            throw new InvalidPathException(hyponymFile, "File Not Found");
        }

        while (hFile.hasNextLine()) {
            String line = hFile.readLine();
            String[] rawTokens = line.split(",");
            int v = Integer.parseInt(rawTokens[0]);
            for (int i = 1; i < rawTokens.length; i++) {
                int w = Integer.parseInt(rawTokens[i]);
                hyp.addEdge(v, w);
            }
        }

        hFile.close();

        
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    
    public Set<String> hyponyms(String word) {
        if (!this.isNoun(word)) {
            System.out.println("I'm sorry, this word is not available.");
            return null;
        }

        Set<Integer> wordIDs = new HashSet<Integer>();
        HashSet<String> result = new HashSet<String>();

        for (int i = 0; i < syn.size(); i++) {
            String[] values = syn.get(i);
            if (Arrays.asList(values).contains(word)) {
                wordIDs.add(i);
            }
        }

        wordIDs = GraphHelper.descendants(hyp, wordIDs);

        for (Integer i : wordIDs) { //get each string for given id and add it to result
            String[] values = syn.get(i);
            for (String v : values) {
                result.add(v);
            }
        }

        return result;
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String word) {
        return words.contains(word);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return words;
    }
    
}
