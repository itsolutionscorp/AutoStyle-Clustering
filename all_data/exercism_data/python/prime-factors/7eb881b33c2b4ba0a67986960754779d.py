from math import sqrt, ceil
def prime_factors(n):
    primes = sieve(n)
    factors = []
    for p in primes:
        while n % p == 0:
            n //= p
            factors.append(p)

    return factors

def sieve(n):
    if (n < 2):
        return []

    results = list(range(ceil(sqrt(n)) + 1))
    results[1] = 0
    for i in results:
        if (i):
            for j in range(2 * i, n, i):
                results[j] = 0

    return [n for n in results if n]
