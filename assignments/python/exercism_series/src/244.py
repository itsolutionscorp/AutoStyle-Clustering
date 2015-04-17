import itertools
def slices(num_string, r):
    if not r or r > len(num_string):
        raise ValueError
    return [map(int, num_string[i:i+r]) for i in xrange(len(num_string)-r+1)]
