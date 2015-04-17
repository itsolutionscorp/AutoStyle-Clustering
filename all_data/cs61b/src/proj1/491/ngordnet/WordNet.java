package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.*;
import java.lang.Object;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WordNet{
	// String sy;
	// String hy;
  private Digraph gg;
  private int lenOfsy;
  private int lenOfhy;
  private Set<String> allNouns;
  private Map<Integer,String> sy;
  private Map<String,String> sy1;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
      allNouns = new TreeSet<String>();
      sy = new HashMap<Integer,String>();
      sy1 = new HashMap<String,String>();
      In inSynset = new In(synsetFilename);
      In inHyponym = new In(hyponymFilename);
      String[] synsetLines = inSynset.readAllLines();
      String[] hyponymLines = inHyponym.readAllLines();
      lenOfsy = synsetLines.length;
      lenOfhy = hyponymLines.length;
      gg = new Digraph(lenOfsy);
      for (int i= 0; i < lenOfhy; i++){
        String hyponymLine = hyponymLines[i];
        String[] sepHyponym = hyponymLine.split(",");
        int mainId = Integer.parseInt(sepHyponym[0]);
        for(int j = 1; j < sepHyponym.length; j++){
          int tem = Integer.parseInt(sepHyponym[j]);
          gg.addEdge(mainId, tem);
        }
      }
      for (int i = 0; i < synsetLines.length; i++){
        String synsetLine = synsetLines[i];
        String[] sepSynset = synsetLine.split(",");
        int id = Integer.parseInt(sepSynset[0]);
        String first = sepSynset[0];
        String strNouns = sepSynset[1];
        sy.put(id,strNouns);
        String[] nounString = sepSynset[1].split(" ");
        for(int j = 0; j < nounString.length; j++){
          allNouns.add(nounString[j]);
          if(sy1.containsKey(nounString[j])){
            String first1 = sy1.get(nounString[j]);
            first1 = first1 + " " + first;
            sy1.put(nounString[j],first1);
          }else{
            sy1.put(nounString[j],first);
          }

        } 
      }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun){
      return allNouns.contains(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns(){
    	return allNouns;

    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word){
      Set<String> hyNouns = new TreeSet<String>();
      Set<Integer> needId = new TreeSet<Integer>();
      String sy1Keys = sy1.get(word);
      String[] sy2Keys = sy1Keys.split(" ");
      for(int i = 0; i < sy2Keys.length; i++){
        String temp = sy2Keys[i];
        int x = Integer.parseInt(temp);
        needId.add(x);
      }
      Set<Integer> result = GraphHelper.descendants(gg, needId);
      for(int i = 0; i < lenOfsy; i ++){
        if (result.contains(i)){
          String result1 = sy.get(i);
          String[] result2 = result1.split(" ");
          for(int j = 0; j < result2.length; j++){
          hyNouns.add(result2[j]);
          }
        }
      }
      return hyNouns;
    }
}