#!/usr/bin/python
"""
Tools to list primes.

Contents:
    nth_prime(n):
        -Finds the nth prime
        -ex:
            >>nth_prime(1)
            2
            >>nth_prime(2)
            3
            >>nth_prime(100)
            541
            
    sieve_of_erat(maxn):
        -Lists all primes under maxn using the Sieve of Eratosthenes
        -Slower than the Atkin sieve
        
    sieve_of_atkin(maxn):
        -Lists all primes under maxn using the Sieve of Atkin
        -Much faster than Sieve of Erat
"""



from math import exp
from itertools import permutations

def nth_prime(n):
    """
    Finds nth prime
    """
    if n == 0:
        return 1
    
    # Changed to use Sieve of Atkin, much faster
    # For n=1,000, Atkin took 20 ms, Eratosthenes took 239 ms
    primes = sieve_of_atkin(map_to_primes(n)) 
    return primes[n-1]
    

def sieve_of_erat(maxn):
    """
    Return the Sieve of Eratosthenes for all numbers up to maxn
    
    (not used, but I just kept it here)
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


def sieve_of_atkin(maxn):
    """
    Return all the prime numbers under maxn using sieve of Atkin
    """
    possibilities = {i: False for i in range(5,maxn+1)}
    possibilities[5] = True
    for (x,y) in permutations(range(1,int(maxn**0.5)+1),2):
        n = 4 * x**2 + y **2
        if (n <= maxn) and (n % 12 == 1 or n % 12 == 5):
            possibilities[n] = not possibilities[n]
        n = 3*x**2+y**2
        if (n <= maxn) and (n % 12 == 7):
            possibilities[n] = not possibilities[n]
        n = 3*x**2-y**2
        if (x > y) and (n <= maxn) and (n % 12 == 11):
            possibilities[n] = not possibilities[n]
    results = []
    for n in possibilities:
        if possibilities[n]:
            for i in range(1,(maxn / n**2)+1):
                possibilities[i*n**2] = False
            results.append(n)
    return [2,3] + results


def map_to_primes(n, f=8, exp_factor=10000.):
    return int(float(f)*n*exp(float(n)/exp_factor))
