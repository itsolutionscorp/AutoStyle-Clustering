package ngordnet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
public class WordNet {
    private Map<Integer, Set<String>> numToSynset;
    private Map<String, Set<Integer>> nouns;
    private Digraph digraph;
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        readSynset(synsetFilename);
        readHyponym(hyponymFilename);
    }
    
    private void readHyponym(String hyponymFilename) {
        In reader = new In(hyponymFilename);
        digraph = new Digraph(numToSynset.size());
        while (reader.hasNextLine()) {
            String line = reader.readLine();
            int front = 0;
            int end = 0;
            while (!line.substring(end, end + 1).equals(",")) {
                end += 1;
            }
            int tail = Integer.parseInt(line.substring(front, end));
            end += 1;
            front = end;
            while (end < line.length()) {
                if ((end + 1) == line.length()) {
                    digraph.addEdge(tail, Integer.parseInt(line.substring(front)));
                } else if (line.substring(end, end + 1).equals(",")) {
                    digraph.addEdge(tail, Integer.parseInt(line.substring(front, end)));
                    front = end + 1;
                }
                end += 1;
            }
        }
        reader.close();
    }

    private void readSynset(String synsetFilename) {
        In reader = new In(synsetFilename);
        numToSynset = new HashMap<Integer, Set<String>>();
        nouns = new HashMap<String, Set<Integer>>();
        while (reader.hasNextLine()) {
            String line = reader.readLine();
            Set<String> toAdd = new TreeSet<String>();
            int front = 0;
            int end = 0;
            while (!line.substring(end, end + 1).equals(",")) {
                end += 1;
            }
            int key = Integer.parseInt(line.substring(front, end));
            end += 1;
            front = end;
            boolean done = false;
            while (front < line.length() && !done) {
                if (line.substring(end, end + 1).equals(",")) {
                    String word = line.substring(front, end);
                    toAdd.add(word);
                    if (!nouns.containsKey(word)) {
                        nouns.put(word, new HashSet<Integer>());
                    }
                    nouns.get(word).add(key);
                    done = true;
                } else if (line.substring(end, end + 1).equals(" ")) {
                    String word = line.substring(front, end);
                    toAdd.add(word);
                    if (!nouns.containsKey(word)) {
                        nouns.put(word, new HashSet<Integer>());
                    }
                    nouns.get(word).add(key);
                    end += 1;
                    front = end;
                }
                end += 1;
            }
            numToSynset.put(key, toAdd);
        }
        reader.close();
    }
    
    public boolean isNoun(String s) {
        return nouns.keySet().contains(s);
    }
    
    public Set<String> nouns() {
        return nouns.keySet();
    }
    
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return null;
        }
        Set<Integer> keySet = nouns.get(word);
        Set<Integer> valSet = GraphHelper.descendants(digraph, keySet);
        Set<String> toReturn = new HashSet<String>();
        for (int i: valSet) {
            for (String s : numToSynset.get(i)) {
                toReturn.add(s);
            }
        }
        return toReturn;
    }
}
