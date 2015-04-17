package ngordnet;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private BufferedReader synset, hyponym;
    private TreeMap<Integer, TreeSet<String>> syns;
    private TreeMap<String, TreeSet<Integer>> poly;
    private TreeMap<Integer, TreeSet<Integer>> relation;
    private Digraph diag;


    public WordNet(String synsetFilename, String hyponymFilename) {
        syns = new TreeMap<Integer, TreeSet<String>>();
        poly = new TreeMap<String, TreeSet<Integer>>();
        relation = new TreeMap<Integer, TreeSet<Integer>>();
        this.readSyns(synsetFilename);
        diag = new Digraph(poly.size());
        this.readHypos(hyponymFilename);
    }



    private void readSyns(String synsetFilename) {
        String line;
        try {
            synset = new BufferedReader(new FileReader(synsetFilename));
            while ((line = synset.readLine()) != null) {
                String[] orig = line.split(",");
                Integer index = Integer.parseInt(orig[0]);
                TreeSet<String> words = parse(orig[1]);
                syns.put(index, words);
                for (String word : words) {
                    if (!poly.containsKey(word)) {
                        TreeSet<Integer> ids = new TreeSet<Integer>();
                        ids.add(index);
                        poly.put(word, ids);
                    } else {
                        poly.get(word).add(index);
                    }
                }
            }
        } catch (IOException error) {
            System.out.println("Readline IOException in readSyns");
        }
    }

    private void readHypos(String hyponymFilename) {
        String line;
        TreeSet<Integer> bottoms = new TreeSet<Integer>();
        try {
            hyponym = new BufferedReader(new FileReader(hyponymFilename));
            while ((line = hyponym.readLine()) != null) {
                String[] orig = line.split(",");
                int vertex = Integer.parseInt(orig[0]);
                for (int i = 1; i < orig.length; i++) {
                    diag.addEdge(vertex, Integer.parseInt(orig[i]));
                }
            }
        } catch (IOException error) {
            System.out.println("Readline IOException in readHypos");
        }
    }
   
    private TreeSet<String> parse(String s) {
        TreeSet<String> ret = new TreeSet<String>();
        Scanner parser = new Scanner(s);
        while (parser.hasNext()) {
            ret.add(parser.next());
        }
        return ret;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return poly.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return poly.keySet();
    }


    // * Returns the set of all hyponyms of WORD as well as all synonyms of
    //   * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    //   * all of these synsets. See http://goo.gl/EGLoys for an example.
    //   * Do not include hyponyms of synonyms.

      
    public Set<String> hyponyms(String word) {
        TreeSet<String> ret = new TreeSet<String>();
        TreeSet<Integer> nums = poly.get(word);
        Set<Integer> all = GraphHelper.descendants(diag, nums);
        Iterator<Integer> alliter = all.iterator();
        while (alliter.hasNext()) {
            ret.addAll(syns.get(alliter.next()));
        }
        return ret;
    }
}
