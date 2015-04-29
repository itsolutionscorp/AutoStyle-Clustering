def slices(digits, slice_size):
    if slice_size < 1 or slice_size > len(digits):
        raise ValueError('slice_size must be in the range [1, len(digits)]')
    num_slices = len(digits) - slice_size + 1
    slices = []
    for i in range(num_slices):
        s = [int(d) for d in digits[i:i+slice_size]]
        slices.append(s)
    return slices
