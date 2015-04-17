package ngordnet;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
//import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private In synInput; 
    private In hypInput;
    
    private Set<String> nounSet = new HashSet<String>();
    private Map<Integer, Set> synsetMap = new HashMap<Integer, Set>();
    private Map<String, Set> synDigraphMap = new HashMap<String, Set>();
    private Digraph synDigraph;
    
    private final String DELIMITER = ",";
    private final String SPACE = " ";
    private final int INDEXZERO = 0;
    private final int INDEXONE = 1;

    public WordNet(String synsetFileName, String hyponymFileName) {
        if (synsetFileName == null 
            || hyponymFileName == null) { 
            throw new IllegalArgumentException("One or more filenames is missing."); 
        }

        synInput = new In(synsetFileName);
        hypInput = new In(hyponymFileName);

        if (!synInput.exists() 
            || !hypInput.exists()) { 
            throw new IllegalArgumentException("Files are empty."); 
        }

        while (synInput.hasNextLine()) {
            String synLine = synInput.readLine();           
            String synNounswithSpaces = synLine.split(DELIMITER)[INDEXONE];
            String synIndexString = synLine.split(DELIMITER)[INDEXZERO]; 
            int synIndex = Integer.parseInt(synIndexString);
            String[] listofsynNouns = synNounswithSpaces.split(SPACE);
            Set<String> tele = new HashSet<String>();
            for (String nounElement : listofsynNouns) {
                tele.add(nounElement);
            }
            synsetMap.put(synIndex, tele);
            Set<Integer> temp;
            Set<Integer> temp2;
            for (String synX : listofsynNouns) {
                temp = new HashSet<Integer>();
                nounSet.add(synX); 
                if (synDigraphMap.containsKey(synX)) {
                    temp2 = synDigraphMap.get(synX);
                    temp2.add(synIndex);
                    synDigraphMap.put(synX, temp2);
                } else {
                    temp.add(synIndex);  
                    synDigraphMap.put(synX, temp);
                }
            }
        }

        int digraphSize = synsetMap.size();
        synDigraph = new Digraph(digraphSize);

        while (hypInput.hasNextLine()) {
            String hypline = hypInput.readLine();
            String hypIndexString = hypline.split(DELIMITER)[INDEXZERO];   
            String hyponymNotIncludingIndex = hypline.substring(hypline.indexOf(DELIMITER) + 1);
            String[] hyponymList = hyponymNotIncludingIndex.split(DELIMITER);
            int vVertice = Integer.parseInt(hypIndexString);
            for (String hypX : hyponymList) {
                int wVertice = Integer.parseInt(hypX);
                synDigraph.addEdge(vVertice, wVertice);
            }
        }
        synInput.close();
        hypInput.close();
    }

    public Set<String> hyponyms(String word) {  //returns the set of hyponyms
        if (word == null 
            || !synDigraphMap.containsKey(word)) { 
            throw new IllegalArgumentException("Not in dictionary or is null"); 
        }
        Set<String> hyponymSet = new HashSet<String>(); 
        Set<Integer> setofWords = synDigraphMap.get(word);
        for (int x : setofWords) {
            hyponymSet.addAll(synsetMap.get(x));
        }
        Set<Integer> descendantSet = GraphHelper.descendants(synDigraph, setofWords);
        for (int x : descendantSet) {
            Set<String> string = synsetMap.get(x);
            for (String wordElement : string) {
                hyponymSet.add(wordElement);
            }
        }
        return hyponymSet;
    }

    public Set<String> nouns() {
        if (nounSet.isEmpty()) { 
            throw new IllegalArgumentException("The nounSet is empty."); 
        }
        return nounSet;
    }

    public boolean isNoun(String noun) {
        if (noun == null) { 
            return false; 
        } else if (nounSet.contains(noun)) { 
            return true; 
        }
        return false;
    }
}
