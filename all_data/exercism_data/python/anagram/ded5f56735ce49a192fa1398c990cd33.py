def detect_anagrams(word, candidates):
    return [anagram for anagram in _test_candidates(word, candidates)]

def _test_candidates(word, candidates):
    letters_in_word = sorted(word.lower())
    for candidate in candidates:
        normalized = candidate.lower()
        if normalized != word:
            letters_in_candidate = sorted(normalized)
            if letters_in_candidate == letters_in_word:
                yield candidate
