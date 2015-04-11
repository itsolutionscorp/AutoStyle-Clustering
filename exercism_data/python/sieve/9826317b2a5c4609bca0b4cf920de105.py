def sieve(n):
    l = dict((key, False) for key in range(2, n+1))

    for i in range(2, n+1):
        for j in range(2, n//2):
            l[i*j] = True

    return [k for k,v in l.items() if v is False]
