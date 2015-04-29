package ngordnet;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

public class WordNet {
 
    // Fields
    private HashSet<String> nouns;                      //Set of nouns
    private HashMap<String, ArrayList<String>> numKey;  //Map num -> [word]
    private HashMap<String, ArrayList<String>> strKey;  //Map word -> [#]
    private HashMap<String, ArrayList<String>> branch;  //Map num -> [#]
    private HashMap<String, ArrayList<String>> def;     //Map num -> [def]

    // Constructors

    public WordNet(String synsetFile, String hyponymFile) {
        nouns = new HashSet<String>();
        numKey = new HashMap<String, ArrayList<String>>();
        strKey = new HashMap<String, ArrayList<String>>();
        branch = new HashMap<String, ArrayList<String>>();
        def = new HashMap<String, ArrayList<String>>();

        In in = new In(synsetFile);
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String[] holder = line.split(",");
            numKey.put(holder[0], new ArrayList<String>(Arrays.asList(holder[1].split(" "))));
            for (String word: holder[1].split(" ")) {
                if (strKey.containsKey(word)) {
                    strKey.get(word).add(holder[0]);
                } else {
                    strKey.put(word, new ArrayList<String>(Arrays.asList(holder[0])));
                }
                nouns.add(word);
            }
            def.put(holder[0], new ArrayList<String>(Arrays.asList(holder[2])));
        }

        in = new In(hyponymFile);
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String[] holder2 = line.split(",");
            ArrayList<String> put = new 
                ArrayList<String>(Arrays.asList(Arrays.copyOfRange(holder2, 1, holder2.length)));
            if (branch.containsKey(holder2[0])) {
                put.addAll(branch.get(holder2[0]));
            }
            branch.put(holder2[0], put);
        }
    }

    // Methods

    /*
        Method that returns all the hyponyms of a word.

        @param word     String to be assessed for hyponyms
        @return         Set of Strings of all the hyponyms and synonyms of word.
    */
    public Set<String> hyponyms(String word) {
        ArrayList<String> nums = strKey.get(word);
        HashSet<String> result = new HashSet<String>();
        if (!isNoun(word)) {
            return result;
        }
        result.add(word);
        for (String num: nums) {
            for (String w: numKey.get(num)) {
                result.add(w);
            }
        }
        for (String a: strKey.get(word)) {
            if (branch.containsKey(a)) {
                for (String b: branch.get(a)) {
                    result.addAll(hyponym(b));
                }
            }
        }
        return result;
    }

    /*
        Method that returns all the hyponyms of a word called by number.
        
        @param number   String of the number in branch
        @return         Set of Strings of all the hyponyms.
    */
    private Set<String> hyponym(String number) {
        HashSet<String> result = new HashSet<String>();
        for (String a: numKey.get(number)) {
            result.add(a);
        }
        if (branch.containsKey(number)) {
            for (String b: branch.get(number)) {
                result.addAll(hyponym(b));
            }
        }
        return result;
    }

    /*
        Checks to see if the word is a noun.

        @param noun     String of the word passed in to be checked.
        @return         boolean of whether or not the word passed in is a noun.
    */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /*
        Gives the set of nouns.

        @return Set of Strings of the nounts WordNet holds.
    */
    public Set<String> nouns() {
        return nouns;
    }
}
