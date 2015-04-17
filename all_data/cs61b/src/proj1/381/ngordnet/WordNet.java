package ngordnet;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet { 
    private Digraph d;
    private int count = 0;
    private static final int SIZE = 1000000;
    private Map<String, ArrayList<Integer>> synsetStringToInteger 
        = new HashMap<String, ArrayList<Integer>>(SIZE);
    private Map<Integer, ArrayList<String>> synsetIntegerToString 
        = new HashMap<Integer, ArrayList<String>>(SIZE);
    
    
    public WordNet(String synsetFile, String hyponymFile) { 
        In sFile = new In(synsetFile);
        In hFile = new In(hyponymFile);
        while (sFile.hasNextLine()) {
            String line = sFile.readLine();
            String[] synsetInfo = line.split(",");
            int iD = Integer.parseInt(synsetInfo[0]);
            ArrayList<Integer> intValues = new ArrayList<Integer>();
            ArrayList<String> stringValues = new ArrayList<String>();
            intValues.add(iD);
            String key = synsetInfo[1];
            String[] multipleWords = key.split(" ");
            for (String k: multipleWords) { 
                if (synsetStringToInteger.containsKey(k)) { 
                    ArrayList<Integer> newList = new ArrayList<Integer>();
                    for (int i: synsetStringToInteger.get(k)) { 
                        newList.add(i);
                    } 
                    newList.add(iD);
                    synsetStringToInteger.put(k, newList);
                } else { 
                    synsetStringToInteger.put(k, intValues);
                } 
            } 
            for (String k: multipleWords) { 
                stringValues.add(k);
            } 
            synsetIntegerToString.put(iD, stringValues);
            count += 1;
        } 
        d = new Digraph(count);
        while (hFile.hasNextLine()) { 
            String line = hFile.readLine();
            String[] hyponymInfo = line.split(",", 2);
            int hypernym = Integer.parseInt(hyponymInfo[0]);
            String[] hyponymsArray = hyponymInfo[1].split(",");
            for (String hyponym: hyponymsArray) { 
                int hyponymId = Integer.parseInt(hyponym);
                d.addEdge(hypernym, hyponymId);
            } 
        } 
    } 
    
    public boolean isNoun(String noun) { 
        if (noun == null) { 
            return false;
        } else { 
            if (nouns() == null) { 
                return false;
            } else { 
                return nouns().contains(noun);
            } 
        } 
    } 

    public Set<String> nouns() { 
        if (synsetStringToInteger == null) { 
            return null;
        } else { 
            return synsetStringToInteger.keySet();
        } 
    } 
    
    public Set<String> hyponyms(String word) { 
        Set<String> hyp = new HashSet<String>();
        ArrayList<Integer> synsetId = synsetStringToInteger.get(word);
        Set<Integer> synsetIds = new TreeSet<Integer>();
        for (int i : synsetId) { 
            synsetIds.add(i);
        }
        Set<Integer> gh = GraphHelper.descendants(d, synsetIds);
        for (int i : gh) { 
            for (String g : synsetIntegerToString.get(i)) { 
                hyp.add(g);
            } 
        } 
        return hyp;
    } 
    
} 
