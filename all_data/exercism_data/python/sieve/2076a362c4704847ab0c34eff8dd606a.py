def sieve(limit):
    r=set(range(2,limit+1))
    for i in xrange(2,limit+1):
        if i in r:
            for j in xrange(2, limit/i+1):
                if i*j in r:
                    r.remove(i*j)
    return sorted(r)
