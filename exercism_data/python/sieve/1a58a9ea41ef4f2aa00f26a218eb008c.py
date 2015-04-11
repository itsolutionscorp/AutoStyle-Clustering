def sieve(bound):
    primes = []
    candidates = [ x >= 2 for x in range(bound + 1) ]

    for i in range(2, bound + 1):
        if not candidates[i]: continue

        candidates[i::i] = [False] * (bound/i)
        primes.append(i)

    return primes
