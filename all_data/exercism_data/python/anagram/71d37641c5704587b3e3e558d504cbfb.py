def detect_anagrams(word, list):
    l = []
    for w in list:
        if _is_anagram(w, word):
            l.append(w)
    return(l)

def _is_anagram(word1, word2):
    w1 = word1.lower().strip()
    w2 = word2.lower().strip()
    r = False
    if w1 == w2:
        pass
    elif len(w1) == len(w2):
        w1 = list(w1)
        w2 = list(w2)
        while len(w1)>0:
            l = w1.pop()
            if l not in w2:
                break
            else:
                w2.remove(l)
        if not len(w2):
            r= True
    return(r)
