def detect_anagrams(word, candidates):
    letters = sorted(word.lower())
    return [anagram for anagram in candidates if
        letters == sorted(anagram.lower()) and word.lower() != anagram.lower()]
