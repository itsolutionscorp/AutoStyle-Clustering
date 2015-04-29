def slices(string, slice_len):
    string_len = len(string)

    # raise error for bad slice_len
    if not slice_len or slice_len > string_len:
        raise ValueError

    # convert string of numbers to list of numbers
    digits = list(map(int, list(string)))

    # return slices of list that are of slice_len length
    return [digits[x:x+slice_len] for x in range(string_len - slice_len + 1)]
