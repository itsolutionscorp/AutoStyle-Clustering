
from fractions import gcd

def triplets_in_range(min, max):
    return set((a, b, int(c))
               for a in range(min, max-1)
               for b in range(a+1, max)
               for c in [(a**2 + b**2)**0.5]
               if int(c) == c
               if c <= max)

def is_triplet(triplet):
    a, b, c = sorted(triplet)
    return a**2 + b**2 == c**2

def primitive_triplets(b):
    if b%4:
        raise ValueError
    
    return set(tuple(sorted([(m**2 - n**2), b, (m**2 + n**2)]))
               for m in range(1, b//2 + 1)
               for n in [b//(2*m)]
               if b == 2*m*n
               if gcd(m, n) == 1
               if m > n
               if (m-n)&1)
