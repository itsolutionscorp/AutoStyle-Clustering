package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class WordNet {

    private In synIn;
    private In hypIn;
    private TreeSet<String> synMain = new TreeSet<String>();
    private TreeSet<String> splitNouns = new TreeSet<String>();
    private String[] synInLines;
    private String[] hypInLines;
    private ArrayList<String[]> syns = new ArrayList<String[]>();
    private ArrayList<String[]> hyps = new ArrayList<String[]>();
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synIn = new In(synsetFilename);
        hypIn = new In(hyponymFilename);

        synInLines = synIn.readAllLines();
        hypInLines = hypIn.readAllLines();

        g = new Digraph(synInLines.length);

        for (String x : synInLines) {
            String[] temp = x.split(",");
            syns.add(temp);
            for (String a : temp[1].split(" ")) {
                splitNouns.add(a);
            }
        }

        for (String x : hypInLines) {
            String[] splitN = x.split(",");
            hyps.add(splitN);
            for (int i = 0; i < splitN.length; i++) {
                g.addEdge(Integer.parseInt(splitN[0]),
                        Integer.parseInt(splitN[i]));
            }
        }
    }   

    public Set<String> hyponyms(String word) {

        Set<Integer> nums = new TreeSet<Integer>();
        Set<Integer> reachableNums;
        HashSet<String> retSet = new HashSet<String>();

        // for (String x : synInLines) {
        //     String[] temp = x.split(",");
        //     for (String y : temp[1].split(" ")) {
        //         if (word.equals(y)) {
        //             nums.add(Integer.parseInt(temp[0]));
        //         }
        //     }
        // }

        for (String[] x : syns) {
            for (String y : x[1].split(" ")) {
                if (word.equals(y)) {
                    nums.add(Integer.parseInt(x[0]));
                }
            }
        }

        reachableNums = GraphHelper.descendants(g, nums);

        for (String[] x : syns) {
            if (reachableNums.contains(Integer.parseInt(x[0]))) {
                for (String y : x[1].split(" ")) {
                    retSet.add(y);
                }
            }
        }

        // for (String x : synInLines) {
        //     String[] temp = x.split(",");
        //     if (reachableNums.contains(Integer.parseInt(temp[0]))) {
        //         for (String y : temp[1].split(" ")) {
        //             retSet.add(y);
        //         }
        //     }
        // }

        return retSet;

    }

    public boolean isNoun(String noun) {
        return splitNouns.contains(noun);
    }

    public Set<String> nouns() {
        return splitNouns;
    }

}
