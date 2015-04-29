def slices(series, slice):
    if slice > len(series) or slice == 0:
        raise ValueError()
    slice_list = []
    series_list = [int(c) for c in series]
    for n, item in enumerate(series):
        if n + slice <= len(series):
            slice_list.append(series_list[n:n+slice])
    return slice_list


def largest_product(series, slice):
    largest = 1
    if not series:
        return largest
    for slice in slices(series, slice):
        product = 1
        for i in slice:
            product *= i
        if product > largest:
            largest = product
    return largest
