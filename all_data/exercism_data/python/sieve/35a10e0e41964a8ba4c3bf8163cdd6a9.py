def sieve(target):
    primes = []
    non_primes = set()

    current = 2
    while current <= target:
        primes.append(current)

        multiplier = 1
        while multiplier * current <= target:
            non_primes.add(current * multiplier)
            multiplier += 1

        while current in non_primes:
            current += 1

    return primes
