package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
public class WordNet {
    private Digraph net;
    private TreeSet<Synset> synsets = new TreeSet<Synset>(new SynsetComparator());
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synreader = new In(new File(synsetFilename));
        In hyporeader = new In(new File(hyponymFilename));
        int synsetcount = 0;
        while (synreader.hasNextLine()) {
            String line = synreader.readLine();
            String[] lineitems = line.split(",");
            int synsetid = Integer.parseInt(lineitems[0]);
            String[] words = lineitems[1].split(" ");
            TreeSet<String> synsetwords = new TreeSet<String>(Arrays.asList(words));
            synsets.add(new Synset(synsetid, synsetwords, lineitems[2]));
            synsetcount++;
        }
        net = new Digraph(synsetcount);
        while (hyporeader.hasNextLine()) {
            String line = hyporeader.readLine();
            String[] lineitems = line.split(",");
            int synsetid = Integer.parseInt(lineitems[0]);
            int[] hyponymids = new int[lineitems.length - 1];
            for (int hyponymid:hyponymids) {
                net.addEdge(synsetid, hyponymid);
            }
        }
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Synset s:synsets) {
            for (String word:s.words) { 
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false; //Nothing found
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> nouns = new TreeSet<String>();
        for (Synset synset:synsets) {
            for (String word:synset.words) {
                nouns.add(word);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new TreeSet<String>();
        Set<Synset> allsynsets = new TreeSet<Synset>();
        for (Synset s:synsets) {
            if (s.wordisinsynset(word)) {
                allsynsets.add(s);
            }
        }
        Set<Integer> allsynsetids = new TreeSet<Integer>();
        for (Synset s:allsynsets) {
            allsynsetids.add(s.id);
        }
        Set<Integer> descendants = GraphHelper.descendants(net, allsynsetids);
        for (Synset s:synsets) {
            if (descendants.contains(s.id)) {
                allsynsets.add(s);
            }
        }
        for (Synset s:allsynsets) {
            hyponyms.addAll(s.words);
        }
        return hyponyms;
    }
}
