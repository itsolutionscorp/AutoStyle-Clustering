package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

public class WordNet {
    private Digraph dG;
    private TreeMap<Integer, List<String>> nodeMap;
    private TreeMap<String, Set<Integer>> refMap;
    //to find vertices
    public WordNet(String sysnetFile, String hyponymFile) {
        In inSys = new In(sysnetFile);
        In inHyp = new In(hyponymFile);
        nodeMap = new TreeMap<Integer, List<String>>();
        refMap = new TreeMap<String, Set<Integer>>();

        int i = 0; //creates the TreeMap to associate a value with each vertex
        while (!inSys.isEmpty()) {
            i += 1;
            String[] sysLine = inSys.readLine().split(",");
            int key = Integer.parseInt(sysLine[0]);
            List<String> val = new ArrayList<String>(2);
            val.add(sysLine[1]);
            // val.add(sysLine[2]);
            nodeMap.put(key, val);
            for (String s : sysLine[1].split(" ")) {
                if (refMap.containsKey(s)) {
                    Set<Integer> tempKeySet = refMap.get(s);
                    tempKeySet.add(key);
                    refMap.put(s, tempKeySet);
                } else {
                    Set<Integer> keySet = new HashSet<Integer>();
                    keySet.add(key);
                    refMap.put(s, keySet);
                }
            }
        }
        dG = new Digraph(i); //directs one vertex to its children vertices
        while (!inHyp.isEmpty()) {
            String[] hypLine = inHyp.readLine().split(",");
            int node = Integer.parseInt(hypLine[0]);
            for (int j = 1; j < hypLine.length; j++) {
                dG.addEdge(node, Integer.parseInt(hypLine[j])); 
            }
        }
    }
    private Set<String> wordsInMap(TreeMap<Integer, List<String>> map) {
        Set<String> result = new HashSet<String>();
        Collection<List<String>> valCol = map.values();
        for (List<String> x : valCol) {
            String[] valList = x.toArray(new String[x.size()]);
            for (int l = 0; l < valList.length; l++) {
                String[] ughVals = valList[l].split(" ");
                for (int m = 0; m < ughVals.length; m++) {
                    result.add(ughVals[m]);
                }
            }
        }
        return result;
    }
    public Set<String> nouns() {
        return wordsInMap(nodeMap);
    }
    public boolean isNoun(String wrd) {
        return nouns().contains(wrd);
    }
    public Set<String> hyponyms(String wrd) {
        Set<String> result = new HashSet<String>();
        Set<Integer> wrdSet = refMap.get(wrd);
        Set<Integer> allV = new HashSet<Integer>();
        for (int y : wrdSet) {
            for (int v : dG.adj(y)) {
                allV.add(v);
            }
        }
        for (int x : GraphHelper.descendants(dG, wrdSet)) {
            allV.add(x);
        }
        for (int i : allV) {
            List<String> wordList = nodeMap.get(i);
            String[] words = wordList.toArray(
                    new String[wordList.size()])[0].split(" ");
            for (String j : words) {
                result.add(j);
            }
        }   
        return result;
    }
}
