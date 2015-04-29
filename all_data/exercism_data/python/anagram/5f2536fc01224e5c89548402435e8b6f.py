from collections import Counter

def detect_anagrams(word, candidates):
    word_histogram = Counter(word.lower())
    anagrams = [c for c in candidates if
                c.lower() != word.lower() and Counter(c.lower()) == word_histogram]
    return anagrams

