def largest_product(series, length):
    if series:
        slices_list = slices(series, length)
        return max(get_product(x) for x in slices_list)
    return 1

def slices(series, length):
    if len(series) < length:
        raise ValueError
    else:
        return [listify(series[i:i+length]) for i in range(0, len(series) - (length-1))]

def get_product(slice):
    product = 1
    for x in slice:
        product = product * x
    return product

def listify(string):
    return [int(x) for x in string]
