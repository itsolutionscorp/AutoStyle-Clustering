__author__ = 'DIA'


def largest_product(sequence, n):
    if len(sequence) == 0 and n == 0:
        return 1

    _slices = slices(sequence, n)
    products = []

    ## Couldn't do this with List comprehension - Could someone suggest.? Thanks in advance :)
    for _slice in _slices:
        product = 1
        for num in _slice:
            product *= num
        products.append(product)
    return max(products)


def slices(sequence, n):
    if n > len(sequence):
        raise ValueError('Invalid arguments by length')

    partitions = [sequence[i:i + n] for i in range(len(sequence) - n + 1)]
    return [[int(x) for x in partition] for partition in partitions]
