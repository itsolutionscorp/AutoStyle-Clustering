def slices(series, slice):
    if slice > len(series) or slice == 0:
        raise ValueError()
    slice_list = []
    series_list = [int(c) for c in series]
    for n, item in enumerate(series):
        if n + slice <= len(series):
            slice_list.append(series_list[n:n+slice])
    return slice_list
