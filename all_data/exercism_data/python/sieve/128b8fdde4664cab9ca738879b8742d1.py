def sieve(limit):
    full_series = range(2, limit + 1)
    primes = []
    non_primes = []

    for n in full_series:
        if n not in non_primes:
            primes.append(n)
            for m in range(n*2, limit + 1, n):
                non_primes.append(m)

    return primes
