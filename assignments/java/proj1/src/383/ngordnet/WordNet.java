package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.io.File;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

//Assistance from Ray Zhu
public class WordNet {
    private Digraph wordNet;
    private HashMap<String, HashSet<Integer>> synToInt;
    private HashMap<Integer, HashSet<String>> intToSyn;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In hyponymIn = new In(new File(hyponymFilename));
        In synsetIn = new In(new File(synsetFilename));
        String str = "";
        synToInt = new HashMap<String, HashSet<Integer>>();
        intToSyn = new HashMap<Integer, HashSet<String>>();
        int pos1, pos2, vertices = 0;
        Integer index;
        HashSet<String> strSet;
        HashSet<Integer> intSet;
        while (synsetIn.hasNextLine()) {
            str = synsetIn.readLine();
            pos1 = str.indexOf(",");
            pos2 = str.indexOf(",", pos1 + 1);
            index = Integer.parseInt(str.substring(0, pos1));
            strSet = parseStr(str.substring(pos1 + 1, pos2));
            intToSyn.put(index, strSet);
            for (String s:strSet) {
                if (synToInt.get(s) == null) {
                    intSet = new HashSet<Integer>();
                    intSet.add(index);
                    synToInt.put(s, intSet);
                } else {
                    synToInt.get(s).add(index);
                }
            }
            vertices++;
        }
        wordNet = new Digraph(vertices);
        while (hyponymIn.hasNextLine()) {
            str = hyponymIn.readLine();
            addEdgeHelper(str);
        }
    }

    //Input: A synset 
    //Output: A HashSet of the individual words of the synset
    private HashSet<String> parseStr(String str) {
        HashSet<String> hs = new HashSet<String>();
        if (str.contains(" ")) {
            for (String s:str.split(" ")) {
                hs.add(s);
            }
        } else {
            hs.add(str);
        }
        return hs;
    }

    //Adds edges to digraph
    private void addEdgeHelper(String str) {
        String[] ints = str.split(",");
        int from = Integer.parseInt(ints[0]);
        int to;
        for (int i = 1; i < ints.length; i++) {
            to = Integer.parseInt(ints[i]);
            wordNet.addEdge(from, to);
        }
    }

    public boolean isNoun(String noun) {
        return synToInt.keySet().contains(noun);
    }

    public Set<String> nouns() {
        return synToInt.keySet();
    }

    public Set<String> hyponyms(String word) {
        if (!isNoun(word) || word == null) {
            throw new IllegalArgumentException("WordNet does not contain " + word);
        }
        HashSet<Integer> wordIntSet = synToInt.get(word);
        Set<Integer> hypoIntSet = GraphHelper.descendants(wordNet, wordIntSet);
        Set<String> toReturn = new HashSet<String>();
        for (Integer i:hypoIntSet) {
            toReturn.addAll(intToSyn.get(i));
        }
        return toReturn;
    }
}
