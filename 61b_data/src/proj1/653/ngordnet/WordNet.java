package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class WordNet {

    private Map<Integer , String[]> indexwordmap = new HashMap<Integer , String[]>();
    private Map<String, TreeSet<Integer>> wordindexmap = new HashMap<String, TreeSet<Integer>>();
    private Set<String> wordset = new HashSet<String>();
    private Digraph g;
    
    public WordNet(String syn, String hyp) {
        In synsets = new In(syn);
        In hyponyms = new In(hyp);
        TreeSet<Integer> indexes;
        
        String linereader = synsets.readLine();        
        while (linereader != null) {
            String[] words = linereader.split(",")[1].split(" ");
            int index = Integer.parseInt(linereader.split(",")[0]);
            indexwordmap.put(index, words);
            for (String word : words) {
                if (wordindexmap.containsKey(word)) {
                    indexes = wordindexmap.get(word);
                    indexes.add(index);
                    wordindexmap.put(word, indexes);
                } else {
                    indexes = new TreeSet<Integer>();
                    indexes.add(index);
                    wordindexmap.put(word, indexes);
                    wordset.add(word);
                }
            }       
            linereader = synsets.readLine();
        }
        
        g = new Digraph(indexwordmap.size() + 1);
        linereader = hyponyms.readLine();
        while (linereader != null) {
            String[] nums = linereader.split(",");
            
            for (int i = 1; i < nums.length; i += 1) {
                g.addEdge(Integer.parseInt(nums[0]), Integer.parseInt(nums[i]));
            }
            linereader = hyponyms.readLine();
        }
    }
    public boolean isNoun(String word) {
        return wordindexmap.containsKey(word);
    }
    public Set<String> nouns() {
        return wordindexmap.keySet();
    }
    
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> indexes = wordindexmap.get(word);
        Set<Integer> hyindexes = GraphHelper.descendants(g, indexes);
        Set<String> hystr = new HashSet<String>();
        for (int index : hyindexes) {
            for (String str : indexwordmap.get(index)) {
                hystr.add(str);
            }
        }
        return hystr;
    }   
}
