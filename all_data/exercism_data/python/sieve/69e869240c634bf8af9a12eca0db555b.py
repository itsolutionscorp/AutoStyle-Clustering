def sieve(num):
    primes = [True for _ in range(num + 1)]

    for i in range(2, num+1):
        if primes[i]:
            for j in range(i**2, num + 1, i):
                primes[j] = False

    return [i for i,j in enumerate(primes) if j and i > 1]
