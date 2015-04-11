def sieve(n):
    marked = []
    primes = []
    for i in range(2, n+1):
        if i not in marked:
            primes.append(i)
#            print (i)
            for j in range(i*i, n+1, i):
                marked.append(j)
    return primes

sieve(100)
