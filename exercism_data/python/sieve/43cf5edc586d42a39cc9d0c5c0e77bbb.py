def sieve(limit):
    """
    Finds all the primes from 2 up to a given limit,
    using the Sieve of Eratosthenes algorithm. """
    primes = []

    s = xrange(2, limit + 1)
    while len(s) != 0:
        primes.append(s[0])
        s = [n for n in s if (n % s[0]) != 0]

    return primes
