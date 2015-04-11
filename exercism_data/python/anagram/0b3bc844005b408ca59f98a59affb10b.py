def detect_anagrams(word, candidates):

    word_lower = word.lower()
    word_lower_sorted = sorted(word_lower)

    def is_anagram(candidate):
        if candidate.lower() == word_lower:
            return False

        return sorted(candidate.lower()) == word_lower_sorted

    return filter(is_anagram, candidates)
