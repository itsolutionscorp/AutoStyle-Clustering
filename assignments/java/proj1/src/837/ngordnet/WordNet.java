package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private In sf;
    private In hf;
    private Digraph relation;
    private ArrayList<Set<String>> nouns;
    private int size;

    public WordNet(String synsetFilename, String hyponymFilename) {
        this.sf = new In(synsetFilename);
        this.hf = new In(hyponymFilename);
        this.size = 0;
        nouns = new ArrayList<Set<String>>();
        String line = null;
        while ((line = sf.readLine()) != null) {
            String [] currline = line.split(",");
            int index = Integer.parseInt(currline[0]);
            String [] currnoun = currline[1].split(" ");
            Set<String> currnounset = new HashSet<String>();
            for (String a : currnoun) {
                currnounset.add(a);
            }
            nouns.add(currnounset);
            size += 1;
        }
        String hfline = null;
        relation = new Digraph(size);
        while ((hfline = hf.readLine()) != null) {
            String [] currline = hfline.split(",");
            int parent = Integer.parseInt(currline[0]);
            for (int i = 1; i < currline.length; i++) {
                relation.addEdge(parent, Integer.parseInt(currline[i]));
            }
        }
        this.nouns = nouns;
        this.relation = relation;
    }

    public boolean isNoun(String noun) {
        for (Set<String> a : nouns) {
            if (a.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> a = new HashSet<String>();
        for (Set<String> b : nouns) {
            for (String c : b) {
                a.add(c);
            }
        }
        return a;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> a = new HashSet<Integer>();
        for (int i = 0; i < nouns.size(); i++) {
            if (nouns.get(i).contains(word)) {
                a.add(i);
            }
        }
        Set<Integer> b = GraphHelper.descendants(relation, a);
        Set<String> c = new HashSet<String>();
        for (Integer d : b) {
            for (String e : nouns.get(d)) {
                c.add(e);
            }
        }
        c.add(word);
        return c;
    }
}
