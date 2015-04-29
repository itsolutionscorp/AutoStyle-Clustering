def sieve(n):
    primes = []
    composite = []
    for i in range(2, n+1):
        if i in composite:
            continue
        primes.append(i)
        for j in range(i, n+1, i):
            composite.append(j)
    return primes
