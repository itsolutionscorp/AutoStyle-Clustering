package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Set;


public class WordNet {

    private TreeSet<String> nouncontainer; // a set of all nouns
    private TreeMap<String, TreeSet<Integer>> synsetid; 
    private TreeMap<Integer, TreeSet<String>> idsynset; // a map from a id to a set of its synsets
    private Digraph hypograph;
    private In insynset;
    private In inhyponym;

    public WordNet(String synsetFilename, String hyponymFilename) {

        this.insynset = new In(synsetFilename);
        this.inhyponym = new In(hyponymFilename);
        this.nouncontainer = new TreeSet<String>();
        this.synsetid = new TreeMap<String, TreeSet<Integer>>();
        this.idsynset = new TreeMap<Integer, TreeSet<String>>();
        this.readSynset();
        this.readHyponym();

    }

    /** fill in the nouncontainer & idsynset */
    private void readSynset() {
        
        String[] splitline = null;
        String[] splitelement = null;
        
        while (insynset.hasNextLine()) {
            String line = insynset.readLine(); // get the string of this line
            splitline = line.split(","); // get the array of the string in 3 parts {id, synset, def}
            splitelement = splitline[1].split(" "); // get the array of the synset {noun, noun...}
            for (String noun: splitelement) {
                nouncontainer.add(noun); // fill in the Set nouncontainer
            }
            TreeSet<String> nounelement = new TreeSet<String>();
            for (int i = 0; i < splitelement.length; i += 1) {
                nounelement.add(splitelement[i]); // get the synset of each word in the this array
                idsynset.put(Integer.parseInt(splitline[0]), nounelement); 
                if (synsetid.containsKey(splitelement[i])) {
                    synsetid.get(splitelement[i]).add(Integer.parseInt(splitline[0]));
                } else {
                    TreeSet<Integer> helper = new TreeSet<Integer>();
                    helper.add(Integer.parseInt(splitline[0]));
                    synsetid.put(splitelement[i], helper);
                }
            }
        }

        this.hypograph = new Digraph(nouncontainer.size());
    }

    /** fill in the synsetid & id_hyponym */
    private void readHyponym() {
        String[] splitline = null;
        TreeSet<Integer> splitelement = new TreeSet<Integer>();

        while (inhyponym.hasNextLine()) {
            String line = inhyponym.readLine();
            splitline = line.split(",");
            for (int i = 1; i < splitline.length; i += 1) {
                hypograph.addEdge(Integer.parseInt(splitline[0]), Integer.parseInt(splitline[i]));
            }
        }

    }

    public boolean isNoun(String noun) {
        return nouncontainer.contains(noun);
    }

    public Set<String> nouns() {
        return nouncontainer;
    }

    public Set<String> hyponyms(String word) {
        TreeSet<Integer> findIdOfWord = new TreeSet<Integer>();
        Set<Integer> hypoOfId = new TreeSet<Integer>();
        TreeSet<String> findSynsetOfId = new TreeSet<String>();
        TreeSet<String> whatWeReturn = new TreeSet<String>();
        findIdOfWord = synsetid.get(word);
        hypoOfId = GraphHelper.descendants(hypograph, findIdOfWord);
        for (Integer id: hypoOfId) {
            findSynsetOfId = idsynset.get(id);
            for (String w: findSynsetOfId) {
                whatWeReturn.add(w);
            }
        }
        return whatWeReturn;
    }
}
