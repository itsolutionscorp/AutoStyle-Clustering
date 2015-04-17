package ngordnet;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<Integer, String[]> numWord;
    private Digraph dGraph;
    private Set<String> wordSet;
    private HashMap<String, String> definitions;
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        numWord = new HashMap<Integer, String[]>();
        wordSet = new HashSet<String>();
        definitions = new HashMap<String, String>();
        Integer max = new Integer(0);

        String line;
        String[] hline;

        In reader = new In(hyponymFilename);
        Integer sample;
        while (reader.hasNextLine()) {
            line = reader.readLine();
            hline = line.split(",");
            for (int i = 0; i < hline.length; i++) {
                sample = Integer.parseInt(hline[i]);
                if (max.compareTo(sample) < 0) {
                    max = sample;
                }
            }
        }

        dGraph = new Digraph(max + 1);


        In reader1 = new In(hyponymFilename);
        int firstnum;
        while (reader1.hasNextLine()) {
            line = reader1.readLine();
            hline = line.split(",");
            firstnum = Integer.parseInt(hline[0]);
            for (int i = 0; i < hline.length; i++) {
                dGraph.addEdge(firstnum, Integer.parseInt(hline[i]));
            }
        }

        In reader3 = new In(synsetFilename);
        int num;
        String[] syns;
        String def;
        while (reader3.hasNextLine()) {
            line = reader3.readLine();
            hline = line.split(",");
            num = Integer.parseInt(hline[0]);
            def = hline[2];
            syns = hline[1].split(" ");
            numWord.put(num, syns);
            for (int i = 0; i < syns.length; i++) {
                definitions.put(syns[i], def);
                wordSet.add(syns[i]);
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        String[] nouns;
        Set<String> hypos = new HashSet<String>();
        Set<Integer> w = new TreeSet<Integer>();
        Set<Integer> keys = numWord.keySet();
        String[] keyvalues;
        for (Integer key: keys) {
            keyvalues = numWord.get(key);
            for (int j = 0; j < keyvalues.length; j++) {
                if (keyvalues[j].equals(word)) {
                    w.add(key);
                }
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(dGraph, w);
        for (Integer descendant: descendants) {
            nouns = numWord.get(descendant);
            for (int i = 0; i < nouns.length; i++) {
                hypos.add(nouns[i]);
            }
        }
        return hypos;
    }
}
