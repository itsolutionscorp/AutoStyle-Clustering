from itertools import permutations
from fractions import gcd
from math import sqrt


def primitive_triplets(b):
    if b % 4 != 0 or not b > 0:
        raise ValueError("Argument to primitive_triplets must \
                         be greater than 0 and divisible by 4. \
                         Provided value:{0}".format(b))
    return set(tuple(sorted((n ** 2 - m ** 2, 2 * m * n, m ** 2 + n ** 2)))
               for m, n in find_divisor_pairs(b))


def is_triplet(trio):
    triplet_cases = (a ** 2 + b ** 2 == c ** 2
                     for a, b, c in permutations(trio))
    return any(triplet_cases)


def triplets_in_range(a, b):
    # Accumulation location
    triplets = set()

    # We start iteration at 1 so that we find
    # all possible scalings of primitive triplets
    for i in range(1, b + 1):

        # Restrict our search to just numbers divisible by 4
        if i % 4 == 0:

            # Look through all valid primitive triplets...
            for triplet in primitive_triplets(i):

                # For all scalings of those triplets that fall
                # within the range of [a, b]
                triplets.update(valid_triple_scalings(triplet, a, b))

    return triplets


def valid_triple_scalings(primitive_triple, a, b):
    """Takes a primitive triplet and returns all
    scalings of that primitive triplet that fall within
    the interval [a, b]"""
    triple_set = set()
    x, y, z = primitive_triple

    # This algorithm is incorrect when b is less than z.
    # I'm not sure how to fix that right now.
    for i in range(1, b // z + 1):
        if x * i >= a:
            triple_set.add((x * i, y * i, z * i))
    return triple_set


def find_divisor_pairs(b):
    """Generates all valid divisor pairs
    (m, n) that satisfy the following requirements:
    - b = 2mn
    - m > n
    - m - n odd (one even, one odd...)
    - m, n coprime (do this one last)"""

    c = b / 2
    for m in range(1, int(sqrt(c)) + 1):
        if c / m % 1 == 0:
            n = int(c / m)
            if (m - n) % 2 == 1:
                if gcd(m, n) == 1:
                    yield (m, n)
