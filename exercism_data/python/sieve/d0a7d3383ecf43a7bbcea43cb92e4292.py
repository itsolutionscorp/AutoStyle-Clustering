
def sieve(n):
    primes = {}
    for i in range(2, n+1):
        primes[i] = True

    for i in primes:
        factors = range(i, n+1, i)
        for f in factors[1:]:
            primes[f] = False
    return [i for i in primes if primes[i] == True]

