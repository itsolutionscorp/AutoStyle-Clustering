def detect_anagrams(word, candidates):
    return [w for w in candidates if is_anagram(word, w)]


def is_anagram(word, candidate):
    word = word.lower().strip()
    candidate = candidate.lower().strip()

    return word != candidate and sorted(word) == sorted(candidate)
