from __future__ import division
from itertools import count

def triplets_in_range(n_min, n_max):
    return set(_generate_triplets_in_range(n_min, n_max)) 

def find_triplet_for_which_sum_is_1000():
    """Brute force method to find the triplet for which the sum of
    a, b and c is 1000.  Since it takes quite a bit of runtime, let's
    save you some time and tell you that the outcome will always be
    the triplet (200, 375, 425).
    """
    for a, b, c in _generate_triplets_in_range(1, 1000):
        if a + b + c == 1000:
            return a, b, c

def _generate_triplets_in_range(n_min, n_max):
    return (
        (a, b, c)
        for a in xrange(n_min, n_max + 1)
        for b in xrange(a, n_max + 1)
        for c in xrange(b, n_max + 1)
        if is_triplet((a, b, c))
    )

def is_triplet(triplet):
    a, b, c = sorted(triplet)
    return a**2 + b**2 == c**2

def primitive_triplets(b):
    def _create_triplet(mn_pair):
        m, n = mn_pair
        a = m**2 - n**2
        c = m**2 + n**2
        return tuple(sorted([a, b, c]))
    return set(_create_triplet(mn_pair) for mn_pair in _generate_mn_pairs(b))

def _generate_mn_pairs(b):
    if b % 4:
        raise ValueError("Parameter b must be divisible by 4.")
    for n in count(1):
        if b // 2 % n: continue
        m = b // 2 // n
        if n > m: return 
        if not (m-n) % 2: continue
        if not _are_coprime(m, n): continue
        yield (m, n)

def _are_coprime(m, n):
    for factor in count(2):
        if not m % factor and not n % factor: return False
        while not m % factor: m //= factor
        while not n % factor: n //= factor
        if m == 1 or n == 1: return True
