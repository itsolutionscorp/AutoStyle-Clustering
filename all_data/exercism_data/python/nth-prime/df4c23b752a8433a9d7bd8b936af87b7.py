def nth_prime(n):
    i, primes = 3, [2]
    while len(primes) < n:
        if all(i % p for p in primes):
            primes.append(i)
        i += 2

    return primes[-1]
