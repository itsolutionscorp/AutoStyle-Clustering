def slices(num_string, series_len):
    if series_len > len(num_string) or series_len == 0:
        raise ValueError
    return [[int(num_string[i+y]) for y in range(0, series_len)]
            for i in range(len(num_string) - (series_len - 1))]
