import collections
def word_count(phrase):
    """
    Count the occurences of each word in the given phrase.
    """
    return collections.Counter(phrase.split())
