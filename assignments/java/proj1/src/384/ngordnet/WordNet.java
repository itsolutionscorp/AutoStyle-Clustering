package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;

public class WordNet extends Object {

    private String synsetFile;
    private String hyponymFile;
    private HashMap<String, ArrayList<Integer>> nounToNums;    
    private HashMap<Integer, String[]> numToNouns;
    private ArrayList<Integer> intBox;
    private ArrayList<Integer[]> numSet;

    /* Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = synsetFilename;
        hyponymFile = hyponymFilename;
        intBox = new ArrayList<Integer>();
        numSet = new ArrayList<Integer[]>();
        Set<String> nounList = nouns();
    }

    /* Returns whether NOUN exists in SYNSETFILENAME */

    public boolean isNoun(String noun) {
        Set<String> nounSet = nouns();
        return nounSet.contains(noun);
    }

    /* Creates a TreeSet of nouns.
     * Citations:
     * http://stackoverflow.com/questions/10060804/how-to-check-if-string-contains-character
     */

    public Set<String> nouns() {
        Set<String> lineSet = new TreeSet<String>();
        In in = new In(synsetFile);
        String nextLine;
        while ((nextLine = in.readLine()) != null) {
            lineSet.add(nextLine);
        }
        nounToNums = new HashMap<String, ArrayList<Integer>>();
        numToNouns = new HashMap<Integer, String[]>();
        for (String line : lineSet) {
            String [] parts = line.split("\\,");
            int num = Integer.parseInt(parts[0]);
            String wordsss = parts[1];
            String [] nounsss = wordsss.split("\\ "); 
            numToNouns.put(num, nounsss);
            for (String noun : nounsss) {
                if (!(nounToNums.containsKey(noun))) {          
                    ArrayList<Integer> numArr = new ArrayList<Integer>(); 
                    numArr.add(num);                              
                    nounToNums.put(noun, numArr);              
                } else {                                          
                    (nounToNums.get(noun)).add(num);               
                }
            }
        }
        return nounToNums.keySet();
    }

    /* Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these synsets.
     * Do not include hyponyms of synonyms 
     */

    public Set<String> hyponyms(String word) {
        Set<String> lineSet = new HashSet<String>();        
        In in = new In(hyponymFile);
        String nextLine;
        while ((nextLine = in.readLine()) != null) {
            lineSet.add(nextLine);
        }
        for (String line : lineSet) {                        
            String [] parts = line.split("\\,");            
            int counter = 0;
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (String curr : parts) {
                int num = Integer.parseInt(parts[counter]);
                arr.add(num);
                counter++;
            }
            numSet.add(arr.toArray(new Integer [] {1}));
        }
        ArrayList<Integer> currNumArr = nounToNums.get(word); 
        for (int currNum : currNumArr) {                          
            finder(currNum);                                       
        }
        Set<String> nounSet = new HashSet<String>();                        
        for (Integer i : intBox) {
            String [] currSet = numToNouns.get(i);
            for (String j : currSet) {
                nounSet.add(j);
            }
        }
        numSet.clear();
        intBox.clear();
        return nounSet;
    }

    /* Adds IDs of hyponyms to intBox */

    private void finder(Integer curr) {
        int counter = 0;
        intBox.add(curr);
        while (counter < numSet.size()) {
            if (numSet.get(counter)[0].equals(curr)) {
                for (int i = 1; i < (numSet.get(counter)).length; i++) {
                    finder(numSet.get(counter)[i]);
                }
            }
            counter++;
        }
    }
}
