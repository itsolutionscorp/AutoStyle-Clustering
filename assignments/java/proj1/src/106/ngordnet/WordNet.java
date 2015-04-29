package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, String> map;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetf = new In(synsetFilename);
        In hyponymf = new In(hyponymFilename);
        map = new HashMap<Integer, String>();
        String[] line, elements;
        int x = 0, num = 0, count, hyper;
        boolean f;
        while (synsetf.hasNextLine()) {
            num += 1;
            line = synsetf.readLine().split(",");
            x = Integer.parseInt(line[0], 10);
            map.put(x, line[1]);
        }
        synsetf.close();
        g = new Digraph(num);
        while (hyponymf.hasNextLine()) {
            line = hyponymf.readLine().split(",");
            count = 0;
            hyper = 0;
            for (String id: line) {
                count += 1;
                x = Integer.parseInt(id, 10);
                if (count == 1) {
                    hyper = x;
                } else {
                    g.addEdge(hyper, x);
                }
            }
        }
        hyponymf.close();
    }

    public boolean isNoun(String word) {
        String[] str;
        for (Integer key: map.keySet()) {
            str = map.get(key).split(" ");
            for (String noun: str) {
                if (noun.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        String[] str;
        Set<String> set = new HashSet<String>();
        for (Integer key: map.keySet()) {
            str = map.get(key).split(" ");
            for (String noun: str) {
                set.add(noun);
            }
        }
        return set;
    }

    public Set<String> hyponyms(String word) {
        String[] str;
        Set<Integer> ids = new HashSet<Integer>();
        for (Integer key: map.keySet()) {
            str = map.get(key).split(" ");
            for (String noun: str) {
                if (noun.equals(word)) {
                    ids.add(key);
                }
            }
        }
        Set<String> hypo = new HashSet<String>();
        Set<Integer> des = GraphHelper.descendants(g, ids);
        for (Integer descendant: des) {
            str = map.get(descendant).split(" ");
            for (String noun: str) {
                hypo.add(noun);
            }
        }
        return hypo;
    }
}
