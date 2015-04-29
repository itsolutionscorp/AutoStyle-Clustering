def slices(series, series_len):
    result = []
    index = 0
    if series_len > len(series) or series_len < 1:
        raise (ValueError)
    while (index + series_len) <= len(series):
        nlist = []
        for i in range(index, index+series_len):
            nlist.append(int(series[i]))
        index += 1
        result.append(nlist)
    return result
