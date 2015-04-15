from functools import reduce
from operator import mul

def largest_product(digits, size):
    slices = _shift(digits, size)
    products = map(lambda s: reduce(mul, s, 1), slices)
    return max(products)

def slices(digits, size):
    return list(_shift(digits, size))

def _shift(seq, size):
    if size > len(seq):
        raise ValueError("Slice size is bigger than the sequence length.")
    for i in range(len(seq) - size + 1):
        yield [int(seq[i + j]) for j in range(size)]
