package ngordnet;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;

//received assistance from Annalise Hurst.

public class WordNet {

    private Digraph gHyponyms;
    private List<TreeSet<String>> synslist = new ArrayList<TreeSet<String>>();
    private Set<String> totalSyn = new TreeSet<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        int size = 0;

        while (syn.hasNextLine()) {
            size += 1;
            String line2 = syn.readLine();
            String[] rawTokens2 = line2.split(",");
            Integer synKey = Integer.parseInt(rawTokens2[0]);

            String synonyms = rawTokens2[1];
            String[] tokens2 = synonyms.split(" ");

            TreeSet<String> synsval = new TreeSet<String>();
            for (String t : tokens2) {
                synsval.add(t);
                totalSyn.add(t);
            }
            synslist.add(synKey, synsval);
        }

        gHyponyms = new Digraph(size);
        syn = new In(hyponymFilename);

        while (syn.hasNextLine()) {
            String line1 = syn.readLine();
            String[] rawTokens1 = line1.split(",");
            Integer hypKey = Integer.parseInt(rawTokens1[0]);
            String[] tokens1 = new String[rawTokens1.length - 1];
            System.arraycopy(rawTokens1, 1, tokens1, 0, rawTokens1.length - 1);

            for (String t : tokens1) {
                Integer temp = Integer.parseInt(t);
                gHyponyms.addEdge(hypKey, temp);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (totalSyn.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() { 
        return totalSyn;       
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> related = new TreeSet<String>();
        related.add(word);

        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < synslist.size(); i++) {
            if (synslist.get(i).contains(word)) {
                indices.add(i);
                related.addAll(synslist.get(i));
            }       
        }

        TreeSet<Integer> temp = new TreeSet<Integer>();
        for (int ind : indices) {
            temp.add(ind);
        }

        Set<Integer> hyposet = GraphHelper.descendants(gHyponyms, temp);
        Iterator<Integer> hypoiter = hyposet.iterator();

        while (hypoiter.hasNext()) {
            int index = hypoiter.next();
            related.addAll(synslist.get(index));
        }
        return related; 
    }
}
