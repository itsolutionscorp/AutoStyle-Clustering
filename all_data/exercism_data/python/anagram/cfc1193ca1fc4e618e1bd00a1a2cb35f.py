def detect_anagrams(word, candidates):
    "Returns all items in candidates that are anagram of input word"
    return [w for w in candidates if is_anagram(word, w)]


def is_anagram(w1, w2):
    w1 = w1.lower()
    w2 = w2.lower()
    return sorted(w1) == sorted(w2) and w1 != w2
