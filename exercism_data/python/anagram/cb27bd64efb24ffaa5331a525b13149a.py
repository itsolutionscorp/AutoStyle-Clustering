from collections import Counter


def detect_anagrams(pattern, potential):
    low_ptrn = pattern.lower()
    pattern_cnts = Counter(low_ptrn)
    return [word for word in potential if word.lower() != low_ptrn and
            Counter(word.lower()) == pattern_cnts]
