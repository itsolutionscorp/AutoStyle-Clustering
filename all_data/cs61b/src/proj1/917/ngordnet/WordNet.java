package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.FileNotFoundException;
import java.io.File;

public class WordNet {
    private TreeMap<String, HashSet<Integer>> nouns;
    private TreeMap<Integer, String> dic;
    private Digraph relation;

    public WordNet(String synsetF, String hyponymF) {
        int key, count, v, w;
        String[] token;
        String value = "";
        HashSet<Integer> temp;
        try {
            Scanner sc1 = new Scanner(new File(synsetF)).useDelimiter("\n");
            Scanner sc2 = new Scanner(new File(hyponymF)).useDelimiter("\n");
            nouns = new TreeMap<String, HashSet<Integer>>();
            dic = new TreeMap<Integer, String>();
            count = 0;
            while (sc1.hasNext()) { // Fill in NOUNS set and DIC TreeMap.
                token = sc1.next().split(",");
                key = Integer.parseInt(token[0]);
                value = token[1];
                for (String s : value.split(" ")) {
                    if (nouns.containsKey(s)) {
                        nouns.get(s).add(key);
                    } else {
                        temp = new HashSet<Integer>();
                        temp.add(key);
                        nouns.put(s, temp);
                    }
                }
                dic.put(key, value);
                count++;
            }

            relation = new Digraph(count);
            while (sc2.hasNext()) { // Set up RELATION digraph.
                token = sc2.next().split(",");
                v = Integer.parseInt(token[0]);
                for (int i = 1; i < token.length; i++) {
                    w = Integer.parseInt(token[i]);
                    relation.addEdge(v, w);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File open failed.");
        }
    }

    public boolean isNoun(String n) {
        return nouns.containsKey(n);
    }

    public Set<String> nouns() {
        return nouns.keySet();
    }

    public Set<String> hyponyms(String word) {
        String synsets;
        Set<String> copy = new HashSet<String>();
        Set<Integer> temp = new HashSet<Integer>();
        if (nouns.containsKey(word)) {
            temp = nouns.get(word);
        }

        temp = GraphHelper.descendants(relation, temp);
        for (Integer d : temp) {
            synsets = dic.get(d);
            for (String s : synsets.split(" ")) {
                copy.add(s);
            }
        }

        return copy;
    }
}
