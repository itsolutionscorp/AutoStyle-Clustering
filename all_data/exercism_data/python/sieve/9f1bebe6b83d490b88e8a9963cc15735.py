"""More sivey"""

def sieve(max_prime):
    primes = [True] * (max_prime + 1)
    primes[0] = False
    primes[1] = False
    
    for i in range(int(len(primes)**0.5) + 1):
        if primes[i]:
            for j in range(i**2, len(primes), i):
                primes[j] = False

    return [i for i, prime in enumerate(primes) if prime]
