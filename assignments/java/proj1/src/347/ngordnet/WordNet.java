package ngordnet;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private In inSyn;
    private In inHyp;
    private int numWords;
    private Digraph g;
    private HashMap<Integer, HashSet<Integer>> nounAndHyps;
    private HashMap<Integer, HashSet<String>> wordsList;

    public WordNet(String synsetFilename, String hyponymFilename) {
        inSyn = new In(synsetFilename);
        inHyp = new In(hyponymFilename);
        numWords = 0;
        wordsList = new HashMap<Integer, HashSet<String>>();

        while (inSyn.hasNextLine()) {
            String line = inSyn.readLine();
            String[] rawTokens = line.split(",");
            String[] words = rawTokens[1].split(" ");
            HashSet<String> wordset = new HashSet<String>();
            for (String word : words) {
                wordset.add(word);
            }
            wordsList.put(Integer.parseInt(rawTokens[0]), wordset);
            numWords += wordset.size();
        }

        g = new Digraph(numWords);

        nounAndHyps = new HashMap<Integer, HashSet<Integer>>();

        while (inHyp.hasNextLine()) {
            String line = inHyp.readLine();
            String[] rawTokens = line.split(",");
            String wordIDstr = rawTokens[0];
            int wordID = Integer.parseInt(wordIDstr);
            String[] hypIDstr = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, hypIDstr, 0, rawTokens.length - 1);
            HashSet<Integer> hypIDset = new HashSet<Integer>();
            for (String hypID : hypIDstr) {
                hypIDset.add(Integer.parseInt(hypID));
            }
            nounAndHyps.put(wordID, hypIDset);

            for (int hypID : hypIDset) {
                g.addEdge(wordID, hypID);
            }
        }
    }

    public boolean isNoun(String noun) {
        HashSet<String> nouns = setNouns();
        if (nouns.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return setNouns();
    }

    private HashSet<String> setNouns() {
        Set<Integer> ids = wordsList.keySet();
        HashSet<String> setNouns = new HashSet<String>();
        for (Integer id : ids) {
            for (String word : wordsList.get(id)) {
                setNouns.add(word);
            }
        }
        return setNouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> synIDs = wordsList.keySet();
        Integer id;
        Set<Integer> idset = new HashSet<Integer>();
        for (Integer synID : synIDs) {
            for (String syn : wordsList.get(synID)) {
                if (syn.equals(word)) {
                    id = synID;
                    idset.add(id);
                }
            }
        }
        Set<Integer> hyponymIDs = GraphHelper.descendants(g, idset);
        Set<String> hyponymSet = new HashSet<String>();
        for (Integer hypoID : hyponymIDs) {
            for (String hypo : wordsList.get(hypoID)) {
                hyponymSet.add(hypo);
            }
        }
        return hyponymSet;
    }
}
