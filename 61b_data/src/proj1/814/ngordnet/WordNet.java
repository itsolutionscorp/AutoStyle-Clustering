package ngordnet;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;



/** WordNet
  * @author Yaqi Miao
  */

public class WordNet {

    private int numOfSynset;
    private HashMap<Integer, Synset> map;
    private Digraph net;
    private HashSet<String> v;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        int id;
        String sy;
        String de;
        String line;
        String[] fields;
        int hyper;
        int hypo;
        map = new HashMap<Integer, Synset>();
        v = new HashSet<String>();
        /** read nodes (synsets).*/
        numOfSynset = 0;
        In readSynsets = new In(synsetFilename);
        while (readSynsets.hasNextLine()) {
            if (readSynsets.hasNextChar()) {
                line = readSynsets.readLine();
                fields = line.split(",");
                id = Integer.parseInt(fields[0]);
                sy = fields[1];
                de = fields[2];
                Synset node = new Synset(id, sy, de);
                map.put(numOfSynset, node);
                numOfSynset += 1;
            }
        }

        /** read edges (hyponyms).*/
        In readHyponym = new In(hyponymFilename);
        net = new Digraph(numOfSynset);
        while (readHyponym.hasNextLine()) {
            if (readHyponym.hasNextChar()) {
                line = readHyponym.readLine();
                fields = line.split(",");
                hyper = Integer.parseInt(fields[0]);
                for (int i = 1; i < fields.length; i++) {
                    hypo = Integer.parseInt(fields[i]);
                    net.addEdge(hyper, hypo);
                }
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String word : v) {
            if (word.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return v;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> answer = new HashSet<String>();
        answer.add(word);
        for (int i = 0; i < numOfSynset; i++) {
            Synset s = map.get(i);
            if (s.contains(word)) {
                String[] words = s.groupOfWords();
                for (String w : words) {
                    answer.add(w);
                }
                addHypo(answer, i);
            }
        }
        return answer;
    }

    private void addHypo(Set<String> set, int s) {
        Set<Integer> hypo = new TreeSet<Integer>();
        hypo.add(s);
        hypo = GraphHelper.descendants(net, hypo);
        for (int node : hypo) {
            String[] words = map.get(node).groupOfWords();
            for (String word : words) {
                set.add(word);
            }
        }
    }



    /** The class that represent a synset.*/
    private class Synset {

        int id;
        String[] words;
        String def;

        /** Constructor of Synset. ID the number of this synset; SY the list of
          * words in the synset; DE the dictionary definition.*/
        private Synset(int ide, String sy, String de) {
            this.id = ide;
            words = sy.split(" ");
            def = de;
            for (String word : words) {
                v.add(word);
            }
        }

        private boolean contains(String word) {
            for (String s : words) {
                if (s.equals(word)) {
                    return true;
                }
            }
            return false;
        }

        private String[] groupOfWords() {
            return words;
        }
    }
}
