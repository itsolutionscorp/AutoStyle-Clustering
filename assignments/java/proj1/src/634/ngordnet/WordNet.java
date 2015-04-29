package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
//ideas inspired by Sahil Lamba
public class WordNet {
    private Digraph graph;
    private Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
    private Map<String , Set<Integer>> amap = new HashMap<String , Set<Integer>>();
    public WordNet(String synsetFilename , String hyponymFilename) {
        In synsetfile = new In(synsetFilename);
        In hyponymfile = new In(hyponymFilename);
        String[] synsetstring = synsetfile.readAllLines();
        String[] hypostring = hyponymfile.readAllLines();
        graph = new Digraph(synsetstring.length);
        String synsetstringing = "";
        String hypstring = "";
        String[] synlist;
        String[] hyplist;
        String[] value;
        int number;
        Set<String> astring = new HashSet<String>();
        Set<Integer> bint = new HashSet<Integer>();


        for (int i = 0; i < synsetstring.length; i++) {
            synsetstringing = synsetstring[i];
            synlist = synsetstringing.split(",");
            value = synlist[1].split(" ");
            number = Integer.parseInt(synlist[0]);
            for (String z : value) {
                astring.add(z);
                bint.add(number);
                amap.put(z , bint);
            }
            map.put(number , astring);
            for (int j = 0; j < hypostring.length; j++) {
                hypstring = hypostring[j];
                hyplist = hypstring.split(",");
                int[] intlist = new int[hyplist.length];
                for (int k = 0; k < hyplist.length; k++) {
                    intlist[k] = Integer.parseInt(hyplist[k]);
                }
                if (number == intlist[0]) {
                    for (int m = 1; m < intlist.length; m++) {
                        graph.addEdge(number, intlist[m]);
                    }
                }
            }
        }
    }

    public boolean isNoun(String noun) {
        for (Set<String> element: map.values()) {
            for (String value:element) {
                if (noun.equals(value)) {
                    return true; 
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> newstring = new HashSet<String>();
        Set<Integer> place = map.keySet();
        Set<String> svalue;
        for (Integer places: place) {
            svalue = map.get(places);
            for (String avalue: svalue) {
                newstring.add(avalue);
            }
        }
        return newstring;
    }

    public Set<String> hyponyms(String word) {
        Set<String> newstring = new HashSet<String>();
        Set<Integer> numbers = amap.get(word);
        Set<Integer> match = GraphHelper.descendants(graph , numbers);
        Set<String> fstring = new HashSet<String>();
        for (Integer fc:match) {
            fstring = map.get(fc);
            for (String lstring:fstring) {
                newstring.add(lstring);
            }
        }
        return newstring;
    }
}
