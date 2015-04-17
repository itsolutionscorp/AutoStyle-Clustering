def detect_anagrams(word, candidates):
    return [candidate for candidate in candidates if sorted(candidate.lower()) == sorted(word.lower()) and candidate.lower() != word.lower()]
