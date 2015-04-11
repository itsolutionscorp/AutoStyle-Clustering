from operator import mul

def slices(series, slice_length):
    if (len(series) < slice_length):
        raise ValueError("length argument does not fit the series")

    slices = []
    for i in range(len(series) - slice_length + 1):
        slices.append([int(series[i + j]) for j in range(slice_length)])
    return slices

def largest_product(series, length):
    if (len(series) < length):
        raise ValueError("series length longer than length argument")

    sliced = slices(series, length)
    maximum = 1
    for portion in sliced:
        current = reduce(mul, portion, 1)
        if current > maximum:
            maximum = current
    return maximum
