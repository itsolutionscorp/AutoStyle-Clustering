def slices(series, slice_len):
    series_digits = [int(c) for c in series]
    if not (0 < slice_len < len(series) + 1):
        raise ValueError("slice {0:d} invalid for series of size {1:d}".format(slice_len, len(series)))
    return [
        series_digits[i: i + slice_len]
        for i in xrange(0, len(series) - slice_len + 1)
    ]
