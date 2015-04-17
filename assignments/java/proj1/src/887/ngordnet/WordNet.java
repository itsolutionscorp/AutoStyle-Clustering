package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordNet {
    private HashMap<String, ArrayList<String>> synsets;
    private HashMap<String, ArrayList<String>> hypcontains;
    private HashMap<String, HashSet<String>> hypsets; 
    
    public WordNet(String fileA, String fileB) {
        this.synsets = new HashMap<String, ArrayList<String>>();
        this.hypsets = new HashMap<String, HashSet<String>>();
        this.hypcontains = new HashMap<String, ArrayList<String>>();

        readSynsetFile(fileA);
        createHypRelation(fileB);
        createHyponym();
    }

    private void readSynsetFile(String sf) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sf));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                String id = record[0];
                String[] noun = record[1].split(" ");
                ArrayList<String> arrList = new ArrayList<>(Arrays.asList(noun));
                String definition = record[2];
                synsets.put(id, arrList);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.format("Exception occurred trying to read '%s'.", sf);
            e.printStackTrace();
            this.synsets = null;
        } catch (IOException e) {
            System.err.format("Exception occurred trying to read '%s'.", sf);
            e.printStackTrace();
            this.synsets = null;
        }
    }

    private void createHyponym() {
        for (String key: synsets.keySet()) {
            ArrayList<String> temp = synsets.get(key);
            HashSet<String> temp1 = new HashSet<String>();
            for (int i = 1; i < temp.size(); i += 1) {
                temp1.add(temp.get(i));
            }
            if (hypcontains.containsKey(key)) {
                ArrayList<String> matched = hypcontains.get(key);
                for (int i = 0; i < matched.size(); i += 1) {
                    ArrayList<String> backTo = synsets.get(matched.get(i));
                    for (int j = 0; j < backTo.size(); j += 1) {
                        temp1.add(backTo.get(j));
                    }
                }
            }
            // Insert "self" hyponym. "change" for hyponyms of "change"
            temp1.add(temp.get(0));
            if (!hypsets.containsKey(temp.get(0))) {
                hypsets.put(temp.get(0), temp1);
            } else {
                HashSet<String> copy = hypsets.get(temp.get(0));
                copy.addAll(temp1);
                hypsets.put(temp.get(0), copy);
            }
        }
    }

    private void flattenHypsetValues() {
        for (String key : hypcontains.keySet()) {
            HashSet<String> hyponmyslist = traverse(key);
            ArrayList<String> list = new ArrayList<String>(hyponmyslist);
            this.hypcontains.put(key, list);
        }
    }
    
    private HashSet<String> traverse(String k) {
        HashSet<String> hyponmyslist = new HashSet<String>();
        ArrayList<String> values = hypcontains.get(k);
        hyponmyslist.addAll(values);
        for (int i = 0; i < values.size(); i += 1) {
            if (hypcontains.containsKey(values.get(i))) {
                hyponmyslist.addAll(traverse(values.get(i)));
            } else {
                hyponmyslist.add(values.get(i)); 
            }
        }
        return hyponmyslist;
    }

    private void createHypRelation(String hf) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(hf));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                ArrayList<String> temp = new ArrayList<String>();
                for (int i = 1; i < record.length; i += 1) {
                    temp.add(record[i]);
                }
                if (hypcontains.containsKey(record[0])) {
                    ArrayList<String> values = hypcontains.get(record[0]);
                    values.addAll(temp);
                    hypcontains.put(record[0], values);
                } else {
                    hypcontains.put(record[0], temp);
                }
            }
            flattenHypsetValues();
        } catch (FileNotFoundException e) {
            System.err.format("Exception occurred trying to read '%s'.", hf);
            e.printStackTrace();
            this.hypcontains = null;
        } catch (IOException e) {
            System.err.format("Exception occurred trying to read '%s'.", hf);
            e.printStackTrace();
            this.synsets = null;
        }
    }

    public boolean isNoun(String n) {    
        if (hypsets.containsKey(n)) {
            return true;
        }
        return false;
    }    

    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (String key: synsets.keySet()) {
            ArrayList<String> temp = synsets.get(key);
            for (int i = 0; i < temp.size(); i += 1) {
                nounSet.add(temp.get(i));
            }
        }
        return nounSet;
    }

    public Set<String> hyponyms(String word) {
        return hypsets.get(word);
    }

    /*public static void main(String[] args) {
        WordNet wn = new WordNet("./wordnet/synsets.txt", "./wordnet/hyponyms.txt");
        /*
        for (String key: wn.synsets.keySet()) {
            System.out.println(key +" :: "+ wn.synsets.get(key));
        }  
        for (String key: wn.hypcontains.keySet()) {
            System.out.println(key +" :: "+ wn.hypcontains.get(key));
        }
        System.out.println("All nouns:");
        for (String noun : wn.nouns()) {
            System.out.println(noun);
        }
        for (String key: wn.hypsets.keySet()) {
            System.out.println(key +" :: "+ wn.hypsets.get(key));
        }
        
        System.out.println("All hyponyms:");
        System.out.println(wn.hyponyms("change"));
    }*/
}
