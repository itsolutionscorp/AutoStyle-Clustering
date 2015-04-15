from fractions import gcd
import math


def primitive_triplets(b):
    """ Find all primitive pythagorean triplets having b as one of their components

        Args: b - an integer divisible by 4

        The function primitive_triplets should find all possible pairs (m,n) such that m>n,
        m-n is odd, b=2*m*n and m and n are coprime (no common divisor except 1).

        The triplets are a=(m^2-n^2), b=2*m*n and c=(m^2+n^2).
    """
    if b % 4 != 0:
        raise ValueError('The argument should be divisible by 4')

    base = b / 2  # m*n
    triplets = set()
    for n in xrange(1, base + 1):
        if base % n == 0:
            m = base / n
            if m <= n:
                break
            if (m - n) % 2 != 0 and gcd(m, n) == 1:
                a = m ** 2 - n ** 2
                c = m ** 2 + n ** 2
                triplets.add(tuple(sorted((a, b, c))))
    return triplets


def triplets_in_range(min_range, max_range):
    """ A Pythagorean triplet is a set of three natural numbers, {a, b, c}, for which,
        a**2 + b**2 = c**2
    """
    triplets = set()
    for a in xrange(min_range, max_range + 1):
        for b in xrange(a, max_range + 1):
            c_squared = a**2 + b**2
            if math.sqrt(c_squared).is_integer():
                c = int(math.sqrt(c_squared))
                if c <= max_range:
                    triplets.add(tuple(sorted((a, b, c))))
                else:
                    break
    return triplets


def is_triplet(triplet):
    return tuple(sorted(triplet)) in triplets_in_range(min(triplet), max(triplet))

print 'Searching...'
for triplet in triplets_in_range(1, 1000):
    if sum(triplet) == 1000:
        print 'Found triplet : {0}. a*b*c = {1}.'.format(triplet, reduce(lambda x, y: x * y, triplet))
        break
