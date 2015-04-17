package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;

public class WordNet {
    private Map<Integer, ArrayList<String>> setsLoc = new HashMap<Integer, ArrayList<String>>();
    private Digraph g;
    private int size;

    public boolean isNoun(String n) {
        Set<String> listNouns = nouns();
        List<String> listN = new ArrayList<String>(listNouns);
        boolean b = listN.contains(n);
        return b;
    }

    public Set<String> hyponyms(String hyp) {
        Set<Integer> listLocHyper = new TreeSet<Integer>();
        Set<String> setsNounsHypo = new TreeSet<String>();

        for (Map.Entry<Integer, ArrayList<String>> entry : setsLoc.entrySet()) {
            ArrayList<String> values = entry.getValue();
            // System.out.println(values);
            for (String v : values) {
                if (hyp.equals(v)) {
                    int loc = entry.getKey();
                    listLocHyper.add(loc);
                }
            }
        }
        // System.out.println(g);
        Set<Integer> reachable = GraphHelper.descendants(g, listLocHyper);
        for (int i : reachable) {
            ArrayList<String> nL = setsLoc.get(i);
            for (String n : nL) {
                setsNounsHypo.add(n);
            }
        }
        return setsNounsHypo;
        // String[] listNounsHypo = new String[setsNounsHypo.size()];
        // listNounsHypo = setsNounsHypo.toArray(listNounsHypo);
        // return listNounsHypo;

    }

    public Set<String> nouns() {
        // using hashset to avoid appending the same string value into the list
        // of nouns
        Set<String> rAlist = new HashSet<String>();
        for (int i : setsLoc.keySet()) {
            ArrayList<String> psbNounsList = setsLoc.get(i);
            String[] listNouns = new String[psbNounsList.size()];
            listNouns = psbNounsList.toArray(listNouns);
            for (String p : listNouns) {
                rAlist.add(p);
            }
        }
        return rAlist;
        // String[] rList = new String[rAlist.size()];
        // rList = rAlist.toArray(rList);
        // return rList;
    }

    public WordNet(String synset, String hyponyms) {
        In sets = new In(synset);
        In hypo = new In(hyponyms);
        String line;
        while ((line = sets.readLine()) != null) {
            ArrayList<String> listValue = new ArrayList<String>();
            String[] aList = line.split(",");
            int key = Integer.parseInt(aList[0]);
            size += 1;
            String nouns = aList[1];
            String[] textStr = nouns.split(" ");
            for (String i : textStr) {
                listValue.add(i);
            }
            setsLoc.put(key, listValue);
        }
        g = new Digraph(size);
        while ((line = hypo.readLine()) != null) {
            ArrayList<String> aList = new ArrayList<String>(Arrays.asList(line
                    .split(",")));
            // help from http://www.java-examples.com/java-string-split-example
            int key = Integer.parseInt(aList.get(0));
            for (int i = 1; i < aList.size(); i++) {
                int newElement = Integer.parseInt(aList.get(i));
                g.addEdge(key, newElement);
            }

        }

    }
}
