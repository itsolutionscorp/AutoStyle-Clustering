package ngordnet;
import java.util.Set;
import java.util.Map; 
import java.util.TreeSet;
import java.util.TreeMap;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Map<Integer, Set<String>> idToSynset = new TreeMap<Integer, Set<String>>();
    private Map<String, Set<Integer>> wordToID = new TreeMap<String, Set<Integer>>();
    private Digraph digraph;
    private Map<String, Boolean> wordIsNouns = new TreeMap<String, Boolean>();
    private Set<String> allNouns = new TreeSet<String>();
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        
        //read synsetFile and put data into synset
        while (synsetFile.hasNextLine()) {
            Set<String> synset = new TreeSet<String>();
            String synsetLine = synsetFile.readLine();
            String[] elements = synsetLine.split(",");
            
            //get the ID
            Integer id = Integer.parseInt(elements[0]);
            
            //split the string of words into individual word
            String[] words = elements[1].split(" ");
            
            for (int i = 0; i < words.length; i++) {
              //put each word into synset, allNouns and isNouns
                synset.add(words[i]);
                allNouns.add(words[i]);
                wordIsNouns.put(words[i], true);
                
                //link each word to their IDs by mapping key as word, value as IDs
                if (wordToID.containsKey(words[i])) {
                    wordToID.get(words[i]).add(id);
                } else {
                    Set<Integer> setID = new TreeSet<Integer>();
                    setID.add(id);
                    wordToID.put(words[i], setID);
                }
            }            
            
            //link the id to synset by mapping the key as ID, value as synset
            idToSynset.put(id, synset);
        }
        
        digraph = new Digraph(allNouns.size());
        while (hyponymFile.hasNextLine()) {
            String hyponymLine = hyponymFile.readLine();
            String[] elements = hyponymLine.split(",");
            for (int i = 1; i < elements.length; i++) {
                digraph.addEdge(Integer.parseInt(elements[0]), Integer.parseInt(elements[i]));
            }
        }
    }
    
    public boolean isNoun(String noun) {
        return wordIsNouns.containsKey(noun);
    }
    
    public Set<String> nouns() {
        return allNouns;
    }
    
    public Set<String> hyponyms(String word) {
        Set<Integer> relatedID = GraphHelper.descendants(digraph, wordToID.get(word));
        Set<String> hyponyms = new TreeSet<String>();
        for (Integer id: relatedID) {
            hyponyms.addAll(idToSynset.get(id));
        }
        return hyponyms;
    }
}
