def detect_anagrams(word,cands):
    return filter(lambda c: sorted(word.lower()) == sorted(c.lower()) and word.lower() != c.lower(),cands)
