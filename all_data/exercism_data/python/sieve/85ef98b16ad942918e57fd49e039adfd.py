def sieve(x):
    primes = range(2,x+1)
    i = 0
    while i<len(primes):
        #get multiples of that val through x
        mults = range(primes[i]*2,x+1,primes[i])
        primes = filter(lambda j:not j in mults, primes)
        i+=1
    return primes
