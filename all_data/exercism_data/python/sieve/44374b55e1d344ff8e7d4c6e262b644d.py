def sieve(limit):
    primes = range(2, limit + 1)
    current_index = 0
    while current_index < len(primes):
        current = primes[current_index]
        for composite in range(current*current, limit+1, current):
            if composite in primes:
                primes.remove(composite)
        current_index += 1
    return primes
