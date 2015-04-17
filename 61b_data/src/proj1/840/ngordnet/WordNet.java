package ngordnet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet; 

import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In;

import java.util.HashMap;

public class WordNet {
    //This Digraph maps hypernym-hyponym relationships
    private Digraph g;
    //This HashMap stores key value bindings from a synset ID to a String HashSet of names
    private HashMap<Integer, HashSet<String>> synsetIDToNameMap; 
    //This HashMap stores key value bindings from a name to an Integer HashSet of synsetIDs
    private HashMap<String, HashSet<Integer>> nameToSynsetIDMap;
    
    /* Constructor for WordNet which takes in synset and hyponym files */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nameToSynsetIDMap = new HashMap<String, HashSet<Integer>>();
        synsetIDToNameMap = new HashMap<Integer, HashSet<String>>();
        In synsetfileReader = new In(synsetFilename);
        In hyponymfileReader1 = new In(hyponymFilename);
        In hyponymfileReader2 = new In(hyponymFilename); 
        
        //Reads the synset word file
        while (synsetfileReader.hasNextLine()) { 
            String [] synsetArray = synsetfileReader.readLine().split(",");
            String [] synsetNouns = synsetArray[1].split(" ");    
            int synsetID = Integer.parseInt(synsetArray[0]);     
            
            //Creates a HashSet containing all nouns corresponding to the current synset ID
            HashSet<String> nounsSet = new HashSet<String>();            
            for (String s : synsetNouns) {
                nounsSet.add(s);
            }
            //Adds to the synsetIDToName HashMap;
            synsetIDToNameMap.put(synsetID, nounsSet);    
            
            //Adds to the nameToSynsetID HashMap
            for (String s : synsetNouns) { 
                if (nameToSynsetIDMap.containsKey(s)) {
                    HashSet<Integer> temp = nameToSynsetIDMap.get(s); 
                    temp.add(synsetID);
                    nameToSynsetIDMap.put(s, temp);                
                } else {
                    HashSet<Integer> idList = new HashSet<Integer>();
                    idList.add(synsetID);
                    nameToSynsetIDMap.put(s, idList); 
                }                 
            }            
        }
        
        //Finds the number of vertexes
        int vertecies = 0; 
        while (hyponymfileReader1.hasNextLine()) {
            String [] hyponymArray = hyponymfileReader1.readLine().split(",");
            vertecies += hyponymArray.length;
        }
        //Intiailizes Digraph
        g = new Digraph(vertecies);
        //Reads the hyponym word file
        while (hyponymfileReader2.hasNextLine()) {             
            String [] hyponymArray = hyponymfileReader2.readLine().split(",");
            int primarySynsetID = Integer.parseInt(hyponymArray[0]);              
            for (int count = 1; count < hyponymArray.length; count++) {                  
                g.addEdge(primarySynsetID, Integer.parseInt(hyponymArray[count]));
            }            
        }
    }
    
    /*returns all hyponyms and synonyms of a specified word, but not the hyponyms of the synonyms
     */
    public Set<String> hyponyms(String word) { 
        if (nameToSynsetIDMap.containsKey(word)) {
            HashSet<Integer> synsetIDList = nameToSynsetIDMap.get(word);
            TreeSet<Integer> x = new TreeSet<Integer>();
            for (int i : synsetIDList) {
                x.add(i);
            }
            Set<Integer> hyponymIDS = GraphHelper.descendants(g, x);
            HashSet<String> hyponymNames = new HashSet<String>();
            Iterator<Integer> hypoIterator = hyponymIDS.iterator();
            while (hypoIterator.hasNext()) {
                HashSet<String> hypoList = ((synsetIDToNameMap.get(hypoIterator.next())));
                for (String s: hypoList) {
                    hyponymNames.add(s);
                }
            }
            return hyponymNames;
        }
        return null;        
    }
    
    /* Checks if the specified word is a noun */
    public boolean isNoun(String word) {
        return nameToSynsetIDMap.containsKey(word);
    }
    
    /* Returns a set of Strings of all nouns */
    public Set<String> nouns() {
        return nameToSynsetIDMap.keySet();
    }
    
}
