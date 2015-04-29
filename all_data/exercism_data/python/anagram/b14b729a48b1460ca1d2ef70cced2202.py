def detect_anagrams(word, anas):
    # for ana in anas:
    #     if all(x in sorted(word.lower()) sorted(ana.lower())
    return [ana for ana in anas if word.lower() != ana.lower() and sorted(word.lower()) == sorted(ana.lower())]
