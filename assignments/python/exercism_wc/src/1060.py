import collections
def word_count(phrase):
    return dict(collections.Counter(phrase.split()))
