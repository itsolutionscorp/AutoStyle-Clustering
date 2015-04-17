package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

public class WordNet {

    private ArrayList<Synset> synList;
    private ArrayList<String> synNamesList;
    private Digraph g;
    private HashMap<Integer, TreeSet<Integer>> hypomap;
    private HashMap<String, TreeSet<Integer>> nameIDmap;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In hyponyms = new In(hyponymFilename);
        In synsets = new In(synsetFilename);
        synList = new ArrayList<Synset>();
        synNamesList = new ArrayList<String>();
        nameIDmap = new HashMap<String, TreeSet<Integer>>();
        int id = 0;
        while (synsets.hasNextLine()) {
            String[] line = synsets.readLine().split(",");
            String name = line[1];
            Synset a = new Synset(id, name);
            synList.add(a); //add new Synset object to list of Synset objects.
            synNamesList.add(name); //add Synset object's name to list of synset names.
            for (String n : a.synonyms) { //add each noun in Synset object's name to map 
                                              //of names & IDs.
                if (nameIDmap.containsKey(n)) {
                    nameIDmap.get(n).add(id);
                } else {
                    TreeSet<Integer> val = new TreeSet<Integer>();
                    val.add(id);
                    nameIDmap.put(n, val);
                }
            }
            id += 1;
        }
        g = new Digraph(synList.size());
        hypomap = new HashMap<Integer, TreeSet<Integer>>();
        while (hyponyms.hasNextLine()) { //convert a line of input into a key value pair...
            String[] line = hyponyms.readLine().split(",");
            int synid = Integer.parseInt(line[0]);
            TreeSet<Integer> hypos = new TreeSet<Integer>();
            for (int i = 1; i < line.length; i += 1) { //create the value to be stored in hypomap
                int num = Integer.parseInt(line[i]);
                hypos.add(num);
                g.addEdge(synid, num); //create relationship between synset IDs in digraph.
            }
            hypomap.put(synid, hypos); //add key value pair to hypomap
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < synList.size(); i += 1) {
            if (synList.get(i).synonyms.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> answer = new TreeSet<String>();
        return nameIDmap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        TreeSet<String> answer = new TreeSet<String>();
        if (nouns().contains(word)) {
            TreeSet<Integer> allmysynsets = nameIDmap.get(word);
            Set<Integer> myDescIDs = GraphHelper.descendants(g, allmysynsets);
            for (Integer synID : myDescIDs) {
                answer.addAll(synList.get(synID).synonyms);
            }
        }
        return answer;
    }
}
