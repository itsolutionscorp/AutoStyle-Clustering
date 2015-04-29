def detect_anagrams(word, words):
    wlst = sorted(word.lower())
    return [w for w in words if sorted(w.lower()) == wlst]
