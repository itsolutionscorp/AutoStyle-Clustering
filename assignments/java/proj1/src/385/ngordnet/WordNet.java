package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
public class WordNet {
    private int number;
    private Map<Integer, Set<String>> f;
    private Map<String, Set<Integer>> fInvert;
    private Digraph g;
    private String[] hyponymsArray;
    private String synonyms;
    private String firstStringH;
    private String firstStringS;
    private In synsets;
    private In hyponyms;
    private Set<String> nouns = new TreeSet<String>();
    public WordNet(String syns, String hyps) {
        synsets = new In(syns);
        hyponyms = new In(hyps);
        this.f = new HashMap<>();
        this.fInvert = new HashMap<>();
        while (synsets.hasNextLine()) {
            synsets.readLine();
            number += 1;
        }
        synsets = new In(syns);
        int synsetIDhyponyms;
        g = new Digraph(number);
        while (hyponyms.hasNextLine()) {
            firstStringH = hyponyms.readLine();
            hyponymsArray = firstStringH.split(",");
            Integer synsetId = Integer.parseInt(hyponymsArray[0]);
            for (int z = 1; z < (hyponymsArray.length); z = z + 1) {
                synsetIDhyponyms = Integer.parseInt(hyponymsArray[z]);
                g.addEdge(synsetId, synsetIDhyponyms);
            }
        }
        while (synsets.hasNextLine()) {
            firstStringS = synsets.readLine();
            String[] synsetArray = firstStringS.split(",");
            Integer synsetId = Integer.parseInt(synsetArray[0]); //synsetId gets number of line
            synonyms = synsetArray[1];
            String[] synonymsArray = synonyms.split(" ");
            Set<String> synonymSet = new HashSet<String>();
            for (String i : synonymsArray) { 
                Set<Integer> ids = new TreeSet<Integer>();
                synonymSet.add(i); //synonymSet gets all the words in a life
                nouns.add(i);
                if (!fInvert.containsKey(i)) {
                    ids.add(synsetId);
                    fInvert.put(i, ids);
                } else if (fInvert.containsKey(i)) {
                    fInvert.get(i).add(synsetId);
                }
            }
            f.put(synsetId, synonymSet);
        }
    }
    private Set<Integer> hyponymSetNumbers;
    public Set<String> hyponyms(String wd) {
        Set<Integer> id = fInvert.get(wd);
        hyponymSetNumbers = GraphHelper.descendants(g, id);
        Set<String> setHyponyms = new TreeSet<String>();
        for (Integer x : hyponymSetNumbers) {
            for (String noun : f.get(x)) {
                setHyponyms.add(noun);
            }
        }
        return setHyponyms;
    }
    public boolean isNoun(String nn) {
        if (this.fInvert.containsKey(nn)) {
            return true;
        }
        return false;
    }
    public Set<String> nouns() {
        return nouns;
    }
}


