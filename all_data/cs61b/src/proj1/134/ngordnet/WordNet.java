package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class WordNet { 
    private Map<Integer, ArrayList<String>> synMap = new HashMap();
    private Map<Integer, ArrayList<Integer>> hypMap = new HashMap();
    private Digraph g;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        In hyp = new In(hyponymFilename);
        while (syn.hasNextLine()) {
            String line = syn.readLine();
            String[] tokens = line.split(",");
            int i = Integer.parseInt(tokens[0]);
            ArrayList temp = new ArrayList();
            String word = tokens[1];
            String[] wordsList = word.split(" ");
            for (int p = 0; p < wordsList.length; p++) {
                temp.add(wordsList[p]); 
            }
            synMap.put(i, temp); 
        }

        while (hyp.hasNextLine()) {
            String line = hyp.readLine();
            String[] tokens = line.split(",");
            int i = Integer.parseInt(tokens[0]);
            ArrayList temp = new ArrayList();
            if (hypMap.containsKey(i)) {
                temp = hypMap.get(i);
            }
            for (int p = 1; p < tokens.length; p++) {
                int word = Integer.parseInt(tokens[p]);
                temp.add(word);
            }
            hypMap.put(i, temp); 
        }
        g = new Digraph(synMap.size());
        for (int p = 0; p < synMap.size(); p++) {
            if (hypMap.containsKey(p)) {
                ArrayList valueList = hypMap.get(p);
                for (int q = 0; q < valueList.size(); q++) {
                    g.addEdge(p, (int) valueList.get(q)); 
                }
            }
        }
    }

    public boolean isNoun(String noun) {
        for (int i = 0; i < synMap.size(); i++) {
            ArrayList<String> temp = synMap.get(i);
            for (String s: temp) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nouns = new HashSet();
        for (int i = 0; i < synMap.size(); i++) {
            ArrayList<String> temp = synMap.get(i);
            for (String s: temp) {
                nouns.add(s); 
            }
        }
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> temp = new HashSet();
        for (int p = 0; p < synMap.size(); p++) {
            if (synMap.get(p).contains(word)) {
                int wordIndex = p;
                temp.add(wordIndex);
            }
        }
        temp = GraphHelper.descendants(g, temp);
        Set<String> finalSet = new HashSet();
        for (int i: temp) {
            ArrayList<String> tempp = synMap.get(i);
            for (String s: tempp) {
                finalSet.add(s);
            }
        }
        return finalSet;
    }


}

