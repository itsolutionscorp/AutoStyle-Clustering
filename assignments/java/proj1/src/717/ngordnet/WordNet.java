package ngordnet;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private class Synset {
        private int id;
        private String[] synonyms;
        private String definition;

        public int getId() {
            return id;
        }

        public void setId(int id2) {
            this.id = id2;
        }

        public String[] getSynonyms() {
            return synonyms;
        }

        public void setSynonyms(String[] synonyms2) {
            this.synonyms = synonyms2;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition2) {
            this.definition = definition2;
        }

        public Synset(int id2, String[] synonyms2, String definition2) {
            this.id = id2;
            this.synonyms = synonyms2;
            this.definition = definition2;
        }
    }

    private HashMap<Integer, Synset> synsetsInt;
    private HashMap<String, LinkedList<Synset>> synsetsStr;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        /*
         * synsets.txt 79537,viceroy vicereine,governor of a country or province
         * who rules... 38611,exarch,a viceroy who governed a large province in
         * the Roman Empire 9007,Khedive,one of the Turkish viceroys who ruled
         * Egypt between...
         */

        /*
         * hyponyms.txt 79537,38611,9007
         */

        this.synsetsInt = new HashMap<Integer, Synset>();
        this.synsetsStr = new HashMap<String, LinkedList<Synset>>();

        File file1 = new File(synsetFilename);
        File file2 = new File(hyponymFilename);
        if (!file1.exists() || !file2.exists()) {
            return;
        }

        In synIn = new In(synsetFilename);
        In hypoIn = new In(hyponymFilename);

        String line, numStr, synsStr, defStr;
        String[] synsArr;
        int num;
        StringTokenizer tok, synsTok;
        Synset synset;
        LinkedList<Synset> synsetsList;
        int hypoCount = 0;
        while (synIn.hasNextLine()) {
            line = synIn.readLine();
            hypoCount++;
            tok = new StringTokenizer(line, ",");
            numStr = tok.nextToken();
            synsStr = tok.nextToken();
            defStr = tok.nextToken();
            num = Integer.parseInt(numStr);
            synsTok = new StringTokenizer(synsStr, " ");
            synsArr = new String[synsTok.countTokens()];
            for (int i = 0; i < synsArr.length; i++) {
                synsArr[i] = synsTok.nextToken();
            }
            synset = new Synset(num, synsArr, defStr);
            synsetsInt.put(num, synset);
            for (int i = 0; i < synsArr.length; i++) {
                synsetsList = synsetsStr.get(synsArr[i]);
                if (synsetsList == null) {
                    synsetsList = new LinkedList<Synset>();
                    synsetsList.add(synset);
                    synsetsStr.put(synsArr[i], synsetsList);
                }
                synsetsList.add(synset);
            }
        }

        synIn.close();

        int len;
        hyponyms = new Digraph(hypoCount * 2);
        while (hypoIn.hasNextLine()) {
            line = hypoIn.readLine();
            tok = new StringTokenizer(line, ",");
            numStr = tok.nextToken();
            num = Integer.parseInt(numStr);
            len = tok.countTokens();
            for (int i = 0; i < len; i++) {
                hyponyms.addEdge(num, Integer.parseInt(tok.nextToken()));
            }
        }

        hypoIn.close();

    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        LinkedList<Synset> synsets = synsetsStr.get(word);
        HashSet<Integer> ids = new HashSet<Integer>();
        HashSet<String> ret = new HashSet<String>();
        Synset tailSyn;
        if (synsets == null) {
            return ret;
        }
        for (Synset synset : synsets) {
            ids.add(synset.id);
        }
        Set<Integer> descendants = GraphHelper.descendants(hyponyms, ids);
        for (Integer descendant : descendants) {
            tailSyn = synsetsInt.get(descendant);
            for (String synset2 : tailSyn.synonyms) {
                ret.add(synset2);
            }
        }
        return ret;
    }

    public boolean isNoun(String noun) {
        return (synsetsStr.containsKey(noun));
    }

    public Set<String> nouns() {
        return synsetsStr.keySet();
    }

}
