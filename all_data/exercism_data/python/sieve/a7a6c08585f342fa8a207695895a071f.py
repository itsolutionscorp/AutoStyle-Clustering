def sieve(n):
    primes = []
    numbers = range(2,n)
    while numbers:
        prime = numbers.pop(0)
        for i in range(prime,n,prime):
            try:
                numbers.remove(i)
            except ValueError:
                #already removed non-prime
                pass
        primes.append(prime)
    return primes
