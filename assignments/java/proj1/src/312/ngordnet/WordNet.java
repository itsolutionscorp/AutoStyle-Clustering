package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import ngordnet.GraphHelper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private int v = 0;
    private int w;
    private HashMap<Integer, String[]> forsyn;
    private Digraph s;
    
    

    public WordNet(String synsetFilename, String hyponymFilename) {/** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
        
        forsyn = new HashMap<Integer,String[]>();//hashmap for the synsets//
        ArrayList<String> hypo = new ArrayList<String>();
        In synsetfiles = new In(synsetFilename);
        In hypofiles = new In(hyponymFilename);
        
        while(!synsetfiles.isEmpty()) {
            String line = synsetfiles.readLine();
            String[] information = line.split(","); 
            int values = Integer.parseInt(information[0]);
            String[] synonyms = information[1].split(" ");
            forsyn.put(values, synonyms);
        }
        s = new Digraph(forsyn.size());
        while (!hypofiles.isEmpty()) {
            String line = hypofiles.readLine();
            String [] information = line.split(",");
            for(int i = 1; i < information.length; i += 1){
                s.addEdge(Integer.parseInt(information[0]), Integer.parseInt(information[i]));
            }
        }
    }   
    


    public boolean isNoun (String noun) {/* Returns true if NOUN is a word in some synset. */
        for (String[] synonyms : forsyn.values()) {
            for (String word : synonyms) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> x = new HashSet<String>();
        for(String[] synonyms : forsyn.values()) {
            for(String word : synonyms){
                x.add(word);
            }
        }
        return x;
    }


    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> hypernymNums = new HashSet<Integer>();
        for (Integer num : forsyn.keySet()) {
            String[] synonyms = forsyn.get(num);
            for (String synonym : synonyms) {
                if (synonym.equals(word)) {
                    hypernymNums.add(num);
                }
            }
        }
        if (hypernymNums == null) {
            return null;
        }

        Set<Integer> hyponymNums = 
            GraphHelper.descendants(s, hypernymNums);

        HashSet<String> hyponyms = new HashSet<String>();
        for (Integer hyponymNum : hyponymNums) {
            String[] hyponymSynonyms = forsyn.get(hyponymNum);
            for (String synonym : hyponymSynonyms) {
                hyponyms.add(synonym);
            }
        }
        return hyponyms;
    }
}
    