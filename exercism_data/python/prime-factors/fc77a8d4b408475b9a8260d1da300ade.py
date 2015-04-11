def prime_factors(n, factors=None):
    if factors is None:
        factors = []
    if n < 2:
        return(factors)
    try:
        start = max(factors)
    except:
        start = 2
    for f in range(start, n+1):
        if not n % f:
            factors.append(f)
            return(prime_factors(int(n/f), factors))
            break
