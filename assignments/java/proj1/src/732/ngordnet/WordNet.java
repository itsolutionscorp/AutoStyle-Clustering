package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.HashMap;


public class WordNet extends java.lang.Object {

    private Digraph graph;
    private HashMap<Integer, String[]> indexToWord;
    private HashMap<String, ArrayList<Integer>> wordToIndex;

    public WordNet(String file1, String file2) {
        indexToWord = new HashMap<Integer, String[]>();
        wordToIndex = new HashMap<String, ArrayList<Integer>>();
        String synsetFile = file1;
        String hyponymsFile = file2;

        //read in all the nouns and store in the right place
        In synsetIn = new In(synsetFile);
        while (!synsetIn.isEmpty()) {

            String str = synsetIn.readLine();
            String[] tokens = str.split(",", -1);
            int index = Integer.parseInt(tokens[0]);
            String nouns = tokens[1];
            String[] words = nouns.split(" ", -1);

            for (int i = 0; i < words.length; i++) {
                ArrayList<Integer> indices = new ArrayList<Integer>();
                indices.add(index);
                if (isNoun(words[i])) {
                    wordToIndex.get(words[i]).add(index);
                } else {
                    wordToIndex.put(words[i], indices);
                }
            }

            indexToWord.put(index, words);
        }
        int vertices = wordToIndex.size();
        graph = new Digraph(vertices);

        //read in the file and make the relations as suggested
        In hyponymsIn = new In(hyponymsFile);
        while (!hyponymsIn.isEmpty()) {
            String str = hyponymsIn.readLine();
            String[] tokens = str.split(",", -1);
            int from = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(from, Integer.parseInt(tokens[i]));
            }
        }
    }


    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> temporary = new TreeSet<Integer>();
            if (wordToIndex.get(word) == null) {
                return null;
            }
            Iterator<Integer> iterator = wordToIndex.get(word).iterator();
            while (iterator.hasNext()) {
                int x = iterator.next();
                temporary.add(x);
            }
    
            Set<Integer> result = GraphHelper.descendants(graph, temporary);
            Iterator<Integer> items = result.iterator();
            Set<String> hyponyms = new HashSet<String>();
            while (items.hasNext()) {
                int index = items.next();
                String[] noun = indexToWord.get(index);
                for (int i = 0; i < noun.length; i++) {
                    hyponyms.add(noun[i]);
                }          
            }
            return hyponyms;
        } else {
            return null;
        }
    }

    public boolean isNoun(String noun) {
        return wordToIndex.containsKey(noun);
    }

    public Set<String> nouns() {
        return wordToIndex.keySet();
    }

}

