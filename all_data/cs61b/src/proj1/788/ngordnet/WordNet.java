package ngordnet;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, Set<String>> datastore = new HashMap<Integer, Set<String>>();
    private Map<Integer, Set<Integer>> storelinks = new HashMap<Integer, Set<Integer>>();
    private int numberOfVertices = 0;
    private int numberOfEdges = 0;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        this.readFile(synsetFilename);
        this.readHyponym(hyponymFilename);
        g = new Digraph(numberOfVertices);
        for (Integer k : storelinks.keySet()) {     //Discussed this loop with lab TA 
            Set<Integer> values = storelinks.get(k);
            //System.out.println(values);
            for (Integer i : values) {
                g.addEdge(k, i);
                numberOfEdges++;
                //System.out.println("hey");
            }
        }
        //System.out.println(g);
        //System.out.println(numberOfEdges);
    }

    // Discussed the logic of read with Jash Mahipal
    private void readFile(String filename) {
        In in = new In(filename);
        int iD;
        Set<String> hyponymz;
        String oneline = in.readLine();
        while (oneline != null) {
            String[] splitline = oneline.split(",");
            String[] synset = splitline[1].split(" ");
            iD = Integer.parseInt(splitline[0]);
            hyponymz = new HashSet<String>();
            for (String x : synset) {
                hyponymz.add(x);
            }
            /* System.out.println("I got here"); **/
            datastore.put(iD, hyponymz);
            numberOfVertices++;
            oneline = in.readLine();
        }
    }

    private void readHyponym(String filename) {
        In in = new In(filename);
        int synsetID;
        Set<Integer> hyponymIDs;
        String[] hyps = new String[2];
        String eachline = in.readLine();
        while (eachline != null) {
            String[] line = eachline.split(",");
            //System.out.println(line[0]);
            //System.out.println(line[1]);
            hyps[0] = line[0];
            hyps[1] = line[1];
            synsetID = Integer.parseInt(line[0]);
            if (storelinks.get(synsetID) == null) {
                hyponymIDs = new HashSet<Integer>();
            } else {
                hyponymIDs = storelinks.get(synsetID);
            }
            for (String b : line) { 
                hyponymIDs.add(Integer.parseInt(b));
            }
            storelinks.put(synsetID, hyponymIDs);
            //System.out.println(storelinks);
            eachline = in.readLine();
        }       
    }

    public boolean isNoun(String noun) {
        for (Integer r : datastore.keySet()) {
            Set<String> values = datastore.get(r);
            for (String synS : values) {
                if (noun.equals(synS)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> allnouns = new HashSet<String>();
        for (Integer r : datastore.keySet()) {
            Set<String> values = datastore.get(r);
            for (String synS : values) {
                allnouns.add(synS);
            }
        }
        return allnouns;
    }

    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> ids = new HashSet<Integer>();
            for (Integer r : datastore.keySet()) {
                Set<String> values = datastore.get(r);
                if (values.contains(word)) {
                    ids.add(r);
                } //CHANGE THIS TO THING BELOW??
                /*
                for (String z : values) {
                    if (z.equals(word)) {
                        ids.add(r);
                    }
                } **/
            }
            Set<String> syn = new HashSet<String>();
            Set<Integer> ths = GraphHelper.descendants(g, ids);
            for (Integer iN : ths) {
                syn.addAll(datastore.get(iN));
            }
            return syn; 
        } else {
            throw new IllegalArgumentException();
        }
    }
}
