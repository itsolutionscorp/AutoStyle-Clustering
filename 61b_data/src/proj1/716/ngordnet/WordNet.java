package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeSet;
import java.io.FileReader; 
import java.io.BufferedReader; 
import java.io.IOException;

public class WordNet { 
    private edu.princeton.cs.algs4.Digraph graph;
    private Map<String, Set<Integer>> map;
    private Map<Integer, List<String>> synsetMap;

    public WordNet(String synsetFilename, String hyponymFilename) {
        String[] oneLineWord;
        Integer idKey;
        BufferedReader stdin;
        String line; 
        this.synsetMap = new HashMap<Integer, List<String>>(); 
        this.map = new HashMap<String, Set<Integer>>(); 
        Set<Integer> idSet;

        try {
            stdin = new BufferedReader(new FileReader(synsetFilename));
            while ((line = stdin.readLine()) != null) { 
                String[] separate = line.split(",");
                oneLineWord = separate[1].split(" "); 
                idKey = Integer.valueOf(separate[0]); 
                this.synsetMap.put(idKey, Arrays.asList(oneLineWord)); 
                for (int n = 0; n < oneLineWord.length; n += 1) { 
                    idSet = new HashSet<Integer>();
                    idSet.add(idKey);
                    if (this.map.containsKey(oneLineWord[n])) {
                        idSet = this.map.get(oneLineWord[n]);
                        idSet.add(idKey);
                    }
                    this.map.put(oneLineWord[n], idSet); 
                }
            }
        } catch (IOException e) {
            System.out.println("IO error");
        }
        int i; 
        Set<String> allSynsetId = new TreeSet<String>(); 
        ArrayList<Integer> edgeList = new ArrayList<Integer>();
        try { 
            stdin = new BufferedReader(new FileReader(hyponymFilename));
            while ((line = stdin.readLine()) != null) {
                String[] separateId = line.split(",");
                for (i = 0; i < separateId.length; i += 1) {
                    allSynsetId.add(separateId[i]);
                }
                for (i = 1; i < separateId.length; i += 1) { 
                    edgeList.add(Integer.valueOf(separateId[0]));
                    edgeList.add(Integer.valueOf(separateId[i]));
                }
            }
            this.graph = new Digraph(allSynsetId.size());
            for (i = 0; i < edgeList.size(); i += 2) {
                this.graph.addEdge(edgeList.get(i).intValue(), edgeList.get(i + 1).intValue()); 
            }
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public boolean isNoun(String noun) {
        return map.containsKey(noun);
    }

    public Set<String> nouns() {
        return map.keySet();
    }
    
    public Set<String> hyponyms(String word) {
        Set<String> hyponymsSet = new HashSet<>(); 
        Set<Integer> child = GraphHelper.descendants(graph, map.get(word)); 
        for (Integer i : child) {
            List<String> wordList = synsetMap.get(i);
            for (String aSynset : wordList) { 
                hyponymsSet.add(aSynset);
            }
        }
        return hyponymsSet;
    }

    // public static void main(String[] args) {
    //  WordNet wn = new WordNet(args[0], args[1]);
    // }  
} 
