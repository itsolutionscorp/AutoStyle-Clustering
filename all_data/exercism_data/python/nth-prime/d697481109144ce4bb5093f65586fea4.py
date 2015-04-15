def nth_prime(n):
    primes = []
    current = 2
    while len(primes) < n:
        if not is_divisible(current, primes):
            primes.append(current)
        current += 1
    return primes[-1]


def is_divisible(current, primes):
    return any(current % p == 0 for p in primes)
