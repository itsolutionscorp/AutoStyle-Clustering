package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, HashSet<String>> idsToWords;
    private HashMap<String, HashSet<Integer>> wordsToIds;
    private Digraph hypos;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In in1 = new In(synsetFilename);
        idsToWords = new HashMap<Integer, HashSet<String>>();
        wordsToIds = new HashMap<String, HashSet<Integer>>();

        String line;
        String[] tokens;

        while (in1.hasNextLine()) {
            line = in1.readLine();
            tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            String[] words = tokens[1].split(" ");
            HashSet<String> wordList = new HashSet<String>();
            for (int i = 0; i < words.length; i += 1) {
                wordList.add(words[i]);
            }
            idsToWords.put(id, wordList);
            for (int i = 0; i < words.length; i += 1) {
                if (wordsToIds.containsKey(words[i])) {
                    HashSet<Integer> current = wordsToIds.get(words[i]);
                    current.add(id);
                } else {
                    HashSet<Integer> ids = new HashSet<Integer>();
                    ids.add(id);
                    wordsToIds.put(words[i], ids);
                }
            }
        }

        In in2 = new In(hyponymFilename);
        hypos = new Digraph(idsToWords.size());
        while (in2.hasNextLine()) {
            line = in2.readLine();
            tokens = line.split(",");
            int vertex = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i += 1) {
                int curr = Integer.parseInt(tokens[i]);
                hypos.addEdge(vertex, curr);
            }
        }
    }

    public boolean isNoun(String noun) {
        return wordsToIds.containsKey(noun);
    }

    public Set<String> nouns() {
        Set<String> answer = wordsToIds.keySet();
        return answer;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> answer = new HashSet<String>();
        if (!isNoun(word)) {
            return answer;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int x : wordsToIds.get(word)) {
            queue.add(x);
        }    
        while (queue.size() != 0) {
            int current = queue.remove();
            for (String hyponym : idsToWords.get(current)) {
                answer.add(hyponym);
            }
            for (int x : hypos.adj(current)) {
                queue.add(x);
            }
        }        
        return answer;
    }
}
