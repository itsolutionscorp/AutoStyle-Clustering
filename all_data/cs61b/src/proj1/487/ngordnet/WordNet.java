package ngordnet; 
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private Map<Integer, String> words;
    private Map<String, ArrayList<Integer>> words2;
    private Map<Integer, Integer> numMaps;
    private Map<Integer, Integer> reverse;
    private Digraph digraph;
    private int count;

    public WordNet(String synfile, String hypofile) {
        words = new HashMap<Integer, String>();
        words2 = new HashMap<String, ArrayList<Integer>>();
        numMaps = new HashMap<Integer, Integer>();
        reverse = new HashMap<Integer, Integer>();
        count = 0;
        In hyponyms = new In(hypofile);
        In synsets = new In(synfile);
        while (synsets.hasNextLine()) {
            String line = synsets.readLine();
            String[] fullLine = line.split(",");
            int id = Integer.parseInt(fullLine[0]);
            String vals = fullLine[1];
            words.put(id, vals);
            numMaps.put(count, id);
            reverse.put(id, count);
            String[] vals2 = vals.split(" ");
            for (int j = 0; j < vals2.length; j++) {
                if (!(words2.containsKey(vals2[j]))) {
                    words2.put(vals2[j], new ArrayList<Integer>());
                }
                words2.get(vals2[j]).add(id);
            }
            count++;
        }
        digraph = new Digraph(count);
        while (hyponyms.hasNextLine()) {
            String temp = hyponyms.readLine();
            String[] ids = temp.split(",");
            int top = reverse.get(Integer.parseInt(ids[0]));
            for (int i = 1; i < ids.length; i++) {
                digraph.addEdge(top, reverse.get(Integer.parseInt(ids[i])));
            }
        }
    }

    public boolean isNoun(String word) {
        if (words2.containsKey(word)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return words2.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyps = new TreeSet<String>();
        if (words2.containsKey(word)) {
            ArrayList<Integer> temp = words2.get(word);
            for (int q: temp) {
                String temp2 = words.get(q);
                String[] allWords = temp2.split(" ");
                for (String t: allWords) {
                    hyps.add(t);
                }
                int node = reverse.get(q);
                Set<Integer> parent = new TreeSet<Integer>();
                parent.add(node);
                Set<Integer> ids = GraphHelper.descendants(digraph, parent);
                for (int i: ids) {
                    String[] name = words.get(numMaps.get(i)).split(" ");
                    for (int j = 0; j < name.length; j++) {
                        hyps.add(name[j]);
                    }
                }
            }
        }
        return hyps;
    }
}
