package ngordnet;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {
    private Map<String, List<String>> hyponyms;
    private Map<String, List<String>> synsetwordKeys;
    private Map<String, List<String>> synsetnumKeys;
    private Set<String> hyphashset;
    private Set<String> synhashset;
    private Set<String> allhyponyms;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In inhyponyms = new In(hyponymFilename);
        In insynonyms = new In(synsetFilename);
        hyponyms = new HashMap<String, List<String>>();
        synsetwordKeys = new HashMap<String, List<String>>();
        synsetnumKeys = new HashMap<String, List<String>>();

        //Get # of edges
        String line = "";
        while ((line = inhyponyms.readLine()) != null) {
            String[] parts = line.split(",");
            List<String> hypvals = new ArrayList<String>();
            for (int i = 1; i < parts.length; i++) {
                hypvals.add(parts[i]);
            }
            if (hyponyms.containsKey(parts[0])) {
                List<String> allvalues = hyponyms.get(parts[0]);
                for (String h: hypvals) {
                    allvalues.add(h);
                }
                hyponyms.put(parts[0], allvalues);
            } else {
                hyponyms.put(parts[0], hypvals);
            }
        }

        //Maps words to #s
        while ((line = insynonyms.readLine()) != null) {
            String[] parts = line.split(",");
            List<String> numid = new ArrayList<String>();
            numid.add(parts[0]);
            String words = parts[1];
            synsetwordKeys.put(words, numid);
        }

        //Maps #s to words
        insynonyms = new In(synsetFilename);
        while ((line = insynonyms.readLine()) != null) {
            String[] parts = line.split(",");
            List<String> synvals = new ArrayList<String>();
            String[] words = parts[1].split(" ");
            for (String word: words) {
                synvals.add(word);
            }
            synsetnumKeys.put(parts[0], synvals);
        }

        //Gets # of vertices
        inhyponyms = new In(hyponymFilename);
        hyphashset = new HashSet<String>();

        while ((line = inhyponyms.readLine()) != null) {
            String[] parts = line.split(",");
            for (int i = 0; i < parts.length; i++) {
                hyphashset.add(parts[i]);
            }
        }
        g = new Digraph(hyphashset.size());    

        //Adds edges to digraph
        for (String key: hyponyms.keySet()) {
            List<String> val = hyponyms.get(key);
            for (int i = 0; i < val.size(); i++) {
                int numericalKey = Integer.parseInt(key);
                int numericalValue = Integer.parseInt(val.get(i));
                g.addEdge(numericalKey, numericalValue);
            }
        }

        //Gets unique word from Synset
        insynonyms = new In(synsetFilename);
        synhashset = new HashSet<String>();
        while ((line = insynonyms.readLine()) != null) {
            String[] parts = line.split(",");
            String[] words = parts[1].split(" ");
            for (String word: words) {
                synhashset.add(word);
            }
        }
    }

    public boolean isNoun(String noun) {
        return synhashset.contains(noun);
    }

    public Set<String> nouns() {
        return synhashset;
    }

    public Set<String> hyponyms(String word) {
        allhyponyms = new HashSet<String>();
        Set<Integer> values = new TreeSet<Integer>();
        allhyponyms.add(word);
        for (String key: synsetwordKeys.keySet()) {
            String[] parts = key.split(" ");
            for (String p: parts) {
                if (p.equals(word)) {
                    values.add(Integer.parseInt(synsetwordKeys.get(key).get(0)));
                }
            }
        }
        Set<Integer> valdesc = GraphHelper.descendants(g, values);
        for (Integer v: valdesc) {
            List<String> blah = synsetnumKeys.get(v.toString());
            if (blah != null) {
                for (String b: blah) {
                    allhyponyms.add(b);
                }
            }
        }
        return allhyponyms;
    }
}
