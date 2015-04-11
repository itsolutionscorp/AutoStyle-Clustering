def sieve(limit):
    """ Use Sieve of Eratosthenes to return all prime numbers until limit. """
    is_prime = [True] * (limit + 1)
    primes = []
    for n in xrange(2, limit + 1):
        if is_prime[n]:
            primes.append(n)
            for p in xrange(n * n, limit + 1, n):
                is_prime[p] = False
    return primes

