def prime_factors(product, start_factor = 2):
    if product <= start_factor:
        return [] if product == 1 else [product]

    for x in xrange(start_factor, product + 1):
        if product % x == 0:
            return [x] + prime_factors(product / x, x)
