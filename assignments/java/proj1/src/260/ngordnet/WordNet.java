package ngordnet;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.Set;
import java.io.FileNotFoundException;

public class WordNet {
    private HashMap<Integer, HashSet<String>> idKey;
    private HashMap<String, HashSet<Integer>> wordKey;
    private Digraph graph;

    public WordNet(java.lang.String synsetFilename,
            java.lang.String hyponymFilename) {
        int lineCount = 0;
        idKey = new HashMap<Integer, HashSet<String>>();
        wordKey = new HashMap<String, HashSet<Integer>>();
        try {
            InputStream in1 = new FileInputStream(new File(synsetFilename));
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                    in1));
            String line;
            while ((line = reader1.readLine()) != null) {
                lineCount++;
                String[] parts = line.split(",");
                String[] words = parts[1].split(" ");
                int id = Integer.parseInt(parts[0]);
                HashSet<String> wordList = new HashSet<String>();
                for (int i = 0; i < words.length; i++) {
                    wordList.add(words[i]);
                    // save word as key
                    if (wordKey.containsKey(words[i])) {
                        HashSet<Integer> wordKeyVal = wordKey.get(words[i]);
                        wordKeyVal.add(id);
                        wordKey.put(words[i], wordKeyVal);
                    } else {
                        HashSet<Integer> idList = new HashSet<Integer>();
                        idList.add(id);
                        wordKey.put(words[i], idList);
                    }
                }
                // save ID as key
                idKey.put(id, wordList);
            }
            reader1.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found " + synsetFilename);
        } catch (IOException e) {
            System.out.println("Cannot open file " + synsetFilename);
        }
        try {
            graph = new Digraph(lineCount);
            InputStream in2 = new FileInputStream(new File(hyponymFilename));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                    in2));
            String line;
            while ((line = reader2.readLine()) != null) {
                String[] edges = line.split(",");
                int n1 = Integer.parseInt(edges[0]);
                for (int i = 1; i < edges.length; i++) {
                    int n2 = Integer.parseInt(edges[i]);
                    graph.addEdge(n1, n2);
                }
            }
            reader2.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + hyponymFilename);
        } catch (IOException e) {
            System.out.println("Cannot open file" + synsetFilename);
        }

    }

    public boolean isNoun(String noun) {
        if (!wordKey.isEmpty()) {
            return wordKey.containsKey(noun);
        }
        return false;
    }

    public Set<String> nouns() {
        if (wordKey.isEmpty()) {
            return null;
        }
        return wordKey.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<String> hypReturn = new HashSet<String>();
        if (wordKey.containsKey(word)) {
            HashSet<Integer> ids = wordKey.get(word);
            Set<Integer> descent = GraphHelper.descendants(graph, ids);
            Iterator<Integer> id = descent.iterator();
            while (id.hasNext()) {
                Set<String> words = idKey.get(id.next());
                Iterator<String> wordList = words.iterator();
                while (wordList.hasNext()) {
                    hypReturn.add(wordList.next());
                }
            }
        }
        return hypReturn;
    }
}
