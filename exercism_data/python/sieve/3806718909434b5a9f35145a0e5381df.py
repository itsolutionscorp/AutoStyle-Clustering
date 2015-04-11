#!/usr/bin/python
def sieve(n):
    valid_primes = [2]
    r = range(3, n)
    while r != []:
        r = [x[1] for x in filter(lambda x: x[0] != 0, [(x%valid_primes[-1], x) for x in r])]
        valid_primes, r = valid_primes + r[:1], r[1:]
    return valid_primes
