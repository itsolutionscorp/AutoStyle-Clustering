package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;


public class WordNet {
    
    private HashMap<Integer, List<String>> wordMap;
    private String nextSynsetLine;
    private String nextHyponymLine;
    private String[] tempSynsetArray;
    private String[] tempHyponymArray;
    //String tempSynonyms[][] = new String[2][];
    private String[] tempSplitArray;
    private int nextKey;
    private int hypernymKey;
    private int hyponymKey;
    private In synsetChunk;
    private In hyponymsChunk;
    private Digraph wordNetGraph;
    // List<String> synonymsList;


    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetChunk = new In(synsetFilename);
        hyponymsChunk = new In(hyponymFilename);
        wordMap = new HashMap<Integer, List<String>>();
        // hyponymsMap = new HashMap<Integer, List<Integer>>();
        // synonymsList = new ArrayList<String>();

        while (synsetChunk.hasNextLine()) {
            List<String> synonymsList = new ArrayList<String>();
            nextSynsetLine = synsetChunk.readLine();
            tempSynsetArray = nextSynsetLine.split(",");
            tempSplitArray = tempSynsetArray[1].split(" ");
            for (int i = 0; i < tempSplitArray.length; i++) {
                synonymsList.add(tempSplitArray[i]);
            }
            nextKey = Integer.parseInt(tempSynsetArray[0]);
            wordMap.put(nextKey, synonymsList);
        }

        wordNetGraph = new Digraph(wordMap.size());


        while (hyponymsChunk.hasNextLine()) {
            nextHyponymLine = hyponymsChunk.readLine();
            tempHyponymArray = nextHyponymLine.split(",");
            hypernymKey = Integer.parseInt(tempHyponymArray[0]);

            for (int i = 1; i < tempHyponymArray.length; i++) {
                hyponymKey = Integer.parseInt(tempHyponymArray[i]);
                if (wordMap.containsKey(hypernymKey) && wordMap.containsKey(hyponymKey)) {
                    wordNetGraph.addEdge(hypernymKey, hyponymKey);
                
                    
                }
            }   
        }
    }

    public boolean isNoun(String noun) {
        for (int i = 0; i < wordMap.size(); i++) {
            if (wordMap.get(i).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nounsSet = new HashSet<String>();

        for (int i = 0; i < wordMap.size(); i++) {
            for (int k = 0; k < wordMap.get(i).size(); k++) {
                nounsSet.add(wordMap.get(i).get(k));
            }
            
        }
        //addAll
        return nounsSet;
    }

    // private void helperHyponyms(String word, int foundHyponym, Set<String> hyponymsSet) {
    //     List<Integer> foundHyponymsList = new ArrayList<Integer>();
    //     // for(int i = 0; i < wordMap.size(); i++){
    //     //  for(int k = 0; k <wordMap.get(i).size(); k++){
    //     //      if(wordMap.get(i).get(k).contains(word)){
    //     if (hyponymsMap.containsKey(foundHyponym)) {
    //         foundHyponymsList = hyponymsMap.get(foundHyponym);
    //         for (int j = 0; j < foundHyponymsList.size(); j++) {
    //             hyponymsSet.addAll(wordMap.get(foundHyponymsList.get(j)));
    //             helperHyponyms(word, foundHyponymsList.get(j), hyponymsSet);
    //         }
    //     }
    //     //      }
    //     //  }
    //     // }
    //                 //GraphHelper.descendants(wordNetGraph, hypernymKey);
    // }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymsSet = new HashSet<String>();
        Set<Integer> foundHyponymsList = new HashSet<Integer>();


        for (int i = 0; i < wordMap.size(); i++) {
            if (wordMap.get(i).contains(word)) {
                hyponymsSet.addAll(wordMap.get(i));
                foundHyponymsList.add(i);
            }
        }
        // for (Integer synsetMapKey : wordMap.keySet()) {
        //     for (int k = 0; k < wordMap.get(synsetMapKey).size(); k++) {
        //         if (wordMap.get(synsetMapKey).get(k).contains(word)) {
        //             // helperHyponyms(word, i, hyponymsSet);
        //             foundHyponymsList.add(synsetMapKey);
        //             // if(hyponymsMap.containsKey(i)){
        //             //  foundHyponymsList = hyponymsMap.get(i);
        //             //  for(int j = 0; j <foundHyponymsList.size(); j++){
        //             //      hyponymsSet.addAll(wordMap.get(foundHyponymsList.get(j)));

        //             //  }
        //             // }
        //         }
        //     }
        // }

        Set<Integer> descendants = GraphHelper.descendants(wordNetGraph, foundHyponymsList);

        for (Integer hyponymsKey : descendants) {
            for (String hyponymVal : wordMap.get(hyponymsKey)) {
                hyponymsSet.add(hyponymVal);
            }

        }

        return hyponymsSet;

    }

}
