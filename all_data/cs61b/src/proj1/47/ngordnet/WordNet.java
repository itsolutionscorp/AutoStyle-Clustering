package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.Scanner; 
import java.util.HashSet;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
    private HashSet<String> nouns;
    private Digraph wnDigraph;
    private HashMap<Integer, String> idSynsets;
    private HashMap<String, HashSet<Integer>> synsetsID;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        nouns = new HashSet<String>();
        idSynsets = new HashMap<Integer, String>();
        synsetsID = new HashMap<String, HashSet<Integer>>();
        int verticeNumber = 0; 
        Scanner s = new Scanner(synsetFilename); 
        /**Credit to 
        http://stackoverflow.com/questions/11963563/reading-utf-8-characters-using-scanner 
        for telling how to read file using Scanner */
        try {
            s = new Scanner(new File(synsetFilename), "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        s.useDelimiter(",");
        while (s.hasNextLine()) {
            int id = s.nextInt();
            String synset = s.next();
            String[] words = synset.split(" ");
            for (String w : words) {
                nouns.add(w);
            }
            
            idSynsets.put(id, synset);
            if (synsetsID.get(synset) == null) {
                synsetsID.put(synset, new HashSet<Integer>());
            }
            synsetsID.get(synset).add(id);
            verticeNumber += 1;
            s.nextLine();       
        }
        wnDigraph = new Digraph(verticeNumber);
        Scanner h = new Scanner(hyponymFilename); 
        try {
            h = new Scanner(new File(hyponymFilename), "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        while (h.hasNextLine()) {
            String line = h.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            int hyperID = lineScanner.nextInt();
            String hypernym = idSynsets.get(hyperID);
            while (lineScanner.hasNextInt()) {
                int hypoID = lineScanner.nextInt();
                wnDigraph.addEdge(hyperID, hypoID); 
            }                
        }

    }
        

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
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
        Set<Integer> wordIn = new HashSet<Integer>();
        for (String w : synsetsID.keySet()) {
            String[] split = w.split(" ");
            for (String s : split) {
                if (word.equals(s)) {
                    for (Integer i : synsetsID.get(w)) {
                        wordIn.add(i);
                    }
                }
            }
        }
        Set<Integer> hypos = GraphHelper.descendants(wnDigraph, wordIn);
        Set<String> converted = new HashSet<String>();
        for (Integer hypo : hypos) {
            converted.add(idSynsets.get(hypo));
        }
        Set<String> splitConverted = new HashSet<String>();
        for (String hypo : converted) {
            String[] splitHypo = hypo.split(" ");
            for (String s : splitHypo) {
                splitConverted.add(s);
            }
        }
        return splitConverted;
    }

}
