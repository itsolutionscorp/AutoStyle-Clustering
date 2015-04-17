package ngordnet;
import edu.princeton.cs.introcs.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;

/**
 * Citations: I discussed what type of data structures were the best types to use with
 * Bruce Cong (student). I also discussed the strategy of using two HashMaps as opposed
 * to one with Wesley Hsu (reader).
 * @author Jeff Weng
 */
public class WordNet {
    private Digraph d;
    private HashMap<String, ArrayList<Integer>> data;
    private HashMap<Integer, ArrayList<String>> data2;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) { 
        data = new HashMap<String, ArrayList<Integer>>();
        data2 = new HashMap<Integer, ArrayList<String>>();
        int maxSize = 0;
        In synReader = new In(synsetFilename);
        In hypReader = new In(hyponymFilename);
        while (synReader.hasNextLine()) { 
            String synset = synReader.readLine();
            String[] sTokens = synset.split(",");
            String[] sWords = sTokens[1].split(" ");
            int id = Integer.parseInt(sTokens[0]);
            ArrayList<String> x = new ArrayList<String>();
            for (String word: sWords) {
                x.add(word);
            }
            data2.put(id, x);
            for (String word: sWords) {
                if (data.containsKey(word)) {
                    data.get(word).add(id);
                } else {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(id);
                    data.put(word, temp);
                } 
                maxSize += 1;
            } 
        } 
        d = new Digraph(maxSize);
        while (hypReader.hasNextLine()) { 
            String hyponym = hypReader.readLine();
            String[] vertices = hyponym.split(",", 2);
            int head = Integer.parseInt(vertices[0]);
            String[] vertices2 = vertices[1].split(",");
            for (String vertex: vertices2) {
                d.addEdge(head, Integer.parseInt(vertex));
            } 
        } 
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) { 
        return nouns().contains(noun);
    } 
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() { 
        return data.keySet();   
    } 

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) { 
        if (!data.containsKey(word)) { 
            throw new IllegalArgumentException(word + " is not contained in any synset.");
        } 
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> idSet = new TreeSet<Integer>(data.get(word));
        Set<Integer> descID = GraphHelper.descendants(d, idSet);
        for (int id: descID) { 
            for (String w: data2.get(id)) { 
                hyponyms.add(w);
            } 
        } 
        return hyponyms;
    } 
} 
