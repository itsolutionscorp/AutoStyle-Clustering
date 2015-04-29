from itertools import izip_longest

def distance(strand1, strand2):            
    return sum(x != y for (x, y) in izip_longest(strand1, strand2))
