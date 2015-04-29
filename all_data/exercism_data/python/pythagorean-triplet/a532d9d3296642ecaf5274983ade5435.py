from __future__ import division

import math
import fractions

def small_factors(n):
    """
    small_factors(int) -> yields int

    For each factors a*b = n, return min(a, b).
    """

    for i in range(1, int(math.sqrt(n)) + 1):
        if n % i == 0:
            yield i

def triplets_in_range(minimum, maximum):
    triplets = set()

    for a in range(minimum, maximum + 1):
        for b in range(a, maximum + 1):
            c_sq = a ** 2 + b ** 2
            c_int = int(math.sqrt(c_sq))

            if c_int <= maximum and c_int ** 2 == c_sq:
                triplets.add((a, b, c_int))

    return triplets

def primitive_triplets(b):
    if b % 4 != 0:
        raise ValueError("b must be divisible by 4")

    triplets = set()
    for n in small_factors(b//2):
        m = b // (2 * n)

        if (m - n) % 2 == 1 and fractions.gcd(m, n) == 1:
            a = m ** 2 - n ** 2
            c = m ** 2 + n ** 2

            triplets.add(tuple(sorted((a, b, c))))

    return triplets

def is_triplet(triplet):
    a, b, c = sorted(triplet)

    return a**2 + b**2 == c**2
