package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class WordNet {

    private HashMap<String, String[]> allSynsets;
    private HashMap<String, String[]> allHyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        allSynsets = new HashMap();
        allHyponyms = new HashMap();
        In synsetFile =  new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        while (synsetFile.hasNextLine()) {
            String[] currLine = synsetFile.readLine().split(",");
            allSynsets.put(currLine[0], currLine[1].split(" "));
            //System.out.println(currLine[0] + " " + allSynsets.get(currLine[0])[0]);
            //allSynsets.add(currLine);
            //System.out.println(currLine[0] + " " + currLine[1]);
        }
        //System.out.println("Hyponyms");
        while (hyponymFile.hasNextLine()) {
            String[] currLine = hyponymFile.readLine().split(",");
            //allHyponyms.add(currLine);
            allHyponyms.put(currLine[0], currLine);
            //System.out.println(currLine[0] + " " + currLine[1]);
        }
    }

    public boolean isNoun(String noun) {
        Set<String> nouns = this.nouns();
        for (String n : nouns) {
            if (n.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        HashSet nouns = new HashSet();
        for (String[] s : allSynsets.values()) {
            for (String str : s) {
                nouns.add(str);
            }
        }
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        HashSet h = new HashSet();
        if (this.isNoun(word)) {
            ArrayList<String> keysOfWord = new ArrayList();
            for (String key : allSynsets.keySet()) {
                for (String str : allSynsets.get(key)) {
                    if (str.equals(word)) {
                        keysOfWord.add(key);
                        //for (String strings : tempArray) {
                        //  hyponyms.add(s);
                        //  i = s[0];
                        //}
                    }
                }
            }
            //Adds all synonyms of word
            for (String key : allSynsets.keySet()) {
                for (String keyOfWord : keysOfWord) {
                    if (key.equals(keyOfWord)) {
                        for (String str : allSynsets.get(key)) {
                            h.add(str);
                        }
                    }
                }
            }
            //Adds direct hynonyms of word
            for (String hypKey : allHyponyms.keySet()) {
                for (String keyOfWord : keysOfWord) {
                    if (hypKey.equals(keyOfWord)) {
                        for (String hypStr: allHyponyms.get(hypKey)) {
                            for (String synKey : allSynsets.keySet()) {
                                if (synKey.equals(hypStr)) {
                                    for (String synStr : allSynsets.get(hypStr)) {
                                        h.add(synStr);
                                    }
                                    for (String s : hyponymsHelper(synKey)) {
                                        h.add(s);
                                    }
                                }
                                
                            }
                        }
                    }
                }
            }
            return h;
        } else {
            throw new IllegalArgumentException();
        }
    }
    //Returns hyponyms of a specific key
    private Set<String> hyponymsHelper(String key) {
        HashSet<String> h = new HashSet();
        for (String hypKeyHelper : allHyponyms.keySet()) {
            if (hypKeyHelper.equals(key)) {
                for (String hypStrHelper: allHyponyms.get(hypKeyHelper)) {
                    for (String synKeyHelper : allSynsets.keySet()) {
                        if (synKeyHelper.equals(hypStrHelper)) {
                            for (String synStrHelper : allSynsets.get(hypStrHelper)) {
                                h.add(synStrHelper);
                            }
                        }
                            
                    }
                }
            }
        }
        return h;
    }

}
