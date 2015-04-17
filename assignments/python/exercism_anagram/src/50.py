def detect_anagrams(word, anagrams):
    real_anagrams = []
    for candidate in anagrams:
        # Case insensitive
        lower_word = word.lower()
        lower_candidate = candidate.lower()
        for char in lower_word:
            if char in lower_candidate:
                lower_candidate = lower_candidate.replace(char, "", 1)
        if not lower_candidate and len(candidate) == len(word):
            if candidate.lower() != lower_word:
                real_anagrams.append(candidate)
    return real_anagrams
