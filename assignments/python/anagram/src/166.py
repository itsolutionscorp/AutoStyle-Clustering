def detect_anagrams(x, wlist):
    poss = [w for w in wlist if sorted(x.lower()) == sorted(w.lower())]
    anag = [w for w in poss if w.lower() != x]
    return anag
