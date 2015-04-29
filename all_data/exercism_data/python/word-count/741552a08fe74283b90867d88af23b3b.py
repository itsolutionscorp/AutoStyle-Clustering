import collections
import re


def word_count(phrase):
    word_freq = collections.defaultdict(int)
    for word in re.split('\s+', phrase.strip()):
        word_freq[word] += 1
    return word_freq
