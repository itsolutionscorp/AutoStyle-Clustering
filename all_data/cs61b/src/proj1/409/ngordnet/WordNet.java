package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

/************************************************
*
*  PROJECT #1 - CS61B
*  SPRING 2015, UNIVERSITY OF CALIFORNIA-BERKELEY
*
*  Instructor: Josh Hug
*
* ----------------------------------------------
*  
*  WORDNET CLASS
*  –––––––––––––
*
*  @author Alejandro Esteban Salazar
*          SID: 25583969
*
**************************************************/

public class WordNet {
    private int keymapLength = 0; 
    private HashMap<Integer, String[]> keyMap = new HashMap<Integer, String[]>();
    private HashMap<String, ArrayList> reversedMap = new HashMap<String, ArrayList>();
    private HashMap<Integer, String[]> valMap = new HashMap<Integer, String[]>();
    private Digraph digraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In inSynset = new In(synsetFilename);
        In inHyponym = new In(hyponymFilename);

        while (inSynset.hasNextLine()) {
            String currLine = new String(inSynset.readLine());
            String[] keysAndvalues = currLine.split(",", 3);
            String[] valueArray = keysAndvalues[1].split(" ");
            ArrayList idArray = new ArrayList();
            idArray.add(keysAndvalues[0]);

            for (String h : valueArray) {
                if (!reversedMap.containsKey(h)) {
                    reversedMap.put(h, idArray);
                } else {
                    ArrayList a = reversedMap.get(h);
                    a.add(keysAndvalues[0]);
                    reversedMap.put(h, a);
                }
            }
            keyMap.put(Integer.parseInt(keysAndvalues[0]), valueArray);
            keymapLength += 1;
        }

        digraph = new Digraph(keymapLength);
        while (inHyponym.hasNextLine()) {
            String currLine = new String(inHyponym.readLine());
            String[] keysAndvalues = currLine.split(",", 2);
            String[] valueArray = keysAndvalues[1].split(",");
            valMap.put(Integer.parseInt(keysAndvalues[0]), valueArray);		        
            for (String e : valueArray) {
                int a = Integer.parseInt(keysAndvalues[0]);
                int b = Integer.parseInt(e);
                digraph.addEdge(a, b);
            }
        }
    }

    public boolean isNoun(String noun) {
        if (reversedMap.get(noun) != null) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return reversedMap.keySet();
    }

    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            throw new IllegalArgumentException("Invalid word."); 
        }
        Set<Integer> idSet = new HashSet<Integer>();
        Set<String> returnedWords = new HashSet<String>();
        for (int a = 0; a < keymapLength; a++) {
            for (String f : keyMap.get(a)) {
                if (f.equals(word)) {
                    idSet.add(a);                   
                }
            }
        }
        for (Integer j : GraphHelper.descendants(digraph, idSet)) {
            for (String h : keyMap.get(j)) {
                returnedWords.add(h);
            }
        }
        return returnedWords;
    }
}
