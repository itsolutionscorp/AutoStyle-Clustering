"""
anagram - a module for detecing anagrams.
"""
from itertools import permutations
def detect_anagrams(word, candidates):
    """
    Filter out all entries among the candidates that are not anagrams of the given word.
    """
    # Brute force approach using a set comprehension.
    word = word.lower()
    anagrams = {''.join(permutation) for permutation in permutations(word)}
    anagrams.remove(word)
    return [candidate for candidate in candidates if candidate.lower() in anagrams]
