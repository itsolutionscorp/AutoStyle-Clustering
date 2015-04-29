def detect_anagrams(word, words):
    anagrams = []
    sortedword = sorted(word.lower())
    for a in words:
        if sorted(a.lower()) == sortedword and (a.lower() != word.lower()):
            anagrams.append(a)
    return anagrams
