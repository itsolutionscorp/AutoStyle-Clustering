__author__ = 'emiller42'


def _gcd(x, y):
    while y != 0:
        x, y = y, x % y
    return x


def _find_factor_pairs(m_n):
    pairs = []
    for n in range(1, int(m_n**0.5)+1):
        if m_n % n == 0:
            m = m_n / n
            if m > n:
                pairs.append((m, n))
    return pairs


def primitive_triplets(num):
    if num % 4:
        raise ValueError
    triples = set()
    m_n = num / 2
    for m, n in _find_factor_pairs(m_n):
        if _gcd(m, n) == 1:
            triples.add(tuple(sorted((m**2 - n**2, 2*m*n, m**2 + n**2))))
    return triples


def triplets_in_range(min_val, max_val):
    triples = set()
    for i in range(1, max_val+1):
        if i % 4 == 0:
            for a, b, c in primitive_triplets(i):
                k = 1
                while c*k <= max_val:
                    if all(min_val <= val*k <= max_val for val in (a, b, c)):
                        triples.add(tuple(sorted((a*k, b*k, c*k))))
                    k += 1

    return triples


def is_triplet(triplet):
    a, b, c = sorted(triplet)
    return a**2 + b**2 == c**2
