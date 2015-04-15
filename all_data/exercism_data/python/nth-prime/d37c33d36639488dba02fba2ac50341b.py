def nth_prime(num):
    primes = []
    gen_primes = primes_generator()
    for x in xrange(num):
        primes.append(next(gen_primes))
    return primes[-1]


def primes_generator():
    """ Generate an infinite sequence of prime numbers."""

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
