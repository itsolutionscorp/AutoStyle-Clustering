from collections import Counter

def word_count(phrase):
    words = phrase.split()
    return Counter(words)
