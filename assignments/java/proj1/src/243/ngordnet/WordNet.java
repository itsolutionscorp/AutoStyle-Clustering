package ngordnet; 
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph; 
import java.util.HashMap;
import java.util.HashSet; 
import java.util.Set;  

public class WordNet {

    private In synsetIn; 
    private In synsetIn2; 
    private In hyponymIn; 
    private In hyponymIn2; 
    private In hyponym3; 
    private HashMap<String, HashSet<Integer>> wordToNum; 
    private HashMap<Integer, HashSet<String>> numberToWord; 
    private Digraph numberHypos; 

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetIn = new In(synsetFilename); 
        synsetIn2 = new In(synsetFilename); 
        hyponymIn = new In(hyponymFilename); 
        hyponymIn2 = new In(hyponymFilename); 
        hyponym3 = new In(hyponymFilename); 
        wordToNum = wordToNumber(synsetIn); 
        numberToWord = numberToWord(synsetIn2);
        numberHypos = numberToNumber(hyponymIn2); 
    }

    private Digraph numberToNumber(In in) {
        In saved = hyponym3; 
        int count = 0; 
        int currentInt = 0; 
        while (in.hasNextLine()) {
            String line = in.readLine(); 
            String[] nums = line.split(","); 
            for (String x : nums) {
                count += 1; 
            }
        }
        Digraph numbers = new Digraph(count); 
        while (saved.hasNextLine()) {
            String line = saved.readLine(); 
            String[] nums = line.split(","); 
            int firstInt = 0; 
            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    firstInt = Integer.parseInt(nums[i]); 
                } else {
                    numbers.addEdge(firstInt, Integer.parseInt(nums[i])); 
                }
            }
        } return numbers; 
    }

    private HashMap<Integer, HashSet<String>> numberToWord(In in) {
        HashMap<Integer, HashSet<String>> temp = new HashMap<Integer, HashSet<String>>(); 
        String currString = ""; 
        String number = ""; 
        HashSet<String> words = new HashSet<String>(); 
        boolean comma = false; 
        while (in.hasNextLine()) {
            String line = in.readLine(); 

            for (int i = 0; i < line.length(); i++) {

                char ch = line.charAt(i); 
                String str = String.valueOf(ch); 

                if (str.equals(",")) {
                    if (!comma) {
                        comma = true; 
                    } else {
                        words.add(currString); 
                        Integer num = Integer.parseInt(number); 
                        temp.put(num, words);
                        comma = false; 
                        currString = ""; 
                        number = ""; 
                        words = new HashSet<String>();  
                        break;  
                    }
                } else if (ch == ' ') {
                    words.add(currString); 
                    currString = ""; 
                } else {
                    if (!comma) {
                        number += str; 
                    } else {
                        currString += str; 
                    }
                }
            }
        } return temp; 
    }

    private HashMap<String, HashSet<Integer>> wordToNumber(In in) {
        HashMap<String, HashSet<Integer>> temp = new HashMap<String, HashSet<Integer>>(); 
        String number = ""; 
        String word = ""; 
        boolean comma = false; 
        while (!in.isEmpty()) {
            String line = in.readLine(); 
            for (int i = 0; i < line.length() - 1; i++) {
                char ch = line.charAt(i); 
                String str = String.valueOf(ch);  
                if (str.equals(",")) {
                    if (!comma) {
                        comma = true; 
                    } else {
                        Integer num = Integer.parseInt(number);
                        if (temp.get(word) != null)  {
                            temp.get(word).add(num); 
                        } else {
                            HashSet<Integer> newWord = new HashSet<Integer>(); 
                            newWord.add(num); 
                            temp.put(word, newWord); 
                        }
                        comma = false; 
                        number = ""; 
                        word = ""; 
                        break; 
                    }
                } else if (ch == ' ') {
                    Integer num = Integer.parseInt(number); 
                    if (temp.get(word) != null) {
                        temp.get(word).add(num); 
                    } else {
                        HashSet<Integer> newWord = new HashSet<Integer>(); 
                        newWord.add(num);
                        temp.put(word, newWord); 
                    }
                    word = ""; 
                } else if (!comma) {
                    number += str; 
                } else {
                    word += str; 
                }
            }
        } return temp; 
    }
    
    public boolean isNoun(String noun) {
        if (wordToNum.containsKey(noun)) {
            return true; 
        }
        return false; 
    }

    public Set<String> nouns() {
        return this.wordToNum.keySet(); 
    }

    public Set<String> hyponyms(String word) {
        Set<String> hypos = new HashSet<String>(); 
        HashSet<Integer> wordNums = wordToNum.get(word); 

        HashSet<String> synonyms = new HashSet<String>(); 
        if (wordNums != null) {
            for (Integer a : wordNums) {
                HashSet<String> toAdd = numberToWord.get(a); 
                for (String b : toAdd) {
                    synonyms.add(b); 
                }
            }
            Set<Integer> ints = GraphHelper.descendants(numberHypos, wordNums); 
            if (ints != null) {
                for (Integer x : ints) {
                    Set<String> added = (HashSet<String>) numberToWord.get(x); 
                    for (String y : added) {
                        hypos.add(y); 
                    }
                }
            }
            if (synonyms != null) {
                for (String z : synonyms) {
                    hypos.add(z); 
                }
            }
        }
        return hypos; 
    }
}
