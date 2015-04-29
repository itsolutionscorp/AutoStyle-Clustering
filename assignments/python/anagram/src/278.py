__author__ = 'Hinek'
import collections
def detect_anagrams(word, candidates):
    chars_orig = list(word.lower())
    result = []
    for c in candidates:
        if word == c.lower():
            continue
        chars_cand = list(c.lower())
        compare = lambda x, y: collections.Counter(x) == collections.Counter(y)
        if compare(chars_orig, chars_cand):
            result.append(c)
    return result
