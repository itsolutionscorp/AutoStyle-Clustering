package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

public class WordNet {
    private Digraph graph;
    private ArrayList<Set<String>> idSyn = new ArrayList<Set<String>>();
    private HashMap<Integer, Set<Integer>> synHyp = new HashMap<Integer, Set<Integer>>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        String[] fields;
        Set<String> syns;
        In readSyn = new In(synsetFilename);
        In readHyp = new In(hyponymFilename);

        while (readSyn.hasNextLine()) {
            fields = readSyn.readLine().split(",")[1].split(" ");
            syns = new HashSet<String>(Arrays.asList(fields));
            idSyn.add(syns);
        }

        graph = new Digraph(idSyn.size());

        Set<Integer> hyps;
        Integer hyper;
        Integer hypo;

        while (readHyp.hasNextLine()) {
            fields = readHyp.readLine().split(",");
            hyps = new HashSet<Integer>();
            hyper = Integer.parseInt(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                hypo = Integer.parseInt(fields[i]);
                hyps.add(hypo);
                graph.addEdge(hyper, hypo);
            }
            if (synHyp.containsKey(hyper)) {
                synHyp.get(hyper).addAll(hyps);
            } else {
                synHyp.put(hyper, hyps);
            }
        }
    }

    public boolean isNoun(String noun) {
        for (Set<String> syn : idSyn) {
            if (syn.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> ret = new HashSet<String>();
        for (Set<String> syn : idSyn) {
            ret.addAll(syn);
        }
        return ret;
    }

    public Set<String> hyponyms(String word) {
        Set<String> ret = new HashSet<String>();
        Set<String> target;
        for (int i = 0; i < idSyn.size(); i++) {
            if (idSyn.get(i).contains(word)) {
                target = new HashSet<String>();
                target.addAll(idSyn.get(i));
                ret.addAll(findHyponyms(i, target));
            }
        }
        return ret;
    }

    private Set<String> findHyponyms(Integer id, Set<String> syn) {
        if (synHyp.containsKey(id)) {
            Set<String> next;
            for (Integer hyp : synHyp.get(id)) {
                next = new HashSet<String>();
                next.addAll(idSyn.get(hyp));
                syn.addAll(findHyponyms(hyp, next));
            }
        }
        return syn;
    }
}
