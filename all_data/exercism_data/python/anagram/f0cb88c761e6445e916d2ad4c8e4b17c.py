def detect_anagrams(word, candidates):
    anagrams = []
    for candidate in candidates:
        if not equal_lower(candidate, word) and equal_sorted(candidate, word):
            anagrams.append(candidate)
    return anagrams

def equal_lower(first, second):
    return first.lower() == second.lower()

def equal_sorted(first, second):
    return sorted(first.lower()) == sorted(second.lower())
