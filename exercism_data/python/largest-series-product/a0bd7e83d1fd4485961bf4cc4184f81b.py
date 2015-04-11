from operator import mul

def slices(series, length):
    slicing = []
    digits_holder = series
    if length == 0 or length > len(series):
        raise ValueError
    else:
        while len(digits_holder) >= length:
            slicing.append([int(digits_holder[x]) for x in xrange(length)])
            digits_holder = digits_holder[1:]
    return slicing

def largest_product(series, length):
    largest = 0
    if length == 0 and len(series) == 0:
        return 1
    for sli in slices(series, length):
        prod = reduce(mul, sli)
        if prod > largest:
            largest = prod

    return largest
