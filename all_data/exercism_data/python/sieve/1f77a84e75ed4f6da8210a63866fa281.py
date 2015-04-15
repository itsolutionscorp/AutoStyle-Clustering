def primeGenerator(maxPrime):
    isPrime = [True] * maxPrime
    isPrime[0] = isPrime[1] = False
    for (i, prime) in enumerate(isPrime):
        if prime:
            yield i
            for multiple in range(i**2, maxPrime, i):
                isPrime[multiple] = False

def sieve(n):
    return list(primeGenerator(n))
