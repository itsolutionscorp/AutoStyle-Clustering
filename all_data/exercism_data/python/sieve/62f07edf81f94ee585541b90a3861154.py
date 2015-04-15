def sieve(upper):
    primes = []
    non_primes = []
    for n in xrange(2, upper + 1):
        if n not in non_primes:
            primes.append(n)
            for i in xrange(upper / n + 1):
                non_primes.append(n * i)
    return primes
