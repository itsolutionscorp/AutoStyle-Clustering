package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class WordNet {
    private String fileName1;
    private String fileName2;
    private String[] linesSyns;
    private String[] linesHyps;
    private Map<Integer, String> wholeSyns;
    private String[][] wholeHyps;
    private int[] lineNums;
    private Digraph g;
    private boolean bool;
    private Set<Integer> storageNums;

    public WordNet(String fileName1or, String fileName2or) {
        fileName1 = fileName1or;
        fileName2 = fileName2or;

        In file1in = new In(fileName1);
        In file2in = new In(fileName2);

        linesSyns = file1in.readAllLines();
        linesHyps = file2in.readAllLines();
        wholeSyns = new HashMap<Integer, String>();
        lineNums = new int[linesSyns.length];
        for (int i = 0; i < linesSyns.length; i++) {  
            String[] elems = linesSyns[i].split(",");
            // wholeSyns[i] = elems;
            // String num = elems[0];
        //  lineNums[i] = Integer.parseInt(num);
            wholeSyns.put(Integer.parseInt(elems[0]), elems[1]);
        }
        wholeHyps = new String[linesHyps.length][];
        for (int i = 0; i < linesHyps.length; i++) {
            wholeHyps[i] = linesHyps[i].split(",");
        }
        g = new Digraph(lineNums.length);
        for (int i = 0; i < linesHyps.length; i++) {
            for (int j = 1; j < wholeHyps[i].length; j++) {
                g.addEdge(Integer.parseInt(wholeHyps[i][0]), Integer.parseInt(wholeHyps[i][j]));
            }
        }
    }
    public boolean isNoun(String noun) {
        storageNums = new HashSet<Integer>();
        bool = false;
        for (int i = 0; i < linesSyns.length; i++) {
            String[] checkThese = wholeSyns.get(i).split(" ");
            for (int j = 0; j < checkThese.length; j++) {
                if (noun.equals(checkThese[j])) {
                    bool = true;
                    storageNums.add(i);
                }
            }
        }
        return bool;
    }

    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (int i = 0; i < linesSyns.length; i++) {
            String[] eachNoun = wholeSyns.get(i).split(" ");
            for (int j = 0; j < eachNoun.length; j++) {
                allNouns.add(eachNoun[j]);
            }
        }
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<String> set1 = new HashSet<String>();
            Set<Integer> all = new TreeSet<Integer>();
            for (int s : storageNums) {
                all.add(s);
                String[] e = wholeSyns.get(s).split(" ");
                for (String st : e) {
                    set1.add(st);
                }
            }
            all = GraphHelper.descendants(g, all);
            for (int s : all) {
                String[] eachHyp = wholeSyns.get(s).split(" ");
                for (int j = 0; j < eachHyp.length; j++) {
                    set1.add(eachHyp[j]);
                }
            }
            return set1;

        } else {
            throw new RuntimeException("this word is not available in the given files");
        }
    }
}
