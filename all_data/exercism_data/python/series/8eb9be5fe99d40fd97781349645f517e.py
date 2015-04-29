def slices(num_string, arr_size):
    if len(num_string) < arr_size or arr_size == 0:
        raise ValueError
    segments = []
    for seg_num in range(len(num_string) - arr_size + 1):
        segments.append([int(num_string[i + seg_num]) for i in range(arr_size)])
    return segments
