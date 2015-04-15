def sieve(limit):
    primes = range(2, limit + 1)
    current_index = 0
    while current_index < len(primes):
        current = primes[current_index]
        for factor in range(2, (limit/current)+1):
            if current * factor in primes:
                primes.remove(current * factor)
        current_index += 1
    return primes
