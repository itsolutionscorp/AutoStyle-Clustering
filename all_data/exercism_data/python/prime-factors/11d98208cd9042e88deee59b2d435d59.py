import math

def prime_factors(n):
    factors = []
    max_factor = n
    possible = []
    if n > 100:
        possible = primes_sieve(n/2)
    else:
        possible = range(2,max_factor + 1)
    for i in possible:
        while n > 1 and n % i == 0:
            factors.append(i)
            n = n / i
    return factors

def eratosthenes(n):
    primes = []
    multiples = []
    high = int(math.sqrt(n))
    print high
    for i in xrange(2, high + 1):
        if i not in multiples:
            primes.append(i)
            for j in xrange(i*i, n+1, i):
                multiples.append(j)
    return primes

def primes_sieve(limit):
    a = [True] * limit                          # Initialize the primality list
    a[0] = a[1] = False

    for (i, isprime) in enumerate(a):
        if isprime:
            yield i
            for n in xrange(i*i, limit, i):     # Mark factors non-prime
                a[n] = False
