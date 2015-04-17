package ngordnet;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collection;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private TreeMap<Integer, String> vToword = new TreeMap();
    private TreeMap<String, TreeSet<Integer>> wordTov = new TreeMap();
    private TreeMap<String, String> wordTomean = new TreeMap();
    private TreeMap<Integer, String[]> vTosyno = new TreeMap();
    private TreeMap<Integer, TreeSet<Integer>> vTov = new TreeMap();
    private int size;
    private Digraph wordmap;
    
    public WordNet(String synsetsTopath, String hyponymsTopath) {
        In synsets = new In(synsetsTopath);
        while (synsets.hasNextLine()) {
            String line = synsets.readLine();
            String[] rawTokens = line.split(",");
            Integer id = Integer.parseInt(rawTokens[0]);

            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            String[] synonyms = tokens[0].split(" ");
            vTosyno.put(id, synonyms);
            
            if (synonyms.length > 1) {
                vToword.put(id, synonyms[0]);
                for (String synonym : synonyms) {
                    TreeSet<Integer> wordToset = new TreeSet();

                    //System.out.println(vTosyno.get(id)[0]);
                    if (wordTov.get(synonym) != null) {
                        Integer oldv = wordTov.get(synonym).last();
                        for (String word : vTosyno.get(oldv)) {
                            if (word.equals(synonym)) {
                                wordToset = wordTov.get(synonym);
                            }
                        }                      
                    }
                    wordToset.add(id);
                    wordTomean.put(synonym, tokens[1]);
                    wordTov.put(synonym, wordToset);
                    
                }
            } else {
                TreeSet<Integer> wordToset = new TreeSet();
                vToword.put(id, tokens[0]);
                //if(id == 298) System.out.println(wordTov.get(tokens[0] != null));
                if (wordTov.get(tokens[0]) != null) {
                    Integer oldv = wordTov.get(tokens[0]).last();
                    //System.out.println(vTosyno.get(oldv).contains(tokens[0]));
                    for (String word : vTosyno.get(oldv)) {
                        if (word.equals(tokens[0])) {
                            wordToset = wordTov.get(tokens[0]);
                        }
                    }        
                }
                wordToset.add(id);
                //System.out.println(wordToset);
                //System.out.println(tokens[0]);
                wordTov.put(tokens[0], wordToset);
                wordTomean.put(tokens[0], tokens[1]);
            }
        }
        size = wordTov.size();
        wordmap = new Digraph(size);
        In hyponyms = new In(hyponymsTopath);
        while (hyponyms.hasNextLine()) {
            TreeSet<Integer> vertexToset;
            String line = hyponyms.readLine();
            String[] rawTokens = line.split(",");
            if (vTov.get(Integer.parseInt(rawTokens[0])) != null) { 
                vertexToset = new TreeSet((vTov.get(Integer.parseInt(rawTokens[0]))));
            } else { 
                vertexToset = new TreeSet(); 
            }
            for (int i = 1; i < rawTokens.length; i += 1) {  
                vertexToset.add(Integer.parseInt(rawTokens[i]));  
                wordmap.addEdge(Integer.parseInt(rawTokens[0]), Integer.parseInt(rawTokens[i]));
                //if(Integer.parseInt(rawTokens[i])==0) System.out.println(wordmap);
            }
            //System.out.println(wordmap);
            vTov.put(Integer.parseInt(rawTokens[0]), vertexToset);
        }
    }

    public Set<String> hyponyms(String word) {
        TreeSet<String> hyponyms = new TreeSet();
        TreeSet<Integer> verticeToword = wordTov.get(word);
        for (Integer v : GraphHelper.descendants(wordmap, verticeToword)) {
            for (String syno : vTosyno.get(v)) {
                hyponyms.add(syno);
            }
        }
        hyponyms.add(word);
        return hyponyms;
    }

    public boolean isNoun(String word) {
        if (wordTov.get(word) != null) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        Collection<String[]> synonyms = vTosyno.values();
        TreeSet<String> nouns = new TreeSet();
        for (String[] syns : synonyms) {
            for (String s : syns) {
                nouns.add(s);
            }
        }

        return nouns;
    }
} 
