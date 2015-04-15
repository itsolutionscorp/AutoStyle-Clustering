__author__ = 'emiller42'


def _gcd(x, y):
    while y != 0:
        x, y = y, x % y
    return x


def _find_factor_pairs(m_n):
    pairs = []
    for m in range(1, m_n+1):
        if m_n % m == 0:
            n = m_n / m
            if m > n and m - n % 2:
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


def triplets_in_range(min, max):
    triples = set()
    for i in range(1, max+1):
        if i < 4:
            pass
        else:
            if i % 2:
                multiplied = 2
                m_n = i
            else:
                multiplied = 1
                m_n = i / 2
            for m, n in _find_factor_pairs(m_n):
                x, y, z = (m**2 - n**2)/multiplied, 2*m*n/multiplied, (m**2 + n**2)/multiplied
                test = (min <= x <= max, min <= y <= max, min <= z <= max)
                if all(test):
                    triples.add(tuple(sorted((x, y, z))))
    return triples


def is_triplet(triplet):
    triplet = sorted(triplet)
    return triplet[0]**2 + triplet[1]**2 == triplet[2]**2
