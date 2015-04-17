package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;

public class WordNet {
    private Map<Integer, WordNetObj> allNouns;
    private Digraph g;




    public WordNet(String synsetL, String hyponymsL) {
        In synIn = new In(synsetL);
        In hypIn = new In(hyponymsL);

        allNouns = new HashMap<Integer, WordNetObj>();
        while (synIn.hasNextLine()) {
            String[] words =  synIn.readLine().split(",");

            allNouns.put(Integer.valueOf(words[0]), new WordNetObj(words[1].split(" ")));
        }

        g = new Digraph(allNouns.size());
        while (hypIn.hasNextLine()) {
            String[] hypo = hypIn.readLine().split(",");
            Integer synId = Integer.valueOf(hypo[0]);
            String[] withoutSynId = new String[hypo.length - 1];
            System.arraycopy(hypo, 1, withoutSynId, 0, withoutSynId.length);
            for (String s: withoutSynId) {
                g.addEdge(synId, Integer.valueOf(s));
            }

        }
    }




    //stackoverflow.com/questions/20454192/how-to-convert-string-to-integer-array-and-back-in-java
    //Made modifications so that it return array of Integers instead of primitives
    private static Integer[] toIntArray(String input) {
        String beforeSplit = input.replaceAll("\\[|\\]|\\s", "");
        String[] split = beforeSplit.split("\\,");
        Integer[] result = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.valueOf(split[i]);
        }
        return result;
    }

    public boolean isNoun(String noun) {

        for (WordNetObj w : allNouns.values()) {
            for (String s: w.getNouns()) {
                if (s.equals(noun)) {
                    return true;
                }
            }

        }


        return false;

    }

    public Set<String> nouns() {
        Set<String> retState = new HashSet<String>();
        for (WordNetObj w : allNouns.values()) {
            for (String s: w.getNouns()) {
                retState.add(s);
            }
        }

        return retState;
    }


    public Set<String> hyponyms(String s) {
        if (!isNoun(s)) {
            throw new IllegalArgumentException();
        }

        Set<Integer> retState = new TreeSet<Integer>();
        for (Integer i : allNouns.keySet()) {
            if (Arrays.asList(allNouns.get(i).getNouns()).contains(s)) {
                retState.add(i);
            }
        }
        Set<Integer> hypos = GraphHelper.descendants(g, retState);
        Set<String> ret = new TreeSet<String>();
        for (Integer i : hypos) {
            for (String word : allNouns.get(i).getNouns()) {
                ret.add(word);
            }
        }

        return ret;




    }




}
