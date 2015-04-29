package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordNet {

    private In inSyn;
    private In inHyp;
    private String strSyn;
    private String strHyp;
    private ArrayList parsedSyn;
    private HashMap<Integer, ArrayList> synsetMap;
    private HashMap<Integer, ArrayList> hyponymMap;
    private ArrayList test;
    private ArrayList tempKeys;
    private ArrayList tempValues;
    private ArrayList tempHKeys;
    private ArrayList tempHValues;
    private ArrayList allValues;
    private Digraph g;

    /* Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        inSyn = new In(synsetFilename);
        inHyp = new In(hyponymFilename);
        parsedSyn = new ArrayList();
        tempHKeys = new ArrayList();
        tempHValues = new ArrayList();
        hyponymMap = new HashMap<Integer, ArrayList>();
        ArrayList tempor = new ArrayList();

        while (inHyp.hasNextLine()) {
            tempor = new ArrayList();
            strHyp = inHyp.readLine();
            String[] temp2 = strHyp.split(",");
            tempHKeys.add(temp2[0]);
            for (int i = 1; i < temp2.length; i++) {
                tempor.add(temp2[i]);
            }
            tempHValues.add(tempor);
        }
        synsetMap = new HashMap<Integer, ArrayList>();
        allValues = new ArrayList();

        while (inSyn.hasNextLine()) {
            strSyn = inSyn.readLine();
            String[] tempchange = strSyn.split(",");
            String[] everythingBut = new String[tempchange.length - 1];
            System.arraycopy(tempchange, 0, everythingBut, 0, tempchange.length - 1);
            String[] keyvals = everythingBut[1].split(" ");
            int val = Integer.parseInt(everythingBut[0]);
            ArrayList<String> reval = new ArrayList<String>();
            for (int i = 0; i < keyvals.length; i++) {
                reval.add(keyvals[i]);
                allValues.add(keyvals[i]);
            }
            synsetMap.put(val, reval);
        }
        Set xVal = synsetMap.keySet();
        tempKeys = new ArrayList(xVal);
        tempValues = new ArrayList();

        for (int i = 0; i < tempKeys.size(); i++) {
            tempValues.add(synsetMap.get(tempKeys.get(i)));
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (this.nouns().contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allnouns = new HashSet<String>();
        for (int i = 0; i < allValues.size(); i++) {
            if (!allnouns.contains((String) allValues.get(i))) {
                allnouns.add((String) allValues.get(i));
            }
        }
        return allnouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<Integer> allsynsets = new HashSet<Integer>();
        Set<String> finalresult = new HashSet<String>();
        for (int j = 0; j < tempValues.size(); j++) {
            ArrayList sub2 = (ArrayList) tempValues.get(j);
            for (int k = 0; k < sub2.size(); k++) {
                if (sub2.get(k).equals(word)) {
                    allsynsets.add((Integer) tempKeys.get(j));
                }
            }
        }
        g = new Digraph(tempKeys.size());
        for (int j = 0; j < tempHValues.size(); j++) {
            ArrayList sub = (ArrayList) tempHValues.get(j);
            for (int k = 0; k < sub.size(); k++) {
                g.addEdge(Integer.parseInt((String) tempHKeys.get(j)),
                        Integer.parseInt((String) sub.get(k)));
            }
        }
        Set<Integer> resultant = GraphHelper.descendants(g, allsynsets);
        List result = new ArrayList(resultant);
        for (int i = 0; i < result.size(); i++) {
            ArrayList sub3 = synsetMap.get(result.get(i));
            for (int j = 0; j < sub3.size(); j++) {
                if (!finalresult.contains(sub3.get(j))) {
                    finalresult.add((String) sub3.get(j));
                }
            }
        }
        return finalresult;
    }
}
