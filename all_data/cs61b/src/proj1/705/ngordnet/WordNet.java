package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.Set;

public class WordNet {
    private Digraph d;
    private TreeSet<String> nouns = new TreeSet<String>();
    private HashMap<Integer, String[]> number = new HashMap<Integer, String[]>();
    private HashMap<String, Set<Integer>> sn = new HashMap<String, Set<Integer>>();
    
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syns = new In(synsetFilename);
        In hy = new In(hyponymFilename);
        readIn(syns, hy);
    }
    private void readIn(In b, In c) {
        int count = 0;
        while (!b.isEmpty()) {
            count++;
            String line = b.readLine();
            String[] temp = line.split(",");
            int keyint = Integer.parseInt(temp[0]);

            String[] words = temp[1].split(" ");
            number.put(keyint, words);
            for (int i = 0; i < words.length; i++) {
                nouns.add(words[i]);
                if (!sn.containsKey(words[i])) {
                    Set<Integer> setn = new TreeSet<Integer>();
                    setn.add(keyint);
                    sn.put(words[i], setn);
                } else {
                    Set<Integer> value = sn.get(words[i]);
                    value.add(keyint);
                    sn.put(words[i], value);

                }

            }
            
        }

        d = new Digraph(count);
        while (!c.isEmpty()) {
            String[] div = c.readLine().split(",");
            for (int j = 1; j < div.length; j++) {
                d.addEdge(Integer.parseInt(div[0]), Integer.parseInt(div[j]));
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
        
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> values = sn.get(word);
        Set<Integer> x = GraphHelper.descendants(d, values);
        Set<String> hyponyms = new TreeSet<String>();
        for (int i: x) {
            String[] listofwords = number.get(i);
            for (String result: listofwords) {
                hyponyms.add(result);
            }
        }
        return hyponyms;
    }

}

