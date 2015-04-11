def detect_anagrams(word, candidates):
    true_candidates = []

    for candidate in candidates:
        if is_anagram(word, candidate.lower().strip()):
            true_candidates.append(candidate)

    return true_candidates


def is_anagram(word, candidate):
    if word == candidate or len(word) != len(candidate):
        return False

    letters = list(word.lower().strip())

    for c in candidate:
        if c in letters:
            letters.remove(c)
        else:
            return False
    
    return True
