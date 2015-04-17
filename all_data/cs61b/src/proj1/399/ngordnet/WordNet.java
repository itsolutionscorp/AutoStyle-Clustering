package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.ArrayList;

public class WordNet {
    private Digraph graph;
    private Set<String> all;
    private Map<Integer, Set<String>> pairs;
    private Map<Set<String>, ArrayList<Integer>> reverse;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        all = new HashSet<String>();
        pairs = new HashMap<Integer, Set<String>>();
        reverse = new HashMap<Set<String>, ArrayList<Integer>>();
        try {
            Scanner in = new Scanner(new File(synsetFilename));
            while (in.hasNextLine()) {
                String[] entire = in.nextLine().split(",");
                int num = Integer.parseInt(entire[0]);
                String str = entire[1];
                String[] indiv = str.split(" ");
                Set<String> each = new HashSet<String>();
                for (int i = 0; i < indiv.length; i++) {
                    String temp = indiv[i];
                    each.add(temp);
                    all.add(temp);
                }
                pairs.put(num, each);
                //added line after this
                if (reverse.containsKey(each)) {
                    reverse.get(each).add(num);
                } else {
                    ArrayList<Integer> temporary = new ArrayList<Integer>();
                    temporary.add(num);
                    reverse.put(each, temporary);
                }
            }
            graph = new Digraph(pairs.size());
            Scanner sc = new Scanner(new File(hyponymFilename));
            while (sc.hasNextLine()) {
                String[] entire2 = sc.nextLine().split(",");
                int first = Integer.parseInt(entire2[0]);
                for (int i = 1; i < entire2.length; i++) {
                    int temp2 = Integer.parseInt(entire2[i]);
                    graph.addEdge(first, temp2);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return all.contains(noun);
    }   

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return all;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        Set<Integer> synsetNums = new TreeSet<Integer>();
        for (Set<String> k : reverse.keySet()) {
            if (k.contains(word)) {
                for (Integer j : reverse.get(k)) {
                    synsetNums.add(j);
                }
            }
        }
        if (synsetNums.isEmpty()) {
            return null;
        }
        Set<Integer> hyponymNums = GraphHelper.descendants(graph, synsetNums);
        Set<String> res = new HashSet<String>();
        for (Integer i : hyponymNums) {
            Set<String> hyps = pairs.get(i);
            for (String s : hyps) {
                if (!s.equals(word)) {
                    res.add(s);
                }
            }
        }
        res.add(word);
        return res;
    }
}
