package ngordnet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<String, Set<Integer>> dict; //Word -> IDs
    private HashMap<Integer, Set<String>> syns; //ID -> words
    private Digraph hyps; //hyps digraph
    
    private HashMap<String, Set<String>> cachedHyps; //cached hyps generation
    
    private String line = "";
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    
    private BufferedReader br;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        
        dict = new HashMap<String, Set<Integer>>();
        syns = new HashMap<Integer, Set<String>>();
        
        cachedHyps = new HashMap<String, Set<String>>();
        
        try {
            //synsets to hashmap
            br = new BufferedReader(new FileReader(synsetFilename));
            TimeLogger.debugTime("reading synset");
            while ((line = br.readLine()) != null) {
                
                String[] fields = line.split(COMMA);
                
                int id = Integer.parseInt(fields[0]);
                String[] words = fields[1].split(SPACE);
                
                Set<String> wordsSet = new HashSet<String>();
                //words to ids
                for (String s : words) {
                    wordsSet.add(s);
                    if (dict.containsKey(s)) {
                        dict.get(s).add(id);
                    } else {
                        Set<Integer> newIdList = new HashSet<Integer>();
                        newIdList.add(id);
                        dict.put(s, newIdList);
                    }
                }
                //id to ids
                syns.put(id, wordsSet);                    
            }
            
            //hyps digraph
            hyps = new Digraph(syns.size());
            TimeLogger.debugTime("reading hyps");
            br = new BufferedReader(new FileReader(hyponymFilename));
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(COMMA);
                
                int parentID = Integer.parseInt(fields[0]);
                
                for (int i = 1; i < fields.length; i += 1) {
                    int childID = Integer.parseInt(fields[i]);
                    hyps.addEdge(parentID, childID);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return dict.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return dict.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        
        if (!cachedHyps.containsKey(word)) {
            
            Set<Integer> ids = dict.get(word);
            Set<String> hypSet = new HashSet<String>();
            Set<Integer> hypSetIDs = new HashSet<Integer>();
            
            hypSetIDs = GraphHelper.descendants(hyps, new HashSet<Integer>(ids));
            
            for (int id : hypSetIDs) {
                hypSet.addAll(syns.get(id));
            }

            cachedHyps.put(word, hypSet);
        }
        
        return cachedHyps.get(word);
    }
}
