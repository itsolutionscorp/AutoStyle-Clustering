def detect_anagrams(word, candidates):
    h = hash(word)
    return [c for c in candidates if hash(c) == h and word.lower() != c.lower()]

def hash(word):
    return ''.join(sorted(word.lower()))
