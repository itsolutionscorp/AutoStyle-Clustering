def prime_factors(i):
    j, r = 2, []

    while j <= i:
        while i % j == 0:
            r.append(j)
            i /= j
        j += 1
    return r
