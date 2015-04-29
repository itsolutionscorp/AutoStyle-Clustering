from fractions import gcd
from copy import copy

def is_triplet(trip):
    trip = sorted(trip)
    return trip[0]**2 + trip[1]**2 == trip[2]**2

def primitive_triplets(b):
    if b % 4:
        raise ValueError('To find primitive triplet, integer must be divisible by 4')
    triplets = []
    for n in range(1, int((b/2)**0.5)+1):
        if (b//2) % n == 0:
            m = (b//2) // n
            if gcd(n,m) == 1 and (m-n) % 2:
                a = m*m - n*n
                triplets.append((min(a,b), max(a,b), m*m + n*n))
    return set(triplets)

def triplets_in_range(low, high):
    candidates = [primitive_triplets(b) for b in range(1, high) if not b % 4]
    primitives = set([triplet for sublist in candidates for triplet in sublist
            if max(triplet) <= high])
    triples = copy(primitives)    
    for p in primitives:
        i = 2
        while i * max(p) <= high:
            triples.add(tuple((i*k for k in p)))
            i += 1
    return set([t for t in triples if min(t) >= low])
