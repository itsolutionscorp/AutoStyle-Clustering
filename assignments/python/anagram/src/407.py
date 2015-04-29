def detect_anagrams(original, sentence):
    res = []
    for w in sentence:
        if original.lower() == w.lower():
            continue
        if sorted(original.lower()) == sorted(w.lower()):
            res.append(w)
    return res
