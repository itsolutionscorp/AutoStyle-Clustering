package ngordnet;
//import edu.princeton.cs.introcs.In;
//import ngordnet.GraphHelper;
//import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
//import java.util.Map; 
import java.util.HashMap; 
//import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class WordNet {

    private TreeSet<String> nouns;
    private HashMap<String, String[]> idWords;
    private HashMap<String, Set<String>> wordsId;
    private HashMap<String, Set<String>> hyponyms;
    private Set<String> rv;

    private String[] openFile(String path) {
        String[] textData = null;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fr);
            int numberOfLines = readLines(path);
            textData = new String[numberOfLines];
            int i;
            for (i = 0; i < numberOfLines; i++) {
                textData[i] = textReader.readLine();
            }
            textReader.close();
        } catch (IOException iox) {
            System.out.println("Error: File not found");
        }       
        return textData;    
    }

    private int readLines(String path) {
        int numberOfLines = 0;
        try {
            FileReader fileToRead = new FileReader(path);
            BufferedReader bf = new BufferedReader(fileToRead);
            String aLine;
            while ((aLine = bf.readLine()) != null) {
                numberOfLines += 1;
            }
            bf.close();
        } catch (IOException iox) {
            System.out.println("Error: File not found");
        }       
        return numberOfLines;
    }

    public WordNet(java.lang.String synsetFilename,
        java.lang.String hyponymFilename) {
        String[] synsettext = openFile(synsetFilename);
        String[] hyponymtext = openFile(hyponymFilename);
        idWords = new HashMap<String, String[]>();
        wordsId = new HashMap<String, Set<String>>();
        hyponyms = new HashMap<String, Set<String>>();
        nouns = new TreeSet<String>();
        String[] idWordList;
        String[] nounset;
        for (int i = 0; i < synsettext.length; i++) {
            idWordList = synsettext[i].split(",");
            if (idWordList[1].contains(" ")) {
                nounset = idWordList[1].split(" ");
            } else {
                nounset = new String[1];
                nounset[0] = idWordList[1];
            }
            idWords.put(idWordList[0], nounset);
            for (String noun : nounset) {
                nouns.add(noun);
                Set<String> ids = wordsId.get(noun);
                if (ids != null) {
                    ids.add(idWordList[0]);
                    wordsId.put(noun, ids);
                } else {
                    Set<String> newIds = new HashSet<String>();
                    newIds.add(idWordList[0]);
                    wordsId.put(noun, newIds);
                }
            }
        }

        String[] hyponymGroup;
        String hypernym;
        Set<String> hyponymSet;
        for (String line : hyponymtext) {
            hyponymSet = new HashSet<String>();
            hyponymGroup = line.split(",");
            hypernym = hyponymGroup[0];
            for (int i = 1; i < hyponymGroup.length; i++) {
                hyponymSet.add(hyponymGroup[i]);
            }
            if (hyponyms.get(hypernym) != null) {
                for (String oldWord : hyponyms.get(hypernym)) {
                    hyponymSet.add(oldWord);
                }
            }
            hyponyms.put(hypernym, hyponymSet);  
        }
    }

    public boolean isNoun(java.lang.String noun) {
        return (wordsId.get(noun) != null);
    }

    public java.util.Set<java.lang.String> nouns() {
        return nouns;
    }

    public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
        rv = new HashSet<String>();
        if (wordsId.get(word) == null) {
            return rv;
        }
        for (String key : wordsId.get(word)) {
            Set<String> hypos = hyponyms.get(key);
            String[] synos = idWords.get(key);
            if (hypos != null) {
                for (String id : hypos) {
                    hypohelper(id);
                    for (String worsh : idWords.get(id)) {
                        rv.add(worsh);
                    }
                }
            }
            for (String worsh : synos) {
                rv.add(worsh);
            }
        }
        return rv;
    }

    private java.util.Set<java.lang.String> hypohelper(java.lang.String key) {    
        Set<String> hypos = hyponyms.get(key);
        String[] synos = idWords.get(key);
        if (hypos != null) {
            for (String id : hypos) {
                hypohelper(id);
                for (String worsh : idWords.get(id)) {
                    rv.add(worsh);
                }
            }
        }
        for (String worsh : synos) {
            rv.add(worsh);
        }    
        return rv;
    }
}
