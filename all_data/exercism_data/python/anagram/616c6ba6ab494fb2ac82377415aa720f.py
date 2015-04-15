def detect_anagrams(word, cands):
    sword = sorted(word.lower())
    return [cand for cand in cands if sorted(cand.lower()) == sword and cand.lower() != word.lower()]
