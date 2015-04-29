package ngordnet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<Integer, String[]> mapIDToSynset;
    private HashMap<String, List<Integer>> mapWordtoIDs;
    private Digraph digraph; 
    private In readingSynset;
    private In readingHyponym;
    private String[] synsets;
    private List<Integer> id;
    private String synsetLine;
    private String hyponymLine;
    private String[] synsetArray;
    private String[] hyponymArray;
    private Set<Integer> hyponymSet;
    private int count;
    
    
    /* read through a string, split by commas, and then it returns an array */
    public WordNet(String synsetFileName, String hyponymFileName) {
        mapIDToSynset = new HashMap<Integer, String[]>();
        mapWordtoIDs = new HashMap<String, List<Integer>>();
        synsets = new String[100];
        count = 0;
        id = new ArrayList<Integer>();
        readingSynset = new In(synsetFileName); 
        readingHyponym = new In(hyponymFileName); 
        while (readingSynset.hasNextLine()) { 
            synsetLine = readingSynset.readLine();
            synsetArray = synsetLine.split(",");
            synsets = synsetArray[1].split(" ");
            for (int j = 0; j < synsets.length; j += 1) {
                if (!mapWordtoIDs.containsKey(synsets[j])) {
                    id = new ArrayList<Integer>();
                    id.add(Integer.parseInt(synsetArray[0]));
                    count += 1;
                    mapWordtoIDs.put(synsets[j], id);
                } else {
                    List<Integer> oldID = mapWordtoIDs.get(synsets[j]);
                    oldID.add(Integer.parseInt(synsetArray[0]));
                    count += 1;
                    mapWordtoIDs.put(synsets[j], oldID);
                } 
            }
            mapIDToSynset.put(Integer.parseInt(synsetArray[0]), synsets);
        }
        digraph = new Digraph(count);
        while (readingHyponym.hasNextLine()) {
            hyponymLine = readingHyponym.readLine();
            hyponymArray = hyponymLine.split(",");
            for (int i = 1; i < hyponymArray.length; i += 1) {
                digraph.addEdge(Integer.parseInt(hyponymArray[0]), 
                    Integer.parseInt(hyponymArray[i]));
            }
        }
    }


    public Set<String> hyponyms(String word) {
        hyponymSet = new HashSet<Integer>();
        List<Integer> neededVals = new ArrayList<Integer>();
        for (Map.Entry<String, List<Integer>> entry : mapWordtoIDs.entrySet()) { 
        //learned how to iterate over a map from stackoverflow
            String key = entry.getKey();
            if (key.equals(word)) {
                neededVals = entry.getValue();
            }
        }
        for (int k = 0; k < neededVals.size(); k += 1) {
            hyponymSet.add(neededVals.get(k));
        }
        Set<Integer> numberedHyponym = new HashSet<Integer>();
        numberedHyponym = GraphHelper.descendants(digraph, hyponymSet); 
        //got explanation about what GraphHelper and digraph were for 
        //from Daniel Shan
        Set<String> returnHyponym = new HashSet<String>();
        for (Integer i : numberedHyponym) {
            for (Map.Entry<Integer, String[]> enter : mapIDToSynset.entrySet()) {
                if (i.equals(enter.getKey())) {
                    String [] valArray = enter.getValue();
                    for (int m = 0; m < valArray.length; m += 1) {
                        returnHyponym.add(valArray[m]);
                    }
                    
                }
            }
        }
        return returnHyponym;
    }

    public boolean isNoun(String noun) {
        return mapWordtoIDs.get(noun) != null;
    }

    public Set<String> nouns() {
        Set<String> n = new HashSet<String>();
        for (Map.Entry<String, List<Integer>> mapSet : mapWordtoIDs.entrySet()) {
            String noun = mapSet.getKey();
            n.add(noun);
        }
        return n;
    }
}
