import re
from math import ceil, sqrt


def encode(phrase):
    encoded = re.sub(r'[ \\."!.,\']', "", phrase.lower())

    word_len = ceil(len(encoded) / ceil(sqrt(len(encoded))))
    num_words = ceil(sqrt(len(encoded)))

    word_list = [x for i in range(0, num_words) for x in encoded[i::word_len+1]]
    encoded = ''.join(word_list)
    return ' '.join(encoded[i:i+word_len] for i in range(0, len(encoded), word_len))
