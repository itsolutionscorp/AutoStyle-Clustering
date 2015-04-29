def sieve(n):
    a = []
    for i in xrange(2, n+1):
        for x in xrange(2, n+1):
            if i/x !=0:
                a.append(i)
    return a
