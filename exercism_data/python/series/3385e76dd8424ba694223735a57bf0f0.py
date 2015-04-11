def slices(numbers, slice_size):
    slices = []

    if slice_size > len(numbers):
        raise ValueError('Slice size is larger than string.')
    if slice_size < 1:
        raise ValueError('Slice size is overly short.')

    for i in range(len(numbers)-slice_size+1):
        slices.append(list(numbers[i:slice_size+i]))

    return [list(map(int, slice)) for slice in slices]
