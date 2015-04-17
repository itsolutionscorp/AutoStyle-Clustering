package ngordnet;
import edu.princeton.cs.algs4.Digraph;
// import edu.princeton.cs.algs4.DirectedDFS;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.IOException;

public class WordNet {
    private File s;
    private File hypo;
    private Scanner sByLine;
    private String sInfo;
    private Scanner sInfoByLine;
    private Scanner hypoByLine;
    private HashMap<Integer, Set<String>> sid; // keys: synset ID #, values: synset words
    private HashMap<Integer, String> glossMap; // keys: synset ID, values: definition
    private HashMap<Integer, Set<Integer>> hypoMap;
    private Digraph hypoDigraph;
    private int size;

  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        s = new File(synsetFilename);
        hypo = new File(hyponymFilename);
        size = 0;
        sid = new HashMap<Integer, Set<String>>();
        glossMap = new HashMap<Integer, String>();
        hypoMap = new HashMap<Integer, Set<Integer>>();
        try { 
            sByLine = new Scanner(s).useDelimiter("\n");
        } catch (IOException e) {
            System.out.println("Synsets file was incorrectly scanned as " + sByLine);
        }
        while (sByLine.hasNext()) {
            size += 1;
            sInfo = sByLine.next();
            sInfoByLine = new Scanner(sInfo).useDelimiter(","); 
            // Step 1: Getting the ID
            int id = sInfoByLine.nextInt();
            // Step 2: Getting the synset, which may have 2+ words
            String synset = sInfoByLine.next();
            // Checking to see if there are 2+ words in synset
            Set<String> synsetSet = new HashSet<String>();
            if (synset.contains(" ")) {
                for (String word : synset.split(" ")) {
                    synsetSet.add(word);
                } 
            } else {
                synsetSet.add(synset);
            }
            // Step 2.5: Matching synsets to their IDs
            sid.put(id, synsetSet); 
            // Step 3: Matching glosses to their IDs
            // System.out.println("Gloss: " + gloss);
            glossMap.put(id, sInfoByLine.next());
        }
        sByLine.close();
        try {
            hypoByLine = new Scanner(hypo).useDelimiter("\n");
        } catch (IOException e) {
            System.out.println("Hypnoyms file was not correctly read");
        }
        while (hypoByLine.hasNext()) {
            String oneLine = hypoByLine.next();
            String[] idList = oneLine.split(",");
            int idOfS = Integer.parseInt(idList[0]);
            Set<Integer> hypoIDSet = new HashSet<Integer>();
            if (hypoMap.containsKey(idOfS)) {
                hypoIDSet = hypoMap.get(idOfS);
            } 
            for (int i = 1; i < idList.length; i++) {
                hypoIDSet.add(Integer.parseInt(idList[i]));
            }
            hypoMap.put(idOfS, hypoIDSet);
        }
        hypoByLine.close();
        hypoDigraph = new Digraph(size);
        for (Integer sKey : sid.keySet()) { // For IDs of the parent
            for (Integer hKey : hypoMap.keySet()) { // For IDs of the hyponyms
                if (sKey == hKey) {
                    for (Integer hID : hypoMap.get(hKey)) {
                        hypoDigraph.addEdge(hKey, hID);
                    }
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Set<String> synset : sid.values()) {
            if (synset.contains(noun)) {
                return true;
            }
        } return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (Set<String> synset : sid.values()) {
            for (String word : synset) {
                allNouns.add(word);
            }
        } return allNouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<Integer> hypoID = new HashSet<Integer>();
        Set<String> hypoWords = new HashSet<String>();
        // Finding the word's id and adding all words within same synset
        for (Integer id : sid.keySet()) {
            if (sid.get(id).contains(word)) {
                int wordID = id;
                hypoID.add(wordID);
                Set<Integer> hypoIDs = GraphHelper.descendants(hypoDigraph, hypoID);
                for (Integer idNumber : hypoIDs) {
                    for (int synsetID : sid.keySet()) {
                        if (idNumber == synsetID) {
                            Set<String> synsetGroup = sid.get(idNumber);
                            for (String noun : synsetGroup) {
                                hypoWords.add(noun);
                            }
                        }
                    }
                }
            } else {
                hypoWords.add(word);
            }
        } return hypoWords;
    }
}
