from functools import reduce
import operator

def largest_product(series, size):
    if not series: return 1

    multiples = []
    for slice in slices(series, size):
        multiples.append(reduce(operator.mul, slice, 1))

    return max(multiples)


def slices(series, length):
    if length < 1 or length > len(series):
        raise ValueError

    slices = []
    for i in range(len(series) - length + 1):
        slices.append([int(s) for s in series[i: i+length]])

    return slices
