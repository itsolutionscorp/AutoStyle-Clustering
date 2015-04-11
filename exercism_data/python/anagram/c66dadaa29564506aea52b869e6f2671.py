def detect_anagrams(word, words):
    target = source.lower()
    letters = sorted(word)
            
    return [w for w in words if w.lower() != target and sorted(w.lower()) == letters]
