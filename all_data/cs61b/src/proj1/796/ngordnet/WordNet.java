package ngordnet;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


// import edu.princeton.cs.introcs.StdIn;
public class WordNet { 
    //eachWord is a set with single word
    private Set<String> eachWord = new HashSet<String>();
    //wordAndId stores the key of the word and value of a set of synsets ids that it belong to
    private Map<String, Set<Integer>> wordAndId = new HashMap<String, Set<Integer>>();
    private Map<Integer, Set<String>> idAndWord = new HashMap<Integer, Set<String>>();
      
    // A hyponymMap is the map 
    private Map<String, Set<String>> hyponymMap = new HashMap<String, Set<String>>();
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        In br = new In(synsetFilename);
        String line;
        int lineCount = 0;
        //read line by line
        while (br.hasNextLine()) {
            line = br.readLine();
             // process the line.
            String[] rawTokens = line.split(",");
            //if the token contains space, that means it has multiple entries.
            if (rawTokens[1].contains(" ")) {
                String[] entries = rawTokens[1].split(" "); 
                //create a new set of Strings
                Set<String> wordsOfThisId = new HashSet<String>();
                for (int i = 0; i < entries.length; i++) {
                    String word = entries[i];
                    eachWord.add(word);
                    wordsOfThisId.add(word);
                    if (wordAndId.containsKey(word)) {
                        //add the current line id to the idsets of that specific wor wordAndId[word 
                        wordAndId.get(word).add(Integer.parseInt(rawTokens[0]));
                    } else {
                        //create a new entry, and initialize the set with a 
                        //integer of current line number      
                        Set<Integer> idSetsOfThisWord = new HashSet<Integer>();
                        idSetsOfThisWord.add(Integer.parseInt(rawTokens[0]));
                        wordAndId.put(word, idSetsOfThisWord);
                    }
                }
                //after the wordofthisId is added ..
                idAndWord.put(Integer.parseInt(rawTokens[0]), wordsOfThisId); 
            } else {
                 //if there is only one word in synset
                eachWord.add(rawTokens[1]); 
                //same as above
                String word = rawTokens[1];
                Set<String> wordsOfThisId = new HashSet<String>();
                wordsOfThisId.add(word);
                idAndWord.put(Integer.parseInt(rawTokens[0]), wordsOfThisId); 
                //add it to the map. if the key already exists
                if (wordAndId.containsKey(word)) {
                    //add the current line id to the idsets of that specific word
                    //wordAndId[word] is the set of ids of that word
                    wordAndId.get(word).add(Integer.parseInt(rawTokens[0]));
                } else {
                    //else the key hasnt existed yet     
                    Set<Integer> idSetsOfThisWord = new HashSet<Integer>();
                    idSetsOfThisWord.add(Integer.parseInt(rawTokens[0]));
                    wordAndId.put(word, idSetsOfThisWord);
                }
            }
            lineCount += 1;        
        } //end of while

        //Step2: construct a diagraph
        Digraph g = new Digraph(lineCount);
        //read the hypo fille
        br = new In(hyponymFilename);
        //read line by line
        while (br.hasNextLine()) {
             // process the line.
            line = br.readLine();
            String[] entries = line.split(",");
            int hypernym = Integer.parseInt(entries[0]);
            for (int i = 1; i < entries.length; i++) {
                g.addEdge(hypernym, Integer.parseInt(entries[i]));
            }
        }
        //Step3: construct a hyponym set with key of word and value of a set of strings
        for (String word : eachWord) {
            //value: a set of integer
            Set<Integer> ids = GraphHelper.descendants(g, wordAndId.get(word));    
            Set<String> words = new HashSet<String>(); 
            for (Integer i : ids) {
              //System.out.println(word + ":" + i +  "!!!" + idAndWord.get(i) );
                words.addAll(idAndWord.get(i));
            }
            hyponymMap.put(word, words);
        }
    }

    public boolean isNoun(String noun) {
        if (eachWord.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return eachWord;
    }

    //Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
    //If WORD belongs to multiple synsets, return all hyponyms of all of these synsets. 
    //See http://goo.gl/EGLoys for an example.
    //Do not include hyponyms of synonyms.
    public Set<String> hyponyms(String word) {
        return hyponymMap.get(word);  
    }

}

