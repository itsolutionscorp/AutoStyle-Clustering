package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Some Helpful notes:
** Set {
    add (item); --> puts item in set. Does not add duplicates
    contains(item): --> returns whether or not the item is in the set
    items(); --> returns a List of all items in some arb order
*/

public class WordNet {
    private Map<Integer, Set<String>> map1;
    private Map<String, Set<Integer>> map2;
    private String[] splitList;
    private String[] subSplit;
    private Set<String> allNouns;
    private Set<String> nouns;
    private Set<Integer> ids;
    private String[] syns;
    private String[] hyps;
    private Set<Integer> tempVal;
    private Set<Integer> numVals;
    private Integer id;
    private Digraph g;
    private String[] hypsSplit;
    private Set<Integer> returnedHyps;
    private Set<Integer> descendantIds;
    private Set<String> descendants;
    private Set<String> descendantstot;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename)  {
        syns = new In(synsetFilename).readAllLines();
        hyps = new In(hyponymFilename).readAllLines(); // each elem is a line
        map1 = new HashMap<Integer, Set<String>>();
        map2 = new HashMap<String, Set<Integer>>();
        g = new Digraph(syns.length);
        allNouns = new HashSet<String>();
        // Deal with syns
        for (int i = 0; i < syns.length; i++) {
            splitList = syns[i].split(","); //split each line by commas
            subSplit = splitList[1].split(" ");
            nouns = new HashSet<String>();
            id = Integer.parseInt(splitList[0]);
            for (int j = 0; j < subSplit.length; j++) {
                numVals = new HashSet<Integer>();
                allNouns.add(subSplit[j]); // add to total set
                nouns.add(subSplit[j]); // add to not total set
                if (map2.containsKey(subSplit[j])) {
                    tempVal = map2.get(subSplit[j]);
                    tempVal.add(id);
                } else {
                    numVals.add(id);
                    map2.put(subSplit[j], numVals);
                }
             
            }
         
            map1.put(id, nouns);
        }
        for (int i = 0; i < hyps.length; i++) {
            hypsSplit = hyps[i].split(",");
            int v = Integer.parseInt(hypsSplit[0]);
            for (int j = 0; j < hypsSplit.length; j++) {
                int edge = Integer.parseInt(hypsSplit[j]);
                g.addEdge(v, edge);
            }
        }
    } 
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
      // Graph Helper returns a set of descendents
    public Set<String> hyponyms(String word) {
        returnedHyps = map2.get(word);
        descendants = new HashSet();
        descendantstot = new HashSet();
        descendantIds = GraphHelper.descendants(g, returnedHyps);
        for (Integer p: descendantIds) {
            descendants = map1.get(p);
            for (String j: descendants) {
                descendantstot.add(j); 
            }
        }
        return descendantstot;
    }
    
}

