
from itertools import count

def prime_gen():
    yield 2
    primes = [2]

    for d in count(start=3, step=2):
        for p in primes:
            if d%p == 0:
                break
            elif p*p > d:
                yield d
                primes.append(d)
                break

def prime_factors(n):
    factors = []
    primes = prime_gen()
    while n > 1:
        p = next(primes)
        while n%p == 0:
            n //= p
            factors.append(p)
    return factors
