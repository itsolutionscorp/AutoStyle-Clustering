package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.HashMap; 
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;   
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<String, ArrayList<Integer>> nouns;
    private HashMap<Integer, ArrayList<String>> id2nouns;
    private HashMap<Integer, String> synsets;
    private Digraph maingraph;

      
    public WordNet(String synsetfile, String hyponymfile) {
        nouns = new HashMap<String, ArrayList<Integer>>();
        id2nouns = new HashMap<Integer, ArrayList<String>>();
        synsets = new HashMap<Integer, String>();
        synsetParse(synsetfile);
        maingraph = new Digraph(synsets.size());
        hypernymParse(hyponymfile);
    }

    public boolean isNoun(String input) {
        return nouns.containsKey(input);
    }

    public Set<String> nouns() {
        return nouns.keySet();
    }

    public Set<String> hyponyms(String input) {
        Collection<Integer> temp = nouns.get(input);
        Set<Integer> synsetIDs = new HashSet<Integer>();
        synsetIDs.addAll(temp);
        Set<Integer> hypos = GraphHelper.descendants(maingraph, synsetIDs);
        Set<String> result = new TreeSet<String>();
        // for (Integer hypo : hypos) {
        //  result.add(synsets.get(hypo));
        // }
        for (int i = 0; !hypos.isEmpty(); i++) {
            if (hypos.contains(i)) {
                String words = synsets.get(i);
                String[] splitted = words.split(" ");
                for (String split : splitted) {
                    result.add(split);
                }
                hypos.remove(i);
            }
        }
        return result;
    }

    private void synsetParse(String synsetfile) {
        In syntext = new In(synsetfile);
        while (syntext.hasNextLine()) {
            String line = syntext.readLine();
            String[] fields = line.split(",");
            int numID = Integer.parseInt(fields[0]);
            String synsetlist = fields[1];
            String[] nounlist = synsetlist.split(" ");
            synsets.put(numID, synsetlist);
            ArrayList<String> nounz = new ArrayList<String>();
            for (String noun : nounlist) {
                if (nouns.containsKey(noun)) {
                    ArrayList<Integer> temp = nouns.get(noun);
                    temp.add(numID);
                    nouns.put(noun, temp);
                } else {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(numID);
                    nouns.put(noun, temp);
                }
                nounz.add(noun);
            }
            id2nouns.put(numID, nounz);
        }
    }

    private void hypernymParse(String hypernymfile) {
        In hyptext = new In(hypernymfile);
        ArrayList<Integer> edgeslist = new ArrayList<Integer>();
        while (hyptext.hasNextLine()) {
            String line = hyptext.readLine();
            String[] fields = line.split(",");
            int synsetID = Integer.parseInt(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                maingraph.addEdge(synsetID, Integer.parseInt(fields[i]));
            }
          // edges.put(synsetID, edgeslist);
        }
    }

}
