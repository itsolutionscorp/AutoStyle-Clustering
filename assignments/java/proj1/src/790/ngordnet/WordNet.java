package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ArrayListMultimap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Iterator;

/** this class constructs a structure between synsets (hyponyms and hypernyms)
  * @ author Lichang Xu
 */

public class WordNet {
    /** this is the constructor.
      * create a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME.
      */
    private Set<String> allset; // the set that stores all words in wordnet
    private TreeSet<String> synset; // the set that stores each line synset
    private Map<Integer, TreeSet<String>> map;
    private Multimap<String, Integer> map2;
    private Digraph tree; // store data in hyponym file

    public WordNet(String synsetFilename, String hyponymFilename) {
        // first initialize all the instance variables
        allset = new HashSet<String>();
        synset = new TreeSet<String>();
        map = new HashMap<Integer, TreeSet<String>>();
        map2 = ArrayListMultimap.create();

        // first use In class to read all the data in two files
        In reader1 = new In(synsetFilename);
        In reader2 = new In(hyponymFilename);

        // use reader1 to read data in the synset file
        while (reader1.hasNextLine()) {
            String line1 = reader1.readLine(); // read each line of the synsetfile
            // sort each line and store data
            String[] sortline = line1.split(",");
            String num = sortline[0];
            int id = Integer.parseInt(num); // the id for each synset
            String word = sortline[1];
            // sort the word based on space
            String[] word2 = word.split("\\s");
            // add word2 into the set
            for (int i = 0; i < word2.length; i++) {
                String temp = word2[i]; //individual word
                synset.add(temp); // fill synset
                allset.add(temp); // add all words in allset
                map2.put(temp, id); // add each word as key and ID as value
            } //finish filling each line synset
            map.put(id, synset); // add id and synset in map
            synset = new TreeSet<String>();
        }
            
        // use Digraph ADT to store data in the hyponym file
        int size = map.size();
        tree = new Digraph(size); // construct a new digraph
        while (reader2.hasNextLine()) {
            String line2 = reader2.readLine(); // read each line of hyponym file
            String[] sortline2 = line2.split(",");
            String num2 = sortline2[0];
            int id1 = Integer.parseInt(num2);
            for (int j = 1; j < sortline2.length; j++) {
                String temp = sortline2[j];
                int id2 = Integer.parseInt(temp);
                tree.addEdge(id1, id2); 
            }
        }
    }


    /** this method returns whether a word is a noun
      */
    public boolean isNoun(String noun) {
        if (allset.contains(noun)) {
            return true;
        }
        return false;
    }

    /** this method returns all nouns of the synset 
      * and store them in a set.
     */
    public Set<String> nouns() {
        return allset;
    }

    /** this method shoud return all the hyponysms of word as well as all synonyms of word
      * and store them in a set.
      * If word belongs to multiple synsets, return all hyponyms of all of these synsets.
      * Don't include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> hypo = new HashSet<String>();
        Set<Integer> inte = new TreeSet<Integer>(); // the list that stores all the IDs of the word
        Set<Integer> hypoid = new HashSet<Integer>();
        //first get the ID of the word and add it into inte
        Iterator<Integer> it = map2.get(word).iterator();
        while (it.hasNext()) {
            int in = it.next();
            inte.add(in);
        }
        //get all the ids for word's hyponyms in a set
        hypoid = GraphHelper.descendants(tree, inte);
        //loop through elements in the set
        Iterator<Integer> runner = hypoid.iterator();
        while (runner.hasNext()) {
            int hypid = runner.next(); // each hyponym and synonym ID
            Set<String> synset1 = new TreeSet<String>(); // get their corresponding synset
            synset1 = map.get(hypid);
            Iterator<String> runner1 = synset1.iterator();
            while (runner1.hasNext()) {
                String temp2 = runner1.next(); // each word in the synset
                hypo.add(temp2);
            }
        }
        return hypo;
    }

}
