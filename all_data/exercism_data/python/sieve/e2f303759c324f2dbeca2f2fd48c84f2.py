def sieve(n):
    primes = range(2, n + 1)
    marked = []
    for p in primes:
        if p in marked:
            continue
        for i in primes:
            if i is p:
                continue
            if not i % p:
                marked.append(i)
                primes.remove(i)
    return primes
