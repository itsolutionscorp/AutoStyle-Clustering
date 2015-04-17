package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;
import java.util.TreeMap;

public class WordNet {

    private String synFile;
    private String hypFile;
    private Digraph d;
    private TreeMap<Integer, TreeSet<String>> graph;

    public WordNet(String synsetFile, String hyponymFile) {

        synFile = synsetFile;
        hypFile = hyponymFile;
        d = new Digraph(numLines(synFile));
        graph = new TreeMap<Integer, TreeSet<String>>();

        In hyps = new In(hypFile);
        while (hyps.hasNextLine()) {
            String line = hyps.readLine();
            String[] nums = line.split(",");
            for (int i = 1; i < nums.length; i++) {
                d.addEdge(Integer.parseInt(nums[0]), Integer.parseInt(nums[i]));
            }
        }

        In syns = new In(synFile);
        while (syns.hasNextLine()) {
            String line = syns.readLine();
            String[] elems = line.split(",");
            String[] words = elems[1].split(" ");
            TreeSet<String> w = new TreeSet<String>();
            for (String e: words) {
                w.add(e);
            }
            graph.put(Integer.parseInt(elems[0]), w);
        }        

    }

    public Set<String> hyponyms(String word) {

        TreeSet<Integer> id = new TreeSet<Integer>();
        for (int key : graph.keySet()) {
            for (String value : graph.get(key)) {
                if (value.equals(word)) {
                    id.add(key); 
                }
            }
        }

        TreeSet<String> result = new TreeSet<String>();
        for (int hyp : GraphHelper.descendants(d, id)) {
            for (String s : graph.get(hyp)) {
                result.add(s);
            }
        }

        return result;


    }

    public boolean isNoun(String noun) {

        if (nouns().contains(noun)) {
            return true;
        }

        return false;

    }


    public Set<String> nouns() {

        TreeSet<String> result = new TreeSet<String>();
        for (int key : graph.keySet()) {
            for (String s : graph.get(key)) {
                result.add(s);
            }
        }

        return result;


    }

    private int numLines(String file) {

        In in = new In(file);
        int lines = 0;
        while (in.hasNextLine()) {
            in.readLine();
            lines += 1;    
        }
        return lines;

    }

}
