from operator import mul

def slices(number, length):
    result = []
    if len(number) < length or length == 0:
        raise ValueError("Invalid slice")

    for start in range(0, len(number) - length + 1):
        result.append([int(s) for s in list(number[start:start+length])])
    return result

def largest_product(number, length):
    if length == 0:
        return 1
    max = 0
    sliceList = slices(number, length)
    for sl in sliceList:
        product = reduce(mul, sl, 1)
        if(product > max):
            max = product
    return max
