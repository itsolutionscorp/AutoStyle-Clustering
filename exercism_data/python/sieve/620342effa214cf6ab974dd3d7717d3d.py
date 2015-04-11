def sieve(n):
    primes=[]
    candidates=dict([(num,True) for num in xrange(2,n+1)])
    for m in range(2,n+1):
        if candidates[m]:
            primes.append(m)
            for j in range(2,(n/m)+1):
                candidates[j*m]=False
    return primes
