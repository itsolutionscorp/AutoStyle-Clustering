def sieve(max):
    """ Use Sieve of Eratosthenes to return all prime numbers until max. """
    is_prime = [True] * (max + 1)
    primes = []
    for n in xrange(2, max + 1):
        if is_prime[n]:
            primes.append(n)
            for p in xrange(n * n, max + 1, n):
                is_prime[p] = False
    return primes
