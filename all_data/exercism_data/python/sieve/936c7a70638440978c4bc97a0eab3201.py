#!/usr/bin/env python

def sieve(n):
    numbers = range(2, n+1)
    primes = numbers[:]
    for i in numbers:
        limit = (n/i) + 1
        for j in range(i, limit):
            non_prime = i * j
            if non_prime in primes:
                primes.remove(non_prime)
    return primes
