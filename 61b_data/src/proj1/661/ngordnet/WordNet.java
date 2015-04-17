package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<Integer, String> idlist = new HashMap<Integer, String>(100);
    private HashMap<String, String> nounslist = new HashMap<String, String>(100);
    private HashMap<String, String> seperatelist = new HashMap<String, String>(
            100);
    private TreeSet<String> nounlist = new TreeSet<String>();
    private int nounsize;
    private Digraph hyp;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synreader = new In(synsetFilename);
        In hypreader = new In(hyponymFilename);
        nounsize = 0;
        while (synreader.hasNextLine()) {
            String line = synreader.readLine();
            String[] read = line.split(",");
            idlist.put(Integer.parseInt(read[0]), read[1]);
            nounslist.put(read[1], read[0]);
            nounsize++;
            String[] readsplit = read[1].split(" ");
            for (String counter : readsplit) {
                nounlist.add(counter);
                if (seperatelist.containsKey(counter)) {
                    String temp = seperatelist.get(counter);
                    seperatelist.put(counter, temp + "," + read[0]);
                } else {
                    seperatelist.put(counter, read[0]);
                }
            }
        }
        hyp = new Digraph(nounsize);

        while (hypreader.hasNextLine()) {
            String line = hypreader.readLine();
            String[] read = line.split(",");
            for (int counter = 1; counter < read.length; counter++) {
                hyp.addEdge(Integer.parseInt(read[0]),
                        Integer.parseInt(read[counter]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounlist.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounlist;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        String temp = seperatelist.get(word);
        TreeSet<String> result = new TreeSet<String>();
        String[] id = temp.split(",");
        TreeSet<Integer> tempresult = new TreeSet<Integer>();
        for (String counter : id) {
            tempresult.add(Integer.parseInt(counter));
        }
        Set<Integer> descend = GraphHelper.descendants(hyp, tempresult);
        for (int counter : descend) {
            String tempidresult = idlist.get(counter);
            String[] idsplit = tempidresult.split(" ");
            for (String counter1 : idsplit) {
                result.add(counter1);
            }
        }
        return result;
    }
}
