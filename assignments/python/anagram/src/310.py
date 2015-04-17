def detect_anagrams(word, candidates):
    anagrams = []
    for candidate in candidates:
        word_lower = word.lower()
        candidate_lower = candidate.lower()
        if word_lower == candidate_lower:
            continue
        if sorted(list(word_lower)) == sorted(list(candidate_lower)):
            anagrams.append(candidate)
    return anagrams
