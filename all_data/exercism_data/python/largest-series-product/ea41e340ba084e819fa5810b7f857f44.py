def largest_product(sequence, slice_length):
    if slice_length == 0:
        return 1

    largest = 0

    for chunk in slices(sequence, slice_length):
        product = reduce(lambda x, y: x * y, chunk)
        if product > largest:
            largest = product

    return largest

def slices(sequence, slice_length):
    if slice_length > len(sequence):
        raise ValueError

    return [[int(item) for item in sequence[i:i + slice_length]] for i in xrange(len(sequence) - slice_length + 1)]
