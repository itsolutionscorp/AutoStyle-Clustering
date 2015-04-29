def detect_anagrams(word, candidates):
    word_lower = word.lower()
    word_sorted = sorted(word_lower)
    word_length = len(word)
    anagrams = []
    for candidate in candidates:
        if len(candidate) != word_length:
            continue
        candidate_lower = candidate.lower()
        if candidate_lower == word_lower:
            continue
        if sorted(candidate_lower) == word_sorted:
            anagrams.append(candidate)
    return anagrams
