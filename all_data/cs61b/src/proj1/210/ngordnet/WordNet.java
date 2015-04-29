/**
 * @author Kuan Chang
 */

package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private TreeMap<Integer, TreeSet<String>> map;
    private Digraph graph;


    public WordNet(String synsetFile, String hyponymFile) {
        In synsets = new In(synsetFile);
        In hyponyms = new In(hyponymFile);

        map = new TreeMap<Integer, TreeSet<String>>();

        while (synsets.hasNextLine()) {
            String[] line = synsets.readLine().split(",");
            TreeSet<String> synset = new TreeSet<String>();
            String[] words = line[1].split(" ");
            for (int i = 0; i < words.length; i += 1) {
                synset.add(words[i]);
            }
            map.put(Integer.parseInt(line[0]), synset);
        }

        graph = new Digraph(map.size()); //maybe use a size counter instead
        while (hyponyms.hasNextLine()) {
            String[] line = hyponyms.readLine().split(",");
            for (int i = 1; i < line.length; i += 1) {
                graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
            }
        }
    }

    public boolean isNoun(String noun) { //could also use noun() method but this is more efficient
        for (Integer i : map.keySet()) {
            if (map.get(i).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        TreeSet<String> nounSet = new TreeSet<String>();
        for (Integer i : map.keySet()) {
            nounSet.addAll(map.get(i));
        }
        return nounSet;
    }

    public Set<String> hyponyms(String hypernym) {
        HashSet<Integer> hypernymID = new HashSet<Integer>();
        HashSet<String> hyponym = new HashSet<String>();
        for (Integer i : map.keySet()) {
            if (map.get(i).contains(hypernym)) {
                hypernymID.add(i);
            }
        }
        Set<Integer> ids = GraphHelper.descendants(graph, hypernymID);
        for (Integer id : ids) {
            hyponym.addAll(map.get(id));
        }
        return hyponym;
    }
}
