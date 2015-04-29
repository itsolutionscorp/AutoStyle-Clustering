
from itertools import count, islice

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

def nth_prime(n):
    return next(islice(prime_gen(), n-1, None))
