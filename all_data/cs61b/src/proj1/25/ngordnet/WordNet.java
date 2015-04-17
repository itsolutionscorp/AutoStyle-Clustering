package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class WordNet {
    private int fileLength;
    private In fileLengthFinder;
    private int nounsKeyCounter;
    private In synsetIn;
    private In hypoIn;
    private Digraph dg;
    private Map<Integer, String> nounsIS;
    private Map<String, Integer> nounsSI;
    private Map<String, String[]> synonyms;
    private Map<Integer, String[]> lineHolderSynset;
    private Map<Integer, Integer[]> lineHolderHypo;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetIn = new In(synsetFilename);
        hypoIn = new In(hyponymFilename);
        fileLengthFinder = new In(synsetFilename);
        fileLength = 0;
        nounsKeyCounter = 0;
        while (fileLengthFinder.hasNextLine()) {
            fileLengthFinder.readLine();
            fileLength++;
        }
        dg = new Digraph(fileLength);
        nounsIS = new HashMap<Integer, String>();
        nounsSI = new HashMap<String, Integer>();
        synonyms = new HashMap<String, String[]>();
        lineHolderSynset = new HashMap<Integer, String[]>();
        lineHolderHypo = new HashMap<Integer, Integer[]>();
        for (int i = 0; i < fileLength; i++) {
            String hypoStr = hypoIn.readLine();
            if (hypoStr != null) {
                String[] hypoArray = hypoStr.split(","); // http://stackoverflow.com/questions
                                                         // /2858121/convert-comma-separated-
                                                         // string-to-array
                Integer[] hyponymsArray = new Integer[hypoArray.length - 1];
                for (int j = 1; j < hypoArray.length; j++) {
                    dg.addEdge(Integer.parseInt(hypoArray[0]), Integer.parseInt(hypoArray[j]));
                    hyponymsArray[j - 1] = Integer.parseInt(hypoArray[j]);
                }
                lineHolderHypo.put(Integer.parseInt(hypoArray[0]), hyponymsArray);
            }
            String synsetStr = synsetIn.readLine();
            String[] synsetArray = synsetStr.split(",");
            String[] nounArray = synsetArray[1].split(" ");
            lineHolderSynset.put((Integer) i, nounArray);
            for (int j = 0; j < nounArray.length; j++) {
                if (!isNoun(nounArray[j])) {
                    nounsIS.put(nounsKeyCounter, nounArray[j]);
                    nounsSI.put(nounArray[j], nounsKeyCounter);
                    nounsKeyCounter++;
                }
                if (nounArray.length > 1) {
                    for (int h = 0; h < nounArray.length; h++) {
                        String[] tmp = new String[nounArray.length];
                        String[] littleTmp = new String[nounArray.length - 1];
                        tmp = nounArray.clone(); // http://www.journaldev.com/753/how-to-copy
                                                 // -arrays-in-java
                        int count = 0;
                        for (int k = 0; k < nounArray.length; k++) {
                            if (k != h) {
                                littleTmp[count] = tmp[k];
                                count++;
                            }
                        }
                        if (synonyms.get(nounArray[h]) != null) {
                            String[] mergeTmp = synonyms.get(nounArray[h]);
                            String[] mergedArray = new String[mergeTmp.length + littleTmp.length];
                            int counter = 0;
                            for (int x = 0; x < mergeTmp.length; x++) {
                                mergedArray[x] = mergeTmp[x];
                                counter++;
                            }
                            for (int x = 0; x < littleTmp.length; x++) {
                                if (!stringArrayChecker(mergedArray, littleTmp[x])) {
                                    mergedArray[counter] = littleTmp[x];
                                    counter++;
                                }
                            }
                            mergedArray = stringArrayResizer(mergedArray);
                            synonyms.put(nounArray[h], mergedArray);
                        } else {
                            synonyms.put(nounArray[h], littleTmp);
                        }
                    }
                }
            }
        }
    }

    private String[] stringArrayResizer(String[] str) {
        int nullCounter = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == null) {
                nullCounter++;
            }
        }
        if (str.length - nullCounter == 0) {
            return null;
        }
        String[] resizedArray = new String[str.length - nullCounter];
        int indexer = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != null) {
                resizedArray[indexer] = str[i];
                indexer++;
            }
        }
        return resizedArray;
    }

    private Integer[] intArrayResizer(Integer[] str) {
        int nullCounter = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == null) {
                nullCounter++;
            }
        }
        if (str.length - nullCounter == 0) {
            return null;
        }
        Integer[] resizedArray = new Integer[str.length - nullCounter];
        int indexer = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != null) {
                resizedArray[indexer] = str[i];
                indexer++;
            }
        }
        return resizedArray;
    }

    private boolean stringArrayChecker(String[] arr, String x) {
        for (String str : arr) { // http://stackoverflow.com/questions/9712826
                                 // /java-check-if-item-is-in-array
            if (str != null) {
                if (str.equals(x)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nounsSI.containsKey(noun); // http://docs.oracle.com/javase/7
                                               // /docs/api/java/util/HashMap.html
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return this.nounsSI.keySet(); // http://docs.oracle.com/javase/7/docs
                                      // /api/java/util/HashMap.html
    }

    private Integer[] wordIndexFinder(String word) {
        Integer[] indexHolder = new Integer[fileLength];
        int indexHolderindexer = 0;
        for (int i = 0; i < fileLength; i++) {
            String[] synonymArrayLine = lineHolderSynset.get(i);
            for (int j = 0; j < synonymArrayLine.length; j++) {
                if (synonymArrayLine[j].equals(word)) {
                    indexHolder[indexHolderindexer] = i;
                    indexHolderindexer++;
                }
            }
        }
        indexHolder = intArrayResizer(indexHolder);
        if (indexHolder != null) {
            return indexHolder;
        }
        return null;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Integer[] wordIndex = wordIndexFinder(word);
        if (wordIndex != null) {
            Set<String> hypos = new TreeSet<String>();
            Integer[] deepHypoHolder = new Integer[fileLength];
            int deepHypoHolderIndexer = 0;
            while (wordIndex != null) {
                for (int n = 0; n < wordIndex.length; n++) {
                    Set<Integer> hypoCollector = new TreeSet<Integer>();
                    Integer[] hypoHolder = lineHolderHypo.get(wordIndex[n]);
                    if (hypoHolder != null) {
                        for (int i = 0; i < hypoHolder.length; i++) {
                            hypoCollector.add(hypoHolder[i]);
                        }
                        for (int hypoNumber : hypoCollector) {
                            deepHypoHolder[deepHypoHolderIndexer] = hypoNumber;
                            deepHypoHolderIndexer++;
                            int lhsLength = this.lineHolderSynset.get(hypoNumber).length;
                            for (int i = 0; i < lhsLength; i++) {
                                hypos.add(this.lineHolderSynset.get(hypoNumber)[i]);
                            }
                        }
                    }
                    String[] wordSynonyms = this.synonyms.get(word);
                    if (wordSynonyms != null) {
                        for (int i = 0; i < wordSynonyms.length; i++) {
                            hypos.add(wordSynonyms[i]);
                        }
                    }
                    hypos.add(word);
                }
                deepHypoHolder = intArrayResizer(deepHypoHolder);
                wordIndex = deepHypoHolder;
                deepHypoHolder = new Integer[fileLength];
                deepHypoHolderIndexer = 0;
            }
            return hypos;
        }
        throw new NoSuchElementException("Word not in database");
    }
}
