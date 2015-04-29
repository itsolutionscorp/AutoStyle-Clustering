package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Map.Entry;

public class WordNet {
    private HashMap<Integer, String> synsets;
    private HashMap<String, ArrayList<Integer>> nouns;
    private Digraph synsetmap;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {  
        synsets = new HashMap<Integer, String>();
        nouns = new HashMap<String, ArrayList<Integer>>();
        In readsynset = new In(synsetFilename);

        while (readsynset.hasNextLine()) {  
            String[] tokens = readsynset.readLine().split(",");
            Integer index = Integer.parseInt(tokens[0]);
            String[] words = tokens[1].split(" ");

            synsets.put(index, tokens[1]);
            for (String word: words) {  
                if (nouns.containsKey(word)) {  
                    nouns.get(word).add(index);
                } else {      
                    ArrayList<Integer> indices = new ArrayList<Integer>();
                    indices.add(index);
                    nouns.put(word, indices);
                }
            }
        }

        synsetmap = new Digraph(synsets.size());

        In readhyponym = new In(hyponymFilename);
        while (readhyponym.hasNextLine()) {  
            String[] vertices = readhyponym.readLine().split(",");
            Integer hypernym = Integer.parseInt(vertices[0]);
            for (int i = 1; i < vertices.length; i++) {  
                synsetmap.addEdge(hypernym, Integer.parseInt(vertices[i]));
            }
        }

    }     

    /* Returns the set of all nouns. */
    public Set<String> nouns() {  
        return nouns.keySet();
    }
 

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {  
        Set<Integer> synsetlist = new HashSet<Integer>();
        
        //Gives you all the indices of the synsets of word
        for (Integer index : nouns.get(word)) {  
            synsetlist.add(index);
        }

        Set<String> hyponymlist = new HashSet<String>();

        Set<Entry<String, ArrayList<Integer>>> entries = nouns.entrySet();

        for (Integer i : GraphHelper.descendants(synsetmap, synsetlist)) {

            for (Entry<String, ArrayList<Integer>> entry: entries) {
                if (entry.getValue().contains(i)) {
                    hyponymlist.add(entry.getKey());
                }
            }
            
        }
        hyponymlist.add(word);
        return hyponymlist; 
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {  
        return nouns.containsKey(noun);
    }
}
