package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.HashSet;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private In syn;
    private In syn2;
    private In hyp;
    private String sAll;
    private String[] synevery;
    private Set<String> nouns = new HashSet<String>();
    private int counter;
    private Map<Integer, String> hypMap;
    private Map<String, String> synMap = new HashMap<String, String>();
    private Map<Integer, String> synMap2;
    private Digraph hyD;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        readSyn(synsetFilename);
        readHyp(hyponymFilename);
    } 
    private void readSyn(String synsetFilename) {
        syn = new In(synsetFilename);
        synMap2 = new HashMap<Integer, String>();
        while (syn.hasNextLine()) {
            String s1 = syn.readLine();
            String[] stuff = s1.split(",");
            int num = Integer.parseInt(stuff[0]);
            synMap2.put(num, stuff[1]);
            String[] f = stuff[1].split(" ");
            if (f.length > 0) {
                for (int y = 0; y < f.length; y++) {
                    nouns.add(f[y]);
                    if (synMap.get(f[y]) != null) {
                        String bee = synMap.get(f[y]);
                        synMap.put(f[y], bee + " " + stuff[0]);
                    } else {
                        synMap.put(f[y], stuff[0]);
                    }
                }
            }
        }
    }
    /* http://blog.csdn.net/liuweiran900217/article/details/22603325 
    for help with digraph */
    private void readHyp(String hyponymFilename) { 
        hyp = new In(hyponymFilename);
        hypMap = new HashMap<Integer, String>();
        In hyp2 = new In(hyponymFilename);
        hyD = new Digraph(synMap.size());
        counter = 0;
        while (hyp.hasNextLine()) {
            String line = hyp.readLine();
            String[] split = line.split(",");
            int in = Integer.parseInt(split[0]);
            counter += split.length;
            String all = "";
            for (int i = 1; i < split.length; i++) {
                all = all + split[i] + ",";
            }
            hypMap.put(in, all);
            //parse int only on first integer of line
            //then store the rest in a map or array
        }
        while (hyp2.hasNextLine()) {
            String a = hyp2.readLine();
            String[] b = a.split(",");
            int c = Integer.parseInt(b[0]);
            for (int d = 1; d < b.length; d++) {
                int e = Integer.parseInt(b[d]);
                hyD.addEdge(c, e);
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
        Set<String> hypos = new HashSet<String>();
        String a = synMap.get(word);
        if (synMap.containsKey(word)) {
            String[] mul = a.split(" ");
            if (mul.length > 0) { // multiple synsets
                hypos.add(word);
                Set<Integer> des = new TreeSet<Integer>();
                for (int b = 0; b < mul.length; b++) {
                    int ba = Integer.parseInt(mul[b]);
                    String da = synMap2.get(ba);
                    String[] fa = da.split(" "); //get synonyms
                    if (fa.length > 0) {
                        for (int pi = 0; pi < fa.length; pi++) {
                            hypos.add(fa[pi]);
                        }
                    } else {
                        hypos.add(da);
                    }
                    des.add(ba); //get all synsets
                }
                Set<Integer> ds = new HashSet<Integer>();
                ds = GraphHelper.descendants(hyD, des);
                Iterator<Integer> ba = ds.iterator();
                while (ba.hasNext()) {
                    int x = ba.next();
                    String z = synMap2.get(x);
                    String[] v = z.split(" ");
                    if (v.length > 0) {
                        for (int f = 0; f < v.length; f++) {
                            hypos.add(v[f]);
                        }
                    } else {
                        hypos.add(z);
                    }
                }
            } else { //1synset
                hypos.add(word);
                Set<Integer> vs = new HashSet<Integer>();
                int jh = Integer.parseInt(a);
                Set<Integer> gh = new TreeSet<Integer>();
                gh.add(jh);
                vs = GraphHelper.descendants(hyD, gh);
                Iterator<Integer> jhk = vs.iterator();
                while (jhk.hasNext()) {
                    int g = jhk.next();
                    String m = synMap2.get(g);
                    String[] j = m.split(" ");
                    if (j.length > 0) {
                        for (int k = 0; k < j.length; k++) {
                            hypos.add(j[k]);
                        }
                    } else {
                        hypos.add(m);
                    }
                }
            }
        } 
        return hypos;
    }
}
