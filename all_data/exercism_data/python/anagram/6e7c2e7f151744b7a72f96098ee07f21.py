def detect_anagrams(word, candidates):
    anagrams = [w for w in candidates if word.lower() != w.lower() and len(word) == len(w)]
    wordChars = getCharCounts(word)

    if anagrams:
        anagrams=[w for w in anagrams if wordChars == getCharCounts(w)]

    return anagrams

def getCharCounts(word):
    word = word.lower()
    return {c: word.count(c) for c in set(word)}
