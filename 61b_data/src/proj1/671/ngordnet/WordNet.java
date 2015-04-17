package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet {
    private Digraph relationships;
    private HashMap<Integer, List<String>> wordDic = new HashMap<Integer, List<String>>();
    private int wordCount = 0;
    private String commaSplit = ",";
    private String spaceSplit = " ";
    
    public WordNet(String synsetFile, String hyponymFile) {
        In in;
        String [] line;
        List<String> synset;
        
        //creating HashMap: key is synset ID # and values are synset
        in = new In(synsetFile);
        while (in.hasNextLine()) {
            line = in.readLine().split(commaSplit);
            wordCount += 1;
            synset = new ArrayList<String>();
            for (String s: line[1].split(spaceSplit)) {
                synset.add(s);
            }
            wordDic.put(Integer.parseInt(line[0]), synset);
        }
        
        //creating Digraph 
        in = new In(hyponymFile);
        relationships = new Digraph(wordCount);
        int hypernym = 0;
        while (in.hasNextLine()) {
            line = in.readLine().split(commaSplit);
            for (int i = 0; i < line.length; i++) {
                if (i == 0) {
                    hypernym = Integer.parseInt(line[i]);
                } else {
                    relationships.addEdge(hypernym, Integer.parseInt(line[i]));
                }
            }
        }    
    }
    
    public boolean isNoun(String word) {
        for (List<String> s : wordDic.values()) {
            for (String noun : s) {
                if (noun.equals(word)) {
                    return true; 
                }    
            }
        }
        return false;

    }
    
    public Set<String> nouns() {
        Set<String> n = new HashSet<String>();
        for (List<String> s : wordDic.values()) {
            for (String noun : s) {
                n.add(noun);
            }
        }
        return n;
    }
    
    public Set<String> hyponyms(String word) {
        if (this.isNoun(word)) {
            int hypernymID = 0;
            Set<Integer> hyperSet = new HashSet<Integer>();
            for (Integer entry : wordDic.keySet()) {
                if (wordDic.get(entry).contains(word)) {
                    hypernymID = entry;
                    hyperSet.add(hypernymID);
                }                
            }
            Set<Integer> hypoSet = GraphHelper.descendants(relationships, hyperSet);
            Set<String> hypoWords = new HashSet<String>();
            for (Integer i : hypoSet) {
                for (String s : wordDic.get(i)) {
                    hypoWords.add(s);
                }
            }
            return hypoWords;
        }
        
        return null;
    }
    

}
