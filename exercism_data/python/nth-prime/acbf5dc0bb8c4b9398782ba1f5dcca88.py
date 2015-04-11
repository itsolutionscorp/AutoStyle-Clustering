def nth_prime(n):
    primes = [2]
    cur = 3
    while len(primes) < n:
        if not any([cur % i == 0 for i in primes]):
            primes.append(cur)
        cur += 2

    return primes[-1]
