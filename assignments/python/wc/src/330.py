"""
Count some words
"""
from collections import Counter
__version__ = '0.0.1'
__all__ = ['__version__', 'word_count']
def word_count(phrase):
    """Returns counts of the occurences of each word
    ..versionadded:: 0.0.1
    :param phrase: The phrase for which we count the words
    """
    return Counter(phrase.split())
