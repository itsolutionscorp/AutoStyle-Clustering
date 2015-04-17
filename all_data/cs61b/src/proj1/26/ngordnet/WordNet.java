package ngordnet;

import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private List<HashSet<String>> nouns;
    private Map<Integer, HashSet<Integer>> hypos;
    private Map<String, HashSet<Integer>> nounMap;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synIn = new In(synsetFilename);
        In hypIn = new In(hyponymFilename);

        nouns = new ArrayList<HashSet<String>>();
        nounMap = new HashMap<String, HashSet<Integer>>();

        while (synIn.hasNextLine()) {
            String line = synIn.readLine();
            String[] elements = line.split(",");
            Integer curLine = Integer.parseInt(elements[0]);

            HashSet<String> synSet = new HashSet<String>();
            for (String s : elements[1].split(" ")) {
                synSet.add(s);
            }

            nouns.add(curLine, synSet);
            
            for (String s : synSet) {
                HashSet<Integer> nSet = nounMap.get(s);
                if (nSet == null) {
                    nSet = new HashSet<Integer>();
                    nSet.add(curLine);
                    nounMap.put(s, nSet);
                } else {
                    nSet.add(curLine);
                }
            }            
        }

        hypos = new HashMap<Integer, HashSet<Integer>>();

        while (hypIn.hasNextLine()) {
            String line = hypIn.readLine();
            String[] elements = line.split(",");

            HashSet<Integer> hypSet = hypos.get(Integer.parseInt(elements[0]));
            if (hypSet == null) {
                hypSet = new HashSet<Integer>();
                hypos.put(Integer.parseInt(elements[0]), hypSet);
            }
                
            for (int i = 1; i < elements.length; i += 1) {
                hypSet.add(Integer.parseInt(elements[i]));
            }
        }
        synIn.close();
        hypIn.close();
    }
    
    /** Works by taking the starting nodes of the provided word and
     *  flattening the tree in hypos through getAllHyponyms such that
     *  there is a Set<Integer> with the starting nodes and all lower
     *  nodes. It then uses that set to get all words at those nodes.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> allSet = getAllHyponyms(nounMap.get(word));
        Set<String> retStrings = new HashSet<String>();
        for (Integer i : allSet) {
            retStrings.addAll(nouns.get(i));
        }
        return retStrings;
    }

    /** Takes in a Set<Integer> that represents the starting nodes
     *  and recursively returns the entire Set<Integer> that represents
     *  all nodes to the bottom of the graph represented by the edge set hypos
     */ 
    private Set<Integer> getAllHyponyms(Set<Integer> nodes) {
        if (nodes == null) {
            return Collections.emptySet();
        }
        Set<Integer> retSet = new HashSet<Integer>(nodes);
        for (Integer i : nodes) {
            Set<Integer> nextSet = hypos.get(i);
            retSet.addAll(getAllHyponyms(nextSet));
        }
        return retSet;
    }

    public Set<String> nouns() { 
        return nounMap.keySet();
    }

    public boolean isNoun(String noun) {
        return nounMap.keySet().contains(noun);
    }
}
