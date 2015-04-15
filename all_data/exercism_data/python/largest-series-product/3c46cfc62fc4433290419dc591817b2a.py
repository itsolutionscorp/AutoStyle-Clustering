def islices(series, n):
    if n > len(series):
        raise ValueError("slice {0:d} too big for series of size {1:d}".format(n, len(series)))
    digits = [int(c) for c in series]
    for i in xrange(0, len(digits) - n + 1):
        yield digits[i:i+n]

def slices(series, n):
    return list(islices(series, n))

def iproducts(series, n):
    for slice in slices(series, n):
        yield reduce(lambda x, y: x*y, slice, 1)

def largest_product(series, length):
    return max(iproducts(series, length))
