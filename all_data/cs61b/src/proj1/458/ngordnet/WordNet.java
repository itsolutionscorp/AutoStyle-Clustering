package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private List<String> nums = new ArrayList<String>();
    private Set<String> words = new TreeSet<String>();
    private Digraph di;
    private Map<String, Set<String>> hypos = new TreeMap<String, Set<String>>();
    private Set<String> hyps = new TreeSet<String>();

    public WordNet(java.lang.String s, java.lang.String h) {
//        private List<Integer> helperDi(Digraph d, Integer i1) {
//            List<Integer> allhypoints = new ArrayList<Integer>();
//            allhypoints.add(i1)
//            Iterator<Integer> dit = di.adj(i1).iterator();
//            while (dit.hasNext()) {
//                Integer it = dit.next();
//                allhypoints.addAll(helperDi(d, it));
//            }
//            for (Integer hypoint : allhypoints) {
//                String[] words2 = nums.get(hypoint).split(" ");
//                for (String word : words2) {
//                    if (!hyps.contains(word)) {
//                        hyps.add(word);
//                    }
//                }
//            }
//        }
        In syn = new In(s);
        In hyp = new In(h);
        while (!syn.isEmpty()) {
            String a = syn.readLine();
            String[] b = a.split(",");
            nums.add(Integer.parseInt(b[0]), b[1]);
        }
        di = new Digraph(nums.size());
        while (!hyp.isEmpty()) {
            String a = hyp.readLine();
            String[] b = a.split(",");
//            String word = nums.get(Integer.parseInt(b[0]));
//            hyps.clear();
            int i = 1;
            while (i < Arrays.asList(b).size()) {
//                hyps.add(nums.get(Integer.parseInt(b[i])));
                di.addEdge(Integer.parseInt(b[0]), Integer.parseInt(b[i]));
                i = i + 1;
            }
//            hypos.put(word,hyps);
        }
        int i = 0;
        while (i < nums.size()) {
            String a = nums.get(i);
            String[] b = a.split(" ");
            for (String item : b) {
                if (!words.contains(item)) {
                    words.add(item);
                }
                Set<String> hyps2;
                if (hypos.containsKey(item)) {
                    hyps2 = hypos.get(item);
                } else {
                    hyps2 = new TreeSet<String>();
                }
//                Iterator<Integer> dit = di.adj(i).iterator();
//                while (dit.hasNext()) {
//                    Integer it = dit.next();
//                    String[] words2 = nums.get(i).split(" ");
//                    for (String word : words2) {
//                        if (!hyps.contains(word)) {
//                            hyps.add(word);
//                        }
//                    }
//                }
                List<Integer> allhypints = helperDi(di, i);
                for (Integer i3 : allhypints) {
                    String[] words2 = nums.get(i3).split(" ");
                    for (String word : words2) {
                        if (!hyps2.contains(word)) {
                            hyps2.add(word);
                        }
                    }
                }
                hypos.put(item, hyps2);
            }
            i = i + 1;
        }
//        Iterable<Integer> dit = di.adj();
    }

    private List<Integer> helperDi(Digraph d, Integer i1) {
        List<Integer> allhypoints = new ArrayList<Integer>();
        allhypoints.add(i1);
        Iterator<Integer> dit = di.adj(i1).iterator();
        while (dit.hasNext()) {
            Integer it = dit.next();
            allhypoints.addAll(helperDi(d, it));
        }
        return allhypoints;
//        for (Integer hypoint : allhypoints) {
//            String[] words2 = nums.get(hypoint).split(" ");
//            for (String word : words2) {
//                if (!hyps.contains(word)) {
//                    hyps.add(word);
//                }
//            }
//        }
    }

    public boolean isNoun(java.lang.String str) {
        if (words.contains(str)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return words;
    }

    public java.util.Set<java.lang.String> hyponyms(java.lang.String str) {
        if (hypos.containsKey(str)) {
            return hypos.get(str);
        }
        return null;
    }

//    public static void main(java.lang.String[] args) {
//    }
}
