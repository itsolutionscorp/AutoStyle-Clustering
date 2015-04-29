from collections import Counter

def word_count(phrase):
    return dict(Counter(phrase.split()))
