package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.HashSet;



public class WordNet {

    private TreeMap<Integer, Set<String>> intmap;
    private TreeMap<String, Set<Integer>> nounmap;
    private ArrayList<String> defs;
    private Digraph hypmap;
    private ArrayList<String> lines;


    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        In syn = new In(synsetFilename);
        In hyp = new In(hyponymFilename);
        int i = 0;
        intmap = new TreeMap<Integer, Set<String>>();
//        nounmap = new TreeMap<String, Set<Integer>>();
        defs = new ArrayList<String>();
//        Map<Integer, String> intMap = new Map<Integer, String>();
//        ArrayList<String> defs = new ArrayList<String>();
        while (!syn.isEmpty()) {
            String temp = syn.readLine();
            String[] bits = temp.split(",");
            i = Integer.parseInt(bits[0]);
            String[] words = bits[1].split(" ");
            Set<String> strings = new TreeSet<String>();
            for (int k = 0; k<words.length; k++) {
                strings.add(words[k]); 
            }
            intmap.put(i, strings);
            // for (int j = 0; j<words.length; j++) {
            //     nounmap.put(words[j], i);
            // }
            defs.add(i, bits[2]);
        }

        lines = new ArrayList<String>();
        while (!hyp.isEmpty()) {
            String temp = hyp.readLine();
            lines.add(temp);
         // this.hypmap = new Digraph(i + 1);
         //    Integer point = Integer.parseInt(lilbits[0]);
         //    for (int j = 1; j<lilbits.length; j++) {
         //        System.out.print(point);
         //        System.out.println(lilbits[j]);
         //        this.hypmap.addEdge(point, Integer.parseInt(lilbits[j]));
         //        Set<Integer> wo = new TreeSet<Integer>();
         //        wo.add(point);
         //        System.out.println(wo);
         //        System.out.println(GraphHelper.descendants(this.hypmap, wo));
            
        }
    }

    public boolean isNoun(String noun) {
        if (this.getInts(noun).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Set<String> nouns() {
        Set<String> nounWords = new TreeSet<String>();
        for (int i: intmap.keySet()) {
            nounWords.addAll(intmap.get(i));
        }
        return nounWords;
    }

    public Set<String> hyponyms(String word) {
        if (this.isNoun(word)) {
            this.hypmap = new Digraph(intmap.size());
            for (int k = 0; k<lines.size(); k ++) {
                String[] lilbits = lines.get(k).split(",");
                Integer point = Integer.parseInt(lilbits[0]);
                for (int j = 1; j<lilbits.length; j++) {
                    this.hypmap.addEdge(point, Integer.parseInt(lilbits[j]));
                }
            }
            Set<Integer> tempSet = this.getInts(word);
            Set<Integer> setInt = GraphHelper.descendants(this.hypmap, tempSet);
            Set<String> setString = new HashSet<String>();
            for (Integer i: setInt) {
                Set<String> splitter = intmap.get(i);
                for (String j: splitter) {
                    setString.add(j);
                }
            }
            return setString;
        }
        return null;
    }

    private Set<Integer> getInts(String word) {
        Set<Integer> ints = new TreeSet<Integer>();
        for (int i: intmap.keySet()) {
            if (intmap.get(i).contains(word)) {
                ints.add(i);
            }
        }
        return ints;
    }

}