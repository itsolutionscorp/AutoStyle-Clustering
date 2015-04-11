import operator
import functools


def slices(sequence, size):
    if len(sequence) < size:
        raise ValueError('Requested Slice size is greater than the sequence length')

    slice_start = 0

    result = []
    for x in range(size, len(sequence)+1):
        slice_end = x
        result.append(list(map(int,(sequence[slice_start:slice_end]))))
        slice_start += 1
    return result


def largest_product(sequence, size):
    product = 1
    if len(sequence) == 0:
        return product

    for subsequence in slices(sequence, size):
        product = max(product, functools.reduce(operator.mul,subsequence))
    return product
