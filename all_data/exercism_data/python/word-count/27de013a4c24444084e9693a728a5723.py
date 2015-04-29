from collections import Counter

def word_count(words):
    return dict(Counter(words.split()))
