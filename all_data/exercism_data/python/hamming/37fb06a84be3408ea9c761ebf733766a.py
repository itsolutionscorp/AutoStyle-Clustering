# Python 2
# from itertools import izip_longest as zip_longest

#  Python 3
from itertools import zip_longest


def hamming(sequence1, sequence2):
    distance = 0
    for ch1, ch2 in zip_longest(sequence1, sequence2):
        if ch1 != ch2:
            distance += 1
    return distance
