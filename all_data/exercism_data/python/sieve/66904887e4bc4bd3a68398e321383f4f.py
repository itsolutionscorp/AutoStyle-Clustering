def sieve(N):
    primes=[]
    i=1
    while i<N:
        i+=1
        if 0==[i%p for p in primes].count(0):
            primes.append(i)
    return primes
