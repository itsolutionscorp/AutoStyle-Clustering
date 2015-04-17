package ngordnet;
import java.lang.String;
import java.util.*;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, Set<String>> synsetMapper;
    private Map<String, Set<Integer>> revrseSynsetMapper;
    private Digraph hypGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synfil = new In(synsetFilename);
        In hypfil = new In(hyponymFilename);
        // will use this later
        synsetMapper = new HashMap<Integer, Set<String>>();// creates the map
        revrseSynsetMapper = new HashMap<String, Set<Integer>>();

        int vertexCounter = 0;
        while (synfil.hasNextLine()) {
            String synslineHolder = synfil.readLine();
            String[] A = synslineHolder.split(","); // the first array and the
                                                    // main one
            int syn_id = Integer.parseInt(A[0]);// storing the beginning into a
                                                // variable
            String[] arr1 = A[1].split(" ");// split according to space and
                                            // store in the arr1
            int max = arr1.length;// length of the arry to loop in the for loop
            Set<String> syno_set = new HashSet<String>();// creating a new set
            Set<Integer> syno_set_int = new HashSet<Integer>();
            // the reverse
            String reverseFirstWord = arr1[0];
            for (int ite = 0; ite < max; ite++) {
                Set<Integer> inthelper = new HashSet<>();
                syno_set.add(arr1[ite]);// adding the elements of array into the
                                        // set
                syno_set_int.add(syn_id);
                if (!revrseSynsetMapper.containsKey(arr1[ite])) {
                    revrseSynsetMapper.put(arr1[ite], inthelper);
                }
                revrseSynsetMapper.get(arr1[ite]).add(syn_id);
            }
            synsetMapper.put(syn_id, syno_set);// adding stuff in to the map
            vertexCounter += 1;// declaring the size of the Diagraph
        }
        hypGraph = new Digraph(vertexCounter);
        while (hypfil.hasNextLine()) {
            String hypoLineHolder = hypfil.readLine();
            String[] B = hypoLineHolder.split(",");
            int hyp_id = Integer.parseInt(B[0]);
            int max = B.length;
            for (int ite = 1; ite < max; ite++) {
                int sec_Hyp = Integer.parseInt(B[ite]);
                hypGraph.addEdge(hyp_id, sec_Hyp);
            }
        }

    }

    public boolean isNoun(String noun) {
        return revrseSynsetMapper.containsKey(noun);
    }

    public Set<String> nouns() {
        return revrseSynsetMapper.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */

    public Set<String> hyponyms(String word) {
        Set<Integer> wordIds = revrseSynsetMapper.get(word);
        Set<String> superSet = new HashSet<String>();
        Set<Integer> descendant = GraphHelper.descendants(hypGraph, wordIds);
        Iterator<Integer> iter = descendant.iterator();
        while (iter.hasNext()) {
            Set<String> string_word = synsetMapper.get(iter.next());
            Iterator<String> A = string_word.iterator();
            while (A.hasNext()) {
                superSet.add(A.next());
            }
        }
        return superSet;
    }
}
