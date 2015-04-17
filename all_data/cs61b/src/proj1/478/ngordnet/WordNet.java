package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;


public class WordNet {
    private HashMap<Integer, String[]> synsetHashMap = new HashMap<Integer, String[]>();
    private HashMap<String, ArrayList<Integer>> wordIDHashMap = 
            new HashMap<String, ArrayList<Integer>>();
    private Digraph hypoGrapher;
    private ArrayList<Integer> identification;
    

    public WordNet(String synsetFilename, String hyponymFilename) {
        // Get the numbers from the synsetFilename, use that as the key
        // Get the word(s), make that as the value as a string array, 
        // 
        
        /**       Create HashMap of ID -> synset 
         *        Create HashMap of Word -> ID
         *        
         *        Synsets consist of one or more words in English that all have
         *        the same meaning. For example, one synset is "jump, parachuting"
         * */
        In inSynset = new In(synsetFilename);
        int synsetCounter = 0;
        while (inSynset.hasNextLine()) {
            String synsetLine = inSynset.readLine();
            String[] synsetSplit = synsetLine.split(",");
            Integer synsetID = Integer.parseInt(synsetSplit[0]);
            ArrayList<Integer> synsetIDList = new ArrayList<Integer>();
            String[] synsetWords = synsetSplit[1].split(" ");
            synsetCounter += 1;
            synsetHashMap.put(synsetID, synsetWords);
            for (int i = 0; i < synsetWords.length; i += 1) {
                if (wordIDHashMap.containsKey(synsetWords[i])) {
                    wordIDHashMap.get(synsetWords[i]).add(synsetID);
                } else {
                    synsetIDList.add(synsetID);
                    wordIDHashMap.put(synsetWords[i], synsetIDList);
                }
            }
        }
        
        /**  Create Digraph so that we can create arrows */
        hypoGrapher = new Digraph(synsetCounter);
        In inHyponym = new In(hyponymFilename);
        Integer currHyponyms;
        while (inHyponym.hasNextLine()) {
            String hyponymLine = inHyponym.readLine();
            if (hyponymLine.equals("")) {
                hyponymLine = inHyponym.readLine();
            }
            String[] hyponymSplit = hyponymLine.split(",");
            Integer hyponymID = Integer.parseInt(hyponymSplit[0]);
            for (int i = 1; i < hyponymSplit.length; i += 1) {
                currHyponyms = Integer.parseInt(hyponymSplit[i]);
                hypoGrapher.addEdge(hyponymID, currHyponyms);
            }
        }        
    }
    
    public boolean isNoun(String noun) {
        return wordIDHashMap.containsKey(noun);
    }
    
    public Set<String> nouns() {
        Set<String> mySet = new HashSet<String>();
        mySet = wordIDHashMap.keySet();
        return mySet;
    }
    
    public Set<String> hyponyms(String word) {
        Set<Integer> idOfWord = new TreeSet<Integer>();
        Set<String> hypoConverted = new TreeSet<String>();
        if (wordIDHashMap.get(word) == null) {
            throw new IllegalArgumentException();
        }
        for (int x = 0; x < wordIDHashMap.get(word).size(); x += 1) {
            idOfWord.add(wordIDHashMap.get(word).get(x));
            Set<Integer> hypoGetter = GraphHelper.descendants(hypoGrapher, idOfWord);
            Integer[] arrHypo = hypoGetter.toArray(new Integer[hypoGetter.size()]);
            for (int i = 0; i < arrHypo.length; i += 1) {
                String[] str = synsetHashMap.get(arrHypo[i]);
                for (int j = 0; j < str.length; j += 1) {
                    String str1 = str[j];
                    hypoConverted.add(str1);
                }
            }
        }
        return hypoConverted;
    }    
}
