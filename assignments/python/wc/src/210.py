from collections import Counter
def word_count(phrase):
    '''Returns dict with word counts for a given phrase.'''
    return Counter(phrase.split())
