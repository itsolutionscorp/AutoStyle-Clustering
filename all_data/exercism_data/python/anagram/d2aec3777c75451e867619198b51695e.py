def detect_anagrams(word, words):

    wset = set(word)
    return [w for w in words if set(w) == wset]
