def prime_factors(what):
    factors = []
    for x in primes():
        if what < x:
            break
        while what % x == 0:
            factors.append(x)
            what /= x

    return factors


def primes():
    """Sieve of Eratosthenes (as a generator)
    David Eppstein, UC Irvine, 28 Feb 2002
    see: http://code.activestate.com/recipes/117119/"""
    D = {}
    q = 2
    while True:
        if q not in D:
            yield q
            D[q * q] = [q]
        else:
            for p in D[q]:
                D.setdefault(p + q, []).append(p)
            del D[q]
        q += 1
