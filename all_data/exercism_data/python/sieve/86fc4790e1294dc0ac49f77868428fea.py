def sieve(n):
    n1 = n+1
    prime = [True] * n1
    sqrt_n = (int(n ** .5) + 1) | 1
    for p in xrange(3, sqrt_n, 2):
        if prime[p]:
            for i in xrange(p * p, n1, 2 * p):
                prime[i] = False
    return [2] + [p for p in xrange(3, n1, 2) if prime[p]]
