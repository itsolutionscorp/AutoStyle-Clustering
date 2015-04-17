package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {

    private Digraph hyponyms;
    private TreeMap<String, ArrayList<Synset>> synsets;
    private TreeMap<Integer, Synset> allSynsets;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new TreeMap<String, ArrayList<Synset>>();
        allSynsets = new TreeMap<Integer, Synset>();
        In synsIn = new In(synsetFilename);
        String[] lineSplit;
        String[] synoSplit;
        while (!synsIn.isEmpty()) {
            lineSplit = synsIn.readLine().split(",");
            synoSplit = lineSplit[1].split(" ");
            Synset tempSyn = new Synset(Integer.parseInt(lineSplit[0]), synoSplit, lineSplit[2]);
            allSynsets.put(tempSyn.id(), tempSyn);
            for (int i = 0; i < synoSplit.length; i++) {
                if (synsets.containsKey(synoSplit[i])) {
                    synsets.get(synoSplit[i]).add(tempSyn);
                } else {
                    ArrayList<Synset> tempList = new ArrayList<Synset>();
                    tempList.add(tempSyn);
                    synsets.put(synoSplit[i], tempList);
                }
            }
        }
        In hypoIn = new In(hyponymFilename);
        int max = 0;
        String[] vals;
        int[] nums;
        ArrayList<int[]> tracker = new ArrayList<int[]>();
        while (!hypoIn.isEmpty()) {
            vals = hypoIn.readLine().split(",");
            nums = new int[vals.length];
            for (int i = 0; i < vals.length; i++) {
                int storeage = Integer.parseInt(vals[i]);
                if (storeage > max) { 
                    max = storeage; 
                }
                nums[i] = storeage;
            }
            tracker.add(nums);
        }
        hyponyms = new Digraph(max + 1);
        for (int i = 0; i < tracker.size(); i++) {
            int[] watch = tracker.get(i);
            for (int j = 1; j < watch.length; j++) {
                hyponyms.addEdge(watch[0], watch[j]);
            }
        }
    }
 
    public Set<String> hyponyms(String word) {
        TreeSet<String> hypos = new TreeSet<String>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<Integer> hypID = new ArrayList<Integer>();
        for (Synset syn : synsets.get(word)) {
            ids.add(syn.id());
            hypos.addAll(syn.getSynonyms());
            hypID.addAll(getAllHyp(syn.id()));
        }
        for (int id : hypID) {
            hypos.addAll(allSynsets.get(id).getSynonyms());
        }
        return hypos;
    }

    private ArrayList<Integer> getAllHyp(int y) {
        ArrayList<Integer> z = new ArrayList<Integer>();
        for (int id : hyponyms.adj(y)) {
            z.add(id);
            z.addAll(getAllHyp(id));
        }
        return z;
    }

    public boolean isNoun(String noun) {
        return synsets.containsKey(noun);
    }

    public Set<String> nouns() {
        return synsets.keySet();
    }
}
