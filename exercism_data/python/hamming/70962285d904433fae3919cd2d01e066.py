from itertools import izip


def distance(s1, s2):
    assert len(s1) == len(s2), \
            'Hamming distance is only defined for same length strings'

    return sum(c1 != c2 for c1, c2 in izip(s1, s2))
