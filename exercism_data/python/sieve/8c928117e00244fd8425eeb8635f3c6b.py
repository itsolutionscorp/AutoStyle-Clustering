# -*- coding: utf-8 -*-

def sieve(limit):
    primes = range(2, limit + 1)
    return sieve_rec(primes)

def sieve_rec(primes):
    """
    Recursive solution for Eratosthenes sieve
    """
    if primes:
        prime = primes[0]
        return [prime] + sieve_rec([p for p in primes[1:] if p%prime!=0])
    else:
        return []


