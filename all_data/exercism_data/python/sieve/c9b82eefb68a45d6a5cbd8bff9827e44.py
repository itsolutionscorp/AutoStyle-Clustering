def sieve(n):
    vals = [ True ] * n
    vals[0] = vals[1] = False
    for i, v in enumerate(vals):
        if v:
            for j in xrange(2*i, n, i):
                vals[j] = False
    return [i for i in xrange(n) if vals[i]]
