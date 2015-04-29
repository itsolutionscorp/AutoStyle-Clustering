def detect_anagrams(word, candidates):
    return [c for c in candidates if is_anagram(c, word)]

def is_anagram(candidate, word):
    return candidate.lower() != word.lower() and \
        sorted(candidate.lower()) == sorted(word.lower())
