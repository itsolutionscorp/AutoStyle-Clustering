package ngordnet;
import edu.princeton.cs.introcs.In;
import com.google.common.collect.Multimap;
import com.google.common.collect.HashMultimap;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.Arrays;
import java.util.Collection;

public class WordNet {
    private Multimap<Integer, String> synsets;
    private Multimap<Integer, Integer> hyponyms;
    private Multimap<String, Integer> synsetInts;
    private Multimap<Integer, Integer> hypernyms;

    public WordNet(String synFile0, String hypFile0) {
        In synFile = new In(synFile0);
        In hypFile = new In(hypFile0);
        synsets = HashMultimap.create();
        synsetInts = HashMultimap.create();
        String[] allLinesSyn = synFile.readAllLines();
        for (int i = 0; i < allLinesSyn.length; i++) {
            String[] seperatedLinesSyn = allLinesSyn[i].split(",");
            String entry = seperatedLinesSyn[1];
            String[] entries = entry.split(" ");
            for (int j = 0; j < entries.length; j++) {
                synsets.put(i, entries[j]);
                synsetInts.put(entries[j], i);
            }
        }
        hyponyms = HashMultimap.create();
        hypernyms = HashMultimap.create();
        String[] allLinesHyp = hypFile.readAllLines();
        for (int i = 0; i < allLinesHyp.length; i++) {
            String[] seperatedLinesHyp = allLinesHyp[i].split(",");
            int num = Integer.parseInt(seperatedLinesHyp[0]);
            String[] entries = Arrays.copyOfRange(seperatedLinesHyp, 1, seperatedLinesHyp.length);
            for (String s : entries) {
                hyponyms.put(num, Integer.parseInt(s));
                hypernyms.put(Integer.parseInt(s), num);
            }
        }
    }

    public boolean isNoun(String s) {
        if (synsets.containsValue(s)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return new TreeSet<String>(Arrays.asList(synsets.values().toArray(new String[0])));
    }

    private Collection<Integer> ids;
    private Collection<Integer> idList;
    private Collection<String> set;
    private Map<String, Integer> result = new TreeMap<String, Integer>();
    private Set<Integer> allIds = new TreeSet<Integer>();

    public Set<String> hyponyms(String s) {
        ids = synsetInts.get(s);
        allIds.addAll(ids);
        for (int num : ids) {
            Collection<String> addToResult = synsets.get(num);
            for (String syn : addToResult) {
                result.put(syn, num);
            }
            idList = hyponyms.get(num);
            for (int i : idList) {
                allIds.add(i);
                set = synsets.get(i);
                for (String j : set) {
                    result.put(j, i);
                    deeperhyponyms(j, i);
                }
            }
        }
        Set<String> temp = result.keySet();
        result = new TreeMap<String, Integer>();
        allIds = new TreeSet<Integer>();
        return temp;
    }

    private void deeperhyponyms(String s, int num) {
        idList = hyponyms.get(num);
        for (int i : idList) {
            if (!allIds.contains(i)) {
                allIds.add(i);
                set = synsets.get(i);
                for (String j : set) {
                    result.put(j, i);
                    deeperhyponyms(j, i);
                }
            }
        }
    }
} 
