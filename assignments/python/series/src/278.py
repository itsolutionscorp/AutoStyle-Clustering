def slices(series, slice_length):
    if (len(series) < slice_length):
        raise ValueError("length argument does not fit the series")
    if (slice_length < 1):
        raise ValueError("length argument cannot be less than 1")
    slices = []
    for i in range(len(series) - slice_length + 1):
        slices.append([int(series[i + j]) for j in range(slice_length)])
    return slices
