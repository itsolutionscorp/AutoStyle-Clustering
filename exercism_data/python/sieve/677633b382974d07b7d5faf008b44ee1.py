def sieve(limit):
    primes = [2]
    pRange = range(3, limit, 2)
    while pRange:
        p = pRange.pop(0)
        pRange = filter(lambda x: x % p, pRange)
        primes.append(p)
    return primes
