""" Word count.
    Excercism.io test 3
"""
import re

WORDS_SEPARATORS = [" ", "\n"]

def word_count(sentence, words_separators=None):
    """ :returns: a dictionnary with the word and their occurrences in 
            <sentence>
        :param str sentence: a string
        :param str words_separators: a list of delimiters to split <sentence> 
            into words
            if None, set to default constant WORDS_SEPARATORS
    """
    if words_separators is None:
        words_separators = WORDS_SEPARATORS
    
    words  = re.split("|".join(words_separators), sentence)
    counts = {}
    for word in words:
        if word:
            try:
                counts[word] += 1
            except KeyError:
                counts[word] = 1

    return counts
