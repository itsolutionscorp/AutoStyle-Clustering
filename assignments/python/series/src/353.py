def slices(series, test_length):
    if (test_length < 1) or (test_length > len(series)):
        raise ValueError()
    else:
        slice_list = []
        curr = 0
        while (curr + test_length) < (len(series) + 1):
            slice_list.append([int(n) for n in series[curr:curr+test_length]])
            curr += 1
        return slice_list
