#!/usr/bin/env python

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

    # turn punctuation into single space characters
    # http://stackoverflow.com/questions/12437667/how-to-replace-punctuation-in-a-string-python
    replace_punctuation = string.maketrans(string.punctuation, ' '*len(string.punctuation))
    sanitized_phrase = phrase.translate(replace_punctuation)

    # convert to lower case and separate tokens on white space
    tokens = sanitized_phrase.lower().split()

    occurrences = collections.Counter(tokens)

    return dict(occurrences)
