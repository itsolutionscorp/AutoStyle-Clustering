package ngordnet;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph; 
import java.util.HashMap;
import java.util.HashSet;



public class WordNet extends java.lang.Object {
    private HashMap<Integer, Set<String>> vomitnet;
    private HashMap<String, Set<Integer>> nounfamily;
    private Digraph dianet;
    private int synsetslines = 0;


    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        /*creates a wordnet using files from SYNSETFILENAME 
        and HYPONYMFILENAME 
        hypernym (more general)
        hyponym (more specific)
        change is a hypernym of demotion
        demotion(hyponym) is a type of change(hypernym) */
        In scansynsets = new In(synsetFilename);
        In scanhyponyms = new In(hyponymFilename);
        In copysynsets = scansynsets;
        In copyhyponyms = scanhyponyms;

        /*hashmap below maps ID to a string of synsets for that ID*/
        vomitnet = new HashMap<Integer, Set<String>>();
        nounfamily = new HashMap<String, Set<Integer>>();
        while (copysynsets.hasNextLine() && copysynsets != null) {
            String[] splitbycomma = copysynsets.readLine().split(",");
            int id = Integer.parseInt(splitbycomma[0]);
            String[] synsetarray = splitbycomma[1].split(" ");

            HashSet<String> synsethashset = new HashSet<String>();
            for (String element: synsetarray) {
                synsethashset.add(element);
            }
            /*DOING IT!!!! id: set of words corresponding to id*/
            vomitnet.put(id, synsethashset);
            synsetslines++;
            for (String element: synsetarray) {
                if ((nounfamily == null) || (nounfamily.get(element) == null)) {
                    HashSet<Integer> set = new HashSet<Integer>();
                    set.add(id); /* not sure */
                    nounfamily.put(element, set);
                } else {
                    Set<Integer> intid = nounfamily.get(element);
                    intid.add(id);
                    nounfamily.put(element, intid);
                }
            }
        }

        dianet = new Digraph(synsetslines);
        while (copyhyponyms.hasNextLine()) {
            String[] splitbycomma2 = copyhyponyms.readLine().split(",");
            Integer[] intz = new Integer[splitbycomma2.length];
            intz[0] = Integer.parseInt(splitbycomma2[0]);
            for (int i = 1; i < splitbycomma2.length; i++) {
                intz[i] = Integer.parseInt(splitbycomma2[i]);
                dianet.addEdge(intz[0], intz[i]);
            }
        }
    }



    public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
        /*Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
        If WORD belongs to multiple synsets, return all hyponyms of all of these synsets. 
        See http://goo.gl/EGLoys for an example. Do not include hyponyms of synonyms. */
        Set<String> hyponyms = new HashSet<String>();
        if (isNoun(word)) {
            Set<Integer> value = nounfamily.get(word);
            Set<Integer> value2 = GraphHelper.descendants(dianet, value);
            for (Integer i : value2) {
                hyponyms.addAll(vomitnet.get(i));
            }
        } 
        return hyponyms;
    }

    public boolean isNoun(java.lang.String noun) {
        return nounfamily.containsKey(noun);
    }

    public java.util.Set<java.lang.String> nouns() {
        return nounfamily.keySet();
    }
}
