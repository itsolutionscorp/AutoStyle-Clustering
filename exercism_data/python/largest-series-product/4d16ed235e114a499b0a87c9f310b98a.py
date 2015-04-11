def slices(s, n):
    if n > len(s) or n < 1:
        raise ValueError
    x = map(int, list(s))
    return [x[i:i+n] for i in xrange(len(x)-n+1)]

def largest_product(s, n):
    if s == '' and n == 0:
        return 1
        
    z = slices(s, n)
    f = lambda x, y: x*y
    for (i, v) in enumerate(z):
        z[i] = reduce(f, v, 1)
    return max(z)
