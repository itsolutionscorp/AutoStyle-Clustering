def slices(series_string, slice_length):
    """Cuts a given string of integers into all possible slices of a given length"""
    #First turn the string into integers
    series_list = list(map(lambda x: int(x), series_string))

    #Then test for errors
    if (slice_length > len(series_list)) or (slice_length <= 0):
        raise ValueError("Slice length is inappropriate for string length")

    #Finally return the slices
    return [series_list[i:i+slice_length]
            for i
            in range(len(series_list)-slice_length+1)]
