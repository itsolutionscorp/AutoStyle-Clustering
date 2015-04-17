package ngordnet; 
import java.util.HashMap;
import edu.princeton.cs.introcs.In; 
import java.util.Map;
import java.util.HashSet; 
import java.util.Set; 
import java.util.Arrays; 
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private Digraph hyponymWeb;
    private HashMap<Integer, String[]> synsetMap;

    private String tempString;
    private String[] tArray;

    public WordNet(String synsets, String hyponyms) {
        In synsetsIn = new In(synsets);
        In hyponymsIn = new In(hyponyms);

        synsetMap = new HashMap<Integer, String[]>(); 

        int v = 0;

        /*reads in each synset to the dictionary 
        also determines the number of synsets 
        which is the number of vertices in the hyponymWeb*/
        while (synsetsIn.hasNextLine()) { 
            tempString = synsetsIn.readLine();
            tArray = tempString.split(",");
            synsetMap.put(Integer.parseInt(tArray[0]), tArray[1].split(" "));
            v++;
        }

        hyponymWeb = new Digraph(v);
        while (hyponymsIn.hasNextLine()) {
            tempString = hyponymsIn.readLine();
            tArray = tempString.split(",");
            for (int i = 0; i < tArray.length; i++) {
                hyponymWeb.addEdge(Integer.parseInt(tArray[0]), Integer.parseInt(tArray[i]));
            }
        }
    }
    
    public boolean isNoun(String word) {
        for (String[] words : synsetMap.values()) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word)) {
                    return true;
                }
            }
        }
        return false; 
    }

    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();

        for (String[] words : synsetMap.values()) {
            for (int i = 0; i < words.length; i++) {
                nouns.add(words[i]);
            }
        }

        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> keys = new HashSet<Integer>();

        for (Map.Entry<Integer, String[]> e : synsetMap.entrySet()) {
            String[] a = e.getValue();
            if (Arrays.asList(a).contains(word)) {
                for (String stringI : e.getValue()) {
                    hyponyms.add(stringI);
                }
                keys.add(e.getKey());
            }
        }
        keys = GraphHelper.descendants(hyponymWeb, keys);

        for (Integer i : keys) {
            for (int k = 0; k < synsetMap.get(i).length; k++) {
                hyponyms.add(synsetMap.get(i)[k]);
            }
        }
        return hyponyms; 
    }

}
