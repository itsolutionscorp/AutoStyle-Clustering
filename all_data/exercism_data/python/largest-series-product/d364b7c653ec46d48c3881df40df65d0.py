def slices(st, num):
    array = [int(x) for x in st]

    if num > len(st) or  num < 0:
        raise ValueError('%d is not a valid length for "%s"' % (num, st))

    return [array[x:x+num] for x in range(len(array) + 1 - num)]

def largest_product(st, num):
    return max(product(x) for x in slices(st, num))

def product(array):
    result = 1

    for num in array:
        result *= num

    return result
