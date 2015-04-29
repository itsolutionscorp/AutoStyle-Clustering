from itertools import count


def nextprime():
    yield 2
    yield 3
    for n in count(start=5, step=2):
        for i in xrange(3, int(n ** 0.5) + 1, 2):
            if n % i == 0:
                break
        else:
            yield n


def prime_factors(n):
    factors = []
    primes = nextprime()
    while n > 1:
        p = next(primes)
        while n % p == 0:
            factors.append(p)
            n /= p
    return factors
