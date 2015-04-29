def sieve(n):
    # Assume all numbers in this range are prime until marked otherwise.
    primes = list(range(2, n+1))
    
    for prime in primes:
        for num in range(prime*2, n+1, prime):
            # Remove multiples of the prime number.
            if num in primes:
                primes.remove(num)

    return primes
