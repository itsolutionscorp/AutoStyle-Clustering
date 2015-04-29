package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import java.util.Iterator;


public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    // private String synsetFilename;
    // private String hypernymFilename;
    private int counter = 0;
    private Digraph digraph1;
    // List<Integer> idList = new ArrayList<Integer>();

    private Map<Integer, Integer[]> idHyponymMap = new HashMap<Integer, Integer[]>();
    private Set<String> synsetSet = new HashSet<String>();
    private List<Integer> synsetList = new ArrayList<Integer>();
    private Map<String, Integer> wordToId = new HashMap<String, Integer>();
    private Map<Integer, String> idToWord = new HashMap<Integer, String>();

    private Map<String, String[]> synset2words = new HashMap<String, String[]>();
    private Map<String[], String> words2synset = new HashMap<String[], String>();
    private Set<String> wordSet = new HashSet<String>();

    private Map<String, List<String>> multiSynsets = new HashMap<String, List<String>>();


    public WordNet(String synsetFilename, String hypernymFilename) {
        In inSynset = new In(synsetFilename);
        In inHypernym = new In(hypernymFilename);

        String currentLine = inSynset.readLine();
        String currentLine2 = inHypernym.readLine();
        while (currentLine != null) {
            String[] parts = currentLine.split(",");
            String currId = parts[0];
            String currSynset = parts[1];
            String currDefinition = parts[2];
            counter = counter + 1;
            synsetSet.add(currSynset);
            synsetList.add(Integer.parseInt(currId));
            wordToId.put(currSynset, Integer.parseInt(currId));
            idToWord.put(Integer.parseInt(currId), currSynset);
            // break up the synset into words
            String[] words = currSynset.split(" ");
            synset2words.put(currSynset, words);
            words2synset.put(words, currSynset);
            for (int i = 0; i < words.length; i++) {
                wordSet.add(words[i]);
                //Set<String> pivotSet = new HashSet<String>();
                List<String> list1 = new ArrayList<String>();
                if (multiSynsets.keySet().contains(words[i])) {
                    list1 = new ArrayList<String>(multiSynsets.get(words[i]));
                } 
                list1.add(currSynset);
                multiSynsets.put(words[i], list1);
            }
            currentLine = inSynset.readLine();
        }
        while (currentLine2 != null) {
            String[] parts2 = currentLine2.split(",");
            Integer[] parts2Int = new Integer[parts2.length];
            for (int j = 0; j < parts2.length; j++) {
                parts2Int[j] = Integer.parseInt(parts2[j]);
            }

            Integer currId2 = parts2Int[0];
            Integer[] currHyponyms = new Integer[parts2Int.length - 1];

            for (int i = 0; i < parts2Int.length - 1; i++) {
                currHyponyms[i] = parts2Int[i + 1];
            }
            Integer[] ints1 = idHyponymMap.get(currId2);
            if (ints1 == null) {
                idHyponymMap.put(currId2, currHyponyms);
            } else {
                Integer[] hypsTotal = new Integer[currHyponyms.length + ints1.length];
                for (int k = 0; k < currHyponyms.length; k++) {
                    hypsTotal[k] = currHyponyms[k];

                }
                for (int p = 0; p < ints1.length; p++) {
                    hypsTotal[p + currHyponyms.length] = ints1[p];
                }
                idHyponymMap.put(currId2, hypsTotal);
            }
            currentLine2 = inHypernym.readLine();
        }
        // digraph creation
        digraph1 = new Digraph(counter);
        //System.out.println(counter);
        // this for loop loops through all the synsets from synsetList
        for (int m = 0; m < counter; m++) {
            int vertex1 = synsetList.get(m);
            // vertex1 is just the id some synset from the synsetList.  
            // idHyponymMap.get(vertex1) returns the Integer array of hyponyms of vertex1
            // this for loop loops through the hyponyms of the synset vertex1
            if (idHyponymMap.get(vertex1) != null) {
                for (int k = 0; k < idHyponymMap.get(vertex1).length; k++) {
                    int vertex2 = idHyponymMap.get(vertex1)[k];
                    digraph1.addEdge(vertex1, vertex2);
                }
            }       
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        //System.out.println(synset2words.values());

        if (wordSet.contains(noun)) {
            return true;
        }
        return false;

        // for (String i : synset2words.values[i]) {
        //      if (synset2words.values().contains(noun)) {
        //         return true;
        // }
       
        // }
        // return false;

     //    if (synsetSet.contains(noun)) {
     //        return true;
     //    }
     // return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordSet;
    }

    private String word2synset(String word) {
        Set<String> toReturn = new HashSet<String>();
        if (word == null) {
            return null;
        } else {
            Set<String[]> a = new HashSet<String[]>(words2synset.keySet());

            Iterator iterator = a.iterator();
            
            while (iterator.hasNext()) {
                String[] s1 = (String []) iterator.next();
                for (String i : s1) {
                    // System.out.println(i);
                    if (word.equals(i)) {
                        // System.out.println("enters return");

                    
                        return words2synset.get(s1);
                    }
                }  
            }
            // System.out.println("word2synset is null");
            return null;
        }
        
    }

    private Set<String> synset2words(String synset) {
        String[] words = synset.split(" ");
        Set<String> toReturn = new HashSet<String>();
        for (String i : words) {
            toReturn.add(i);
        }
        return toReturn;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word)  {
        
        // getting the set of hyponyms from a synset using digraph
        // for (int i: )
        // words2synset.get(word)
        String initialSynset = word2synset(word);
        //System.out.println(initialSynset);


        Set<Integer> inputOfDescendants2 = new HashSet<Integer>();

        List<String> list2 = new ArrayList<String>(multiSynsets.get(word));
        //System.out.println(list2);
        for (String j : list2) {
            // System.out.println(j);
            inputOfDescendants2.add(wordToId.get(j));
        }





        Set<Integer> inputOfDescendants = new HashSet<Integer>();
        inputOfDescendants.add(wordToId.get(initialSynset));

        // descendants takes in a digraph and a set of integer IDs
        // outputs the set of Integers that are hyponyms of that input set(in this case)
        if (inputOfDescendants.isEmpty()) {
            // System.out.println("inputOfDescendants is empty");
            return null;
        }
        // System.out.println(inputOfDescendants);

        Set<Integer> descendantsIdsSet = GraphHelper.descendants(digraph1, inputOfDescendants2);

        List<Integer> descendantsIdsList = new ArrayList<Integer>(descendantsIdsSet);
        //System.out.println(descendantsIdsList);
        
        Set<String> finalSynsets = new HashSet<String>();

        for (int r = 0; r < descendantsIdsList.size(); r++) {
            finalSynsets.add(idToWord.get(descendantsIdsList.get(r)));
            //System.out.println(finalSynsets);
            //toReturn.add(hyponyms(idToWord.get(descendantsIdsList.get(r))));
        }

        // String[] hypWords = currSynset.split(" ");
        //     // System.out.println(words);
        //     synset2words.put(currSynset, words);
        //     words2synset.put(words, currSynset);
        //     for (int i = 0; i < words.length; i++) {
        //         wordSet.add(words[i]);
        //     }
        Set<String> finalWords = new HashSet<String>();
        Iterator iterator = finalSynsets.iterator();  
        while (iterator.hasNext()) {
            String s1 = (String) iterator.next();
            //finalWords.addAll(synset2words(s1));
            String[] words = s1.split(" ");
            
            for (String i : words) {
                finalWords.add(i);
                // Set<String> hyps = new HashSet<String>(hyponyms(i));
                // System.out.println(hyps);
                // if (hyponyms(i) != null) {
                    
                    
                //     Iterator iterator2 = hyps.iterator();
                //     while (iterator2.hasNext()) {
                //         String s2 = (String) iterator2.next();
                //         finalWords.add(s2);
                // }
                // }
                
                
            }
        }
        return finalWords;
    }
}




