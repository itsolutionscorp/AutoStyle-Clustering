package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;

public class WordNet {
    private Digraph network;
    private int wordCount;
    private HashMap<String, HashMap<String, String>> net;
    private Set<String> nouns;
    private Set<Integer> verticies;
    private HashMap<String, String> val;

    /**Constructor*/
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsfile = new In(synsetFilename);
        net = new HashMap<String, HashMap<String, String>>();
        String sampleKey;
        String sampleSecondKey;
        String sampleDef;
        nouns = new HashSet<String>();
        while (synsfile.hasNextChar()) {
            String currentLine = synsfile.readLine();
            String[] line = currentLine.split(",");
            sampleKey = line[0];
            sampleSecondKey = line[1];
            sampleDef = line[2];
            val = new HashMap<String, String>();
            String[] subMapKeys = sampleSecondKey.split(" ");
            for (String s : subMapKeys) {
                val.put(s, sampleDef); // The values for the larger HashMap net
                nouns.add(s);
            }
            net.put(sampleKey, val);
        }
        network = new Digraph(net.size());
        In hypofile = new In(hyponymFilename);
        while (hypofile.hasNextChar()) {
            String tempLine = hypofile.readLine();
            String[] splitTemp = tempLine.split(",");
            for (int i = 0; i < splitTemp.length; i++) {
                network.addEdge(Integer.parseInt(splitTemp[0]), Integer.parseInt(splitTemp[i]));
            }
        }
    }

    /**Returns the set of all hyponyms of WORD as well as all of WORD*/
    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new HashSet<String>();
        verticies = new HashSet<Integer>();
        for (String key : net.keySet()) {
            if (net.get(key).containsKey(word)) {
                verticies.add(Integer.parseInt(key));
            }
        }
        Set<Integer> allVerticiesToReturn = GraphHelper.descendants(network, verticies); 
        for (Integer i : allVerticiesToReturn) {
            for (String key : net.get(Integer.toString(i)).keySet()) {
                toReturn.add(key);
            }
        }
        return toReturn;
    }

    /**Returns true if NOUN is a noun, and false if it isn't.*/
    public boolean isNoun(String noun) {
        return (nouns().contains(noun));
    }

    public Set<String> nouns() {
        return nouns;
    }

}
