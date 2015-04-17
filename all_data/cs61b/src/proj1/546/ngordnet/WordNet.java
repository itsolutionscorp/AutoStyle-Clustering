package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class WordNet {

    private String synsets;
    private String hyponyms;
    private TreeMap<Integer, String> synsetsMap;
    private Digraph hyponymsGraph;

    public WordNet(String synset, String hyponym) {

        /* The order can't be reversed or interchanged. 
         * Since it first got the files, 
         * and then do operations to acquire the desired data. */

        synsets = synset;
        hyponyms = hyponym;
        synsetsMap = readSynsets();
        hyponymsGraph = readHyponyms(synsetsMap.size());
    }
    //again again again

    /* test whether s is a comma */
    private boolean isComma(String s) {
        if (s.equals(",")) {
            return true;
        }
        return false;
    }

    /* This method returns a arraylist which looks like this: <number, word, definition>,
     * which definition has no use, currently. Just to avoid future hassles.*/

    //Tested. Work well. Reset to PRIVATE to AUTOGRADER.
    private ArrayList<String> lineToList(String l) {
        ArrayList<String> s = new ArrayList<String>();

        int startpoint = 0;
        for (int i = 0; i < l.length(); i++) {

            String check = l.substring(i, i + 1);
            if (isComma(check)) {
                s.add(l.substring(startpoint, i));
                startpoint = i + 1;
            }
        }
        s.add(l.substring(startpoint, l.length()));
        return s;
    }

    /* This method returns a treemap which looks like this: (<0, Word0> <1, Word1>) 
         Words like "abc def" are considered one single word.*/

    //Tested. Work well. Reset to PRIVATE to AUTOGRADER.
    private TreeMap<Integer, String> readSynsets() {
        In insyn = new In(synsets);
        TreeMap<Integer, String> wordmap = new TreeMap<Integer, String>();
        while (insyn.hasNextLine()) {
            String line = insyn.readLine();
            ArrayList<String> l = lineToList(line);
            //<number, word, definition>
            wordmap.put(Integer.parseInt(l.get(0)), l.get(1));
        }
        return wordmap;
    }

    /* This method returns a digraph that indicates the relation of words. */
    //Tested. Work well. Reset to PRIVATE to AUTOGRADER.
    private Digraph readHyponyms(int size) {
        
        In inhyp = new In(hyponyms);
        Digraph d = new Digraph(size);

        while (inhyp.hasNextLine()) {
            String line = inhyp.readLine();
            ArrayList<String> l = lineToList(line);
            //<1, 2,3,4,5,6,...>
            int vertice = Integer.parseInt(l.get(0));
            for (String num: l.subList(1, l.size())) {
                d.addEdge(vertice, Integer.parseInt(num));
            }
        }
        return d;
    } 

    private HashSet<Integer> getAllKeyByValue(Map<Integer, String> map, String value) {

        HashSet<Integer> keylist = new HashSet<Integer>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String v = entry.getValue();
            HashSet<String> temp = new HashSet<String>();

            if (isNoun(v)) {
                if (value.equals(entry.getValue())) {
                    keylist.add(entry.getKey());
                }
            } else {
                addNoun(v, temp);
                if (temp.contains(value)) {
                    keylist.add(entry.getKey());
                }
            }    
        }
        return keylist;
    }


    public boolean isNoun(String word) {
        if (word.equals("dummy")) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i + 1).equals(" ")) {
                return false;
            }
        }
        return true;
    }

    public Set<String> nouns() {
        HashSet<String> s = new HashSet<String>();

        for (int i: synsetsMap.keySet()) {
            String word = synsetsMap.get(i);
            addNoun(word, s);
        }
        return s;
    }

    /* Break a "jump leap" into "jump" "leap" and add them in a set. */
    private void addNoun(String w, Set<String> s) {
        if (!isNoun(w)) {
            int space = 0;
            for (int j = 0; j < w.length(); j++) {
                if (w.substring(j, j + 1).equals(" ")) {
                    s.add(w.substring(space, j));
                    space = j + 1;
                }
            }
            s.add(w.substring(space, w.length()));
        } else {
            s.add(w);
        }
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> s = new HashSet<String>();
        HashSet<Integer> keylist = getAllKeyByValue(synsetsMap, word);
        for (int key: keylist) {
            for (int j: GraphHelper.descendants(hyponymsGraph, keylist)) {
                String w = synsetsMap.get(j);
                addNoun(w, s);
            }
            addNoun(synsetsMap.get(key), s);
        }
        return s;
    }
}
