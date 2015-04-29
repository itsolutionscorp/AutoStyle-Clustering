package ngordnet;
import java.util.*;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private Map<Integer, Set<String>> integerMap = new HashMap<Integer, Set<String>>();
    private Map<String, Set<Integer>> stringMap = new HashMap<String, Set<Integer>>();
    private Digraph diGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {

        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);     
        String[] synsetStrings = synsetFile.readAllLines();
        diGraph = new Digraph(synsetStrings.length);
        String[] hyponymStrings = hyponymFile.readAllLines();
        int id;
        Set<String> setOfStrings;
        String currentSynset, currentHyponym;   
        String[] synElements, hypElements, values;
        int synlength = synsetStrings.length;
        int hyplength = hyponymStrings.length;

        for (int i = 0; i < synlength; i += 1) {
            setOfStrings = new HashSet<String>();
            currentSynset = synsetStrings[i];
            synElements = currentSynset.split(",");
            id = Integer.parseInt(synElements[0]);
            values = synElements[1].split(" ");
            
            for (String stringy : values) {
                setOfStrings.add(stringy);
                if (stringMap.keySet().contains(stringy) == false) stringMap.put(stringy,new HashSet<Integer>());
                stringMap.get(stringy).add(id);
            }
            integerMap.put(id, setOfStrings);
        }

        for (int ii = 0; ii < hyplength; ii += 1) {
            currentHyponym = hyponymStrings[ii];
            hypElements = currentHyponym.split(",");
            int[] intArray = new int[hypElements.length];
            for (int iii = 0; iii < hypElements.length; iii += 1) intArray[iii] = Integer.parseInt(hypElements[iii]);
            for (int lol = 0; lol < intArray.length - 1; lol += 1)diGraph.addEdge(intArray[0], intArray[lol]);
        }
    } 

    public Set<String> hyponyms(String ladida) {

        Set<Integer> idents = stringMap.get(ladida);
        Set<String> endyStrings = new HashSet<String>();
        Set<String> stringyStrings = new HashSet<String>();
        Set<Integer> matchings = GraphHelper.descendants(diGraph, idents);

        for (Integer inty : matchings) {
            for (String slinky : endyStrings) stringyStrings.add(slinky);
            endyStrings = integerMap.get(inty);
        }

        return stringyStrings;
    }

    public Set<String> nouns() {

        Set<String> values;
        Set<String> stupid =  new HashSet<String>();
        Set<Integer> numbers = integerMap.keySet();

        for (Integer number : numbers) {
            values = integerMap.get(number);
            for (String valero : values) stupid.add(valero);
        }
        /** I feel like my brain returns stupid fairly often */
        return stupid; 
    }

    public boolean isNoun(String noun) {

        for (Set<String> words : integerMap.values()) {
            for (String otherwords : words){
                if (noun.equals(otherwords)) return true;
            }
        }
        return false;
    }
}
