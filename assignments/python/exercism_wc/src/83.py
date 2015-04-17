"""Word Count"""
import collections
import string
__author__ = u'David Muse'
__date__ = u'2014-09-24'
__version__ = u'0.0.2'
def word_count(phrase):
    """
    @param String
    @returns Dictionary
    @brief Word Count
    """
    replace_punctuation = string.maketrans(string.punctuation, ' '*len(string.punctuation))
    sanitized_phrase = phrase.translate(replace_punctuation)
    tokens = sanitized_phrase.lower().split()
    occurrences = collections.Counter(tokens)
    return dict(occurrences)
