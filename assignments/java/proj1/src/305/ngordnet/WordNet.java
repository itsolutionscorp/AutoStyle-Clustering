package ngordnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, ArrayList<String>> synsetsInts;
    private Map<String, ArrayList<Integer>> synsetsStrs;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetsInts = new HashMap<Integer, ArrayList<String>>();
        synsetsStrs = new HashMap<String, ArrayList<Integer>>();
        In syn = new In(synsetFilename);

        while (syn.hasNextLine()) {
            String[] synLine = syn.readLine().trim().split(",");
            int key = Integer.parseInt(synLine[0]);
            ArrayList<String> names = new ArrayList<String>(// find the names
                    Arrays.asList(synLine[1].split(" ")));
            synsetsInts.put(key, names); // key=int, value=names

            for (String s : names) {
                if (synsetsStrs.get(s) != null) {
                    synsetsStrs.get(s).add(key);
                } else {
                    ArrayList<Integer> sKeyVals = new ArrayList<Integer>();
                    sKeyVals.add(key);
                    synsetsStrs.put(s, sKeyVals);
                }
            }
        }

        hyponyms = new Digraph(synsetsInts.size());
        In hypo = new In(hyponymFilename);
        while (hypo.hasNextLine()) {
            String[] hypoLine = hypo.readLine().trim().split(",");
            for (int i = 1; i < hypoLine.length; i++) {
                hyponyms.addEdge(Integer.parseInt(hypoLine[0]),
                        Integer.parseInt(hypoLine[i]));
            }
        }
    }

    public Set<String> hyponyms(String word) {
        Set<String> ret = new HashSet<String>();
        ArrayList<Integer> wordKeys = synsetsStrs.get(word);
        for (int wordKey : wordKeys) {
            for (String s : synsetsInts.get(wordKey)) {
                if (!ret.contains(s)) {
                    ret.add(s);
                }
            }

            Set<Integer> wordKeyS = new TreeSet<Integer>();
            wordKeyS.add(wordKey);
            for (int i : GraphHelper.descendants(hyponyms, wordKeyS)) {
                for (String s : synsetsInts.get(i)) {
                    if (!ret.contains(s)) {
                        ret.add(s);
                    }
                }
            }
        }
        return ret;
    }

    public boolean isNoun(String noun) {
        return synsetsStrs.get(noun) != null;
    }

    public Set<String> nouns() {
        return synsetsStrs.keySet();
    }
}
