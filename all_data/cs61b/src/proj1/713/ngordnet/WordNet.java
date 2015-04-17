package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Set;
import java.util.TreeSet;
public class WordNet {

    private HashMap<Integer, TreeSet<String>> idsyn = new HashMap<Integer, TreeSet<String>>();
    private HashMap<String, TreeSet<Integer>> nounid = new HashMap<String, TreeSet<Integer>>();
    private HashMap<Integer, String> definitions = new HashMap<Integer, String>();    
    private HashMap<Integer, TreeSet<Integer>> hyponyms = new HashMap<Integer, TreeSet<Integer>>();
    private ConcurrentLinkedQueue<Integer> checkhyponyms = 
        new ConcurrentLinkedQueue<Integer>(); 
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        In scannersynset = new In(synsetFilename);
        In scannerhyponym = new In(hyponymFilename);
        while (scannersynset.hasNextLine()) {
            String line = scannersynset.readLine();    //read in each line of sysnets
            String[] tokens = line.split(",");          //splits line into its individual elements
            String[] nouns = tokens[1].split(" ");
            int id = Integer.parseInt(tokens[0]);       //first element of tokens has to be the id
            definitions.put(id, tokens[2]);             //put definition of synset in definitions
            TreeSet<String> synset = new TreeSet<String>();
            //creates the String list of the nouns in a synset 
            for (int counter = 0; counter < nouns.length; counter = counter + 1) {
                synset.add(nouns[counter]);             //adds synonym to synset
                //with the HashMap going from that noun to a new list with line number put in
                if (nounid.get(nouns[counter]) == null) {
                    TreeSet<Integer> correspondinglines = new TreeSet<Integer>();
                    correspondinglines.add(id);
                    nounid.put(nouns[counter], correspondinglines);
                } else {
                    //else case
                    nounid.get(nouns[counter]).add(id);
                }
            }
            idsyn.put(id, synset);
        }
        while (scannerhyponym.hasNextLine()) {
            String line = scannerhyponym.readLine();
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            if (hyponyms.get(id) != null) {
                for (int counter = 1; counter < tokens.length; counter = counter + 1) {
                    hyponyms.get(id).add(Integer.parseInt(tokens[counter]));
                }
            } else {
                TreeSet<Integer> hyponymsset = new TreeSet<Integer>();
                for (int counter = 1; counter < tokens.length; counter = counter + 1) {
                    hyponymsset.add(Integer.parseInt(tokens[counter]));
                }
                hyponyms.put(id, hyponymsset);
            }
        }
    }

    public boolean isNoun(String noun) {

        for (Integer i: idsyn.keySet()) {
            TreeSet<String> currentsynset = idsyn.get(i);
            if (currentsynset.contains(noun)) {
                return true;
            }
        }

        return false;
    }

    public Set<String> nouns() {
        
        return nounid.keySet();
        
    }

    public Set<String> hyponyms(String word) {
        TreeSet<String> sethyponyms = new TreeSet<String>();
        TreeSet<Integer> firstlevel = nounid.get(word);            //making set into list
        for (Integer i: firstlevel) {
            TreeSet<String> synonyms = idsyn.get(i);
            for (String s: synonyms) {
                sethyponyms.add(s);
            }
            checkhyponyms.add(i);
        }

        while (!checkhyponyms.isEmpty()) {
            
            int id = checkhyponyms.remove();
            TreeSet<String> nounsset = idsyn.get(id);  //making set into list 
            for (String s: nounsset) {
                sethyponyms.add(s);
            }

            TreeSet<Integer> hyponymscurrent = hyponyms.get(id);
            
            if (hyponymscurrent != null) {
                for (Integer i: hyponymscurrent) {
                    checkhyponyms.add(i);
                }
            }
        }
        return sethyponyms;
    }

}
