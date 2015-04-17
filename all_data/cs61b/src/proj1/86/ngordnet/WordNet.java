package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;

/**
 * Created by tommytai on 2/26/15.
 */

public class WordNet {

    private Digraph digraph; //used to check for hyponyms
    private String syn;
    private String hyp;
    private In synin;
    private In hypin;
    private Set<String> synset; // set of all strings
    private Map<Integer, ArrayList<String>> synmap;
    private Map<ArrayList<String>, ArrayList<Integer>> synmap2;
    private Set<ArrayList<String>> stuffint;
    private Map<Integer, Set<Integer>> hypmap; // map of hyp
    private int vertices;
    private Set<Integer> hypsetID;



    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     */
    public WordNet(String synsetFilename, String hyponymFilename) {

        this.syn = synsetFilename;
        this.hyp = hyponymFilename;
        this.synset = new HashSet<String>(); //set of all strings
        this.synmap = new HashMap<Integer, ArrayList<String>>();
        this.synmap2 = new HashMap<ArrayList<String>, ArrayList<Integer>>();
        this.stuffint = new HashSet<ArrayList<String>>();

        synin = new In(syn);
        hypin = new In(hyp);

        int check = 0;
        while (synin.hasNextLine()) {
            ArrayList<String> wrdlst = new ArrayList<String>();
            vertices++;
            String syntempstr = synin.readLine();
            String[] syntokens = syntempstr.split(",");
            int tempint = Integer.parseInt(syntokens[0]);
            if (syntokens[1].contains(" ")) {
                String[] syntokens2 = syntokens[1].split(" ");
                for (int k = 0; k < syntokens2.length; k++) {
                    wrdlst.add(syntokens2[k]);
                    synset.add(syntokens2[k]);
                }
                if (!stuffint.contains(wrdlst)) {
                    stuffint.add(wrdlst);
                    ArrayList<Integer> arrstuff = new ArrayList<Integer>();
                    arrstuff.add(tempint);
                    synmap2.put(wrdlst, arrstuff);
                    synmap.put(tempint, wrdlst);
                } else {
                    ArrayList<Integer> booboo = synmap2.get(wrdlst);
                    booboo.add(tempint);
                    synmap2.put(wrdlst, booboo);
                    synmap.put(tempint, wrdlst);
                }
            } else {
                wrdlst.add(syntokens[1]);
                synset.add(syntokens[1]);
                if (!stuffint.contains(wrdlst)) {
                    stuffint.add(wrdlst);
                    ArrayList<Integer> arrstuff = new ArrayList<Integer>();
                    arrstuff.add(tempint);
                    synmap2.put(wrdlst, arrstuff);
                    synmap.put(tempint, wrdlst);
                } else {
                    ArrayList<Integer> booboo = synmap2.get(wrdlst);
                    booboo.add(tempint);
                    synmap2.put(wrdlst, booboo);
                    synmap.put(tempint, wrdlst);
                }
            }
        }

        this.digraph = new Digraph(vertices);

        while (hypin.hasNextLine()) {
            String hyptempstr = hypin.readLine();
            String[] hyptokens = hyptempstr.split(",");
            int first = (Integer.parseInt(hyptokens[0]));
            Set<Integer> hypset = new HashSet<Integer>();
            for (int i = 1; i < hyptokens.length; i++) {
                int tempint = (Integer.parseInt(hyptokens[i]));
                digraph.addEdge(first, tempint);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {

        return synset.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        return synset;
    }

    /**
     * Returns the set of all hyponyms of WORD including WORD itself.
     */
    public Set<String> hyponyms(String word) {

        Set<String> returnset = new HashSet<String>();
        returnset.add(word);
        Set<ArrayList<String>> checker = synmap2.keySet();
        ArrayList<Integer> hehe = new ArrayList<Integer>();

        for (ArrayList<String> x: checker) {
            if (x.contains(word)) {
                Set<String> lool = new HashSet<String>(x);
                returnset.addAll(lool);
                hehe.addAll(synmap2.get(x));
            }
        }

        Set<Integer> loope = new HashSet<Integer>(hehe);
        Set<Integer> intSet = GraphHelper.descendants(digraph, loope);
        Integer[] intarr = intSet.toArray(new Integer[intSet.size()]);
        for (int ner: intarr) {
            Set<String> loo = new HashSet<String>(synmap.get(ner));
            returnset.addAll(loo);
        }
        return returnset;
    }
}
