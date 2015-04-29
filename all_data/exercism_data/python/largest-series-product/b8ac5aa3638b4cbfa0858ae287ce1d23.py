def slices(digits, n):
    length = len(digits)
    if not 0 < n <= length:
        raise ValueError('Invalid length {}'.format(n))
    digits = [int(d) for d in digits[0:n]]
    return digits
    # return [digits[i:i+n] for i in range(length-n+1)]


def largest_product(string, n):
    sorted_string = sorted(string, reverse=True)
    sliced_string = slices(sorted_string, n)
    print sliced_string
    product = 1
    for x in sliced_string:
        product *= x 
    return product
