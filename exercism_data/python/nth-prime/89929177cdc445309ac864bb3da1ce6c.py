from itertools import count
import numpy as np


def sieve():
    N = 1e7
    is_prime = np.ones(N, dtype=np.bool)
    is_prime[:2] = False
    is_prime[4::2] = False
    Nm = int(np.sqrt(N))
    for i in xrange(3, Nm, 2):
        is_prime[i * i::i] = False
    return np.nonzero(is_prime)[0]

PRIMES = sieve()


def nth_prime(n):
    n -= 1
    try:
        return PRIMES[n]
    except IndexError:
        return _prime_at_index(n)


def _prime_at_index(n):
    current = PRIMES[-1] + 2
    idx = len(PRIMES) - 1
    for p in count(start=current, step=2):
        for i in range(3, int(np.sqrt(p)) + 1, 2):
            if p % i == 0:
                break
        else:
            current = p
            idx += 1
            if n == idx:
                return p
