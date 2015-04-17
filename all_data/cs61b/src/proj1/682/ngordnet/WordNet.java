package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.HashMap;
/* Following four import statements are based on a google search for "input file parsing 
 * Java" (e.g., an equivalent of fgets from C in Java is what is attempted in the 
 * constructor). Source import declarataions from here: 
 * http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/ */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordNet {

    private HashMap<Integer, ArrayList<String>> synData;
    private Digraph synGraph;

    /* Constructor for class. SYNSETFILENAME serves as source file to check for
     * word synsets. HYPONYMFILENAME serves as source to check for hyponym
     * relationships. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        BufferedReader fileRdr;
        synData = new HashMap<Integer, ArrayList<String>>();
        try {
            fileRdr = new BufferedReader(new FileReader(synsetFilename));
            if (fileRdr.ready()) {
                formSynData(fileRdr);
                fileRdr.close();
            }
            int synLen = synData.size();
            synGraph = new Digraph(synLen);
            fileRdr = new BufferedReader(new FileReader(hyponymFilename));
            if (fileRdr.ready()) {
                formEdges(fileRdr);
                fileRdr.close();
            }
        } catch (IOException e1) {
            /* Ignore E1 */
        }
    }

    /* Creates a private instance variables SynData that contains each set of synonyms 
     * line from the synset file as a Strinn ArrayList<String> and the ID as the key 
     * for a key-value pairt file. Input is file buffer for synset File FILERDR. */
    private void formSynData(BufferedReader fileRdr) {
        try {
            String curLine = fileRdr.readLine();
            while (curLine != null) {
                if (curLine.contains(",")) {
                    String[] splitHolder = curLine.split(Pattern.quote(","));
                    int curID = Integer.valueOf(splitHolder[0]);
                    ArrayList<String> curSynonyms = new ArrayList<String>();
                    if ((splitHolder.length - 1) < 2) {
                        throw new IOException("Insufficient args.\n");
                    }
                    String[] synonyms = (splitHolder[1]).split(Pattern.quote(" ")); 
                    int len = synonyms.length;
                    for (int i = 0; i < len; i += 1) {
                        curSynonyms.add(synonyms[i]);
                    }
                    synData.put(curID, curSynonyms);
                }
                curLine = fileRdr.readLine();
            }
        } catch (IOException e) { 
            /* Ignore E */
        }
    }

    /* Adds edges to the private instance variable SynGraph for each line in hyponym file.
    *  Input variable FILERDR is a buffer for that file. */
    private void formEdges(BufferedReader fileRdr) {
        try {
            if (synGraph != null) {
                String curLine = fileRdr.readLine();
                while (curLine != null) {
                    if (curLine.contains(",")) {
                        String[] splitHolder = curLine.split(Pattern.quote(","));
                        int curHead = Integer.valueOf(splitHolder[0]);
                        int curTail;
                        int strHoldLen = splitHolder.length;
                        for (int i = 1; i < strHoldLen; i += 1) {
                            curTail = Integer.valueOf(splitHolder[i]);
                            synGraph.addEdge(curHead, curTail);
                        }
                    }
                    curLine = fileRdr.readLine();
                }
            } else {
                throw new NullPointerException("SynGraph data is empty.");
            }
        } catch (IOException e1) { 
            /* Ignore E1 */
        }
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of WORD. */
    public Set<String> hyponyms(String word) {
        ArrayList<String> synonymData = new ArrayList<String>();
        Set<String> hypData = new TreeSet<String>();
        Iterable<Integer> curIter;
        if (isNoun(word)) {
            String curWord;
            int curNounID;
            hypData.add(word);
            int len = synData.size();
            for (int j = 0; j < len; j += 1) {
                if (word.equals((synData.get(j)).get(0))) {
                    curNounID = j;
                    synonymData = synData.get(j);
                    int len2 = synonymData.size();
                    for (int i = 0; i < len2; i += 1) {
                        curWord = synonymData.get(i);
                        if (!(curWord.equals(word))) {
                            hypData.add(curWord);
                        }
                    }
                    curIter = (Iterable<Integer>) synGraph.adj(curNounID);
                    Iterator<Integer> adjIter = curIter.iterator();
                    while (adjIter.hasNext()) {
                        Integer curID = adjIter.next();
                        ArrayList<String> curWords = synData.get(curID);
                        if (curWords != null) {
                            int len3 = curWords.size();
                            for (int i = 0; i < len3; i += 1) {
                                curWord = curWords.get(i);
                                if (!(curWord.equals(word))) {
                                    hypData.add(curWord);
                                    hypData.addAll(hyponyms(curWord));
                                }
                            }
                        }
                    }
                }
            }
        }
        return hypData;
    }

    /* Returns whether given word NOUN is a noun. */
    public boolean isNoun(String noun) {
        Iterator<Integer> synIDs = ((Set<Integer>) synData.keySet()).iterator();
        int i = 0;
        while (synIDs.hasNext()) {
            Integer curID = synIDs.next();
            ArrayList<String> curData = synData.get(curID);
            int len = curData.size();
            for (int j = 0; j < len; j += 1) {
                if ((curData.get(j)).equals(noun)) {
                    return true;
                }
            }   
        }
        return false;
    }

    /* Outputs a set of all nouns in the file. */
    public Set<String> nouns() {
        Iterator<Integer> synIDs = ((Set<Integer>) synData.keySet()).iterator();
        Set<String> allNounsOnce = new TreeSet<String>();
        while (synIDs.hasNext()) {
            Integer curID = synIDs.next();
            ArrayList<String> curData = synData.get(curID);
            int len = curData.size();
            for (int j = 0; j < len; j += 1) {
                String curWord = curData.get(j);
                if (!(allNounsOnce.contains(curWord))) {
                    allNounsOnce.add(curWord);
                }
            }
        }
        return allNounsOnce;
    }
} 
