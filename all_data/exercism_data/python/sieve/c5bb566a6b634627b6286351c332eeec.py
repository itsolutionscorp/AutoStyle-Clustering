from math import sqrt

def sieve(prime_range):
    """Runs the Sieve of Sieve of Eratosthenes to find all the primes from 2 up to a given number"""
    if (prime_range%2 == 0):
        prime_range -= 1
    retlist = range(3,prime_range+1,2)
    maxval = sqrt(prime_range)
    for i in retlist:
        if i > maxval:
            break
        retlist = [x for x in retlist if x == i or x % i != 0]
    return [2]+retlist
