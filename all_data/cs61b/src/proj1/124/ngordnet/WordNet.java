package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.Map;

public class WordNet {
    private HashMap<Integer, ArrayList<String>> swagMap = new HashMap<Integer, ArrayList<String>>();
    private Digraph swagGraph;
    
    public WordNet(String synsetFile, String hyponymFile) {
        In synset = new In(synsetFile);
        String synLine;
        String hypLine;
        while ((synLine = synset.readLine()) != null) {
            ArrayList<String> swagList = new ArrayList<String>();
            String[] lineVector = synLine.split(",");
            int id = Integer.parseInt(lineVector[0]);
            String word = lineVector[1];
            String[] wordVector = word.split(" ");
            
            for (int i = 0; i < wordVector.length; i += 1) {
                swagList.add(wordVector[i]);
            }
            swagMap.put(id, swagList);
        }
        swagGraph = new Digraph(swagMap.size());
        In hyponym = new In(hyponymFile);
        while ((hypLine = hyponym.readLine()) != null) {
            String[] lineVector = hypLine.split(",");
            int parent = Integer.parseInt(lineVector[0]);
            for (int i = 1; i < lineVector.length; i += 1) {
                swagGraph.addEdge(parent, Integer.parseInt(lineVector[i]));
            }
        }
    }
    public boolean isNoun(String input) {
        for (Integer idNum : swagMap.keySet()) {
            if (swagMap.get(idNum).contains(input)) {
                return true;
            }
        }
        return false;
    }
    
    public Set<String> nouns() {
        Set<String> nounList = new HashSet<String>();
        for (Map.Entry<Integer, ArrayList<String>> entry : swagMap.entrySet()) {
            ArrayList<String> curr = entry.getValue();
            //loop through the array list which is a value for swagMap
            for (int i = 0; i < curr.size(); i += 1) {
                nounList.add(curr.get(i));
            }
        }
        return nounList;
    }
    
    public Set<String> hyponyms(String parent) {
        HashSet<Integer> idSet = new HashSet<Integer>(); //all the ids containing the parent
        for (Integer idNum : swagMap.keySet()) {
            if (swagMap.get(idNum).contains(parent)) {
                idSet.add(idNum);
                // System.out.print(entry.getValue());
            }
        }



        Set<Integer> childSet = new HashSet<Integer>(); //getting all the descendant numbers
        childSet = GraphHelper.descendants(swagGraph, idSet);
        //array list containing children
        HashSet<ArrayList<String>> hypoVal = new HashSet<ArrayList<String>>(); 
        for (Integer i: childSet) {
            hypoVal.add(swagMap.get(i));
        }

        HashSet<String> hypo = new HashSet<String>();
        for (ArrayList<String> entry : hypoVal) {
            //loop through the array list which is a value for swagMap
            for (int i = 0; i < entry.size(); i += 1) {
                hypo.add(entry.get(i));
            }
        }
        return hypo;
    }
}
