from fractions import gcd
from math import ceil,floor

def primitive_triplets(b):
    if not b or b % 4:
        raise ValueError('b must be positive integer, divisible by 4.')

    return set(get_triplet(m,n) for m,n in gen_mn(b))

def triplets_in_range(start, end):
    tset = set()
    for b in xrange(4, end, 4):
        for triplet in primitive_triplets(b):
            kstart = int(ceil(float(start)/triplet[0]))
            kend = int(floor(float(end)/triplet[2]))
            tset.update(set([tuple(k*t for t in triplet) for k in xrange(kstart,kend+1)]))

    return tset

def is_triplet(t):
    t = sorted(t)
    return t[0]**2 + t[1]**2 == t[2]**2

def gen_mn(b):
    '''Generator function for m,n satisfying Euclid's formula for primitive triplets.'''
    for m in xrange(1, b/2+1):
        n,r = divmod(b/2,m)
        if not r and m > n and (m-n)%2 and gcd(m,n)==1: 
            yield m,n

def get_triplet(m,n):
    return tuple(sorted([m*m-n*n, 2*m*n, m*m+n*n]))
