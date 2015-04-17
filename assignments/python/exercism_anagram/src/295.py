from collections import Counter
def detect_anagrams(word, candidates):
    return [x for x in candidates if Counter(x.lower()) == Counter(word.lower()) and word.lower() != x.lower()]
