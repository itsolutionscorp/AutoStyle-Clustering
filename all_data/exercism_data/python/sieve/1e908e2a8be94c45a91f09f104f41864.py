import numpy as np

def sieve(n):
    
    primes = []

    unmarked = np.arange(2, n+1)
    
    while len(unmarked) > 0:
        primes.append(unmarked[0])
        unmarked = get_unmarked(unmarked)

    return primes

def get_unmarked(unmarked):
    
    return unmarked[unmarked % unmarked[0] > 0]
