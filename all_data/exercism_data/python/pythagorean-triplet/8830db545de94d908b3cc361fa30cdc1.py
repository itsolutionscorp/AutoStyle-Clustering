import math


def is_triplet(triplet):
    a, b, c = sorted(triplet)
    return a * a + b * b == c * c


def triplets_in_range(m, n):

    triplets = set()

    for a in range(m, n + 1):
        for b in range(a + 1, n + 1):
            for c in range(b + 1, n + 1):
                if is_triplet((a, b, c)):
                    triplets.add((a, b, c))

    return triplets


def coprime(a, b):
    for i in range(2, max(a, b) // 2):
        if a % i == 0 and b % i == 0:
            return False

    return True

def m_n(b):
    b = b // 2
    for n in range(1, math.ceil(math.sqrt(b))):
        if b % n == 0:
            m = b // n
            if abs(m - n) % 2 == 1 and coprime(m, n):
                yield m, n


def primitive_triplets(b):
    if b % 4 != 0:
        raise ValueError("'%d' is not divisible by 4!" % b)

    triplets = set()

    for m, n in m_n(b):
        triplet = (m * m - n * n, b, m * m + n * n)
        triplets.add(tuple(sorted(triplet)))

    return triplets
