package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class WordNet {
    private ArrayList<String[]> synsets;
    private Map<String, Set<Integer>> backwards;
    private Digraph d;

    public WordNet(String synsetFilename, String hyponymFilename) {
        Set<Integer> temp;
        backwards = new HashMap<String, Set<Integer>>();
        synsets = new ArrayList<String[]>();
               
        In in = new In(synsetFilename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] lineParams = line.split(",");
            String[] lineWords = lineParams[1].split(" ");
            synsets.add(lineWords);
        }
        for (int i = 0; i < synsets.size(); i++) {
            for (String word : synsets.get(i)) {
                if (backwards.containsKey(word)) { 
                    backwards.get(word).add(i);
                } else {
                    temp = new HashSet<Integer>();
                    temp.add(i);
                    backwards.put(word, temp);
                }               
            }
        }

        d = new Digraph(synsets.size()); 

        In in2 = new In(hyponymFilename);
        while (in2.hasNextLine()) {
            String line2 = in2.readLine();
            String[] lineParams2 = line2.split(",");
            int hypernym = Integer.parseInt(lineParams2[0]);
            int[] subs = new int[lineParams2.length - 1];
            for (int i = 1; i < lineParams2.length; i++) {
                subs[i - 1] = Integer.parseInt(lineParams2[i]);
            }
            for (int id : subs) {
                d.addEdge(hypernym, id);
            }

        }
    }
    public boolean isNoun(String noun) {
        return (backwards.containsKey(noun));
    }

    public Set<String> nouns() {
        Set<String> nounSet;
        nounSet = backwards.keySet();
        return nounSet;
    }


    public Set<String> hyponyms(String word) {
        Set<Integer> hypIdSet = new HashSet<Integer>();
        Set<String> hypWords = new HashSet<String>();
        //hypIdSet =  backwards.get(word);

        for (int hyp : GraphHelper.descendants(d, backwards.get(word))) {
            hypIdSet.add(hyp);
        }

        for (int id : hypIdSet) {
            for (String item : synsets.get(id)) {
                hypWords.add(item);
            }
        }
        return hypWords;
    }
}
