def detect_anagrams(word, candidates):
    letters_in_word = sorted(word.lower())
    return [candidate for candidate in candidates
            if candidate.lower() != word.lower()
            and letters_in_word == sorted(candidate.lower())]
