def sieve(n):
    allnbrs = xrange(2, n+1)
    primes = []
    while allnbrs:
        p = allnbrs[0]
        allnbrs = [f for f in allnbrs if f not in range(p,n+1,p)]
        primes.append(p)
    return primes
