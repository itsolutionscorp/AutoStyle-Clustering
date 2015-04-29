package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {

    private Entry front = null;

    public WordNet(String synsetsTXT, String hyponymsTXT) {
        In synsets = new In(synsetsTXT);
        In hyponyms = new In(hyponymsTXT);
        String[] synsetsAll = synsets.readAllLines();
        String[] hyponymsAll = hyponyms.readAllLines();
        String[] nounsAll = new String[synsetsAll.length];
        for (int a = 0; a < synsetsAll.length; a++) {
            String[] dataSplit1 = synsetsAll[a].split(",");
            nounsAll[Integer.parseInt(dataSplit1[0])] = dataSplit1[1];
        }

        for (int b = 0; b < hyponymsAll.length; b++) {
            String[] dataSplit2 = hyponymsAll[b].split(",");
            String[] hypons = new String[dataSplit2.length - 1];
            for (int i = 0; i < hypons.length; i++) {
                hypons[i] = nounsAll[Integer.parseInt(dataSplit2[i + 1])];
            }
            front = new Entry(nounsAll[Integer.parseInt(dataSplit2[0])], hypons, front);
        }

        for (int c = 0; c < nounsAll.length; c++) {
            if (!front.contains(nounsAll[c])) {
                front = new Entry(nounsAll[c], null, front);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        Set<String> answer = hyponymsAlmost(word); Entry y = front;
        while (y != null) {
            String[] temp6 = y.hypernym.split(" ");
            if (temp6.length > 1) {
                for (int i = 0; i < temp6.length; i++) {
                    if (temp6[i].equals(word)) {
                        for (int j = 0; j < temp6.length; j++) {
                            if (!answer.contains(temp6[j])) {
                                answer.add(temp6[j]);
                            }
                        }
                    }
                }
            }
            y = y.next;
        }
        return answer;
    }

    private Set<String> hyponymsAlmost(String word) {
        TreeSet<String> answer = new TreeSet<String>(); Entry x = front;
        String temp5 = word;
        String[] temp4 = word.split(" ");
        if (temp4.length > 1) {
            temp5 = temp4[0];
        }
        while (x != null) {
            String[] temp = x.hypernym.split(" ");
            if ((temp.length == 1) && (!x.hypernym.equals(temp5))) {
                x = x.next;
                continue;
            } else {
                boolean continueLoop = false;
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i].equals(temp5)) {
                        continueLoop = true;
                    }
                }
                if (!continueLoop) {
                    x = x.next;
                    continue;
                }
            }
            if (x.hyponyms != null) {
                for (int j = 0; j < x.hyponyms.length; j++) {
                    Set<String> temp2 = hyponymsAlmost(x.hyponyms[j]);
                    if (!temp2.isEmpty()) {
                        answer.addAll(temp2);
                    }
                }
            }
            String[] temp3 = word.split(" ");
            if (temp3.length == 1) {
                answer.add(word);
            } else {
                for (int k = 0; k < temp3.length; k++) {
                    answer.add(temp3[k]);
                }
            }
            x = x.next;
        }
        return answer;
    }

    public boolean isNoun(String noun) {
        Entry x = front;
        while (x != null) {
            String[] temp = x.hypernym.split(" ");
            if ((temp.length == 1) && (x.hypernym.equals(noun))) {
                return true;
            } else {
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i].equals(noun)) {
                        return true;
                    }
                }
            }
            x = x.next;
        }
        return false;
    }

    public Set<String> nouns() {
        TreeSet<String> answer = new TreeSet<String>(); Entry x = front;
        while (x != null) {
            String[] temp = x.hypernym.split(" ");
            if (temp.length == 1) {
                answer.add(x.hypernym);
            } else {
                for (int i = 0; i < temp.length; i++) {
                    answer.add(temp[i]);
                }
            }
            x = x.next;
        }
        return answer;
    }

    private class Entry {
        private String hypernym;
        private String[] hyponyms;
        private Entry next;
        public Entry(String n, String[] nn, Entry nnn) {
            hypernym = n;
            hyponyms = nn;
            next = nnn;
        }

        public Entry get(String k) {
            for (Entry e = this; e != null; e = e.next) {
                if (k.equals(e.hypernym)) {
                    return e;
                }
            }
            return null;
        }

        public boolean contains(String k) {
            for (Entry e = this; e != null; e = e.next) {
                if (k.equals(e.hypernym)) {
                    return true;
                }
            }
            return false;
        }
    }
}
