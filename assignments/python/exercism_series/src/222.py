def slices(val, l):
    if not l or l > len(val):
        raise ValueError
    len_val = len(val)
    str_lists = [list(val[i:i + l]) for i in range(len_val) if i + l <= len_val]
    return [map(int, str_list) for str_list in str_lists]
