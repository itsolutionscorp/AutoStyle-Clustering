def sieve(n):
    s = [True] * (n+1)
    s[0] = s[1] = False 

    for (i, v) in enumerate(s):
        if v:
            s[i] = i
            for z in xrange(i*i, n+1, i):
                s[z] = False
    return filter(lambda x: x, s)
