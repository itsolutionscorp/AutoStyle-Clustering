package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Digraph digraph;
    private HashMap<String, Integer> synsetAndInteger = new HashMap<String, Integer>();
    private HashMap<Integer, ArrayList<String>> integerAndWordArrayList 
        = new HashMap<Integer, ArrayList<String>>();
    private HashSet<String> wordSet = new HashSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        In in = new In(synsetFilename);
        String line;
        String[] array;
        int integer;
        int size = 0;
        ArrayList<String> arrayList;
        String[] wordArray;
        while (in.hasNextLine()) {
            size++;
            line = in.readLine();
            array = line.split(",");
            integer = Integer.parseInt(array[0]);
            synsetAndInteger.put(array[1], integer);
            arrayList = new ArrayList<String>();
            for (String word : array[1].split(" ")) {
                arrayList.add(word);
            }
            integerAndWordArrayList.put(integer, arrayList);
        }
        
        digraph = new Digraph(size);
        
        in = new In(hyponymFilename);
        while (in.hasNextLine()) {
            line = in.readLine();
            array = line.split(",");
            integer = Integer.parseInt(array[0]);
            for (int i = 1; i < array.length; i++) {
                digraph.addEdge(integer, Integer.parseInt(array[i]));
            }
        }

        for (String string : synsetAndInteger.keySet()) {
            if (string.contains(" ")) {
                array = string.split(" ");
                for (String syn : array) {
                    if (!wordSet.contains(syn)) {
                        wordSet.add(syn);
                    }
                }
            } else {
                if (!wordSet.contains(string)) {
                    wordSet.add(string);
                }
            }
        }     
    }
    
    public Set<String> hyponyms(String word) {
        Set<Integer> integerSet = new HashSet<Integer>();
        String[] array;
        for (Integer integer : integerAndWordArrayList.keySet()) {
            ArrayList<String> wordArrayList = integerAndWordArrayList.get(integer);
            if (wordArrayList.contains(word)) {
                integerSet.add(integer);
            }
        }

        Set<Integer> graph = GraphHelper.descendants(digraph, integerSet);
        HashSet<String> returnSet = new HashSet<String>();

        ArrayList<String> arrayList;
        for (Integer integer : graph) {
            arrayList = integerAndWordArrayList.get(integer);
            for (String item : arrayList) {
                returnSet.add(item);
            }
        }
        return returnSet;
    }
    
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }
    
    public Set<String> nouns() {
        return wordSet;
    }
}
