from collections import Counter

def detect_anagrams(word, candidates):
    word_lower = word.lower()
    word_counter = Counter(word_lower)
    def pred(c):
        return Counter(c) == word_counter and c != word_lower
    return [c for c in candidates if pred(c.lower())]
