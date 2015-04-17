def slices(s, n):
    if n > len(s) or n < 1:
        raise ValueError
    x = map(int, list(s))
    return [x[i:i+n] for i in xrange(len(x)-n+1)]
