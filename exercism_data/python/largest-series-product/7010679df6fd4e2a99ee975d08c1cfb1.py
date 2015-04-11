def generate_slices(series, n):
    if n > len(series):
        raise ValueError("slice {0:d} too big for series of size {1:d}".format(n, len(series)))
    digits = [int(c) for c in series]
    for i in xrange(0, len(digits) - n + 1):
        yield [c for c in digits[i:i+n]]

def slices(series, n):
    return list(generate_slices(series, n))

def largest_product(series, length):
    max_product = 0

    for slice in generate_slices(series, length):
        product = 1
        for digit in slice:
            product *= digit
        if product > max_product:
            max_product = product

    return max_product

