def detect_anagrams(word, candidates):
    return [
        c for c in candidates
        if sorted(word.lower()) == sorted(c.lower())
        and word.lower() != c.lower()
    ]
