def slices(digits, n):
    if len(digits) < n:
        raise ValueError
    elif n is 0:
        return []

    result = []
    digits = map(int, digits)
    range = xrange(0, len(digits) - n + 1)
    for i in range:
        result.append(digits[i:i+n])
    return result

def largest_product(digits, n):
    the_slices = slices(digits, n)
    if not the_slices:
        return 1

    product = 0
    for a_slice in the_slices:
        product_temp = reduce(lambda x, y: x * y, a_slice)
        if product < product_temp:
            product = product_temp

    return product
