def genPrimes():
    p = [True for i in xrange(1000000)]
    p[0] = p[1] = False

    for (i, prime) in enumerate(p):
        if prime:
            yield i
            for j in xrange(i*i, 1000000, i):
                p[j] = False

def prime_factors(n):
    primes = genPrimes()
    f = []
    for i in primes:
        if i > n:
            return f
   
        while n % i == 0:
            n /= i
            f.append(i)
