#!/usr/bin/python
from math import exp


def nth_prime(n):
    """
    Finds nth prime
    """
    if n == 0:
        return 1
    primes = sieve_of_erat(map_to_primes(n))
    return primes[n-1]
    

def sieve_of_erat(maxn):
    """
    Return the Sieve of Eratosthenes for all numbers up to maxn
    """
    possibilities = range(2,maxn+1)
    for i in range(len(possibilities)):
        try:
            number1 = possibilities[i]
        except:
            break   
        for number2 in possibilities[i+1:]:
            if number2 % number1 == 0 and number1 != number2:
                possibilities.remove(number2)
    return possibilities

def map_to_primes(n, f=8, exp_factor=10000.):
    return int(float(f)*n*exp(float(n)/exp_factor))
