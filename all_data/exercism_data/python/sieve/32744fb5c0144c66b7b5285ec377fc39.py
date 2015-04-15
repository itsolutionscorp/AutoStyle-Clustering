def sieve(n):

    primes = [x for x in range(2, n+1)]

    for i in range(2, n+1/2):
        for j in range(2*i, n+1, i):
            try:
                primes.remove(j)
            except ValueError:
                pass

    return primes
