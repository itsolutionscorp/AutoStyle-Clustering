package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.ArrayList;


public class WordNet {
    
    private Digraph graph;
    private int graphsize = 0;

    private Map<String, ArrayList<Integer>> nounlist = new HashMap<String, ArrayList<Integer>>();
    private Map<Integer, String[]> synsets = new HashMap<Integer, String[]>();
    private Map<Integer, ArrayList<Integer>> edgelist = new HashMap<Integer, ArrayList<Integer>>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        ArrayList<Integer> synsetidset;
        String[] currentSynsetNouns;

        while (syn.hasNextLine()) { 
            String currentline = syn.readLine();
            String[] everythinginline = currentline.split(",");
            Integer synsetid = Integer.parseInt(everythinginline[0]);
            String[] nouns = everythinginline[1].split(" ");
            for (String noun : nouns) {
                if (this.nounlist.containsKey(noun)) { 
                    synsetidset = this.nounlist.get(noun);
                } else {
                    synsetidset = new ArrayList<Integer>(); 
                }
                if (this.synsets.containsKey(synsetid)) { 
                    currentSynsetNouns = this.synsets.get(synsetid);
                } else {
                    currentSynsetNouns = new String[nouns.length]; 
                }
                synsetidset.add(synsetid);
                for (int i = 0; i < nouns.length; i++) {
                    currentSynsetNouns[i] = nouns[i];
                }
                this.nounlist.put(noun, synsetidset);
                this.synsets.put(synsetid, currentSynsetNouns);
            }
            graphsize += 1;
        }
        this.graph = new Digraph(graphsize);
        In hypo = new In(hyponymFilename);
        ArrayList<Integer> edgeset;
        while (hypo.hasNextLine()) {    
            String currentlne = hypo.readLine();
            String[] everythinginlne = currentlne.split(",");
            Integer synsetid = Integer.parseInt(everythinginlne[0]);

            if (edgelist.get(Integer.parseInt(everythinginlne[0])) != null) {
                edgeset = edgelist.get(Integer.parseInt(everythinginlne[0]));
            } else {
                edgeset = new ArrayList<Integer>();
            }
            for (int i = 1; i < everythinginlne.length; i++) {
                edgeset.add(Integer.parseInt(everythinginlne[i]));
            }
            edgelist.put(Integer.parseInt(everythinginlne[0]), edgeset);
        }
        for (Map.Entry<Integer, ArrayList<Integer>> entry : edgelist.entrySet()) {
            for (Integer i : entry.getValue()) {
                this.graph.addEdge(entry.getKey(), i);
            }
        }
    }

    public boolean isNoun(String noun) {
        return this.nounlist.containsKey(noun);
    }

    public Set<String> nouns() {
        return this.nounlist.keySet();
    }

    public Set<String> hyponyms(String word) {
        ArrayList<Integer> wordids = nounlist.get(word);
        Set<Integer> hypon = new TreeSet<Integer>();
        for (Integer intg : wordids) {
            hypon.add(intg);
        }
        Set<String> hyposet = new TreeSet<String>();
        for (Integer integ : GraphHelper.descendants(this.graph, hypon)) {
            for (String str : synsets.get(integ)) {
                hyposet.add(str);
            }
        }
        return hyposet;
    }

}
