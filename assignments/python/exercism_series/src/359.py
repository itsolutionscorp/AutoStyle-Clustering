def slices(digits, num_slices):
    if num_slices > len(digits) or not num_slices:
        raise ValueError
    return [map(int, list(digits[i:i+num_slices])) for i in xrange(len(digits) - num_slices + 1)]
