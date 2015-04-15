from collections import Counter

def word_count(phrase):
    """Returns a dictionary with word mapped to number of occurances."""
    return dict(Counter(phrase.split()))
