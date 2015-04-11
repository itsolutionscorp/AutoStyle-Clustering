def slices(series, length):
    if length > len(series) or length < 1:
        raise ValueError

    slices = []
    series_array = [int(x) for x in series]

    for x in xrange((len(series) - length) + 1):
        slices.append(series_array[x:x + length])

    return slices
