def sieve(n):
    primes = [i for i in xrange(2, n+1)]
    current = 0
    while current < len(primes):
        print primes
        primes = [i for i in primes if (i % primes[current] or i == primes[current])]
        current += 1
    return primes
