
package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
/* @author: Ami Bigit*/
public class WordNet {
    private Map<Integer, Set<String>> mSyn;
    private Map<String, Set<Integer>> mWord;
    private Set<String> allWords = new HashSet<String>();
    private Set<String> words = new HashSet<String>();
    private Digraph d;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        Integer tIntK;
        String tStrK;
        Set<Integer> iTS;
        Set<String> sTS;
        String[] subStrS;
        String[] subSubStrS;
        int sSubSubStrS;
        String[] subStrH;
        int sSubStrH;
        Integer[] tIntA;
        String[] inSyn = new In(synsetFilename).readAllLines();
        String[] inHyp = new In(hyponymFilename).readAllLines();
        int sSyn = inSyn.length;
        int sHyp = inHyp.length;
        mSyn = new HashMap<Integer, Set<String>>(sSyn);
        for (int i = 0; i < sSyn; i++) {
            subStrS = inSyn[i].split(",");
            sTS = new HashSet<String>();
            tIntK = Integer.valueOf(subStrS[0]);
            subSubStrS = subStrS[1].split(" ");
            sSubSubStrS = subSubStrS.length;
            for (int j = 0; j < sSubSubStrS; j++) {
                sTS.add(subSubStrS[j]);
                words.add(subSubStrS[j]);
            }
            mSyn.put(tIntK, sTS);
        }
        int numWords = words.size();
        mWord = new HashMap<String, Set<Integer>>(numWords);
        d = new Digraph(sSyn);



        for (int i = 0; i < sHyp; i++) {
            subStrH = inHyp[i].split(",");
            sSubStrH = subStrH.length;
            Set<String> twords = mSyn.get(Integer.valueOf(subStrH[0]));
            int stwords = twords.size();
            Iterator<String> itSet = twords.iterator();
            int ii = 0;
            String[] tempwords = new String[stwords];

            for (int j = 1; j < sSubStrH; j++) {
                d.addEdge(Integer.valueOf(subStrH[0]), Integer.valueOf(subStrH[j]));
            }
        } 

        Set<Integer> allKeys = new HashSet<Integer>();
        allKeys = mSyn.keySet();
        Iterator<Integer> allKeysIt = allKeys.iterator();
        for (int i = 0; i < sSyn; i++) {
            int tempKey = allKeysIt.next();
            Iterator<String> wordIt = mSyn.get(tempKey).iterator();
            while (wordIt.hasNext()) {
                String tempWord = wordIt.next();
                if (!mWord.containsKey(tempWord)) {
                    mWord.put(tempWord, new HashSet<Integer>());
                }
                mWord.get(tempWord).add(tempKey);
            }

        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return words.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return words;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> toRet = new HashSet<String>();
        if (!mWord.containsKey(word)) {
            return null;
        } else {
            Set<Integer> wSynsets = new HashSet<Integer>();
            wSynsets = mWord.get(word);
            int numofSynsets = wSynsets.size();
            Iterator<Integer> idSynset = wSynsets.iterator();
            Integer[] synID = new Integer[numofSynsets];
            int i = 0;
            while (idSynset.hasNext()) {
                synID[i] = idSynset.next();
                Iterator<String> tSynset = mSyn.get(synID[i]).iterator();
                while (tSynset.hasNext()) {
                    toRet.add(tSynset.next());
                }
                i++;
            }
            Set<Integer> desc = new HashSet<Integer>();
            desc = GraphHelper.descendants(d, wSynsets);
            int descS = desc.size();
            Iterator<Integer> descIt = desc.iterator();
            Integer[] synIdDesc = new Integer[descS];

            for (i = 0; i < descS; i++) {
                synIdDesc[i] = descIt.next();
                Iterator<String> tSynset2 = mSyn.get(synIdDesc[i]).iterator();
                while (tSynset2.hasNext()) {
                    toRet.add(tSynset2.next());
                }
            }

        }
        return toRet;
    }

}
