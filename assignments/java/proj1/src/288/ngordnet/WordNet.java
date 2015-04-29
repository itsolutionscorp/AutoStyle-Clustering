package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

public class WordNet {
    //Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME

    //Borrowed code from Josh Hugg @ https://github.com/Berkeley-CS61B/skeleton/blob
    private TreeMap<Integer, ArrayList<String>> synsets;
    private TreeMap<Integer, ArrayList<Integer>> hyponyms;


    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetReader = new In(synsetFilename);
        In hyponymReader = new In(hyponymFilename);
        synsets = new TreeMap<Integer, ArrayList<String>>();
        hyponyms = new TreeMap<Integer, ArrayList<Integer>>();
        while (hyponymReader.hasNextLine()) {
            String nextline = hyponymReader.readLine();
            String[] split = nextline.split(",");
            ArrayList<Integer> links = new ArrayList<Integer>();
            Integer id = new Integer(0);
            try {
                id = Integer.parseInt(split[0]);
                for (int x = 1; x < split.length; x++) {
                    //links[x] = Integer.parseInt(split[x+1]);
                    links.add(Integer.parseInt(split[x]));
                }
            } catch (NumberFormatException e) {
                //System.out.println("fucked up 1");
            }


            //some weird cowboy shit...
            if (hyponyms.get(id) == null) {
                hyponyms.put(id, links);
            } else {
                hyponyms.get(id).addAll(links);
            }
        }


        //Setting up synsets reader
        while (synsetReader.hasNextLine()) {
            String nextline = synsetReader.readLine();
            String[] split = nextline.split(",");
            String[] s;
            Integer id = new Integer(0);
            try {
                id = Integer.parseInt(split[0]);
            } catch (NumberFormatException e) {
                //System.out.println("fucked up 1");
            }

            s = split[1].split(" ");
            ArrayList<String> finalWordList = new ArrayList<String>();
            for (int x = 0; x < s.length; x++) {
                finalWordList.add(s[x]);
            }
            synsets.put(id, finalWordList);
        }

    }

    public boolean isNoun(String noun) {
        for (int x = 0; x < synsets.size(); x++) {
            for (String nouncheck: synsets.get(x)) {
                if (noun.equals(nouncheck)) {
                    return true;
                }
            }
        }

        return false;
    }
    //Fuck the AG


    public Set<String> nouns() {
        TreeSet<String> theNouns = new TreeSet<String>();
        for (int x = 0; x < synsets.size(); x++) {
            for (String noun: synsets.get(x)) {
                theNouns.add(noun);
            }
        }

        return theNouns;
    }

   



    public Set<String> hyponyms(String word) {
        TreeSet<String> hp = new TreeSet<String>();
        int wordID = 0;
        //Counter to extract list of all synonyms
        for (int x = 0; x < synsets.size(); x++) {
            for (String s: synsets.get(x)) {
                if (s.equals(word)) {
                    for (int i = 0; i < synsets.get(x).size(); i++) { 
                        hp.add(synsets.get(x).get(i));
                    }
                    List<Integer> hyponymIDs = new ArrayList<Integer>();
                    hyponymIDs = hyponyms.get(x);
                    if (hyponymIDs != null) {
                        for (Integer id: hyponymIDs) {
                            hp.addAll(hyponymsHelper(x));
                        }
                    }
                    //Getting hyponyms
                }
            }
        }
        return hp;

    }

    private String lookup(int i) {
        return synsets.get(i).get(0);
    }

    private Set<String> hyponymsHelper(Integer wordID) {
        TreeSet<String> hp = new TreeSet<String>();
        //Base Case
        if (hyponyms.get(wordID) == null) {
            for (String s: synsets.get(wordID)) {
                hp.add(s);
            }
            return hp;
        } else {
            for (String s: synsets.get(wordID)) {
                hp.add(s);
            }

            for (Integer i: hyponyms.get(wordID)) {
                hp.addAll(hyponymsHelper(i));
            }
            

            return hp;
        }
    }

}
