def slices(string_o_nums, slice_len):
    n = len(string_o_nums)
    if slice_len > n or slice_len == 0:
        raise ValueError("Cant find slices longer than the word.")
    ints = list(map(int, string_o_nums))
    list_o_slices = [ints[i:i + slice_len] for i in range(n - slice_len + 1)]
    return list_o_slices
