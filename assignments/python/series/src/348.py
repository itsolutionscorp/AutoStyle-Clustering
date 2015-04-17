def slices(series, length):
    if length > len(series) or length <= 0:
        raise ValueError
    ranges = zip(xrange(0, len(series)+1), xrange(length, len(series)+1))
    sliced_ranges = [slice(x, y) for x, y in ranges]
    sliced_results = map(lambda x: series[x], sliced_ranges)
    split_strings = [list(sliced) for sliced in sliced_results]
    sliced_results = [map(int, sliced) for sliced in split_strings]
    return sliced_results
