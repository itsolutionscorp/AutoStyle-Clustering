package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.Iterator;
import java.util.TreeMap;

public class WordNet {
    private Digraph dg;
    private int vertices;
    private String[] words;
    private String[] hyponym;
    private Map<Integer, String[]> map;
    public WordNet(String syns, String hypos) {
        In readsyn = new In(syns);
        In readhypos = new In(hypos);
        words = readsyn.readAllLines();
        map = new TreeMap<Integer, String[]>();
        for (int i = 0; i < words.length; i++) {
            String[] tokens1 = words[i].split(",");
            String[] arr = tokens1[1].split(" ");
            map.put(Integer.parseInt(tokens1[0]), arr);
        }
        hyponym = readhypos.readAllLines();
        vertices = words.length;
        dg = new Digraph(vertices);
        for (int i = 0; i < hyponym.length; i++) {
            String[] tokens = hyponym[i].split(",");
            int firstNumber = Integer.parseInt(tokens[0]);
            for (int j = 1; j < tokens.length; j++) {
                dg.addEdge(firstNumber, Integer.parseInt(tokens[j]));
            }
        }
    }

    public Set<String> hyponyms(String word) { 
        Set<String> hyponyms = new TreeSet<String>();
        Set<Integer> hyponymids = new TreeSet<Integer>();
        Set<Integer> ids = map.keySet();
        Iterator<Integer> iter = ids.iterator();
        /* find all ID numbers with word*/
        while (iter.hasNext()) {
            int id = iter.next();
            for (int i = 0; i < map.get(id).length; i++) {
                if (map.get(id)[i].equals(word)) {
                    hyponymids.add(id);
                }
            }
        }
        hyponymids = GraphHelper.descendants(dg, hyponymids);
        Iterator<Integer> iter2 = hyponymids.iterator();
        while (iter2.hasNext()) {
            int storage = iter2.next();
            for (int i = 0; i < map.get(storage).length; i++) {
                hyponyms.add(map.get(storage)[i]);
            }
        }
        return hyponyms;
    }

    public boolean isNoun(String word) { 
        Set<Integer> ids = map.keySet();
        Iterator<Integer> iter = ids.iterator();
        while (iter.hasNext()) {
            int storage = iter.next();
            String[] arr = map.get(storage);
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() { 
        Set<String> nouns = new TreeSet<String>();
        Set<Integer> ids = map.keySet();
        Iterator<Integer> iter = ids.iterator();
        while (iter.hasNext()) {
            int storage = iter.next();
            String[] arr = map.get(storage);
            for (int i = 0; i < arr.length; i++) {
                nouns.add(arr[i]);
            }
        }
        return nouns;
    }
}
