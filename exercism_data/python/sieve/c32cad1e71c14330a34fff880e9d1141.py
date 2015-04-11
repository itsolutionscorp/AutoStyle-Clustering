def sieve(n):
    # Assume all numbers in this range are prime until marked otherwise.
    primes = list(range(2, n+1))
    
    for prime in primes:
        num = prime
        while num <= n:
            # Remove multiples of the prime number.
            num += prime
            if num in primes:
                primes.remove(num)

    return primes
