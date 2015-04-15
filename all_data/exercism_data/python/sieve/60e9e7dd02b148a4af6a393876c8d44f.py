def sieve(n):
    values = list(xrange(2, n+1))
    i = 0
    while values and i < len(values):
        values = values[:i+1] + (filter(lambda x: x%values[i], values[i+1:]))
        i += 1
    return values
