package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
public class WordNet {
    private Digraph wn;
    private HashMap<Integer, String> vals; //index to total string
    private HashMap<String, Set<Integer>> valrev; //strings & splitted strings to index
    private HashMap<String, Integer> dupsvalrev; //when a word under more than one index
    private Set<String> values;
  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
  /** takes in two files and puts them in apppropiate data structure for each one? **/
    public WordNet(String synsetFilename, String hyponymFilename) { 
                //add synsets to hashmaps find number of lines
        vals = new HashMap<Integer, String>();
        valrev = new HashMap<String, Set<Integer>>();
        dupsvalrev = new HashMap<String, Integer>();
        int lines = 0;
        int index = 0;
        In scanSynset = new In(synsetFilename);
        while (scanSynset.hasNextLine()) {
            String[] synlinesplit = scanSynset.readLine().split(",");
            index = Integer.parseInt(synlinesplit[0]);
            vals.put(index, synlinesplit[1]);
                    
            if (synlinesplit[1].indexOf(' ') >= 0) {
                String[] splited = synlinesplit[1].split("\\s+");
                for (int k = 0; k < splited.length; k++) {
                    if (valrev.containsKey(splited[k])) {
                        Set<Integer> vs = valrev.get(splited[k]);
                        vs.add(index);
                        valrev.put(splited[k], vs);
                    } else {
                        Set<Integer> vs = new HashSet<Integer>();
                        vs.add(index);
                        valrev.put(splited[k], vs);
                    }
                }
            } else {
                if (valrev.containsKey(synlinesplit[1])) {
                    Set<Integer> vs = valrev.get(synlinesplit[1]);
                    vs.add(index);
                    valrev.put(synlinesplit[1], vs);
                } else {
                    Set<Integer> vs = new HashSet<Integer>();
                    vs.add(index);
                    valrev.put(synlinesplit[1], vs);
                }
            }
            lines = index + 1;
        }
              
                // create empty digraph with #lines vertices
        wn = new Digraph(lines);
        In scanHyponym = new In(hyponymFilename);
        while (scanHyponym.hasNextLine()) {
            String[] hyps = scanHyponym.readLine().split(",");
            int[] ints = new int[hyps.length];
            ints[0] = Integer.parseInt(hyps[0]);
            for (int k = 1; k < hyps.length; k++) {
                ints[k] = Integer.parseInt(hyps[k]);
                wn.addEdge(ints[0], ints[k]);
            }
        }
    }
              
                
  /* Returns true if NOUN is a word in some synset. */
  /* checks if in synset data structure or hyponym structure */
    public boolean isNoun(String noun) {  
        values = this.nouns();    
        return values.contains(noun);    
    }

    /* Returns the set of all nouns. */
    /*Returns the list of synsets */
    public Set<String> nouns() {
        Set<String> nouns = valrev.keySet();
        return nouns;
    }

    /* Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        if (valrev.containsKey(word)) {
            Set<Integer> thisone = valrev.get(word);
            Set<Integer> vertices = GraphHelper.descendants(wn, thisone);
            Object[] verticesobj = vertices.toArray();
            Integer[] verticesint = new Integer[verticesobj.length];
            for (int l = 0; l < verticesobj.length; l++) {
                verticesint[l] = (Integer) verticesobj[l];
            }
            for (int i = 0; i < verticesint.length; i++) {
                String temp = vals.get(verticesint[i]);
            //hyponyms.add(temp);
                if (temp.indexOf(' ') >= 0) {
              //hyponyms.remove(temp);
                    String[] splited = temp.split("\\s+");
                    for (int k = 0; k < splited.length; k++) {
                        hyponyms.add(splited[k]);
                    }
                } else {
                    hyponyms.add(temp);
                }
            }
          // Object[] vertices = valrev.get(word);
          // String synonyms = vals.get(vertice);
          // if (synonyms.indexOf(' ') >= 0) {
          //   String[] splited = synonyms.split("\\s+");
          //     for (int k = 0; k < splited.length; k++) {
          //       hyponyms.add(splited[k]);
          //     }
          // }
          // else {
          //   hyponyms.add(synonyms);
          // }
        }
      
      
        return hyponyms;
    }
}


