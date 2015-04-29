package ngordnet;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<String, TreeSet<Integer>> synsetsMap;
    private Digraph dg;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        File synsetsFile = new File(synsetFilename);
        File hyponymsFile = new File(hyponymFilename);
        try {
            Scanner synsets = new Scanner(synsetsFile).useDelimiter(",");
            synsetsMap = new HashMap<String, TreeSet<Integer>>();

            int id;
            int numberOfSynsets = 0;    // # of vertices in Digraph;
            StringTokenizer words;
            String word;
            while (synsets.hasNextLine()) {
                id = synsets.nextInt();
                words = new StringTokenizer(synsets.next());
                while (words.hasMoreTokens()) {
                    word = words.nextToken();
                    if (synsetsMap.containsKey(word)) {
                        // synsetsMap.get(word) will return the TreeSet
                        synsetsMap.get(word).add(id);
                    } else {
                        TreeSet<Integer> newID = new TreeSet<Integer>();
                        newID.add(id);
                        synsetsMap.put(word, newID);
                    }
                }
                numberOfSynsets += 1;
                synsets.nextLine();  // Skipping the definition of the synset
            }
            synsets.close();


            dg = new Digraph(numberOfSynsets);
            Scanner hyponyms = new Scanner(hyponymsFile);
            /*  1. break lines into Stringtokenizer using hyponyms.nextLine()
                2. use Stringtokenizer with delim(",") to next()
                3. Convert String into Integer using Integer.parseInt(...)  
                4. Use addEdge  */
            StringTokenizer line;
            int hypernymID;
            while (hyponyms.hasNext()) {
                line = new StringTokenizer(hyponyms.nextLine(), ",");
                hypernymID = Integer.parseInt(line.nextToken());
                while (line.hasMoreTokens()) {
                    dg.addEdge(hypernymID, Integer.parseInt(line.nextToken()));
                }
            }
            hyponyms.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsetsMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetsMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        /*  Use GraphHelper.descendants */
        Set<String> toReturn = new TreeSet<String>();
        if (!synsetsMap.containsKey(word)) {
            return null;
        }
        Set<Integer> descendantsOfWord = GraphHelper.descendants(dg, synsetsMap.get(word));
        for (int id : descendantsOfWord) {
            for (Map.Entry<String, TreeSet<Integer>> entry : synsetsMap.entrySet()) {
                String hyponym = entry.getKey();
                TreeSet<Integer> idSet = entry.getValue();
                if (idSet.contains(id)) {
                    toReturn.add(hyponym);
                }
            }
        }
        return toReturn;
    }
}
