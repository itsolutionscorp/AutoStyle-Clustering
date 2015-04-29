def nth_prime(nth):
    # The scaling factor 15 is a trade-off between the sieve size and its correctness
    gen = next_prime(nth*15)
    for i in range(nth-1):
        gen.next()
    return gen.next()
    
def next_prime(n):
    allnbrs = [0,0]+[1]*n
    pidx = 2
    while pidx<=n:
        yield pidx
        for i in xrange(pidx,n+1,pidx):
            allnbrs[i] = 0
        pidx = allnbrs.index(1,pidx)
