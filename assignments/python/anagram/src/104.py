def detect_anagrams(anagram, words):
    a = sorted([c.lower() for c in anagram])
    anagrams = []
    for word in words:
        w = sorted([c for c in word.lower()])
        if w == a and anagram != word.lower():
            anagrams.append(word)
    return anagrams
