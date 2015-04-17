package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Bag;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.lang.String;

//Significant help in understanding this class and developing my code was given by Mary Sayre Sherrill who talked everything through with me as I coded this
//she also helped me debug this class and edit my code when it wasn't working.
public class WordNet {

  private ArrayList<String> vertices = new ArrayList<String>();
  private HashMap<Integer, ArrayList<Integer>> hyponyms = new HashMap<Integer, ArrayList<Integer>>();
  private HashMap<String, ArrayList<Integer>> synsetIDs = new HashMap<String, ArrayList<Integer>>();;
  private HashMap<Integer, ArrayList<String>> synSets = new HashMap<Integer, ArrayList<String>>();;
  private ArrayList<String> nouns = new ArrayList<String>();
  private int v;

  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
  public WordNet(String synsetFilename, String hyponymFilename) {
    synsetsHelper(synsetFilename);
    hyponymHelper(hyponymFilename);
    v = vertices.size();
    Digraph d = new Digraph(v);
    ArrayList<Integer> inputs;
    if(hyponyms.isEmpty()){
      return;
    }else{
      for (int i = 0; i < hyponyms.size(); i++) {
        if(hyponyms.get(i) != null){
          inputs = hyponyms.get(i);
          for(int j = 0; j < inputs.size(); j++){
            d.addEdge(i, inputs.get(j));
          }
        }
      }
    }
  }

  private void synsetsHelper(String synsetFilename){
    In input = new In(synsetFilename);
    if(input.isEmpty())
      throw new IllegalArgumentException("empty file input");
    String line = input.readLine();
    while (line != null) {
      ArrayList<Integer> idList = new ArrayList<Integer>();
      if(line.equals(""))
        continue;
      String[] lineElements = line.split(","); // split the elements with commas
      vertices.add(lineElements[1]);  //establish the vertices from the synset
      String[] words = lineElements[1].split(" ");
      ArrayList<String> currentVals = new ArrayList<String>();
      for (int i = 0; i < words.length; i++) { //separate words
        currentVals.add(words[i]);
      }
      Integer id = Integer.parseInt(lineElements[0]);
      synSets.put(id, currentVals);
      for (int i = 0; i < words.length; i++) {
        if (synsetIDs.containsKey(words[i])) {
          synsetIDs.get(words[i]).add(id);
        }
        else {
          idList.add(id);
          synsetIDs.put(words[i], idList);
        }
      }
      for (int i = 0; i < currentVals.size(); i++) {
        nouns.add(currentVals.get(i));
      }
      line = input.readLine();
    }
  }
  private void hyponymHelper(String hyponymFilename){
    In input = new In(hyponymFilename);
    if(input.isEmpty())
      throw new IllegalArgumentException("empty file input");
    String line = input.readLine();
    while (line != null){
      if(line.equals(""))
        continue;
      String[] lineElements = line.split(",");
      ArrayList<Integer> edgeList = new ArrayList<Integer>();
                  ArrayList<Integer> synonyms = new ArrayList<Integer>();
      for(int i = 0; i < lineElements.length; i ++){
        edgeList.add(Integer.parseInt(lineElements[i]));
      }
      for (int x = 0; x < edgeList.size(); x++) {
                synonyms.add(edgeList.get(x));
            }
      hyponyms.put(Integer.parseInt(lineElements[0]),synonyms);
      line = input.readLine();
    }
  }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
      return synsetIDs.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
      return new TreeSet<String>(nouns);
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
      ArrayList<String> hyponymList = new ArrayList<String>();
      if (!this.nouns().contains(word)) {
        throw new IllegalArgumentException("No such word in synset");
      }
      else {
        ArrayList<Integer> ids = this.synsetIDs.get(word);
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> holder;
        for (int i = 0; i < ids.size(); i++) {
          holder = synSets.get(ids.get(i));
          for (int j = 0; j < holder.size(); j++) {
            temp.add(holder.get(j));
          }
        }
        for(int i = 0; i < temp.size(); i++)
          hyponymList.add(temp.get(i)); 
        ArrayList<Integer> hyponymIDs;
        for (int i = 0; i < ids.size(); i++) {
          if(hyponyms.get(ids.get(i)) == null)
            continue;
          hyponymIDs = hyponyms.get(ids.get(i));
          System.out.println(ids);
          System.out.println(i);
          for (int j = 0; j < hyponymIDs.size(); j++) {
            holder = synSets.get(hyponymIDs.get(j));
            for (int k = 0; k < holder.size(); k++) {
              hyponymList.add(holder.get(k));
            }
          }
        }    
      } 
      return new HashSet(hyponymList);
  }
} 
