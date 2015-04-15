from fractions import gcd
from math import ceil, sqrt

def primitive_triplets(b):
    if b % 4:
        raise ValueError('b must be a multiple of 4.')
    return {tuple(sorted((m**2 - (b/m/2)**2, b, m**2 + (b/m/2)**2)))
            for m in range(ceil(sqrt(b//2)), b//2 + 1, 1)
            if gcd(m, (b/m/2)) == 1 and (m - (b/m/2)) % 2}

def triplets_in_range(min, max):
    return {(a, b, c)
            for a in range(min, max+1)
            for b in range(a, max+1)
            for c in range(b+1, max+1)
            if is_triplet((a, b, c))}

def is_triplet(abc):
    a, b, c = sorted(abc)
    return a**2 + b**2 == c**2
