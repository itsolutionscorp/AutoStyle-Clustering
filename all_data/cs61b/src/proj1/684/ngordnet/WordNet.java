package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
// import java.util.TreeSet;

public class WordNet {

    private int totalVertex = 0;
    private WordRealMap<Integer, String[]> synsetMap = new WordRealMap<Integer, String[]>();
    private Digraph dG;
    public WordNet(String synset, String hyponym) {
        try {
            Scanner scanner = new Scanner(new File(synset));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();     
                String[] words = line.split(",");                    
                int id = Integer.parseInt(words[0]);            
                String [] noun = words[1].split("\\s+");        
               
                if (noun.length > 0) {
                    synsetMap.put(id, noun);
                    totalVertex += 1;
                }
            } scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dG = new Digraph(totalVertex);

        try { 
            Scanner scanner2 = new Scanner(new File(hyponym));
            while (scanner2.hasNextLine()) {
                String line2 = scanner2.nextLine();                    
                String[] ids = line2.split(",");                    

                int thisId = Integer.parseInt(ids[0]);

                int n = ids.length;
                Integer[] restId = new Integer[n];

                int i = 0;
                while (i < n) {
                    restId[i] = Integer.parseInt(ids[i]);
                    dG.addEdge(thisId, restId[i]);
                    i++;
                } 

            } scanner2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } 

    public boolean isNoun(String value) {
        // for 
        for (int k = 0; k < totalVertex; k++) {
            for (String nounValue : synsetMap.get(k)) {
                if (nounValue.equals(value)) {
                    return true;
                }

            }

        }
        return false;
    } 



    public Set<String> nouns() {
        // if(totalVertex == 0) return result; 
        Set<String> nounSet = new TreeSet<String>();
        for (int k = 0; k < totalVertex; k++) {
            for (int j = 0; j < synsetMap.get(k).length; j++) {
                String nounValue = synsetMap.get(k)[j];
                nounSet.add(nounValue);
            }
        }

        return nounSet;
    } 

    public Set<String> hyponyms(String key) {

        Set<Integer> idSet = new HashSet<Integer>();
        Set<Integer> hyponymIdSet = new TreeSet<Integer>();
        Set<String> nounSet = new TreeSet<String>();

        // find id for key
        for (int k = 0; k < totalVertex; k++) {
            // System.out.println("group num "+k);
            for (String nounValue : synsetMap.get(k)) {

                // store id when key match
                if (nounValue.equals(key)) {
                    idSet.add(k);
                }
            }

        }

        // get hyponym Ids of id found
        hyponymIdSet = GraphHelper.descendants(dG, idSet);

        for (Integer keyId : hyponymIdSet) {
            for (String noun : synsetMap.get(keyId)) {
                nounSet.add(noun);
            }
        }
        return nounSet;
    }




}

