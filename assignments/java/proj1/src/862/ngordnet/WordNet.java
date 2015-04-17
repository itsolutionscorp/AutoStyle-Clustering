package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class WordNet {

    private In synsetFile;
    private In hyponymFile;
    private HashMap<Integer, String[]> synsetMap;
    private HashMap<Integer, Integer[]> hyponymMap;
    private Integer synsetID;
    private String synset;
    private String line;
    private String[] lineArray;
    private Integer[] hyponymArray;
    private HashSet<String> nouns;
    private String[] synsetArray;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = new In(synsetFilename);
        hyponymFile = new In(hyponymFilename);
        synsetMap = new HashMap<Integer, String[]>(); //Creating map of synsetID's to synsets
        hyponymMap = new HashMap<Integer, Integer[]>();
        nouns = new HashSet<String>();

        while (synsetFile.hasNextLine()) { //putting synsetID and synset into a map
            line = synsetFile.readLine();
            lineArray = line.split(",");
            synsetID = Integer.parseInt(lineArray[0]); //read the ID
            synset = lineArray[1];
            synsetArray = synset.split(" ");
            synsetMap.put(synsetID, synsetArray);
            for (int i = 0; i < synsetArray.length; i++) {
                nouns.add(synsetArray[i]);      
            }
        }
        
        while (hyponymFile.hasNextLine()) { //putting synsetID and hyponyms into a map
            line = hyponymFile.readLine();
            lineArray = line.split(",");
            hyponymArray = new Integer[lineArray.length - 1];
            synsetID = Integer.parseInt(lineArray[0]);
            for (int i = 1; i < lineArray.length; i++) {
                hyponymArray[i - 1] = Integer.parseInt(lineArray[i]);
            }
            if (hyponymMap.containsKey(synsetID)) {
                Integer[] temp = hyponymMap.get(synsetID);
                Integer[] total = new Integer[temp.length + hyponymArray.length];
                for (int i = 0; i < temp.length; i++) {
                    total[i] = temp[i];
                }
                for (int i = temp.length; i < hyponymArray.length; i++) {
                    total[i] = hyponymArray[i];
                }
                hyponymMap.put(synsetID, total);
            } else {
                hyponymMap.put(synsetID, hyponymArray);
            }
        }
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        HashSet<Integer> synsetIDs = new HashSet<Integer>();
        HashSet<Integer> hyponymIDs = new HashSet<Integer>();
        HashSet<String> hyponyms = new HashSet<String>();
        Integer[] wordHyponyms;
        Iterator synsetIterator = synsetMap.entrySet().iterator();
        while (synsetIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) synsetIterator.next();
            String[] synsetWords = (String[]) pair.getValue();
            for (int i = 0; i < synsetWords.length; i++) {
                if (synsetWords[i].equals(word)) {
                    synsetIDs.add((Integer) pair.getKey());
                }
            }
        }
        HashSet<Integer> fullHyponymSet = new HashSet<Integer>();
        Iterator<Integer> sIterator = synsetIDs.iterator();
        while (sIterator.hasNext()) {
            fullHyponymSet.add(sIterator.next());
        }
        while (!fullHyponymSet.isEmpty()) {
            HashSet<Integer> temp = new HashSet<Integer>();
            Iterator<Integer> hyponymIterator = fullHyponymSet.iterator();
            while (hyponymIterator.hasNext()) {
                Integer tempInt = hyponymIterator.next();
                Integer[] tempArray = hyponymMap.get(tempInt);
                if (tempArray != null) {
                    for (int i = 0; i < tempArray.length; i++) {
                        temp.add(tempArray[i]);
                    }
                }
            }
            synsetIDs.addAll(temp);
            fullHyponymSet = temp;
        }

        Iterator<Integer> idIterator = synsetIDs.iterator();
        while (idIterator.hasNext()) {
            Integer id = idIterator.next();
            String[] synsets = synsetMap.get(id);
            for (int k = 0; k < synsets.length; k++) {
                hyponyms.add(synsets[k]);
            }
            Integer[] wordHyponymsID = hyponymMap.get(id);
            if (wordHyponymsID != null) {
                for (int m = 0; m < wordHyponymsID.length; m++) {
                    String[] hyponymSynset = synsetMap.get(wordHyponymsID[m]);
                    for (int n = 0; n < hyponymSynset.length; n++) {
                        hyponyms.add(hyponymSynset[n]);
                    }
                }
            }
        }
        return hyponyms;
    }
}
