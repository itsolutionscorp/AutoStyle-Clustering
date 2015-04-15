def sieve(maxnum):
    marked = []
    primes = []
    for int in xrange(2, maxnum):
        if int not in marked:
            primes.append(int)
            for i in xrange(maxnum):
                marked.append(int * i)

    return primes
