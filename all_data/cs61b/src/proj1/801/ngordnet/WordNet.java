

package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private In inSynset;
    private In inHyponym;
    private Digraph wordgraph;
    private HashMap<Integer, SynsetNode> synsetMap = new HashMap<Integer, SynsetNode>();
    private Set<String> nouns = new HashSet<String>();
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        
        inSynset = new In(synsetFilename);
        inHyponym = new In(hyponymFilename);

        int id;
        String[] synonyms;
        int[] hyponyms;
        String definition;
        String[] temp;
        int vertices = 0;

        while (inSynset.hasNextChar()) {
            temp = (inSynset.readLine()).split(",");
            id = Integer.parseInt(temp[0]);
            synonyms = temp[1].split(" ");

            for (String arg : synonyms) {
                nouns.add(arg);
            }
            
            definition = temp[2];
            synsetMap.put(id, new SynsetNode(id, synonyms));
            vertices += 1;
        }

        wordgraph = new Digraph(vertices);
        int count;

        while (inHyponym.hasNextChar()) {
            temp = (inHyponym.readLine()).split(",");
            hyponyms = new int[temp.length];
            count = 0;
            for (String hype : temp) {
                hyponyms[count] = Integer.parseInt(hype);
                count += 1;
            }
            for (int i = 1; i < hyponyms.length; i++) {
                wordgraph.addEdge(hyponyms[0], hyponyms[i]);
            }
        }
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (SynsetNode element : synsetMap.values()) {
            if (element.checkWord(noun)) {
                return true;
            }
        }
        return false;

    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;

    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> hypoIDs = new HashSet<Integer>();
        Set<String> hypoALL = new HashSet<String>();
        for (SynsetNode element : synsetMap.values()) {
            if (element.checkWord(word)) {
                hypoIDs.add(element.id);
                for (String elem : element.synonymsArray) {
                    hypoALL.add(elem);
                }
            }
            //send id's to graph helper to get all hyponyms reachable
        }
        hypoIDs = GraphHelper.descendants(wordgraph, hypoIDs);
        SynsetNode temp;
        for (Integer num : hypoIDs) {
            temp = synsetMap.get(num);
            for (String ele : temp.synonymsArray) {
                hypoALL.add(ele);
            }
        }

        hypoALL.add(word);
        return hypoALL;
    }
    //Data Structure for storing synsets
    //maybe add id?
    private class SynsetNode {
        private int id;
        private String[] synonymsArray;

        private SynsetNode(int iden, String[] synonyms) {
            this.synonymsArray = synonyms;  
            this.id =  iden;
        }

        private boolean checkWord(String word) {
            for (String arg: synonymsArray) {
                if (arg.equals(word)) {
                    return true;
                }
            }
            return false;
        }
    }


}
