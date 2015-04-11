def prime_factors(n):
    for i in xrange(2, int(n**0.5)+1):
        if n % i == 0:
            return [i] + prime_factors(n/i)

    return [n] if n >= 2 else []
