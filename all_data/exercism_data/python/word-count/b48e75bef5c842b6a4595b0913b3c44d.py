from collections import defaultdict


def word_count(phrase):
    """Count occurence of words in input string"""
    count = defaultdict(int)
    words = phrase.split()
    for w in words:
        count[w] += 1
    return count
