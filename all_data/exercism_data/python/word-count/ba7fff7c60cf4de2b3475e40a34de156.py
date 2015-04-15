import collections
import re


def word_count(phrase):
    ''' Returns a dictionary showing the term frequency in the phrase '''

    word_freq = collections.defaultdict(int)
    for word in re.split(r'\s+', phrase.strip()):
        word_freq[word] += 1
    return word_freq
