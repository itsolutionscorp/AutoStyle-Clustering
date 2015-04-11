__author__ = 'Hinek'

def sieve(max):
    primes = []
    candidates = range(2, max + 1)
    for n in candidates:
        primes.append(n)
        i = 2
        while i * n <= max:
            if i * n in candidates:
                candidates.remove(i * n)
            i += 1
    return primes
