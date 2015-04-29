def sieve(n):
    is_prime = [True] * (n + 1)
    is_prime[0:1] = [False, False]
    primes = []
    for x in range(2, n + 1):
        if is_prime[x]:
            primes.append(x)
            for y in range(2 * x, n + 1, x):
                is_prime[y] = False
    return primes
