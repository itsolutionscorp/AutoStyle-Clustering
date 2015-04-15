def sieve(num):
    primes = [2]
    for n in xrange(2,num+1):
        prime = True
        for p in primes:
            if n % p == 0:
                prime = False
                break
        if prime:
            primes.append(n)
    return primes
