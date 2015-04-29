def sort_word(lower_word):
    return sorted(lower_word)


def is_anagram(sorted_word, candidate):
    return sorted_word == sort_word(candidate)


def detect_anagrams(word, candidates):
    anagrams = []
    word_lower = word.lower()
    word_sorted = sort_word(word_lower)
    for i, candidate in enumerate(candidates):
        candidate_lower = candidate.lower()
        if word_lower != candidate_lower:
            if is_anagram(word_sorted, candidate_lower):
                anagrams.append(candidate)
    return anagrams
