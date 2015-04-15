#!/usr/bin/python
from math import sqrt
def sieve(n):
    A = dict(zip(range(2, n+1), [True]*n))
    for i in A.keys()[:int(sqrt(n))-1]:
        if A[i]:
            for j in A.keys()[(i**2)-2::i]:
                A[j] = False
    return [a[0] for a in A.items() if a[1]]

def prime_factors(n):
    sqrt_n = int(sqrt(n))
    primes = sieve(sqrt_n)
    primes = [prime for (mod, prime) in [(n % prime, prime) for prime in primes] if mod == 0]
    counter = n
    prime_factors = []
    for prime in primes:
        quotient, remainder = counter / prime, counter % prime
        while remainder == 0:
            prime_factors.append(prime)
            counter = quotient
            quotient, remainder = counter / prime, counter % prime
    if counter != 1:
        primes.append(counter)
        prime_factors.append(counter)
    return prime_factors
