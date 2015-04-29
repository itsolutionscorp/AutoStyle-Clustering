from math import sqrt

def sieve(n):
    sqrt_n = int(sqrt(n))
    
    # all entries in the sieve are marked as prime initially
    sieve_size = n+1
    primes = [True] * sieve_size
    primes[0] = False # 0 is not prime
    primes[1] = False # 1 is not prime
    
    # 2 is the first prime
    p = 2
    while p <= sqrt_n:
        # when p is prime, its multiples are NOT prime
        if primes[p]:
            # start marking at p*p since when p is prime 
            # everything below p*p has already been marked
            for i in range(p*p, sieve_size, p):
                primes[i] = False
        p += (1 if p == 2 else 2)
            
    return [i for i, prime in enumerate(primes) if prime]
