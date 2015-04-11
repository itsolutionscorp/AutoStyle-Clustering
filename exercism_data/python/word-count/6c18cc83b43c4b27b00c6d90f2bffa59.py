from collections

def word_count(phrase):
    """Given a phrase, return count of occurrences of each word"""
    return collections.Counter(phrase.split())
