package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
/**
 * Creates a class that holds synstets and its hyponyms
 * @Author Rohit Braganza
 * All method signature comments courtesy of @Author Josh Hug
 **/
public class WordNet {

    private final int DEFAULT_START_SIZE = 1500;

    //Holds all sysnets as values and their IDs as the keys.
    private HashMap<Integer, TreeSet<String>> synMap  
        = new HashMap<>(DEFAULT_START_SIZE);


    //Holds the hyponym relationships between synsets. 
    private Digraph flowchart;

    /*Holds the IDs of all synsets contianing each noun
     *Used for fast hyponyms() performance. */
    private HashMap<String, TreeSet<Integer>> nounMap
        = new HashMap<>(DEFAULT_START_SIZE);

    //Stores a set of all nouns. Gives constant time on average for isNoun().
    private Set<String> setOfNouns = new HashSet<>(DEFAULT_START_SIZE);
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME 
     * @param synsetFilename
     * @param hyponymFilename
     **/
    public WordNet(String synsetFilename, String hyponymFilename) {
        //Parse through the files.
        In synIn = new In(synsetFilename);
        In hypIn = new In(hyponymFilename);

        //Read each synset line, and store the words of a synset in a TreeSet.
        boolean hasLine = synIn.hasNextLine();
        while (hasLine) {
            String line = synIn.readLine();
            int firstComma = line.indexOf(",");
            int secondComma = line.indexOf(",", firstComma + 1);
            //Retrive the ID number.
            String stringID = line.substring(0, firstComma);
            Integer iD = Integer.parseInt(stringID);
            /*Get individual words, exculding ID & definition
             *All words are between the first and second commas. */
            String lastL = line.substring(firstComma + 1, secondComma);
            String[] words = lastL.split("\\s+"); //words are split by whitespace
            TreeSet<String> nouns = new TreeSet<>(); //holds the words in the synset
            for (String w : words) {
                /*If the noun has already been inputed to nounMap, add
                 *the ID to the list of IDs */
                if (setOfNouns.contains(w)) { //faster than using map's contains method.
                    TreeSet<Integer> hold = nounMap.get(w);
                    hold.add(iD);
                    nounMap.put(w, hold); 
                } else {
                    /*Otherwise, add the new noun and its ID to nounMap.
                     *Add the noun to the setOfNouns as well. */
                    TreeSet<Integer> hold = new TreeSet<>();
                    hold.add(iD);
                    nounMap.put(w, hold);
                    setOfNouns.add(w); 
                }
                nouns.add(w); //Add the noun to the synset's set.
            }
            //Add the synset to the map of all synsets.
            synMap.put(iD, nouns);
            hasLine = synIn.hasNextLine();
        }

        //Create the digraph of hyponyms.
        //The digraph will have the same number of vertices as synsets.
        flowchart = new Digraph(synMap.size());
        boolean hypHasLine = hypIn.hasNextLine();
        while (hypHasLine) {
            String line = hypIn.readLine();
            String[] lNums = line.split(",");
            int[] nums = new int[lNums.length];
            //Convert the line to integers and add them into the array.
            for (int i = 0; i < lNums.length; i++) {
                nums[i] = Integer.parseInt(lNums[i]);
            }
            //create edges
            for (int i = 1; i < nums.length; i++) {
                flowchart.addEdge(nums[0], nums[i]);
            }
            hypHasLine = hypIn.hasNextLine();
        }
    }

    /**
     * Returns true if NOUN is a word in some synset. 
     * @param String noun
     **/
    public boolean isNoun(String noun) {
        return setOfNouns.contains(noun);
    }

    /** Returns the set of all nouns.
     **/
    public Set<String> nouns() {
        return setOfNouns;
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * @param WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) { //if word is not a noun, returns null.
            return null;
        }
        Set<String> hyponyms = new TreeSet<>();

        for (int x : nounMap.get(word)) { //Find synsets with the word.
            Set<Integer> hold = new TreeSet<>();
            hold.add(x);
            //Find all the edges from the word's vertex, and store the ID#'s.
            Set<Integer> locations =
                GraphHelper.descendants(flowchart, hold);
            for (int y : locations) { //search the edge ID's.
                hyponyms.addAll(synMap.get(y)); //Store the hyponyms.
            }
        }
        return hyponyms;
    }           
}

