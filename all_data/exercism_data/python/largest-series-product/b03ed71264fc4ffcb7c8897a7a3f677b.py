from toolz import sliding_window
from toolz.curried import reduce

prod = reduce(lambda a, b: a*b)


def slices(input_string, n):
    if n > len(input_string):
        raise ValueError

    input_ = [int(c) for c in input_string]

    return [list(window) for window in sliding_window(n, input_)]


def largest_product(input_string, n):
    if n == 0:
        return 1

    return max(prod(slice) for slice in slices(input_string, n))
