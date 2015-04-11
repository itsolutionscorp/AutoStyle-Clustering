from math import fabs


def hamming(old, new):
    min_length = min(len(old), len(new))
    hamm = sum([1 for i in xrange(min_length) if old[i] != new[i]])
    return hamm + fabs(len(old) - len(new))
