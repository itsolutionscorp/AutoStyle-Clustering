def sieve(limit):
    non_primes = set()
    primes = []
    for n in xrange(2, limit):
        if n in non_primes:
            continue
        primes.append(n)
        non_primes.update(xrange(n, limit, n))
    return primes
