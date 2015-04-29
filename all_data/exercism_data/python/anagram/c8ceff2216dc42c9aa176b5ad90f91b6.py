def detect_anagrams(word, pos_anagrams):

    w = word.lower()
    ref = list(w)
    ref.sort()

    anagrams = []
    for p in pos_anagrams:
        
        if p.lower() == w:
            continue

        test = list(p.lower())
        test.sort()

        if test == ref:
            anagrams.append(p)

    return anagrams
