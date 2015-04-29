package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

public class WordNet {
    private HashMap<Integer, HashMap<String, String>> synsets;
    private HashSet<String> nouns;
    private Digraph hyponyms;

    public WordNet(String synsetFileName, String hyponymFileName) {
        In synText = new In(synsetFileName);
        In hypText = new In(hyponymFileName);
        synsets = new HashMap<Integer, HashMap<String, String>>();
        nouns = new HashSet<String>();
        int type = 0;
        String tempIndex = "";
        String tempNouns = "";
        String tempDefinition = "";
        String character;

        while (synText.hasNextChar()) {
            char c = synText.readChar();
            if (c == '\n') {
                HashMap<String, String> nounPairs = new HashMap<String, String>();
                String delims = "[ ]+";
                String[] N = tempNouns.split(delims);
              
                for (String noun : N) {
                    nouns.add(noun);
                    nounPairs.put(noun, tempDefinition);
                }
            
                synsets.put(Integer.parseInt(tempIndex), nounPairs);
                tempIndex = "";
                tempNouns = "";
                tempDefinition = "";
                type = 0;
                continue;

            }

            character = Character.toString(c);
            if (character.equals(",")) {
                type += 1;
                continue;
            }

            if (type == 0) {
                tempIndex = tempIndex + character;
            } else if (type == 1) {
                tempNouns = tempNouns + character;
            } else if (type == 2) {
                tempDefinition = tempDefinition + character;
            }
        }

        if (!tempIndex.equals("")) {
            HashMap<String, String> nounPairs = new HashMap<String, String>();
            String delims = "[ ]+";
            String[] N = tempNouns.split(delims);
            for (String noun : N) {
                nouns.add(noun);
                nounPairs.put(noun, tempDefinition);
            }
      
            synsets.put(Integer.parseInt(tempIndex), nounPairs);
        }

        hyponyms = new Digraph(synsets.size());
    
        while (hypText.hasNextChar()) {
            String tempString = hypText.readLine();
            String[] vertices = tempString.split(",");
            for (int i = 1; i < vertices.length; i++) {
                hyponyms.addEdge(Integer.parseInt(vertices[0]), Integer.parseInt(vertices[i]));
            }
        }
    }

    public boolean isNoun(String word) {
        return nouns.contains(word);
    }

    public Set<String> nouns() {
        return nouns;
    }


    public Set<String> hyponyms(String s) {
        HashSet<Integer> sIndex = new HashSet<Integer>();
        Set<String> hypos = new HashSet<String>();
        for (int index : synsets.keySet()) {
            if (synsets.get(index).containsKey(s)) {
                sIndex.add(index);
            } 
        }
    
        for (int i : (GraphHelper.descendants(hyponyms, sIndex))) {
            HashMap<String, String> tempKeys = synsets.get(i);
            for (String k : tempKeys.keySet()) {
                hypos.add(k);
            }
        }
        return hypos;
    }
}
