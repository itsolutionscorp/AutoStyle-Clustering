package ngordnet;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, Set<String>> synsets;
    private Map<Integer, Set<Integer>> hyponyms;

    public WordNet(String synsetFileName, String hyponymFilename) {
        this.synsets = new HashMap<Integer, Set<String>>();
        this.hyponyms = new HashMap<Integer, Set<Integer>>();
        processNouns(synsetFileName);
        processHypernyms(hyponymFilename);
    }

    private int processNouns(String filename) {
        int graphLength = 0;
        In in = new In(filename);
        String line = null;
        Set<String> currList = null; 
        while ((line = in.readLine()) != null) {
            currList = new HashSet<String>();
            if (line.equals("")) {
                continue;
            }
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String[] synonyms = parts[1].split(" ");
            for (String noun : synonyms) {
                currList.add(noun);
            }
            this.synsets.put(id, currList);
            graphLength++;
        }
        return graphLength;
    }

    private void processHypernyms(String filename) {
        In in = new In(filename);
        String line = null;
        Set<Integer> currList = null;
        while ((line = in.readLine()) != null) {

            if (line.equals("")) {
                continue;
            }
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            if (this.hyponyms.get(id) == null) {
                currList = new HashSet<Integer>();
            } else {
                currList = this.hyponyms.get(id);
            }
            for (int x = 0; x < parts.length; x++) {
                currList.add(Integer.parseInt(parts[x]));
            }
            this.hyponyms.put(id, currList);
        }
    }

    public Set<String> hyponyms(String word) {
        Set<String> currList = new HashSet<String>();
        Set<Integer> ids = this.getIdFromWord(word);
        Set<Integer> hypo = new HashSet<Integer>();
        for (Integer a : ids) {
            Set<Integer> v = this.hyponyms.get(a);
            if (v != null) {
                hypo.addAll(v);
            }
            hypo.add(a);
        }
        Set<Integer> t = this.branchMore(hypo);
        if (t != null) {
            for (Integer s : t) {
                Set<String> val = this.synsets.get(s);
                for (String b : val) {
                    currList.add(b);
                }
            }
            currList.add(word);
        }
        return currList;
    }

    private Set<Integer> getIdFromWord(String word) {
        Set<Integer> vals = new HashSet<Integer>();
        for (Integer key : this.synsets.keySet()) {
            Set<String> anal = this.synsets.get(key);
            for (String s : anal) {
                if (s.equals(word)) {
                    vals.add(key);
                }
            }
        }
        return vals;
    }

    private Set<Integer> branchMore(Set<Integer> vals) {
        Set<Integer> v = new HashSet<Integer>();
        v.addAll(vals);
        Set<Integer> backup = new HashSet<Integer>();
        while (v.size() != backup.size()) {
            Set<Integer> found = new HashSet<Integer>();
            backup.addAll(v);
            for (Integer a : v) {
                Set<Integer> moreval = this.hyponyms.get(a);
                if (moreval != null) {
                    found.addAll(moreval);
                }
            }
            v.addAll(found);
        }
        return v;
    }

    public boolean isNoun(String noun) {
        for (Set<String> key : this.synsets.values()) {
            for (String s : key) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> allnouns = new HashSet<String>();
        for (Set<String> key : this.synsets.values()) {
            for (String s : key) {
                allnouns.add(s);
            }
        }
        return allnouns;
    }
}
