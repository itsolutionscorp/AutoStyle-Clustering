def sieve(self):
    primes = list(range(2,self))
    for a in primes:
        for b in primes:
            if (not b == a) and (b % a == 0):
                primes.remove(b)
    return primes
