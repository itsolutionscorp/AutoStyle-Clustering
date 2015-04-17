package ngordnet;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashSet<String> allNounSynsets;
    private HashMap<Integer, HashSet<String>> synMap;
    private HashMap<String, List<Integer>> reverseSynMap;
    private Digraph hypDigraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synMap = new HashMap<Integer, HashSet<String>>();
        reverseSynMap = new HashMap<String, List<Integer>>();
        int intKey = 1;
        try { 
            File synsetFile = new File(synsetFilename);
            FileReader synsetFileReader = new FileReader(synsetFile);
            BufferedReader synsetBufferedReader = new BufferedReader(synsetFileReader);
            String currentLine;
            while ((currentLine = synsetBufferedReader.readLine()) != null) { 
                String[] currentLineArray = currentLine.split(",");
                intKey = Integer.valueOf(currentLineArray[0]); 
                String synsets = currentLineArray[1];
                String[] synsetList = synsets.split(" ");
                HashSet<String> setOfSynsets = new HashSet<String>(Arrays.asList(synsetList));
                synMap.put(intKey, setOfSynsets);
                for (String str : synsetList) {
                    List<Integer> currIntListValue = new ArrayList<Integer>();
                    if (reverseSynMap.containsKey(str)) {
                        currIntListValue = reverseSynMap.get(str);
                    }
                    currIntListValue.add(intKey);
                    reverseSynMap.put(str, currIntListValue);
                }
            }
            synsetBufferedReader.close();
            synsetFileReader.close();
        } catch (IOException e) { 
            e.printStackTrace();
        } 

        hypDigraph = new Digraph(intKey + 1);

        try {
            File hyponymFile = new File(hyponymFilename);
            FileReader hyponymFileReader = new FileReader(hyponymFile);
            BufferedReader hyponymBufferedReader = new BufferedReader(hyponymFileReader);
            String currLine;
            while ((currLine = hyponymBufferedReader.readLine()) != null) {
                String[] currLineList = currLine.split(",");
                int origInt = Integer.valueOf(currLineList[0]); 
                for (int i = 1; i < currLineList.length; i++) {
                    int currInt = Integer.valueOf(currLineList[i]);
                    hypDigraph.addEdge(origInt, currInt);
                }
            }
            hyponymBufferedReader.close();
            hyponymFileReader.close();
        } catch (IOException e) { 
            e.printStackTrace();
        } 
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) { 
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() { 
        return reverseSynMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) { 
        List<Integer> synsetIDs = reverseSynMap.get(word);
        HashSet<Integer> synsetIDSet = new HashSet<Integer>(synsetIDs);
        Set<Integer> synsetAndHypIDs = GraphHelper.descendants(hypDigraph, synsetIDSet);
        Set<String> toReturn = new HashSet<String>();
        for (int curr : synsetAndHypIDs) {
            toReturn.addAll(synMap.get(curr));
        }
        return toReturn;
    }         
}
