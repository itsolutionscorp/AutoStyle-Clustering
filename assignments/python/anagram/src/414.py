def detect_anagrams(word, words):
    lword = word.lower()
    letters = sorted(lword)
    match = []
    for w in words:
        if sorted(w.lower()) == letters and w.lower() != lword:
            match.append(w)
    return match
