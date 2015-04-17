def slices(string, slice_len):
    string_len = len(string)
    if not slice_len or slice_len > string_len:
        raise ValueError
    digits = list(map(int, list(string)))
    return [digits[x:x+slice_len] for x in range(string_len - slice_len + 1)]
