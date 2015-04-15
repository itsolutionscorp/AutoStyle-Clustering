from itertools import izip_longest
def hamming(s1, s2):
    return sum([c1 != c2 for c1, c2 in izip_longest(s1, s2)])
