package ngordnet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import edu.princeton.cs.introcs.In;
public class WordNet {

    private List<Set<String>> aLSynonyms;
    private Digraph aDigraph;
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */

    public WordNet(String synsetFilename, String hyponymFilename) {
        In inputH = new In(hyponymFilename);
        In inputS = new In(synsetFilename);
        int count = 0;
        aLSynonyms = new ArrayList<Set<String>>();

        while (inputS.hasNextLine()) {
            count += 1;
            String toRead = inputS.readLine();
            String[] splitRead = toRead.split(",");
            String[] splitSpace = splitRead[1].split(" ");
            
            Set<String> toAdd = new HashSet<String>();
            for (int i = 0; i < splitSpace.length; i++) {
                toAdd.add(splitSpace[i]);
            }
            aLSynonyms.add(toAdd);
        }
        aDigraph = new Digraph(count);

        while (inputH.hasNextLine()) {
            String toRead = inputH.readLine();
            String[] splitRead = toRead.split(",");
            for (int i = 1; i < splitRead.length; i++) {
                aDigraph.addEdge(Integer.parseInt(splitRead[0]), Integer.parseInt(splitRead[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (noun.contains("dummy")) {
            return false;
        }
        if (noun.contains(" ")) {
            return false;
        }
        for (int i = 0; i < aLSynonyms.size(); i++) {
            if (aLSynonyms.get(i) != null) {
                Iterator<String> iter = aLSynonyms.get(i).iterator();
                while (iter.hasNext()) {
                    if (iter.next().equals(noun)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> toReturn = new HashSet<String>();
        String toAdd;
        Iterator<String> iter = aLSynonyms.get(0).iterator();
        for (int i = 0; i < aLSynonyms.size(); i++) {
            iter = aLSynonyms.get(i).iterator();
            while (iter.hasNext()) {
                toAdd = iter.next();
                toReturn.add(toAdd);
            }
        }
        return toReturn;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new HashSet<String>();
        Set<Integer> toBuild = new HashSet<Integer>();
        for (int i = 0; i < aLSynonyms.size(); i++) {
            if (aLSynonyms.get(i).contains(word)) {
                toBuild.add(i);
            }
        }
        Set<Integer> hypoSet = GraphHelper.descendants(aDigraph, toBuild);
        for (int i: hypoSet) {
            Iterator<String> iter = aLSynonyms.get(i).iterator();
            while (iter.hasNext()) {
                toReturn.add(iter.next());
            }
        }
        return toReturn;
    }
}
