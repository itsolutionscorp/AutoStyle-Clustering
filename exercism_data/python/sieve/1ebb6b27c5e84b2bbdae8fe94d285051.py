def sieve(n):
    primes = range(2, n + 1)
    current_index = 0
    
    while current_index < len(primes):
        primes = [item for item in primes if (item % primes[current_index] or item == primes[current_index])]
        current_index += 1

    return primes
