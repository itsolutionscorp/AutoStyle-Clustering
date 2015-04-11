def detect_anagrams(target, words):
    anagrams = []

    ltarget = target.lower()

    for word in words:
        lword = word.lower()
        if ltarget != lword and sorted(ltarget) == sorted(lword):
            anagrams.append(word)

    return anagrams
