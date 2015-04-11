import collections
import string


def word_count(sentence):
    sentence = ''.join(char.lower() for char in sentence if char not in string.punctuation)

    return dict(collections.Counter(sentence.split()))
