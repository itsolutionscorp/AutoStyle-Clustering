from operator import mul


def largest_product(s, n):
    slizes = slices(s, n)

    max_product = 0
    for slize in slizes:
        product = reduce(mul, slize, 1)
        max_product = max(max_product, product)

    return max_product


def slices(s, n):
    slen = len(s)

    if slen < n:
        raise ValueError

    return [map(int, s[start:(start + n)])
            for start in range(len(s) - n + 1)]
