from operator import mul
from functools import reduce

def largest_product(string, length):
    l = []
    for s in slices(string, length):
        l.append(reduce(mul, s, 1))
    return(max(l))

def slices(string, length):
    if length > len(string):
        raise(ValueError)
    r = []
    for i in range(0,len(string)+1-length):
        r.append([int(n) for n in (string[i:i+length])])
    return(r)
