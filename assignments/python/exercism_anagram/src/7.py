def detect_anagrams(word, cands):
    base = lambda w: sorted(w.lower())
    return [c for c in cands if base(c) == base(word) and
            c.lower() != word.lower()]
