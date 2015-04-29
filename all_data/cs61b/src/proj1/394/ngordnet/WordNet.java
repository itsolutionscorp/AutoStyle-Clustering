package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet; 
import java.util.Set;
public class WordNet {
    private Digraph graph;
    private HashMap<Integer, Entry> hm;
    private class Entry {
        private int vertex;
        private String[] word;
        private String def;
        private int[] hyponyms; 
        private Entry(int v, String[] w, String d) {
            this.vertex = v;
            this.word = w;
            this.def = d;
            this.hyponyms = null; 
        }
    }
    private Entry synKey(String line) {
        String[] words = line.split(",");
        int vertex = Integer.parseInt(words[0]);
        String[] word = words[1].split(" ");
        String def = words[2];
        return new Entry(vertex, word, def);
    }
    private int[] hypList(String hypData) {
        String[] words = hypData.split(",");
        int[] values = new int[words.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = Integer.parseInt(words[i]);
        }
        return values;
    }
    private void makeDigraph() {
        graph = new Digraph(hm.size()); 
        for (Integer i : hm.keySet()) {
            Entry temp = hm.get(i);
            if (temp.hyponyms != null) {
                for (int j : temp.hyponyms) {
                    graph.addEdge(i, j);  
                }
            }

        }
    }
    public WordNet(String synsets, String hyponyms) {
        In synData = new In(synsets);
        In hypData = new In(hyponyms);
        hm = new HashMap<Integer, Entry>();

        while (synData.hasNextLine()) {
            Entry e = synKey(synData.readLine());
            hm.put(e.vertex, e);
        }
        
        while (hypData.hasNextLine()) {
            int[] hyps = hypList(hypData.readLine());
            Entry temp = hm.get(hyps[0]); 
            if (temp.hyponyms != null) {
                int[] tempHyps = new int[temp.hyponyms.length + hyps.length]; 
                System.arraycopy(temp.hyponyms, 0, tempHyps, 0, temp.hyponyms.length);
                System.arraycopy(hyps, 0, tempHyps, temp.hyponyms.length, hyps.length);
                temp.hyponyms = tempHyps;
            } else {
                temp.hyponyms = hyps;
            }
       
        }
       
        makeDigraph();
    }
    public boolean isNoun(String noun) {
        for (Integer i : hm.keySet()) {
            for (String s : hm.get(i).word) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }
    public Set<String> nouns() {
        HashSet<String> ns = new HashSet<String>();
        for (Integer i : hm.keySet()) {
            for (String w : hm.get(i).word) {
                ns.add(w);
            }
        }
        return ns;
    }
    public Set<String> hyponyms(String word) {

        GraphHelper gh = new GraphHelper();
        HashSet<Integer> ids = new HashSet<Integer>(); 
        for (Integer i : hm.keySet()) {
            Entry temp = hm.get(i);
            for (String s : temp.word) {
                if (s.equals(word)) {
                    ids.add(i); 
                }
            }
        }

        Set<Integer> descendants = gh.descendants(graph, ids); 
        HashSet<String> hyps = new HashSet<String>(); 
        for (Integer d : descendants) {
            Entry temp = hm.get(d); 
            for (String dw : temp.word) {
                hyps.add(dw); 
            }
        }
        return hyps;
    }
}

