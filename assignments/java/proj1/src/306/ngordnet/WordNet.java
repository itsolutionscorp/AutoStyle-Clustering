package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet extends Object {

    private TreeMap<String, Integer> nouns;
    private TreeMap<Integer, String> synset;
    private Digraph graph;

    
    public WordNet(String synsetFilename, String hyponymFilename) {
        this.nouns = new TreeMap<String, Integer>();
        this.synset = new TreeMap<Integer, String>();
        In in = new In(synsetFilename);

        int size = 0;

        while (in.hasNextLine()) {
            size++;
            String line = in.readLine();
            String[] arguments = line.split(",");
            Integer num = Integer.parseInt(arguments[0]);
            synset.put(num, arguments[1]);
            String[] nounList = arguments[1].split(" ");
            for (String aNoun : nounList) {
                nouns.put(aNoun, num);
            }
        }

        graph = new Digraph(size);

        in = new In(hyponymFilename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[]arguments = line.split(",");
            Integer num = Integer.parseInt(arguments[0]);
            for (int i = 0; i < arguments.length; i++) {
                Integer n = Integer.parseInt(arguments[i]);
                if (i > 0) {
                    graph.addEdge(num, n);
                }
            }
        }


    }

    public boolean isNoun(String noun) {
        return this.nouns.keySet().contains(noun);
    }

    public Set<String> nouns() {
        return this.nouns.keySet();
    }

    public Set<String> hyponyms(String word) {
        // Set<String> vals = new TreeSet<String>();
        // for (Integer i : this.synset.keySet()) {
        //     vals.add(this.synset.get(i));
        // }
        // Set<String> x = new TreeSet<String>();
        // Set<Integer> y =
        Set<Integer> g = GraphHelper.descendants(graph, this.synset.keySet());
        Set<String> result = new TreeSet<String>();
        for (Integer num : g) {
            String n = num.toString();
            result.add(n);
        }
        return result;
    }
}
