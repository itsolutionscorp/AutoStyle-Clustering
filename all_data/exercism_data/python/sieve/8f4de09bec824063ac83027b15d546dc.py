# Sieve Python exercism, 2nd iteration
from math import sqrt

def sieve(n):
    ran = range(2,n+1)
    prime = 2
    i = 0
    while i <= sqrt(n):
        if ran[i] != 0: # If ran[i] is prime we set multiples of ran[i] to zero
            ran = [0 if number > ran[i] and number % ran[i] == 0 else number for number in ran]        
        i += 1
        
    primes = [number for number in ran if number != 0]
    return primes
