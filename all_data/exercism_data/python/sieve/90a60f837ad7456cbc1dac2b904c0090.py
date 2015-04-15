def sieve(limit):
    """
    Finds all the primes from 2 up to a given limit,
    using the Sieve of Eratosthenes algorithm. """
    primes = []

    s = [True]*(limit+1)
    s[0] = s[1] = False
    for item, is_prime in enumerate(s):
        if is_prime:
            primes.append(item)
            for n in xrange(item, limit+1, item):
                s[n] = False
    return primes
