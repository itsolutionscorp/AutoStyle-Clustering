from collections import Counter

def word_count(string):
    return dict(Counter(string.split()))
