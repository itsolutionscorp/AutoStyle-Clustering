from itertools import count

def prime_factors(n):
    factors = []
    for p in _mainly_primes():
        while n % p == 0:
            factors.append(p)
            n //= p
        if n == 1:
            return factors

def _mainly_primes():
    yield 2
    yield 3
    for n in count(6, 6):
        yield n - 1
        yield n + 1
