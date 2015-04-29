def detect_anagrams(word, words):
    wlst = sorted(word)
    return [w for w in words if sorted(w) == wlst]
