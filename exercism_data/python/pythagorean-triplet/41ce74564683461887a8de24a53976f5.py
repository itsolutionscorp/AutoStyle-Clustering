from fractions import gcd
from math import floor, sqrt

def triplets_in_range(min, max):
    result = set()
    for a in range(min, max + 1):
        for b in range(min, max + 1):
            for c in range(min, max + 1):
                if a**2 + b**2 == c**2 and a < b < c:
                    result.add((a, b, c))
    return result

def primitive_triplets(b):
    if b % 2 == 1:
        raise ValueError
    results = set()
    pairs = getFactorPairs(b // 2)
    for p in pairs:
        if areCoPrime(p[0], p[1]) and (p[0] - p[1]) % 2 == 1:
            a = p[0]**2 - p[1]**2
            b = 2 * p[0] * p[1]
            c = p[0]**2 + p[1]**2
            results.add(tuple(sorted((a, b, c))))
    return results

def is_triplet(t):
    ts=sorted(t)
    return ts[0]**2 + ts[1]**2 == ts[2]**2

def areCoPrime(a, b):
    return gcd(a, b) == 1

def getFactorPairs(n):
    results = []
    for i in range(1, 1 + floor(sqrt(n))):
        if n % i == 0:
            results.append((n // i, i))
    return results
