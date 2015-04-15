def slices(series, slices):
    '''Slices function
    params:
        series (string of ints): string to be sliced
        slices (int): how large the slices are to be
    returns:
        return_list (list of list containing ints)
    '''
    # Raise value error if requested slices is not suitable
    if slices < 1 or slices > len(series):
        raise ValueError
    # Set default values
    return_list = []
    gather_list = []
    slice_index = 0
    loop_index = 0
    while loop_index < len(series):
        slice_index += 1
        gather_list.append(int(series[loop_index]))
        if slice_index == slices:
            return_list.append(gather_list)
            slice_index = 0
            gather_list = []
            loop_index -= slices - 1
        loop_index += 1
    return return_list
