package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Arrays;

public class WordNet {
    private String synFile;
    private String hypFile;
    private Digraph g;
    private Set<String> hypSet;
    private In allsyns;
    private boolean isDone = false;
    private int at;
    private HashMap<Integer, String[]> syns = new HashMap<Integer, String[]>();
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synFile = synsetFilename; hypFile = hyponymFilename;
        In syn = new In(synsetFilename); 
        In hyp = new In(hyponymFilename); 
        In hypIter = new In(hyponymFilename);
        final int i = 1234567890; int temped = 0; int max = 0;
        char[] chars = String.valueOf(i).toCharArray();
        boolean prev = false; boolean in = false;
        String temp = "";
        while (!syn.isEmpty()) {
            String line = syn.readLine();
            String[] split = line.split(",");
            String[] words = split[1].split(" ");
            syns.put(Integer.parseInt(split[0]), words);
        }
        g = new Digraph(syns.size());
        while (!hyp.isEmpty()) {
            String line = hyp.readLine();
            String[] split = line.split(",");
            int lead = Integer.parseInt(split[0]);
            String[] hyps = Arrays.copyOfRange(split, 1, split.length);
            for (String s : hyps) {
                g.addEdge(lead, Integer.parseInt(s));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer key : syns.keySet()) {
            if (Arrays.asList(syns.get(key)).contains(noun)) {
                return true;
            }
        }
        return false;
    }
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new TreeSet<String>();
        for (Integer key : syns.keySet()) {
            for (String s : syns.get(key)) {
                nounSet.add(s);
            }
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> answer = new TreeSet<String>();
        Set<Integer> ids = new TreeSet<Integer>();
        for (Integer key : syns.keySet()) {
            if (Arrays.asList(syns.get(key)).contains(word)) {
                ids.add(key);
            }
        }
        Set<Integer> wordHyps = GraphHelper.descendants(g, ids);
        Iterator<Integer> read = wordHyps.iterator();
        while (read.hasNext()) {
            int now = read.next();
            for (String s : syns.get(now)) {
                answer.add(s);
            }
        }
        return answer;
    }
}
