package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private Digraph g;
    private HashMap<Integer, HashMap<String, String>> synid;
    private HashMap<String, Set<Integer>> wdToIds;
    private Set<String> allnouns = new TreeSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        synid = new HashMap<Integer, HashMap<String, String>>();
        In synsetfile = new In(synsetFilename);
        wdToIds = new HashMap<String, Set<Integer>>();
        String[] splits;
        while (synsetfile.hasNextLine()) {
            splits = synsetfile.readLine().split(",");
            String expl = splits[2];
            int num = Integer.parseInt(splits[0]);
            HashMap<String, String> exp = new HashMap<String, String>();
            String[] wordname = splits[1].split(" ");
            for (int i = 0; i < wordname.length; i++) {
                exp.put(wordname[i], expl);
                if (wdToIds.containsKey(wordname[i])) {
                    wdToIds.get(wordname[i]).add(num);
                } else {
                    Set<Integer> newid = new TreeSet<Integer>();
                    newid.add(num);
                    wdToIds.put(wordname[i], newid);
                }
            }
            synid.put(num, exp);
        }
        In hyponymfile = new In(hyponymFilename);
        In synsetlength = new In(synsetFilename);
        int linenum = 0;
        while (synsetlength.hasNextLine()) {
            linenum = linenum + 1;
            synsetlength.readLine();
        }
        g = new Digraph(linenum);
        while (hyponymfile.hasNextLine()) {
            String[] hyposplits = hyponymfile.readLine().split(",");
            int[] ids = new int[hyposplits.length];
            for (int i = 0; i < ids.length; i++) {
                int wdID = Integer.parseInt(hyposplits[i]);
                ids[i] = wdID;
            }
            for (int i = 1; i < ids.length; i++) {
                g.addEdge(ids[0], ids[i]);
            }
        }
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        for (HashMap<String, String> map : synid.values()) {
            allnouns.addAll(map.keySet());
        }
        return allnouns;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> wordID = wdToIds.get(word);
        Set<Integer> hypoindex = new TreeSet<Integer>();
        for (Integer i : wordID) {
            hypoindex.add(i);
        }
        Set<Integer> ansInt = GraphHelper.descendants(g, hypoindex);
        Set<String> ans = new TreeSet<String>();
        for (Integer i : ansInt) {
            Set<String> keywd = synid.get(i).keySet();
            ans.addAll(keywd);
        }
        return ans;
    }
}
