def detect_anagrams(seed, comparisons):
    anagrams = list(word for word in comparisons if sorted(word.lower()) == sorted(seed.lower()))
    return anagrams
