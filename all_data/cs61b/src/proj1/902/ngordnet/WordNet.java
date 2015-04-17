package ngordnet;
// import GraphHelper;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
public class WordNet {
    private String[] sset;
    private String[] hset;
    private int len;
    private Map<Integer, Set<String>> StoI = new HashMap<Integer, Set<String>>();
    private Digraph bik;
    private Set<String> allNouns = new HashSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        In x = new In(synsetFilename);
        In y = new In(hyponymFilename);
        sset = x.readAllLines();
        hset = y.readAllLines();
        len = sset.length;
    
        putinDigraph();
        putinMap();
        for (int p: StoI.keySet()) {
            allNouns.addAll(StoI.get(p));
        }   
    }
    private void putinDigraph() {
        bik = new Digraph(len);

        for (int i = 0; i < hset.length; i += 1) {
            String[] eachline = hset[i].split(",");
            for (int s = 1; s < eachline.length; s += 1) {
                bik.addEdge(Integer.parseInt(eachline[0]), Integer.parseInt(eachline[s]));
            }

        }

    }
    private void putinMap() {

        for (int i = 0; i < sset.length; i += 1) {
            String[] eachline = sset[i].split(",");
            String[] eachword = eachline[1].split(" ");
            Set<String> eachlineset = new HashSet<String>();
            for (String q: eachword) {

                eachlineset.add(q);
            }
            StoI.put(Integer.parseInt(eachline[0]), eachlineset);
        }

    }
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }
    public Set<String> nouns() {
        return allNouns;
    }
    public Set<String> hyponyms(String word) {

        Set<Integer> findhyp = new HashSet<Integer>();
        for (int x: StoI.keySet()) {
            if (StoI.get(x).contains(word)) {
                findhyp.add(x);
            }
        }
        
        Set<String> results = new HashSet<String>();
        Set<Integer> hypnumbers = GraphHelper.descendants(bik, findhyp);
        for (int x: hypnumbers) {
            results.addAll(StoI.get(x));
        }
        return results;


    }


}

