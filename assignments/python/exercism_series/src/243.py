def slices(in_string, size):
    if size < 1 or size > len(in_string):
        raise ValueError('Slice size must be a positive integer less than or equal to the string length')
    slices_list = []
    for slice_start in range(len(in_string) - size + 1):
        this_slice = list(in_string[slice_start: slice_start + size])
        slices_list.append([int(x) for x in this_slice])
    return slices_list
