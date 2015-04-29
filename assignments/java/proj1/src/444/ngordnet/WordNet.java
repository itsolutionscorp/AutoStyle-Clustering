package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private HashMap<Integer, Set<String>> synset = new HashMap<Integer, Set<String>>();
    private HashMap<String, Set<Integer>> wordIDs = new HashMap<String, Set<Integer>>();  
    private HashSet<String> allNouns = new HashSet<String>();
    // HashSet<Integer> allHyps = new HashSet<Integer>();
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        /* 
          1) Put all ID with nouns: treemap; Map<Integer, List<String>> 
          2) 
        */
        In inSyn = new In(synsetFilename); 
        In inHyp = new In(hyponymFilename);        

        // add all the nouns associated with the number;

        while (inSyn.hasNextLine()) {
            String line = inSyn.readLine();
            String[] rawTokens = line.split(",");
            Integer id = Integer.parseInt(rawTokens[0]);
            String temp = rawTokens[1];

            Set<String> inner = new HashSet<String>();
            String[] split = temp.split(" ");
            for (String r: split) {
                inner.add(r);
                if (wordIDs.containsKey(r)) {
                    Set<Integer> ids = wordIDs.get(r);
                    ids.add(id);
                    wordIDs.put(r, ids);
                } else {
                    Set<Integer> ids = new HashSet<Integer>();
                    ids.add(id);
                    wordIDs.put(r, ids);
                }            
            }
            allNouns.add(temp);              
            synset.put(id, inner);
        }

        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> allNums = new ArrayList<Integer>();
        while (inHyp.hasNextLine()) {
            String line = inHyp.readLine();
            String[] rawTokens = line.split(",");
            ArrayList<Integer> innerList = new ArrayList<Integer>();
            for (int o = 0; o < rawTokens.length; o++) {
                innerList.add(Integer.parseInt(rawTokens[o]));
                allNums.add(Integer.parseInt(rawTokens[o]));
            }
            temp.add(innerList);
        }

        int maxInt = 0;
        for (int s: allNums) {
            if (s >= maxInt) {
                maxInt = s;
            }
        }

        g = new Digraph(maxInt + 1);
        for (int i = 0; i < temp.size(); i++) {
            ArrayList<Integer> curr = temp.get(i);
            for (int q = 1; q < curr.size(); q++) {
                g.addEdge(curr.get(0), curr.get(q));
            }
        }
            
        //     HashSet<Integer> temp = new HashSet<Integer>();
            
        //         temp.add(Integer.parseInt(rawTokens[i]));
        //         allHyps.add(Integer.parseInt(rawTokens[i]));              
        //     }
        //     hyponyms.put(id, temp);
        // }
    }

    // private HashMap<String, Integer> invertMap(Map mapp) {
    //     HashMap<String, Integer> rtr = new HashMap<String, Integer>();
    //     for (Integer key: ((Set<Integer>) mapp.keySet())) {
    //         rtr.put((String) mapp.get(key), key);
    //     }
    //     return rtr;
    // }

    public Set<String> hyponyms(String word) {
        // HashMap<String, Integer> invertSyn = invertMap(synset);
        // HashSet<Integer> wordId = new HashSet<Integer>(); 
        // wordId.add(invertSyn.get(word));
        Set<Integer> ids = wordIDs.get(word);
        Set<Integer> nums = GraphHelper.descendants(g, ids);
        Set<String> rtr = new HashSet<String>();
        for (Integer s: nums) {
            Set<String> temp = synset.get(s);
            for (String r: temp) {
                rtr.add(r);
            }
        }
        return rtr; 
        //returns the set of all hyponyms of word & synonyms of word
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        return wordIDs.keySet();
    }
}



