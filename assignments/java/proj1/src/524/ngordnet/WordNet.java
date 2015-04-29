package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class WordNet {
    private String nouns;
    private int numVertices;
    private Map<Integer, List<String>> map1;
    private Map<Integer, int[]> map2;
    private Digraph sh;
    private ArrayList definitions;
    public WordNet(String synsets, String hyponyms) {
        map1 = readFile1(synsets);
        sh = readFile2(hyponyms);
    }
    private Map<Integer, List<String>> readFile1(String scanFile) {
        try {
            map1 = new HashMap<Integer, List<String>>();
            BufferedReader myBR = new BufferedReader(new FileReader(scanFile));
            String line = "";
            String cvsSplitBy = ",";
            String spaceSplitBy = " ";
            definitions = new ArrayList<String>();
            while ((line = myBR.readLine()) != null) {
                ArrayList list = new ArrayList<String>();
                String[] listEntry = line.split(cvsSplitBy);
                int idNum = Integer.parseInt(listEntry[0]);
                // Store the ID in idNum and store words in an array for value.
                String[] synset = listEntry[1].split(spaceSplitBy);
                for (int i = 0; i < synset.length; i += 1) {
                    list.add(i, synset[i]);
                }
                // New ArrayList (id corresponds with synset definition)
                definitions.add(idNum, listEntry[2]);
                map1.put(idNum, list);
                numVertices++;
            }
            myBR.close();
            return map1;
        } catch (IOException e) {
            System.out.println("Can't read synsets file.");
        }
        return map1;
    }

    private Digraph readFile2(String scanFile) {
        try {
            sh = new Digraph(numVertices);
            int vertexNum = 0;
            int next = 0;
            Set<Integer> setID = new TreeSet<Integer>();
            BufferedReader myBR2 = new BufferedReader(new FileReader(scanFile));
            String line = "";
            String cvsSplitBy = ",";
            while ((line = myBR2.readLine()) != null) {
                String[] listEntry2 = line.split(cvsSplitBy);
                int idNum2 = Integer.parseInt(listEntry2[0]);
                int numOfHy = listEntry2.length - 1;
                for (int i = 0; i < numOfHy; i += 1) {
                    next = Integer.parseInt(listEntry2[i + 1]);
                    sh.addEdge(idNum2, next);
                }
                vertexNum += 1;
            }
            return sh;
        } catch (IOException e) {
            System.out.println("Can't read hyponyms file.");
        }
        return sh;
    }


    // Returns true if "noun" is some word in a synset.
    public boolean isNoun(String noun) {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < map1.get(i).size(); j++) {
                if (map1.get(i).get(j).equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Set<String> nouns() {
        Set allNouns = new HashSet();
        for (int i = 0; i < numVertices; i++) {
            allNouns.addAll(map1.get(i));
        }
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        Set set = new HashSet();
        Integer wordID = 0;
        Set<Integer> setID = new TreeSet<Integer>();
        for (int i = 0; i < numVertices; i += 1) {
            if (map1.get(i).get(0).equals(word)) {
                setID.add(i);
                wordID = i;
            }
        }

        //Check each word.
        for (int i = 0; i < numVertices; i++) {
            // Checking each word in each synset.
            for (int j = 0; j < map1.get(i).size(); j += 1) {
                // Found the word in the synset.
                if (map1.get(i).get(j).equals(word)) {
                    set.addAll(map1.get(i));
                    setID.add(i);
                    // Create iterator to add all hyponyms to the set.
                    Iterator iter = GraphHelper.descendants(sh, setID).iterator();
                    // branchHyponyms = GraphHelper.descendants(sh, setID); 
                    while (iter.hasNext()) {
                        int hyponymID = (int) iter.next();
                        // Adds the *entire synset hyponym to the set.
                        set.addAll(map1.get(hyponymID));
                    }
                }
            }
        }
        return set;
    }

    private String wordFinderHelper(int id) {
        if (map1.containsKey(id)) {
            // returns the word if it finds it
            return map1.get(id).get(0);
        }
        // returns null if can't find the word
        return null;
    }

    private boolean hyCheck(int id) {
        // Returns true if id doesn't have any more hyponoyms
        if (map2.get(id) == null) {
            return false;
        }
        return true;
    }
}
