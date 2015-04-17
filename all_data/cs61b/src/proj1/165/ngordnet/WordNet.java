package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
// import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {

    private In synsets;
    private In hypos;
    private HashMap<String, Set<Integer>> map1 = new HashMap();
    private HashMap<Integer, Set<String>> map2 = new HashMap();
    private Set<Integer> idNumbers = new TreeSet<Integer>();
    private Set<String> nouns = new TreeSet<String>();
    private Digraph d;
    private Set<Integer> vertices = new TreeSet<Integer>();


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        int count = 0;
        synsets = new In(synsetFilename);
        hypos = new In(hyponymFilename);

        // Create Sets of idNumbers and nouns from synset file
        if (synsets.isEmpty()) {
            throw new RuntimeException("Empty sysnsetFilename: no nouns");
        } else {
            String[] readLines = synsets.readAllLines();
            for (int i = 0; i < readLines.length; i++) {
                String[] infoArray = readLines[i].split(","); 
                int idNumber = Integer.parseInt(infoArray[0]);
                idNumbers.add(idNumber);
                String[] nounsArray = infoArray[1].split(" ");




                // map1.put(infoArray[1], idNumber);
                // map2.put(idNumber, infoArray[1]);


                for (String noun : nounsArray) {
                    if (!map2.containsKey(idNumber)) {
                        Set<String> stringSet = new HashSet<String>();
                        map2.put(idNumber, stringSet);
                    }
                    map2.get(idNumber).add(noun);
                    if (!map1.containsKey(noun)) {
                        Set<Integer> intSet = new HashSet<Integer>();
                        map1.put(noun, intSet);
                    }
                    map1.get(noun).add(idNumber);
                }
                count += 1;
            }  
        }
        // Create Digraph for Hyponyms
        if (hypos.isEmpty()) {
            // DOES THIS END MY PROGRAM, I JUST WANT A WARNING MESSAGE FOR THESE!
            throw new RuntimeException("Empty hyponymFilename: no hyponyms");
        } else {
            d = new Digraph(count); 
            while (hypos.hasNextLine()) {
                String usplit = hypos.readLine();
                String[] infoArray = usplit.split(","); 
                int vert = Integer.parseInt(infoArray[0]);
                for (int j = 1; j < infoArray.length; j++) {
                    d.addEdge(vert, Integer.parseInt(infoArray[j])); 
                }                       
            }
        }
    }     

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return map1.containsKey(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return map1.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new TreeSet<String>();
        // Set<Integer> hypoNumbers = new TreeSet<Integer>();
        // Set<String> checker = map1.keySet();
        // Iterator<String> iter = checker.iterator();
        // while (iter.hasNext()) {
        //     String unsplit = iter.next();
        //     int edgeFinder = map1.get(unsplit);
        //     String[] splitArray = unsplit.split(" "); 
        //     for (String check : splitArray) {
        //         if (word.equals(check)) {
        //             int hyponym = edgeFinder;
        //             hypoNumbers.add(hyponym);
        //         }
        //     }
        // }


        Set<Integer> hypoNums2 = GraphHelper.descendants(d, map1.get(word));
        for (Integer iter: hypoNums2) {
            Set<String> set = map2.get(iter);
            for (String iter2: set) {
                hyponyms.add(iter2);
            }
        }
        // Iterator<Integer> hypoIter = hypoNums2.iterator();
        // while (hypoIter.hasNext()) {
        //     int getter = hypoIter.next();
        //     String unsplits = map2.get(getter);
        //     String[] splitsArray = unsplits.split(" ");
        //     for (int c = 0; c < splitsArray.length; c++) {
        //         hyponyms.add(splitsArray[c]);
        //     }
        // }



        return hyponyms;   
    }
}
