package ngordnet;

import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
/** Plan for proj 1
NOUN- > hyponymns (HashMap) [id, hyponym]?

noun- is it a key *keys 
nouns- print all keys
hyponymns- loops through for the ids? is this practical? 
maybe i should make another dictionary that goes ID- noun(s)

**/

public class WordNet {
    private HashMap<String, ArrayList<String>> nounToHyponymn; //first order hyponyms only
    private HashMap<String, ArrayList<Integer>> nounToId; //some nouns have same id

    
    public WordNet(String synsetFilename, String hyponymFilename) {
        // creates a wordnet
        // synset id, string, name
        // hyponym id, hyponymns

        In synScanner = new In(synsetFilename);
        In hypScanner = new In(hyponymFilename);
        nounToHyponymn = new HashMap<String, ArrayList<String>>(10);
        nounToId = new HashMap<String, ArrayList<Integer>>(10);

        while (!synScanner.isEmpty()) {
            String entry = synScanner.readLine(); 
            String[] idStringDef = entry.split(","); 
            // index 0- number, 1- word(s), 2-definition (dont need defs)
            //get id
            int id = Integer.parseInt(idStringDef[0]); 
            //get words
            String words = idStringDef[1];
            String[] synonymns = words.split("\\s"); 
            //credit to http://javabeginnerstutorial.com/
            // (continued) core-java-tutorial/string-split-whitespace-example/
            // place in the hashmaps
            for (String noun : synonymns) {
                if (nounToId.containsKey(noun)) {
                    ArrayList<Integer> newId = nounToId.get(noun);
                    newId.add(id);
                    nounToId.put(noun, newId);
                } else {
                    ArrayList<Integer> firstid = new ArrayList<Integer>();
                    firstid.add(id);
                    nounToId.put(noun, firstid);
                }
            } 

            // Now we have dictionary of Noun-->ID

        }

        while (!hypScanner.isEmpty()) {
            String entry = hypScanner.readLine();
            //get the numbers
            String[] numbersAsStrings = entry.split(",");
            //deal with root
            int synonymnsId = Integer.parseInt(numbersAsStrings[0]);
            //find the synonymns for the word
            ArrayList<String> sameId = new ArrayList<String>();

            //http://www.sergiy.ca/how-to-iterate-over-a-map-in-java/
            for (Map.Entry<String, ArrayList<Integer>> pair : nounToId.entrySet()) {
                if ((pair.getValue()).contains(synonymnsId)) {
                    sameId.add(pair.getKey());

                }
            } // i should now have a arraylist with the first id of each thing

            for (String word : sameId) {

                ArrayList<String> hyponymns = new ArrayList<String>();
                for (String n : numbersAsStrings) {
                    int hypId = Integer.parseInt(n);
                    for (Map.Entry<String, ArrayList<Integer>> pair : nounToId.entrySet()) {
                        if ((pair.getValue()).contains(hypId)) {
                            hyponymns.add(pair.getKey());
                        }
                    }
                }
                //should now have an arraylist of all the hyponymn strings
                if (nounToHyponymn.containsKey(word)) {
                    ArrayList<String> newhyponymns = nounToHyponymn.get(word);
                    newhyponymns.addAll(hyponymns);
                    hyponymns = newhyponymns;
                }
                nounToHyponymn.put(word, hyponymns);
            }
            // should have completed noun to first order Hyponym

        }
        

    }

    public Set<String> hyponyms(String word) {

        HashSet<String> hypos = new HashSet<String>();
        if (nounToId.containsKey(word)) { 
            ArrayList<Integer> indices = nounToId.get(word);
            for (Map.Entry<String, ArrayList<Integer>> pair : nounToId.entrySet()) {
                for (int index : indices) {
                    if ((pair.getValue().contains(index))) {
                        hypos.add(pair.getKey());
                    }
                }
            }

        }
        if (nounToHyponymn.containsKey(word)) {
            ArrayList<String> gen1 = nounToHyponymn.get(word);
            if (gen1 != null) {
                hypos.addAll(gen1);
                for (String s : gen1) {
                    if (!s.equals(word) && mutuallyExclusive(s, word)) {
                        Set<String> child = hyponyms(s);
                        hypos.addAll(child);
                
                    }
                }
            }
        }
    
        return hypos; //change
    }
    private boolean mutuallyExclusive(String a, String b) {
        ArrayList<Integer> first = nounToId.get(a);
        ArrayList<Integer> second = nounToId.get(b);
        for (int x : first) {
            if (second.contains(x)) {
                return false;
            }
        }
        return true;
    }
    public boolean isNoun(String noun) {
        return nounToId.containsKey(noun);
         //change
    }

    public java.util.Set<String> nouns() {
        return nounToId.keySet();
         //change
    }



}
