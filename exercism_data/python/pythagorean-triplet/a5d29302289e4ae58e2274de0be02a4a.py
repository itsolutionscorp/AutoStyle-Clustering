from fractions import gcd

def primitive_triplets(b):
    result = set()
    n = 1

    if b % 2:
        raise ValueError('%d is an odd number.' % (b))

    while n * n * 2 < b:
        m = n + 1
        while b > 2 * m * n:
            m += 2
        if b == 2 * m * n:
            a = m**2 - n**2
            c = m**2 + n**2
            if gcd(a, b) == 1:
                result.add(tuple(sorted([a, b, c])))
        n += 1
    return result

def triplets_in_range(lo, hi):
    result = set()

    c = lo + 1
    while c <= hi:
        for a in xrange(lo, c):
            for b in xrange(a + 1, c):
                if is_triplet([a, b, c]):
                    result.add(tuple(sorted([a, b, c])))
        c += 1
    return result

def is_triplet(t):
    a, b, c = [x*x for x in sorted(t)]
    return a + b == c
