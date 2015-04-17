package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private HashMap<Integer, String> teemo = new HashMap<Integer, String>();
    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        In synset = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);

        String[] stringraw = null;
        String stringline = null;
         
        while (!synset.isEmpty()) {
            stringline = synset.readLine();
            stringraw = stringline.split(",");
            stringraw = stringline.split(",");
            teemo.put(Integer.parseInt(stringraw[0]), stringraw[1]);
        }
        graph = new Digraph(Integer.parseInt(stringraw[0] + 1));

        while (!hyponyms.isEmpty()) {
            String intline = hyponyms.readLine();
            String[] intraw = intline.split(",");
            int[] intclean = new int[intraw.length];
            for (int j = 0; j < intclean.length; j++) {
                intclean[j] = Integer.parseInt(intraw[j]);
            }
            // System.out.println(intclean[0]);
            // System.out.println(intclean[1]);
            for (int n = 1; n < intclean.length; n++) {
                graph.addEdge(intclean[0], intclean[n]);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> valueSet = nouns();
        return valueSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> valueSet = new HashSet();
        String[] stringraw;
        for (int index = 0; index < teemo.size(); index++) {
            stringraw = teemo.get(index).split(" ");
            for (int i = 0; i < stringraw.length; i++) {
                valueSet.add(stringraw[i]);
            }
        }
        return valueSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    private Set<Integer> findkey(String val) {
        Set<Integer> answer = new HashSet();
        String[] temp;
        for (int i = 0; i < teemo.size(); i++) {
            temp = teemo.get(i).split(" ");
            for (int j = 0; j < temp.length; j++) {
                if (val.equals(temp[j])) {
                    answer.add(i);
                }
            }
        }
        return answer;
    }

    public Set<String> hyponyms(String word) {
        Set<String> answer = new HashSet();
        String[] split;
        Set<Integer> set = findkey(word);
        Set<Integer> keySet = GraphHelper.descendants(graph, set);
        Object[] hi = keySet.toArray();
        Object[] temp = keySet.toArray();
        for (int i = 0; i < temp.length; i++) {
            split = teemo.get(temp[i]).split(" ");
            for (int j = 0; j < split.length; j++) {
                answer.add(split[j]);
            }
        }
        return answer;
    }
}
