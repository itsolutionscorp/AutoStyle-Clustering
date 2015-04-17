package ngordnet;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.HashMap;
import java.io.File;

public class WordNet {
    private ArrayList<String[]> syns;
    private Digraph G;
    private HashMap<String, Set<Integer>> revmap;
    public WordNet(String synsetfile, String hyposetfile) {
        In synreader = new In(new File(synsetfile));
        In hyporeader = new In(new File(hyposetfile));
        syns  =  new ArrayList<String[]>();
        revmap = new HashMap<String, Set<Integer>>();
        int linenum  =  0;
        while (synreader.hasNextLine()) {
            String[] thisline = synreader.readLine().split(",")[1].split(" ");
            syns.add(thisline);
            for (String s:thisline) {
                if (revmap.get(s) == null) {
                    revmap.put(s, new TreeSet());
                }
                revmap.get(s).add(linenum);
            }
            linenum +=  1;
        }
        G = new Digraph(syns.size());
        while (hyporeader.hasNextLine()) {
            String[] s = hyporeader.readLine().split(",");
            Integer[] n = new Integer[s.length];
            int w = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i += 1) {
                G.addEdge(w, Integer.parseInt(s[i]));
            }
        }
    }
    
    public Set<String> hyponyms(String wut) {
        Set<Integer> wutpos = revmap.get(wut);
        Set<Integer> bebies = GraphHelper.descendants(G, wutpos);
        TreeSet<String> out = new TreeSet<String>();
        for (int i:bebies) {
            for (String s:syns.get(i)) {
                out.add(s);
            }
        }
        return out;
    }
    
    public boolean isNoun(String wut) {
        return revmap.containsKey(wut);
    }
    
    public Set<String> nouns() {
        return revmap.keySet();
    }
    
}
