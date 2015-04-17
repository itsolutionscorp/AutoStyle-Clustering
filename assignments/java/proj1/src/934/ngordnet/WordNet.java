
package ngordnet;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private String fileS;
    private String fileH;
    private Digraph fullGraph;
    private Map<Integer, Synset> synMap = new HashMap<Integer, Synset>();
    private Map<String, ArrayList<Integer>> nouns = new HashMap<String, ArrayList<Integer>>();
    private Set<String> hyponymSet = new HashSet<String>();
    private ArrayList<Synset> synsets;
    private ArrayList<int[]> hyponym;

    public WordNet(String fileSyn, String fileHyp) {
        this.fileS = fileSyn;
        this.fileH = fileHyp;
        this.synsets = FileParser.readSynsets(this.fileS);
        this.hyponym = FileParser.readHyponyms(this.fileH);
        constructSynMap();
        constructHyponymDigraph();
        constructNounsSet();
    }

    public boolean isNoun(String word) {
        return nouns.containsKey(word);
    }

    public Set<String> nouns() {
        return nouns.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<String> results = new HashSet<String>();
        if (!isNoun(word)) {
            return null;
        } else {
            //get ids of the hypnyms of word
            Set<Integer> ids = new HashSet<Integer>(nouns.get(word));
            //get all decendants of ids
            Set<Integer> children = GraphHelper.descendants(fullGraph, ids);
            for (int i : children) {
                results.addAll(synMap.get(i).getSyns());
            }
        }
        return results;
    }
    

    private void constructSynMap() {
        //get synsets and iterate through the elements of an array 
        //list and give the value as the index
        for (Synset i: this.synsets) {
            synMap.put(i.getId(), i);
        }
    }

    private void connect(int[] edges) {
        int l = edges.length;
        if (l < 2) {
            return;
        }
        for (int i = 1; i < edges.length; i += 1) {
            fullGraph.addEdge(edges[0], edges[i]);
        }   
    }

    private void constructHyponymDigraph() {
        this.fullGraph = new Digraph(synMap.size());
        for (int[] i: this.hyponym) {
            connect(i);
        }
    }

    private void constructNounsSet() {
        //iterate through SynMap and then iterate through the words in syn and add to the set
        for (Synset i: this.synsets) {
            Set<String> syns = i.getSyns();
            int id = i.getId();
            for (String s: syns) {
                if (this.nouns.containsKey(s)) {
                    this.nouns.get(s).add(id);
                } else {
                    this.nouns.put(s, new ArrayList<Integer>());
                    this.nouns.get(s).add(id);
                }
            }
        }
    }
}
