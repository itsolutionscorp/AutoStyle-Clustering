def slices(num, slice_size):
    if slice_size == 0 or len(num) < slice_size:
        raise ValueError
    else:
        return [map(int, num[i:i+slice_size]) for i in xrange(len(num) + 1-slice_size)]
