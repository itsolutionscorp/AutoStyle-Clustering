package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


public class WordNet {
    private Map<String, List<String>> nounToNum;
    private Map<String, List<String>> numToNoun;
    private Digraph h;
    private Set<String> nouns;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In inSynset = new In(synsetFilename);
        In inHyponym = new In(hyponymFilename);
        nounToNum = new HashMap<String, List<String>>();
        numToNoun = new HashMap<String, List<String>>();
        nouns = new TreeSet<String>();
        int hypSize = 0;
        while (inSynset.hasNextLine()) {
            String[] line = inSynset.readLine().split(",");
            List<String> synonyms = Arrays.asList(line[1].split(" "));
            nouns.addAll(synonyms);
            for (String synonym : synonyms) {
                List<String> toBeAdded = new ArrayList<String>();
                toBeAdded.add(line[0]);
                if (nounToNum.get(synonym) != null) {
                    toBeAdded.addAll(nounToNum.get(synonym));
                    nounToNum.put(synonym, toBeAdded);
                } else {
                    nounToNum.put(synonym, toBeAdded);
                }    
            }
            numToNoun.put(line[0], synonyms);
            hypSize += 1;
        }
        h = new Digraph(hypSize);
        while (inHyponym.hasNextLine()) { 
            String[] hypLine = inHyponym.readLine().split(",");
            Integer[] intHypLine = new Integer[hypLine.length];
            for (int j = 0; j < hypLine.length; j++) {
                intHypLine[j] = Integer.parseInt(hypLine[j]);
            }
            for (int i = 1; i < hypLine.length; i++) {
                h.addEdge(intHypLine[0], intHypLine[i]);
            }
        }
    }


    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> first = new TreeSet<Integer>();
        List<String> check = nounToNum.get(word);
        for (String each : check) {
            first.add(Integer.parseInt(each));
        }
        if (GraphHelper.descendants(h, first).isEmpty()) {
            return null;
        }
        Set<Integer> ret = new TreeSet<Integer>();
        ret.addAll(GraphHelper.descendants(h, first));
        Set<String> tru = new TreeSet<String>();
        for (Integer num : ret) {
            List<String> strList = numToNoun.get(Integer.toString(num));
            for (String noun : strList) {
                tru.add(noun);
            }
        }
        return tru;

    }

    public Set<String> nouns() {
        return nouns;
    }

//     public static void main(String[] args) {

//     }
}
