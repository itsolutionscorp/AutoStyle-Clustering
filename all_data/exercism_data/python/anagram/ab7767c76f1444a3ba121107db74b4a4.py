from itertools import permutations

def detect_anagrams(word, anagrams):
    return [x for x in anagrams if x.lower() != word.lower() and sorted(x.lower()) == sorted(word.lower())]
