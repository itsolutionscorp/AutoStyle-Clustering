from collections import Counter

def detect_anagrams(word, candidates):
    anagrams = []
    word_histogram = Counter(word.lower())
    for candidate in  candidates:
        if candidate.lower() != word.lower() and Counter(candidate.lower()) == word_histogram:
                anagrams.append(candidate)
    return anagrams

