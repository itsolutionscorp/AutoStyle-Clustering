def prime_factors(N):
    for i in xrange(2, N+1):
        if N % i == 0:
            return [i] + prime_factors(N / i)
    return []
