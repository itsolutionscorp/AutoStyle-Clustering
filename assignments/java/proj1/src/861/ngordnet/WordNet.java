package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<Integer, HashSet<String>> map1;
    private HashMap<String, HashSet<Integer>> map2;
    private Digraph d;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In in = new In(synsetFilename);
        // construct a bidivied map to store synset and its id
        map1 = new HashMap<Integer, HashSet<String>>();
        map2 = new HashMap<String, HashSet<Integer>>();
        while (!in.isEmpty()) {
            // read the file by line
            String a = in.readLine();
            // read each synset, separate by comma
            String[] s = a.split(",");
            int id = Integer.parseInt(s[0]);
            String synset = s[1];
            // parse the word in synset
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(synset).useDelimiter(" ");
            HashSet<String> wordID = new HashSet<String>(); // for map1

            while (sc.hasNext()) {
                String word = sc.next();
                wordID.add(word); // add word in the set that from the same
                // synset
                HashSet<Integer> isWordinMap = map2.get(word);
                if (isWordinMap == null) {
                    HashSet<Integer> idArray = new HashSet<Integer>();
                    idArray.add(id);
                    map2.put(word, idArray);
                } else {
                    isWordinMap.add(id);
                    map2.put(word, isWordinMap);
                }
            }
            // input into the map 1: ID to synset
            map1.put(id, wordID);
        }
        // construct the hyponym hiraechy
        In inHypo = new In(hyponymFilename);

        d = new Digraph(map1.size());
        while (!inHypo.isEmpty()) {
            String b = inHypo.readLine();
            @SuppressWarnings("resource")
            Scanner scHypo = new Scanner(b).useDelimiter(",");
            int head = Integer.parseInt(scHypo.next());
            while (scHypo.hasNext()) {
                int kid = Integer.parseInt(scHypo.next());
                d.addEdge(head, kid);
            }
        }
    }

    public boolean isNoun(String noun) {
        return map2.containsKey(noun);
    }

    public Set<String> nouns() {
        return map2.keySet();
    }

    public Set<String> hyponyms(String word) {
        HashSet<Integer> id = new HashSet<>();
        id = map2.get(word); // get all IDs of this word
        // get all hyponyms' ID of this word
        Set<Integer> hypoID = new TreeSet<Integer>();
        hypoID = GraphHelper.descendants(d, id);

        // mapping hyponyms' ID to word
        Set<String> hypo = new HashSet<String>();
        for (Integer i : hypoID) {
            for (String s : map1.get(i)) {
                hypo.add(s);
            }
        }
        return hypo;
    }
}
