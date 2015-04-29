def sieve(n):
    primes = range(2, n+1)
    
    index = 0
    while index<len(primes):
        prime = primes[index]
        for multiple in range(prime*2, n+1, prime):
            if primes.count(multiple):
                primes.pop(primes.index(multiple))
        index += 1
    return primes
