def slices(num_string, slice_length):
    '''
    Given a string with numeric-only content, and a slice length, return
    all the possible consecutive number series of length `n` in that string.
    '''
    if num_string == '':
        raise ValueError('Numeric string cannot be empty.')
    if slice_length > len(num_string):
        raise ValueError('Slice length too long.')
    if slice_length == 0:
        raise ValueError('Slice length cannot be zero.')
    slices_list = []
    for slice in range(len(num_string)-slice_length+1):
        current_slice = num_string[slice:slice_length+slice]
        slices_list.append([int(number) for number in current_slice])
    return slices_list
