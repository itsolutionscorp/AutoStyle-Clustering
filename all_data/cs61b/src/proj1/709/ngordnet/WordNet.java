package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {

    private Map<Integer, String[]> synsets;  // keeps track of what each number corresponds to
    private Map<String, Set<Integer>> words; // keeps track of what numbers each String is in
    private Digraph digraphs;                // keeps track of the relationships between the words

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new HashMap<Integer, String[]>();

        In synsetStream = new In(synsetFilename);
        String synset = synsetStream.readLine();

        words = new HashMap<String, Set<Integer>>();
        while (synset != null) {
            String[] parts = synset.split(",");       // only used the first two parts
            Integer num = Integer.parseInt(parts[0]); // parts[0] has the number
            String[] splitted = parts[1].split(" ");  // parts[1] contains the Strings
                                                      // parts[2] has the definition (unused)
            
            synsets.put(num, splitted); // can simply add number to synsets
            for (String w : splitted) { // need to check if a Set exists or not
                if (!words.keySet().contains(w)) { 
                    Set<Integer> temp = new TreeSet<Integer>();
                    temp.add(num);
                    words.put(w, temp);
                } else {
                    words.get(w).add(num);
                }
            }

            synset = synsetStream.readLine();
        }

        In hyponymStream = new In(hyponymFilename);
        digraphs = new Digraph(synsets.size());

        String hyponym = hyponymStream.readLine();
        while (hyponym != null) {
            String[] parts = hyponym.split(",");
            int start = Integer.parseInt(parts[0]); // first number is the starting point
            for (int i = 1; i < parts.length; i++) {
                digraphs.addEdge(start, Integer.parseInt(parts[i]));
            }
            hyponym = hyponymStream.readLine();
        }
    }

    public boolean isNoun(String noun) {
        return words.keySet().contains(noun);
    }

    public Set<String> nouns() {
        return words.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> descendants = GraphHelper.descendants(digraphs, words.get(word));
        Set<String> hypo = new TreeSet<String>();
        for (Integer a : descendants) { // go through all the descendants and add them to the Set
            String[] temp = synsets.get(a);
            for (int x = 0; x < temp.length; x++) {
                hypo.add(temp[x]);
            }
        }
        return hypo;
    }
}
