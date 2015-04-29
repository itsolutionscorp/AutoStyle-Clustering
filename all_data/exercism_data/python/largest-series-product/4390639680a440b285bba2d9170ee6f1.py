import operator

def largest_product(number_string, length):
    if number_string == "" or length == 0:
        return 1
    maxProd = 1
    for slice in slices(number_string, length):
        maxProd = max(maxProd, reduce(operator.mul, slice))
    return maxProd

def slices(number_string, length):
    if length == 0 or length > len(number_string):
        raise ValueError
    res = []
    for i in xrange(0, len(number_string) - length + 1):
        res.append([int(x) for x in number_string[i:i + length]])
    return res

