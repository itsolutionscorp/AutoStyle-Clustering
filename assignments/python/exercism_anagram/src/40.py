def detect_anagrams(word, words):
    lword = word.lower()
    wlst = sorted(lword)
    return [w for w in words if w.lower() != lword and sorted(w.lower()) == wlst]
