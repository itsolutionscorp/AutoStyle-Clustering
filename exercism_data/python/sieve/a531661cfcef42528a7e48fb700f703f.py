def sieve(limit):
    sieve_ = [True] * limit
    primes = []

    def mark(n):
        primes.append(n)
        for i in range(n * n, limit, n):
            sieve_[i] = False

    for n in _prime_candidates(limit):
        if sieve_[n]:  # n is prime
            mark(n)

    return primes


def _prime_candidates(limit):
    if limit > 2:
        yield 2
    if limit > 3:
        yield 3
    for n in range(6, limit - 1, 6):
        yield n - 1
        yield n + 1

    rem = limit % 6
    if rem < 2:
        yield limit - rem - 1
