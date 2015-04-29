package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class WordNet {
    private In synsetStream;
    private In hyponymStream;
    private Map<String, ArrayList<String>> nounMap;
    private Map<String, ArrayList<String>> hyponymMap;
    private List<ArrayList<String>> synsetList;
    private List<ArrayList<String>> hyponymList;
    private Digraph wordnet;

    public WordNet(String synsetFilename,   String hyponymFilename) {
        this.synsetStream = new In(synsetFilename);
        this.hyponymStream = new In(hyponymFilename);
        this.synsetList = new ArrayList<ArrayList<String>>();
        this.hyponymList = new ArrayList<ArrayList<String>>();
        this.nounMap = new HashMap<String, ArrayList<String>>(); //maps from id to the synset
        this.hyponymMap = new HashMap<String, ArrayList<String>>();
        int n = 0;
        while (this.synsetStream.hasNextLine()) {
            String line = this.synsetStream.readLine();
            ArrayList<String> tokens  = new ArrayList<String>(Arrays.asList(line.split(",")));
            this.synsetList.add(tokens);
            String id = tokens.get(0);
            this.nounMap.put(id, new ArrayList<String>(Arrays.asList(tokens.get(1).split(" "))));
            n++;
        }
        while (this.hyponymStream.hasNextLine()) {
            String[] line = this.hyponymStream.readLine().split(",");
            ArrayList<String> tokens  = new ArrayList<String>(Arrays.asList(line));
            this.hyponymList.add(tokens);
            String synid = tokens.remove(0);
            if (hyponymMap.get(synid) == null) {
                this.hyponymMap.put(synid, tokens);
            } else {
                this.hyponymMap.get(synid).addAll(tokens);
            }
        }
        this.wordnet = new Digraph(n);
        for (String id : this.nounMap.keySet()) {
            ArrayList<String> hypIds = this.hyponymMap.get(id);
            if (hypIds == null) {
                continue;
            }
            for (String hid: hypIds) {
                this.wordnet.addEdge(Integer.parseInt(id), Integer.parseInt(hid));
            }
        }
    }

    public boolean isNoun(String noun) {
        for (Collection<String> s: this.nounMap.values()) {
            if (s.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (Collection<String> als: this.nounMap.values()) {
            nounSet.addAll(als);
        }
        return nounSet;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> hypSet = new HashSet<String>();
        if (!this.isNoun(word)) {
            return new HashSet<String>();
        } else {
            hypSet.add(word);
        }
        Set<Integer> sid = new TreeSet<Integer>();
        for (Map.Entry<String, ArrayList<String>> nouns: this.nounMap.entrySet()) {
            if (nouns.getValue().contains(word)) {
                sid.add(Integer.parseInt(nouns.getKey())); //* if the given word is part of a synset
            }                                             // * add its synid, e.g"jump" is part of
        }                                                //  * synset "jump leap".
        Set<Integer> hypIds = GraphHelper.descendants(this.wordnet, sid);
        for (Integer id : hypIds) {
            String key = Integer.toString(id);
            if (this.nounMap.get(key).size() == 1) {
                hypSet.add(this.nounMap.get(key).get(0));
            } else {
                for (String s: nounMap.get(key)) {
                    hypSet.add(s);
                }
            }
        }
        return hypSet;
    }
}

