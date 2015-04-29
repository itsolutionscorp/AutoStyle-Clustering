import math

def sieve(n):
    bound = int(math.sqrt(n)) + 1
    is_primes = [True]*(n+1)
    for i in range(2,bound):
        if is_primes[i]:
            for j in range(i**2, n+1, i):
                is_primes[j] = False
    return [i for i in range(2,n+1) if is_primes[i]]
