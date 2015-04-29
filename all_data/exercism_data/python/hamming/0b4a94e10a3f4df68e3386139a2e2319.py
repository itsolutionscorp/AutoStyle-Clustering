from itertools import izip

# the tail of the longer string is dropped. could use izip_longest with a
# fillvalue to count extra chars as different.
def distance(a, b):
    return sum([1 for x in izip(a, b) if x[0] != x[1]])
