def slices(string_o_nums, slice_len):
    n = len(string_o_nums)
    if slice_len > n or slice_len == 0:
        raise ValueError("Cant find slices longer than the word.")
    list_o_slices = []
    for i in range(n - slice_len + 1):
        single_slice = [int(x) for x in string_o_nums[i: i + slice_len]]
        list_o_slices.append(single_slice)
    return list_o_slices
