from collections import Counter

def word_count(phrase):
    """Counts the words in a submitted phrase"""
    return Counter(phrase.split())
