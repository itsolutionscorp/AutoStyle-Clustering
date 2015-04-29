def to_int(chunk):
    ''' 
    converts to integers
    ''' 
    integers = []
    for char in chunk:
        integers.append(int(char))
    return integers


def slices(series,slice_len):
    ''' 
    returns list of lists of consecutive slices
    '''
    consecutives = []
    if slice_len > len(series) or slice_len < 1:
        raise ValueError
    else: 
        for xpos in range(0,len(series)-slice_len+1):
            temp_slice = series[xpos:xpos+slice_len]
            consecutives.append(to_int(temp_slice))
        return consecutives
