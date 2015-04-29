from itertools import permutations

def detect_anagrams(word, anagrams):
    return [x for x in anagrams if x.lower() != word.lower() and x.lower() in set([a.lower() for a in anagrams]).intersection(["".join(p) for p in permutations(word.lower())])]
