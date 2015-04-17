package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
  HashMap<Integer, String> idNoun = new HashMap<Integer, String>();
  HashMap<String, String> nounDef = new HashMap<String, String>();
  Set<String> nounSet = new HashSet<String>();
  Digraph digraph;

  public WordNet(String synsetFilename, String hyponymFilename) {
    In in1 = new In(synsetFilename);
    if (!in1.isEmpty()) {
      while (in1.hasNextLine()) {
        String syn = in1.readLine();
        String[] rawTokens = syn.split(",");
        idNoun.put(Integer.parseInt(rawTokens[0]), rawTokens[1]);
        nounDef.put(rawTokens[1], rawTokens[2]);
        String[] nouns = rawTokens[1].split(" ");
        for (String asset : nouns) {
          nounSet.add(asset);
        }
      }
    }

    digraph = new Digraph(idNoun.size());
    In in = new In(hyponymFilename);
    if (!in.isEmpty()) {
      while (in.hasNextLine()) {
        String syn = in.readLine();
        String[] rawTokens = syn.split(",");
        for (int i = 0; i < rawTokens.length; i++) {
          int a = Integer.parseInt(rawTokens[0]);
          int b = Integer.parseInt(rawTokens[i]);
          if (a != b) {
            digraph.addEdge(a, b);
          }
        }
      }
    }
  }

  /* Returns true if NOUN is a word in some synset. */
  public boolean isNoun(String noun) {
    return nounSet.contains(noun);
  }

  /* Returns the set of all nouns. */
  public Set<String> nouns() {
    return nounSet;
  }

  /**
   * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
   * If WORD belongs to multiple synsets, return all hyponyms of all of these
   * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
   * of synonyms.
   */
  public static Set<Integer> getKeySet(HashMap<Integer, String> hm,
      String value) {
    Set<Integer> keySet = new HashSet<Integer>();
    for (Integer e : hm.keySet()) {
      if (hm.get(e).contains(value)) {
        keySet.add(e);
      }
    }
    return keySet;
  }

  public Set<String> hyponyms(String word) {
    Set<Integer> sethyp = new HashSet<Integer>();
    Set<Integer> setclone = new HashSet<Integer>();
    Set<String> hyposet = new HashSet<String>();
    Iterable<Integer> iterate;
    sethyp.addAll((getKeySet(idNoun, word)));
    if (sethyp.size() != 0) {
      boolean flag = false;
      while(!flag) {
        setclone.addAll(sethyp);
        for (int e : setclone) {
          iterate = digraph.adj(e);
          for (int i : iterate) {
            sethyp.add(i);
          }
        }
        flag = sethyp.equals(setclone);
      }
      for (int i : sethyp) {
        String[] rawTokens = idNoun.get(i).split(" ");
        for (String e : rawTokens) {
          hyposet.add(e);
        }
      }
    }
    return hyposet;
  }
}