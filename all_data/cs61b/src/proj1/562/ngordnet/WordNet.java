package ngordnet;
import edu.princeton.cs.algs4.Digraph;

import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private ArrayList<HashSet<String>> catalog = new ArrayList<HashSet<String>>();
    private HashMap<String, HashSet<Integer>> directory = new HashMap<String, HashSet<Integer>>();
    private HashSet<String> allNouns = new HashSet<String>();
    private Digraph dieMyself;


    public Set<String> hyponyms(String s) {
        Set<String> dummy = new HashSet<String>();
        TreeSet<Integer> diagrph = new TreeSet<Integer>();

        for (int x: directory.get(s)) {
            diagrph.add(x);
            dummy.addAll(catalog.get(x));
        }
        for (int y: GraphHelper.descendants(dieMyself, diagrph)) {
            dummy.addAll(catalog.get(y));
        }


        return dummy;
    }


    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }


    public Set<String> nouns() {
        return allNouns;
    }

    public WordNet(String synHashSetFilename, String hyponymFilename) {

        In synHashSets = new In(synHashSetFilename);
        In hyponyms = new In(hyponymFilename);
        String[] agglomerate = synHashSets.readAllLines();
        String[] aggregate = hyponyms.readAllLines();
        int firstComma = 0;
        int secondComma = 0;
        int number = 0;
        String words;
        HashSet<String> babyHashSet;

        for (String s: agglomerate) {
            babyHashSet = new HashSet<String>();
            firstComma = s.indexOf(',');
            secondComma = s.indexOf(',', firstComma + 1);
            number = Integer.parseInt(s.substring(0, firstComma));
            words = s.substring(firstComma + 1, secondComma);
            while (words.indexOf(' ') != -1) {
                babyHashSet.add(words.substring(0, words.indexOf(' ')));
                words = words.substring(words.indexOf(' ') + 1);
            }
            babyHashSet.add(words);
            catalog.add(number, babyHashSet);
            for (String word: babyHashSet) {
                if (directory.containsKey(word)) {
                    directory.get(word).add(number);
                } else {
                    HashSet<Integer> numholder = new HashSet<Integer>();
                    numholder.add(number);
                    directory.put(word, numholder);
                }
                allNouns.add(word);

            }

        }

        dieMyself = new Digraph(catalog.size());
        String[] items;

        for (String s : aggregate) {
            items = new String[s.split(",").length - 1];
            System.arraycopy(s.split(","), 1, items, 0, s.split(",").length - 1);
            for (String num: items) {
                dieMyself.addEdge(Integer.parseInt(s.split(",")[0]), Integer.parseInt(num));
            }
        }

    }







}


