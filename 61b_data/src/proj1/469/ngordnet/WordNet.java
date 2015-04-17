package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;


public class WordNet {

    private HashMap<Integer, String[]> synsetIDMap = new HashMap<Integer, String[]>();
    private HashMap<String, HashSet<Integer>> synsetWordMap = 
        new HashMap<String, HashSet<Integer>>();
    private HashSet<String> nounSet = new HashSet<String>();
    private Digraph graph;

    /* Constructs WordNet Data Structure and loads with contents of 
    the provided String arguments for the synsetFile and the hyponymFile */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);

        String[] synsetArr;
        String synsetCurrLine;
        String[] synsetCurrSet;
        int numberOfSynsets = 0;

        Integer synsetID;
        String[] hyponymArr;
        String hyponymCurrLine;
        int hyponymDivider;
        int numberOfEdges = 0;

        while (synsetFile.hasNextLine()) {
            synsetCurrLine = synsetFile.readLine();
            synsetArr = synsetCurrLine.split(",");

            String[] synsetWords = new String[synsetArr[1].split(" ").length];
            synsetWords = synsetArr[1].split(" ");
            synsetIDMap.put(Integer.parseInt(synsetArr[0]), synsetWords);

            synsetCurrSet = synsetArr[1].split(" ");

            for (int i = 0; i < synsetCurrSet.length; i++) {
                if (synsetWordMap.containsKey(synsetCurrSet[i])) {
                    HashSet<Integer> setToUpdate = synsetWordMap.get(synsetCurrSet[i]);
                    setToUpdate.add(Integer.parseInt(synsetArr[0]));
                    synsetWordMap.put(synsetCurrSet[i], setToUpdate);       
                } else {
                    HashSet<Integer> setToInsert = new HashSet<Integer>();
                    int integerToAdd = Integer.parseInt(synsetArr[0]);
                    setToInsert.add(integerToAdd);
                    synsetWordMap.put(synsetCurrSet[i], setToInsert);
                }
                nounSet.add(synsetCurrSet[i]);
            }
            numberOfSynsets += 1;
            
        }
        graph = new Digraph(numberOfSynsets);

    
        while (hyponymFile.hasNextLine()) {
            hyponymCurrLine = hyponymFile.readLine();
            hyponymDivider = hyponymCurrLine.indexOf(",");
            synsetID = Integer.parseInt(hyponymCurrLine.substring(0, hyponymDivider));

            hyponymArr = (hyponymCurrLine.substring(hyponymDivider + 1)).split(",");

            
            for (int i = 0; i < hyponymArr.length; i++) {
                int hyponymID = Integer.parseInt(hyponymArr[i]);
                graph.addEdge(synsetID, hyponymID);
            }

            numberOfEdges += hyponymArr.length;
        }
    }

    /* Returns Boolean true or false on if provided String noun is a noun */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /* Returns a set of the WordNet's nouns */
    public Set<String> nouns() {
        Set<String> vertexSet = synsetWordMap.keySet();
        return vertexSet;
    }

    /* Returns a set of a provided word's hyponyms */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> vertexSet = synsetWordMap.get(word);
        return vertexSetToStringSet(GraphHelper.descendants(graph, vertexSet));
    }

    private Set<String> vertexSetToStringSet(Set<Integer> vertexSet) {
        HashSet<String> stringSet = new HashSet<String>();
        for (Integer x : vertexSet) {
            String[] synsetWords = synsetIDMap.get(x);
            for (String xx : synsetWords) {
                stringSet.add(xx);
            }
        }

        return stringSet;
    }

}
