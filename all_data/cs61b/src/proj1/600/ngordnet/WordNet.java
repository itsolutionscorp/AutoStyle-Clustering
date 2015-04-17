// must read in the two files
// create an ADT to store and organize the info from the files
package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/* Definitions:
synset - sets of synonyms (group of words that have same meaning), 
words can belong to multiple synsets,
can include collocations ( words that appear next to each other 
so often that they are considered one word)
hyponym - more specific synset (change is a hyponym of action because change is-a action)
hypernym - more general synset (change is a hypernym of demotion since demotion is-a change)
*/

// will use a set of strings to store synsets, will use a map to map the synset id hyponyms

public class WordNet {
    private int synsetID;
    private ArrayList<String> synset;
    private ArrayList<Integer> ids;
    private HashMap<Integer, ArrayList<String>> sMap;
    private HashMap<String, ArrayList<Integer>> wordIDMap;
    private HashMap<Integer, ArrayList<Integer>> hypoMap;
    private String currWord;
    private char currChar;
    private HashSet<String> nounsList;
    private HashSet<String> hyponyms;
    private ArrayList<String> hypoFinds;

    // Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
    // read in synsetFilename, storing the second field as a list of strings
    // maps id to list of synonyms
    // stores a set of all unique words
    // maps each synonym to set of ids
    public WordNet(String synsetFilename, String hyponymFilename) {
        In sInput = new In(synsetFilename);
        sMap = new HashMap<Integer, ArrayList<String>>(1);
        nounsList = new HashSet<String>(1);
        wordIDMap = new HashMap<String, ArrayList<Integer>>(1);
        while (sInput.hasNextLine()) {
            // read first number
            currChar = sInput.readChar();
            String sIDStr = Character.toString(currChar);
            currChar = sInput.readChar();
            synset = new ArrayList<String>(1);
            ids = new ArrayList<Integer>(1);
            // read the synset id
            while (currChar != ',') {
                sIDStr = sIDStr + Character.toString(currChar);
                currChar = sInput.readChar();
            }
            // set the synset id
            synsetID = Integer.parseInt(sIDStr);
            // read the first synonym
            currWord = readSynonyms(currWord, sInput);
            // add to synonym list
            synset.add(currWord);
            // add to set of all nouns
            nounsList.add(currWord);
            // add Word to ID map
            if (wordIDMap.containsKey(currWord)) {
                ids = wordIDMap.get(currWord);
                ids.add(synsetID);
            } else {
                ids.add(synsetID);
            }
            HashSet<Integer> tempSet = new HashSet<Integer>();
            tempSet.addAll(ids);
            ids.clear();
            ids.addAll(tempSet);
            wordIDMap.put(currWord, ids);
            currChar = getCurrChar();
            // check if more than one synonym per ID, if there are add to the arrayList
            while (currChar == ' ') {
                ids = new ArrayList<Integer>(1);
                currWord = readSynonyms(currWord, sInput);
                synset.add(currWord);
                nounsList.add(currWord);
                if (wordIDMap.containsKey(currWord)) {
                    ids = wordIDMap.get(currWord);
                    ids.add(synsetID);
                } else {
                    ids.add(synsetID);
                }
                HashSet<Integer> tempSet2 = new HashSet<Integer>();
                tempSet2.addAll(ids);
                ids.clear();
                ids.addAll(tempSet2);
                wordIDMap.put(currWord, ids);
                currChar = getCurrChar();
            }
            // map the id to the synset and read through rest of line
            sMap.put(synsetID, synset);
            sInput.readLine();
        }
        // read in hyponymFilename, storing it as a map
        In iInput = new In(hyponymFilename);
        hypoMap = readHypFile(iInput);
    }

    private HashMap<Integer, ArrayList<Integer>> readHypFile(In iInput) {
        hypoMap = new HashMap<Integer, ArrayList<Integer>>(1);
        while (iInput.hasNextLine()) {
            currChar = iInput.readChar();
            String sIDStr = Character.toString(currChar);
            currChar = iInput.readChar();
            // read in first number
            while (currChar != ',') {
                sIDStr = sIDStr + Character.toString(currChar);
                currChar = iInput.readChar();
            }
            // set the synset id
            synsetID = Integer.parseInt(sIDStr);
            currChar = getCurrChar();
            // read the rest of the numbers
            String restofLine = iInput.readLine();
            String[] strArr = new String[restofLine.length()];
            strArr = restofLine.split(",");
            ids = new ArrayList<Integer>(1);
            // add into arraylist and put into map
            for (String x : strArr) {
                ids.add(Integer.parseInt(x));
            }
            if (hypoMap.containsKey(synsetID)) {
                ArrayList<Integer> temp = hypoMap.get(synsetID);
                for (Integer j: ids) {
                    temp.add(j);
                }
                hypoMap.put(synsetID, temp);
            } else {
                hypoMap.put(synsetID, ids);
            }
        }
        return hypoMap;
    }

    private String readSynonyms(String word, In sInput) {
        currChar = sInput.readChar();
        word = Character.toString(currChar);
        while (true) {
            currChar = sInput.readChar();
            if (currChar == ',' || currChar == ' ') {
                break;
            }
            word = word + Character.toString(currChar);
        }
        return word;
    }

    private char getCurrChar() {
        return currChar;
    }

    
    // Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
    // If WORD belongs to multiple synsets, return all hyponyms of all of these synsets. 
    // See http://goo.gl/EGLoys for an example. Do not include hyponyms of synonyms.

    // put in synonyms first by connecting input word to key in hyponym map, then putting correct
    // set in sMap into hyponym
    // then, put in hyponyms by connecting input word to key in hyponym map, then connecting
    // those values to the correct sets in sMap, then putting them in hyponyms
    public Set<String> hyponyms(String word) {
        // converts word to its synsets ids, instantiate lots of objects
        ArrayList<ArrayList<Integer>> hypsOfWord = new ArrayList<ArrayList<Integer>>(1);
        ArrayList<Integer> idsOfWord = new ArrayList<Integer>(1);
        hyponyms = new HashSet<String>(1);
        hypoFinds = new ArrayList<String>(1);
        idsOfWord = wordIDMap.get(word);
        Integer[] idsOfWordArr = new Integer[idsOfWord.size()];
        ArrayList<String> returnHyps = new ArrayList<String>(1);
        idsOfWordArr = idsOfWord.toArray(idsOfWordArr);
        ArrayList<Integer> syns = new ArrayList<Integer>(idsOfWordArr.length);
        ArrayList<String> strSyns = new ArrayList<String>(1);
        // gets the hyponym ids
        for (int x: idsOfWordArr) {
            syns.add(x);
            if (!hypoMap.containsKey(x)) {
                continue;
            }
            hypsOfWord.add(hypoMap.get(x));
        }
        // add synonyms of word;
        for (int u: syns) {
            strSyns = sMap.get(u);
            String[] strSynsArr = new String[strSyns.size()];
            strSynsArr = strSyns.toArray(strSynsArr);
            for (String b: strSynsArr) {
                hyponyms.add(b);
            }
        }
        // if word has hyponyms, call hyponym finder on the hyponyms
        // turns it into an array of the hyponym sets
        if (!hypsOfWord.isEmpty()) {
            returnHyps = hyponymFinder(hypsOfWord);
            String[] returnHypsArr = new String[returnHyps.size()];
            returnHypsArr = returnHyps.toArray(returnHypsArr);
            for (String x : returnHypsArr) {
                hyponyms.add(x);
            }
        }
        return hyponyms;
    }

    private ArrayList<String> hyponymFinder(ArrayList<ArrayList<Integer>> hypsOfWord) {
        ArrayList<Integer> hypsList = new ArrayList<Integer>(1);
        ArrayList<String> hypsStrList = new ArrayList<String>(1);
        ArrayList<ArrayList<String>> strsOfHyps = new ArrayList<ArrayList<String>>(1);
        ArrayList<ArrayList<Integer>> newHyps = new ArrayList<ArrayList<Integer>>(1);
        for (ArrayList<Integer> x : hypsOfWord) {
            Integer[] i = new Integer[x.size()];
            i = x.toArray(i);
            for (Integer r : i) {
                hypsList.add(r);
            }
        }
        Integer[] hypsListArr = new Integer[hypsList.size()];
        hypsListArr = hypsList.toArray(hypsListArr);
        // we now have an array of the ids of the hyponyms
        for (int y: hypsListArr) {
            strsOfHyps.add(sMap.get(y));
        }
        for (ArrayList<String> x : strsOfHyps) {
            String[] i = new String[x.size()];
            i = x.toArray(i);
            for (String r : i) {
                hypsStrList.add(r);
            }
        }
        String[] hypsStrListArr = new String[hypsStrList.size()];
        hypsStrListArr = hypsStrList.toArray(hypsStrListArr);
        // we now have an array of the hyponyms
        for (String z : hypsStrListArr) {
            hypoFinds.add(z);
        }
        // if a word has a hyponym, recursive call
        for (int y : hypsListArr) {
            if (hypoMap.containsKey(y)) {
                newHyps.add(hypoMap.get(y));
            } 
        }
        if (!newHyps.isEmpty()) {
            hyponymFinder(newHyps);
        }
        return hypoFinds;
    }

    // Returns true if NOUN is a word in some synset.
    public boolean isNoun(String noun) {
        return nounsList.contains(noun);
    }

    // returns the set of all nouns
    public Set<String> nouns() {
        return nounsList;
    }
}

