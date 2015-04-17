package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/** WordNet.java.
 *  @author Nag Alluri
 */

public class WordNet {
    private Map synsetmap = new HashMap<Integer, ArrayList<String>>();
    private Map hyponymmap = new HashMap<Integer, ArrayList<Integer>>();
    private Digraph hyponymgraph = new Digraph(hyponymmap.size());


    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsetscanner(synsetFilename);
        this.hyponymscanner(hyponymFilename);
        this.digraphmaker();
    }

    private void synsetscanner(String textfile) {
        In synsettext = new In(textfile);
        while (!synsettext.isEmpty()) {
            String line = synsettext.readLine();
            ArrayList<String> parsedline = new ArrayList<String>(Arrays.asList(line.split(",")));
            int key = Integer.parseInt((String) parsedline.get(0));
            String synsets = (String) parsedline.get(1);
            ArrayList<String> values = new ArrayList<String>(Arrays.asList(synsets.split(" ")));
            synsetmap.put(key, values);
        }
    }

    public boolean isNoun(String noun) {
        for (int i = 0; i < synsetmap.size(); i += 1) {
            ArrayList<String> values = (ArrayList) synsetmap.get(i);
            if (values.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        HashSet nouns = new HashSet();
        for (int i = 0; i < synsetmap.size(); i += 1) {
            ArrayList values = (ArrayList) synsetmap.get(i);
            values.trimToSize();
            for (int j = 0; j < values.size(); j += 1) {
                String toadd = (String) values.get(j);
                nouns.add(toadd);
            }
        }
        return nouns;
    }   

    private void hyponymscanner(String textfile) {
        In hyponymtext = new In(textfile);
        while (!hyponymtext.isEmpty()) {
            ArrayList hyponyms = new ArrayList();           
            String line = hyponymtext.readLine();
            ArrayList parsedline = new ArrayList(Arrays.asList(line.split(",")));
            int key = Integer.parseInt((String) parsedline.get(0));
            parsedline.trimToSize();
            for (int i = 1; i < parsedline.size(); i += 1) {
                int hyponym = Integer.parseInt((String) parsedline.get(i));
                hyponyms.add(hyponym);
                hyponyms.trimToSize();
            
                if (hyponymmap.containsKey(key)) {
                    ArrayList multkeys = (ArrayList) hyponymmap.get(key);
                    multkeys.add(hyponym);
                    hyponymmap.remove(key);
                    hyponymmap.put(key, multkeys);
                } else {
                    hyponymmap.put(key, hyponyms);
                }
            }
        }
    }

    private void digraphmaker() {
        hyponymgraph = new Digraph(synsetmap.size());
        Set<Integer> keys = hyponymmap.keySet();
        for (int s : keys)  {
            ArrayList values = (ArrayList) hyponymmap.get(s);
            values.trimToSize();
            for (int j : (ArrayList<Integer>) values) {
                hyponymgraph.addEdge(s, j); 
            }
        }
    }

    private Set<Integer> keyfinder(String word) {
        Set keys = new HashSet();
        for (int i = 0; i < synsetmap.size(); i += 1) {
            ArrayList values = (ArrayList) synsetmap.get(i);
            if (values.contains(word)) {
                keys.add(i);
            }               
        }
        return keys;
    }
        
    public Set<String> hyponyms(String word) {
        HashSet hyposet = new HashSet();
        ArrayList w = new ArrayList();
        w.add(word);
        Set keys = keyfinder(word);
        for (int key : (Set<Integer>) keys) {
            ArrayList<String> synonym = (ArrayList<String>) synsetmap.get(key);
            hyposet.addAll(synonym);
        }
        Set hyponyms = GraphHelper.descendants(hyponymgraph, keys);
        for (int hyponymkey : (Set<Integer>) hyponyms) {
            ArrayList<String> hyponymstoadd = (ArrayList<String>) synsetmap.get(hyponymkey);
            hyposet.addAll(hyponymstoadd);
        }
        return hyposet;     
    }       
}   



