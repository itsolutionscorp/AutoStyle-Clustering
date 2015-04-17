def detect_anagrams(word, candidates):
    anagrams = []
    for candidate in candidates:
        if sorted(word.lower()) == sorted(candidate.lower()) and word.lower() != candidate.lower():
            anagrams.append(candidate)
    return anagrams
