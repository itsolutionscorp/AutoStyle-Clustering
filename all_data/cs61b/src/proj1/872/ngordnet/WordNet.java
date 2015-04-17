package ngordnet;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;


public class WordNet {

    private HashMap<String, Word> stringMap = new HashMap<String, Word>();
    private HashMap<Integer, ArrayList<Word>> idMap = new HashMap<Integer, ArrayList<Word>>();

    public WordNet(String synsetText, String hyponymText) {
        In synsetReader = new In(synsetText);
        In hyponymReader = new In(hyponymText);
        String[] split;

        ArrayList<Word> tempList;

        while (synsetReader.hasNextLine()) {
            split = synsetReader.readLine().split(",");
            Word tempWord;
            tempList = new ArrayList<Word>();

            for (String temp : split[1].split(" ")) {
                if (stringMap.get(temp) == null) {
                    tempWord = new Word(Integer.parseInt(split[0]), temp, split[2]);
                    stringMap.put(temp, tempWord);
                } else {
                    tempWord = stringMap.get(temp);
                    tempWord.addID(Integer.parseInt(split[0]));
                }
                tempList.add(tempWord);
            }

            idMap.put(Integer.parseInt(split[0]), tempList);
        }

        while (hyponymReader.hasNextLine()) {
            split = hyponymReader.readLine().split(",");
            ArrayList<Word> synsetWordList = idMap.get(Integer.parseInt(split[0]));
            for (int i = 1; i < split.length; i += 1) {
                for (Word tempWord : synsetWordList) {
                    tempWord.addHyponym(Integer.parseInt(split[i]));
                }
            }
        }

    }

    public boolean isNoun(String wordQuery) {
        return stringMap.containsKey(wordQuery);
    }

    public Set<String> nouns() {
        return stringMap.keySet();
    }

    public Set<String> hyponyms(String wordQuery) {
        Word temp = stringMap.get(wordQuery);
        HashSet<String> result = new HashSet<String>();
        ArrayList<Integer> currentHyponyms = temp.myHyponyms;
        for (int tempID : temp.synsetID) {
            for (Word tempWord : idMap.get(tempID)) {
                result.add(tempWord.synsetName);
            }
        }
        while (currentHyponyms.size() != 0) {
            ArrayList<Integer> newHyponyms = new ArrayList<Integer>();
            for (int hyponymID : currentHyponyms) {
                for (Word hyponym : idMap.get(hyponymID)) {
                    newHyponyms.addAll(hyponym.myHyponyms);
                    result.add(hyponym.synsetName);
                }
            }
            currentHyponyms = newHyponyms;  
        }

        return result;
    }

    private class Word {

        private ArrayList<Integer> synsetID = new ArrayList<Integer>();
        private String synsetName;
        private String definition;
        private ArrayList<Integer> myHyponyms = new ArrayList<Integer>();


        private Word(int i, String n, String g) {
            synsetID.add(i);
            synsetName = n;
            definition = g;
        }

        private void addID(int i) {
            synsetID.add(i);
        }

        private void addHyponym(int i) {
            myHyponyms.add(i);
        }

    }

}
