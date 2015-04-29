from itertools import izip_longest


def hamming(s1, s2):
  return sum([1 for x, y in izip_longest(s1, s2) if x != y])
