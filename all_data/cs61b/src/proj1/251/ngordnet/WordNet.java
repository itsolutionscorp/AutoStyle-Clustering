package ngordnet;

//import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.LinkedList;

public class WordNet {
    // public Set<String> indexSet;
    private Digraph dg;
    private HashMap<Integer, LinkedList<String>> map = new HashMap<Integer, LinkedList<String>>();
    private Set<String> nounSet = new TreeSet<String>();

    // private Hashmap<String, LinkedList<Integer>> invertMap = new
    // Hashmap<String, Integer>();

    public WordNet(String synsetFile, String hyponymFile) {
        // Create a map with key being indices and values being corresponding
        // nouns.
        In in = new In(synsetFile);
        String lineStr = in.readLine();
        while (lineStr != null) {
            String[] helpStr = lineStr.split(",");
            int key = Integer.parseInt(helpStr[0]);
            String[] helpNounStr = helpStr[1].split(" ");
            LinkedList<String> lineStrList = new LinkedList<String>();
            for (int i = 0; i < helpNounStr.length; i++) {
                lineStrList.add(helpNounStr[i]);
                // invertMap.put(helpNounStr[i], key);
                nounSet.add(helpNounStr[i]);
            }
            map.put(key, lineStrList);
            lineStr = in.readLine();
        }
        int lsize = map.size();

        // Create the digraph.
        dg = new Digraph(lsize);
        In inhyp = new In(hyponymFile);
        lineStr = inhyp.readLine();
        while (lineStr != null) {
            String[] hypStr = lineStr.split(",");
            for (int i = 1; i < hypStr.length; i++) {
                dg.addEdge(Integer.parseInt(hypStr[0]),
                        Integer.parseInt(hypStr[i]));
            }
            lineStr = inhyp.readLine();
        }

    }

    public Set<String> hyponyms(String word) {
        Set<Integer> intSet = new TreeSet<Integer>();
        Set<String> retStr = new TreeSet<String>();
        Set<Integer> help = map.keySet();
        for (Integer i : help) {
            LinkedList<String> strList = map.get(i);
            if (strList.contains(word)) {
                intSet.add(i);
            }              
        }
        intSet = GraphHelper.descendants(dg, intSet);
        for (Integer i : intSet) {
            LinkedList<String> helper = map.get(i);
            putIn(retStr, helper);
        }

        return retStr;
    }

    private void putIn(Set<String> set, LinkedList<String> llist) {
        for (String s : llist) {
            set.add(s);
        }
    }

    public boolean isNoun(String noun) {
        return nounSet.contains(noun);

    }

    public Set<String> nouns() {
        return nounSet;
    }
    // public static void main(String[] args) {
    // WordNet wn = new WordNet("synsets14.txt", "hyponyms14.txt");
    // System.out.println(wn.hyponyms("change"));

    // }
}
