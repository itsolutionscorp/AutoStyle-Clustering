def sieve(limit):
    a = [True] * limit
    a[0] = a[1] = False
    primes = []
    for i, isprime in enumerate(a):
        if isprime:
            primes.append(i)
            for n in xrange(2*i, limit, i):
                a[n] = False
    return primes
