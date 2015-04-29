from math import log, ceil

def sieve(n):
    vals = [ True ] * n
    vals[0] = vals[1] = False
    for i, v in enumerate(vals):
        if v:
            for j in xrange(2*i, n, i):
                vals[j] = False
    return [i for i in xrange(n) if vals[i]]

def nth_prime(n):
    if n > 39017:
        upper_bound = n * (log(n) + log(log(n)) - 0.9484)
    elif n > 55:
        upper_bound = n * (log(n) + log(log(n)) - 1/2)
    elif n > 1:
        upper_bound = n * (log(n) + log(log(n)) + 8)
    else:
        return 2
    upper_bound = int(ceil(upper_bound))
    return sieve(upper_bound)[n-1]
