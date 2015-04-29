package ngordnet;

// ADTs
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

// DiGraph Imports
import edu.princeton.cs.algs4.Digraph;

//Std.In
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private ArrayList<String> allWords = new ArrayList<String>();
    private TreeMap<String, ArrayList<Integer>> mapStrToIntList 
        = new TreeMap<String, ArrayList<Integer>>();
    private TreeMap<Integer, String[]> mapIntToStrArr = new TreeMap<Integer, String[]>();

    private Digraph dg;
    private int vertices = 0;

    // WORD -> Synonyms
    // WORD -> (Descriptions) hyponyms   [Can be accomplished using DiGraph]
    // Word -> MULTIPLE IDs
    public WordNet(String synsetFilename, String hyponymFilename) {
        parseFile(synsetFilename, "syn");
        parseFile(hyponymFilename, "hyp");
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allWords.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        // move arraylist into String Set.
        Set<String> allNouns = new HashSet<String>(allWords);
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> idList = mapStrToIntList.get(word);
        Set<Integer> synset = new HashSet<Integer>();
        Set<String> hyponymsANDsynonyms = new HashSet<String>();
        if (idList == null) {
            return hyponymsANDsynonyms;
        }
        Set<Integer> hyponymIdSet = GraphHelper.descendants(dg, new HashSet<Integer>(idList));
        for (Integer i : hyponymIdSet) {
            String[] hyponymArr = mapIntToStrArr.get(i);
            Set<String> hyponymStrSet = new HashSet<String>(Arrays.asList(hyponymArr));
            for (String w : hyponymStrSet) {
                hyponymsANDsynonyms.add(w);
            }
        }
        return hyponymsANDsynonyms;
    }


    /************************************************************************
    Custom Private Helper Methods
    ************************************************************************/
    /* This method checks whether the file can be parsed. If
     it can, then pass to parseSYN or parseHYP */
    private void parseFile(String filename, String type) {
        In file = new In(filename);
        String line = file.readLine();
        if (type.equals("hyp")) {
            dg = new Digraph(vertices);
        }
        while (line != null) {
            String[] parts = line.split(",");
            if (type.equals("syn")) {
                vertices += 1;
                parseSYN(line, parts);
            } else if (type.equals("hyp")) {
                parseHYP(line, parts);
            } else {
                throw new IllegalArgumentException("Cannot read file.");
            }
            line = file.readLine();
        }
    }

    private void parseSYN(String line, String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String[] words = parts[1].split(" ");
        for (int i = 0; i < words.length; i += 1) {
            allWords.add(words[i]);
            ArrayList<Integer> idList;
            idList = mapStrToIntList.get(words[i]);
            if (idList == null) {
                idList = new ArrayList<Integer>();
            }
            idList.add(id);
            mapStrToIntList.put(words[i], idList);
        }
        mapIntToStrArr.put(id, words);
    }
    /* Take the first part and label "id".
    *  Take the rest as "id" INTEGERS
    *  E.G. - 273,63716,19590
    */
    private void parseHYP(String line, String[] parts) {
        int id = Integer.parseInt(parts[0]);
        int[] hyponyms = new int[parts.length - 1];
        for (int i = 1; i < parts.length; i += 1) {
            int synsetID = Integer.parseInt(parts[i]);
            dg.addEdge(id, synsetID);
            hyponyms[i - 1] = synsetID;
        }
        ArrayList<Integer> listOfIdNums = new ArrayList<Integer>();
        for (int idValue : hyponyms) {
            listOfIdNums.add(idValue);
        }
        mapStrToIntList.put(parts[0], listOfIdNums);
    }

}
