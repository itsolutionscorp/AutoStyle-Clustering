def sieve(limit):
    multiples = []
    primes = []
    for i in range(2, limit+1):
        if i not in multiples:
            primes.append(i)
            for j in range(i*i, limit+1, i):
                multiples.append(j)
    return primes
