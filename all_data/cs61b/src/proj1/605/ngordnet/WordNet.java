package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<Integer, ArrayList<String>> wordID 
                                                = new HashMap<Integer, ArrayList<String>>();
    private HashMap<String, ArrayList<Integer>> reversed 
                                                = new HashMap<String, ArrayList<Integer>>();
    private Digraph hypoGraph;
    public WordNet(String synsetFile, String hyponymFile) {
        In inS = new In(synsetFile);
        In inH = new In(hyponymFile);
        String[] arrayS;
        String[] arrayH;
        String[] splitWdH;
        String[] splitWdS;

        while (inS.hasNextLine()) {
            arrayS = inS.readLine().split(",", 3);
            splitWdS = arrayS[1].split(" ", 0);
            int n = Integer.parseInt(arrayS[0]);
            for (String s : splitWdS) {
                if (!reversed.containsKey(s)) {
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    reversed.put(s, ids);
                }
                reversed.get(s).add(n);
                if (!wordID.containsKey(n)) {
                    ArrayList<String> synset = new ArrayList<String>();
                    wordID.put(n, synset);
                }
                wordID.get(n).add(s);
            }
        }
        while (inH.hasNextLine()) {
            arrayH = inH.readLine().split(",", 2);
            splitWdH = arrayH[1].split(",", 0);
            int n = Integer.parseInt(arrayH[0]);
            for (int i = 0; i < splitWdH.length; i++) {
                if (hypoGraph == null) {
                    hypoGraph = new Digraph(wordID.keySet().size()); 
                }
                hypoGraph.addEdge(n, Integer.parseInt(splitWdH[i]));
            }
        }
    }
    public boolean isNoun(String noun) {
        return reversed.containsKey(noun);
    }
    public Set<String> nouns() {
        return reversed.keySet();
    }
    public Set<String> hyponyms(String word) {
        Set<Integer> ans = new TreeSet<Integer>();
        for (int i : reversed.get(word)) {
            ans.add(i);
        }
        Set<Integer> mid = GraphHelper.descendants(hypoGraph, ans);
        Set<String> res = new TreeSet<String>();
        for (int i : mid) {
            for (String p : wordID.get(i)) {
                res.add(p);
            }
        }
        return res;
    }
}
