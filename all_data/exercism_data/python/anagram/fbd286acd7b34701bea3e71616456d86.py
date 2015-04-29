from collections import Counter


def detect_anagrams(word, potentials):
    word = word.lower()
    frequency = Counter(word)

    return [w for w in potentials
            if Counter(w.lower()) == frequency and w.lower() != word]
