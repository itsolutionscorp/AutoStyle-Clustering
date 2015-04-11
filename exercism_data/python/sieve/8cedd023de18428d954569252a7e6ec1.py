def sieve(n):
    allnbrs = [0,0]+[1]*n
    primes = []
    pidx = allnbrs.index(1)
    while pidx <= n:
        primes.append(pidx)
        for i in range(pidx,n+1,pidx):
            allnbrs[i] = 0
        pidx = allnbrs.index(1, pidx)
    return primes
