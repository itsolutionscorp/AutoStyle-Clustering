package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {

    private Digraph d;
    private HashMap<Integer, String> synset = new HashMap<Integer, String>();
    private HashMap<String, String> synsetID = new HashMap<String, String>();
    private int id = 0;
    private int hyponymRoot;
    private String wordTemp = "a";
    private String line;
    private String synsetGet;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In file = new In(synsetFilename);
        line = file.readLine();
        while (line != null) {
            String[] rawTokens = line.split(",");
            id = Integer.parseInt(rawTokens[0]);
            wordTemp = rawTokens[1];
            synset.put(id, wordTemp);
            String[] words = wordTemp.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (synsetID.get(words[i]) != null) {
                    synsetID.put(words[i], String.valueOf(synsetID.get(words[i])) 
                            + " " + String.valueOf(id));
                } else {
                    synsetID.put(words[i], String.valueOf(id));
                }
            }
            line = file.readLine();
        }

        d = new Digraph(id + 1);
        file = new In(hyponymFilename);
        line = file.readLine();
        while (line != null) {
            String[] hyponymTokens = line.split(",");
            hyponymRoot = Integer.parseInt(hyponymTokens[0]);
            for (int i = 1; i < hyponymTokens.length; i++) {
                d.addEdge(hyponymRoot, Integer.parseInt(hyponymTokens[i]));
            }
            line = file.readLine();
        }    
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymReturn = new TreeSet<String>();
        Set<Integer> number = new TreeSet<Integer>();
        String[] idToken = synsetID.get(word).split(" ");
        for (int i = 0; i < idToken.length; i++) {
            number.add(Integer.parseInt(idToken[i]));
        }
        Set<Integer> vertex = GraphHelper.descendants(d, number);
        for (int x: vertex) {
            synsetGet = synset.get(x);
            String[] tokens = synsetGet.split(" ");
            for (int i = 0; i < tokens.length; i++) {
                hyponymReturn.add(tokens[i]);
            }
        }
        return hyponymReturn;
    }

    public Set<String> nouns() {
        return synsetID.keySet();
    }

    public boolean isNoun(String noun) {
        return synsetID.get(noun) != null;
    }
}
