package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private Digraph g;
    private Word wordList = null; 

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        String synsetLine;
        String hyponymLine;
        String[] synsetSplit;
        String[] hyponymSplit;
        int totalLine = 0;
        int id;
        String[] synonyms;
        int i;
        
        while (synsetFile.hasNextLine()) {
            totalLine += 1;
            synsetLine = synsetFile.readLine();
            synsetSplit = synsetLine.split(",");

            id = Integer.parseInt(synsetSplit[0]);
            synonyms = synsetSplit[1].split(" ");

            for (i = 0; i < synonyms.length; i++) {
                wordList = new Word(id, synonyms[i], synsetSplit[2], wordList);
            }           
        }

        int hypernym;
        g = new Digraph(totalLine);
        
        while (hyponymFile.hasNextLine()) {
            hyponymLine = hyponymFile.readLine();
            hyponymSplit = hyponymLine.split(",");
            hypernym = Integer.parseInt(hyponymSplit[0]);

            for (i = 1; i < hyponymSplit.length; i++) {
                g.addEdge(hypernym, Integer.parseInt(hyponymSplit[i]));
            } 
            
        }
        // System.out.println(g);
    }

    public boolean isNoun(String noun) {
        if (wordList.containWord(noun) > 0) {
            return true;
        }
        return false;
    }


    public Set<String> nouns() {
        Word p = wordList;
        String latestWord;
        Set<String> nounList = new TreeSet<String>();

        while (p != null) {
            latestWord = p.getWord();

            // System.out.println(latestWord + ", " + p.containWord(latestWord));
            if (p.containWord(latestWord) == 1) {
                nounList.add(latestWord);
            }
            p = p.next;
        }
        return nounList;
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymList = new TreeSet<String>();
        Set<Integer> idList = wordList.idFromWord(word);
        Set<Integer> hyponymID;
        Set<String> synonymList;

        hyponymID = GraphHelper.descendants(g, idList);
        for (int hypID : hyponymID) {
            synonymList = wordList.get(hypID);
            for (String syn : synonymList) {
                hyponymList.add(syn);
            }
        }
        return hyponymList;
    }



    private class Word {
        private int id;
        private String word;
        private String meaning;
        private Word next;

        public Word(int id0, String word0, String meaning0, Word next0) {
            this.id = id0;
            this.word = word0;
            this.meaning = meaning0;
            this.next = next0;
        }

        public Set<String> get(int idNum) {
            Word p = this;
            Set<String> synonymList = new TreeSet<String>();
            while (p != null) {
                if (p.id == idNum) {
                    synonymList.add(p.word);
                }
                p = p.next;
            }
            return synonymList;
        }

        public Set<Integer> idFromWord(String synset) {
            Word p = this;
            Set<Integer> idList = new TreeSet<Integer>();
            while (p != null) {
                if (p.word.equals(synset)) {
                    idList.add(p.id);
                }
                p = p.next;
            }
            return idList;
        }

        public int containWord(String synset) {
            Word p = this;
            int time = 0;
            while (p != null) {
                if (p.word.equals(synset)) {
                    time += 1;
                }
                p = p.next;
            }
            return time;
        }

        public int getID() {
            return id;
        }

        public String getWord() {
            return word;
        }

        public String getMeaning() {
            return meaning;
        }
    }
}
