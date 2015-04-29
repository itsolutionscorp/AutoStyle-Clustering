from math import sqrt, floor, log as ln

def nth_prime(n):
    """Finds the nth prime. Effective up to the 100000th prime."""

    #Eliminate trivial cases
    if n < 6:
        return [2, 3, 5, 7, 11][n-1]

    #Now, find the upper bound of the nth prime
    bound = floor(n*(ln(n)+ln(ln(n))))

    #Next, sieve to the given bound
    primes = sieve(bound)

    #Finally, return the nth prime
    return primes[n-1]

def sieve(prime_range):
    """Runs an optimized Sieve of Eratosthenes
    to find all the primes from 2 up to a given number"""
    if (prime_range%2 == 0):
        prime_range -= 1
    retlist = list(range(3,prime_range+1,2))
    maxval = sqrt(prime_range)
    for i in retlist:
        if i > maxval:
            break
        retlist = list(filter(lambda x: x==i or x%i!=0, retlist))
    return [2]+retlist
