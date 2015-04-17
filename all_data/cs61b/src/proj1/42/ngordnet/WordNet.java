package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;

/** Provides means to analyze synset and hyponym data. 
 *  @author Matthew Kozak
 */
public class WordNet {
    private Digraph relationships;
    private int len = 0;
    private Set<String> nounSet = new HashSet<String>();
    private Map<Integer, Set<String>> nounMap = new HashMap<Integer, Set<String>>();
    private Map<String, Set<Integer>> revNounMap = new HashMap<String, Set<Integer>>();
    private Map<Set<String>, Set<Integer>> hypMap = new HashMap<Set<String>, Set<Integer>>();
    private In synImport;
    private In hypImport;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synImport = new In(synsetFilename);
        hypImport = new In(hyponymFilename);
        setSyn();
        relationships = new Digraph(len);
        setHyp();
    }

    private void setSyn() {
        while (!synImport.isEmpty()) {
            String synTemp = synImport.readLine();
            String[] idNoun = synTemp.split(",");
            Set<String> tempNounSet = new HashSet<String>();
            Set<Integer> tempIDSet = new HashSet<Integer>();
            int id = Integer.parseInt(idNoun[0]);
            String[] nounIter = idNoun[1].split(" ");
            for (int i = 0; i < nounIter.length; i++) {
                Set<Integer> tempTempIDSet = new HashSet<Integer>();
                nounSet.add(nounIter[i]);
                tempNounSet.add(nounIter[i]);
                if (revNounMap.containsKey(nounIter[i])) {
                    tempIDSet = new HashSet<Integer>();
                    tempTempIDSet = new HashSet<Integer>();
                    tempTempIDSet = revNounMap.get(nounIter[i]);
                    tempTempIDSet.add(id);
                    revNounMap.put(nounIter[i], tempTempIDSet);
                    tempIDSet = new HashSet<Integer>();
                    tempTempIDSet = new HashSet<Integer>();
                } else {
                    tempTempIDSet = new HashSet<Integer>();
                    tempIDSet.add(id);
                    revNounMap.put(nounIter[i], tempIDSet);
                    tempIDSet = new HashSet<Integer>();
                }
            }
            nounMap.put(id, tempNounSet);
            len += 1;
        }
    }


    private void setHyp() {
        while (!hypImport.isEmpty()) {
            Set<Integer> temp = new TreeSet<Integer>();
            String hypTemp = hypImport.readLine();
            String[] idHyp = hypTemp.split(",");
            int hasHyp = Integer.parseInt(idHyp[0]);
            int[] hypIter = new int[idHyp.length];
            for (int i = 1; i < idHyp.length; i++) {
                hypIter[i] = Integer.parseInt(idHyp[i]);
                temp.add(hypIter[i]);
                relationships.addEdge(hasHyp, hypIter[i]);
            }
            hypMap.put(nounMap.get(hasHyp), temp);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> interesting = new HashSet<Integer>();
        Set<Set<String>> tempSet = new HashSet<Set<String>>();
        Set<String> returnSet = new HashSet<String>();
        try {
            interesting = GraphHelper.descendants(relationships, revNounMap.get(word));
        } catch (NullPointerException e) {
            return returnSet;
        }
        Iterator<Integer> iter = interesting.iterator();
        while (iter.hasNext()) {
            Object storedVal = iter.next();
            tempSet.add(nounMap.get((int) storedVal));
            Iterator<Set<String>> iter2 = tempSet.iterator();
            while (iter2.hasNext()) {
                Object storedString = iter2.next();
                Iterator<String> iter3 = ((Set<String>) storedString).iterator();
                while (iter3.hasNext()) {
                    returnSet.add(iter3.next());
                }
            }
        }
        return returnSet;
    }
}
