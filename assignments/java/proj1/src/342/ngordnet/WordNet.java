package ngordnet;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph net;
    private HashMap<Integer, Set<String>> synsets;
    private HashMap<String, Set<Integer>> nouns;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new HashMap<Integer, Set<String>>();
        nouns = new HashMap<String, Set<Integer>>();

        String line = "";
        String[] words = {};
        String[] syns = {};
        In in = new In(new File(synsetFilename));

        while (in.hasNextLine()) {
            Set<String> synonyms = new HashSet<String>();
            line = in.readLine();
            words = line.split(",");
            syns = words[1].split(" ");
            for (String s : syns) {
                synonyms.add(s);
                if (nouns.keySet().contains(s)) {
                    nouns.get(s).add(Integer.parseInt(words[0]));
                } else {
                    Set<Integer> set = new HashSet<Integer>();
                    set.add(Integer.parseInt(words[0]));
                    nouns.put(s, set);
                }
            }
            synsets.put(Integer.parseInt(words[0]), synonyms);
        }
        in.close();

        net = new Digraph(nouns.size());

        String[] nums = {};
        in = new In(new File(hyponymFilename));
        while (in.hasNextLine()) {
            line = in.readLine();
            nums = line.split(",");
            int synsetID = Integer.parseInt(nums[0]);
            for (String s : nums) {
                int hyp = Integer.parseInt(s);
                net.addEdge(synsetID, hyp);
            }
        }
    }

    public boolean isNoun(String word) {
        return this.nouns().contains(word);
    }

    public Set<String> nouns() {
        return nouns.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> nums = nouns.get(word);
        Set<Integer> indices = GraphHelper.descendants(net, nums);
        Set<String> hyp = new HashSet<String>();
        for (int i : indices) {
            Set<String> set = synsets.get(i);
            for (String s : set) {
                hyp.add(s);
            }
        }
        return hyp;
    }

}
