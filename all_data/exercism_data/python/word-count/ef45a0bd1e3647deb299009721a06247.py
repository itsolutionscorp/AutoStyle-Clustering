import collections
import re


def word_count(phrase):
    ''' Returns a dictionary showing the term frequency in the phrase '''

    word_freq = collections.defaultdict(int)
    for word in phrase.split():
        word_freq[word] += 1
    return word_freq
