from functools import reduce
from operator import mul

def slices(input, size):
    def slice(input, offset ,size):
        return [int(input[j]) for j in range(offset, offset + size)]
    offsets = range(0, len(input) - size + 1)
    if offsets == range(0):
        raise ValueError
    return [slice(input, offset, size) for offset in offsets]


def largest_product(input, size):
    def product(input):
        return reduce(mul , input, 1)
    return max([product(x) for x in slices(input, size)])
