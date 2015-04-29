#!/usr/bin/env python3

import math

def is_prime(m):
    for i in range(2, int(math.sqrt(m) + 1)):
        if not m % i:
            return False
    return True

def sieve(n):
    primes = []
    for i in range(2, n + 1):
        if is_prime(i):
            primes.append(i)
    return primes
