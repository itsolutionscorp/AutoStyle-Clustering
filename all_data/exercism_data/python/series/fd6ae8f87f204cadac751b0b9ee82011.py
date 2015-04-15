def slices(series, slice):
    slice_too_large = slice > len(series)
    slice_too_small = slice < 1

    if slice_too_small or slice_too_large:
        raise ValueError
    else:
        slices = []
        series_list = [int(number) for number in series]
            
        while len(series_list) >= slice:
            slices.append([series_list[number] for number in range(slice)])
            series_list = series_list[1:]
    return slices
