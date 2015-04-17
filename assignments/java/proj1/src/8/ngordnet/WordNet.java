package ngordnet;
//import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;



public class WordNet {
    private HashMap<Integer, String> sMap = new HashMap<Integer, String>();
    private Digraph hGraph;

    public WordNet(String synsetFile, String hyponymsFile) {
        In synsets = new In(synsetFile);
        while (synsets.hasNextLine()) {
            String currLine = synsets.readLine();
            String[] lineParts = currLine.split(",");
            int synsetId = Integer.parseInt(lineParts[0]);
            String synset = lineParts[1];
            sMap.put(synsetId, synset);
        }

        In myHyponyms = new In(hyponymsFile);
        hGraph = new Digraph(sMap.size());
        while (myHyponyms.hasNextLine()) {
            String currLine = myHyponyms.readLine();
            String[] lineParts = currLine.split(",");
            int size = lineParts.length;
            for (int i = 0; i < size - 1; i++) {
                hGraph.addEdge(Integer.parseInt(lineParts[0]), Integer.parseInt(lineParts[i + 1]));
            }
        }
    }

    public Set<String> nouns() {
        Set<String> allValues = new HashSet<>();
        for (String currEntry : sMap.values()) {
            String[] currValues = currEntry.split(" ");
            for (int i = 0; i < currValues.length; i++) {
                if (!allValues.contains(currValues[i])) {
                    allValues.add(currValues[i]);
                }
            }
        }
        return allValues;
    }

    public Set<String> hyponyms(String synset) {
        Set<Integer> kfResults = keyFinder(synset);
        Set<Integer> allVertexes = GraphHelper.descendants(hGraph, kfResults);
        Set<String> allHyponyms = new HashSet<>();
        for (Integer currVertex : allVertexes) {
            String[] currValues = sMap.get(currVertex).split(" ");
            for (int i = 0; i < currValues.length; i++) {
                if (!allHyponyms.contains(currValues[i])) {
                    allHyponyms.add(currValues[i]);
                }
            }
        }
        return allHyponyms;
    }

    private Set<Integer> keyFinder(String synset) {
//    private Set<Integer> keyFinder(HashMap<Integer, String> sMap, String synset) {
        Set<Integer> matchKeys = new HashSet<>(); //Create a Set with all Matching Keys
        Set<Integer> allKeys = sMap.keySet(); //Create a Set of all the Keys
        for (Integer currKey : allKeys) { //Go through every single Key
            String[] currValues = sMap.get(currKey).split(" "); //Store String Values in an Array
            for (int i = 0; i < currValues.length; i++) { //Loop through array until it is empty
                if (synset.equals(currValues[i])) {
                    matchKeys.add(currKey);
                }
            }
        }
        return matchKeys;
    }

    public boolean isNoun(String word) {
        Set<Integer> allKeys = sMap.keySet();
        Set<String> allNouns = new HashSet<>();
        for (Integer currKey : allKeys) {
            String[] currValues = sMap.get(currKey).split(" "); //Store String Values in an Array
            for (int i = 0; i < currValues.length; i++) { //Loop through array until it is empty
                allNouns.add(currValues[i]);
            }
        }
        return allNouns.contains(word);
    }
}
