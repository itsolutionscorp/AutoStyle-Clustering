package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {
    private final Digraph GRAPH;
    private final HashMap<String, ArrayList<Integer>> MAP;
    private final HashMap<Integer, String[]> MAP_T;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        String lines;
        int size1 = 0;
        int size2 = 0;
        while (synset != null && synset.hasNextLine()) {
            lines = synset.readLine();
            if (lines != null) {
                String[] values = lines.split(",");
                size1 = Integer.parseInt(values[0]);
                String[] v = values[1].split(" ");
                size2 += v.length;
            }
        }
        synset = new In(synsetFilename);
        MAP_T = new HashMap(size1);
        MAP = new HashMap(size2);
        while (synset.hasNextLine()) {
            lines = synset.readLine();
            String[] values = lines.split(",");
            String[] words = values[1].split(" ");
            MAP_T.put(Integer.parseInt(values[0]), words);
            for (String i : words) {
                if (MAP.containsKey(i)) {
                    MAP.get(i).add(Integer.parseInt(values[0]));
                } else {
                    ArrayList<Integer> arr = new ArrayList();
                    arr.add(Integer.parseInt(values[0]));
                    MAP.put(i, arr);
                }
            }
        }
        GRAPH = new Digraph(size1 + 1);
        In hyponym = new In(hyponymFilename);
        In lineVal;
        while (hyponym.hasNextLine()) {
            String line = hyponym.readLine();
            String[] valsStrings = line.split(",");
            int[] vals = new int[valsStrings.length];
            int j = 0;
            for (String i : valsStrings) {
                vals[j] = Integer.parseInt(i);
                j += 1;
            }
            //lineVal = new In(line);
            //int[] vals = lineVal.readAllInts();
            for (int i = 1; i < vals.length; i++) {
                GRAPH.addEdge(vals[0], vals[i]);
            }
        }
        //System.out.println(GRAPH);
    }
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            Set<String> bleh = new HashSet();
            return bleh;
        }
        Set<Integer> ids = new HashSet<Integer>(MAP.get(word));
        //System.out.print(ids);
        GraphHelper help = new GraphHelper();
        Set<Integer> results = help.descendants(GRAPH, ids);
        //System.out.println(help.descendants(GRAPH, ids));
        //System.out.print(results);
        HashSet<String> resultsWords = new HashSet(results.size());
        for (int i: results) {
            for (String j:MAP_T.get(i)) {
                resultsWords.add(j);
            }
        }
        for (int i: MAP.get(word)) {
            for (String j: MAP_T.get(i)) {
                resultsWords.add(j);
            }
        }
        //resultsWords.remove(word);
        return resultsWords;
    }
    public boolean isNoun(String noun) {
        return MAP.containsKey(noun);
    }
  /**  public static void main(String[] args) {
        In balh = new In(args[0]);
    }*/
    public Set<String> nouns() {
        return MAP.keySet();
    }
}
